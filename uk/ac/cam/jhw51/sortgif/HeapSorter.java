package uk.ac.cam.jhw51.sortgif;

import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HeapSorter extends Sorter {

	int a[];
	
	Color std_bg = new Color(232,232,232);
	Color std_fg = new Color(0,0,0);
	Color unsel_bg = new Color(255,255,255);
	Color unsel_fg = new Color(209,209,209);
	Color parent_fg = new Color(242,48,48);
	Color child_fg = new Color(245,154,154);
	Color swap_fg = new Color(2,237,225);
	
	Color k_vl = new Color(143,232,0);
	
	boolean secondstage = false;
	int k;
	
	public HeapSorter(int[] is, FrameBuilder fb) {
		this.a = is;
		this.mFB = fb;
	}

	@Override
	public void sort() throws IOException {
		hs();
		this.endGif();
	}

	public void hs() throws IOException {
		this.makeFrame(makeData());
		for (int j = (int) Math.floor(a.length/2 - 1); j >= 0; j--) {
			heapify(a.length, j);
		}
		secondstage = true;
		for (k = a.length; k > 1; k--) {
			this.makeFrame(this.makeData_swap(0,k-1));
			int tmp = a[0]; a[0] = a[k-1]; a[k-1] = tmp;
			this.makeFrame(this.makeData_swap(0,k-1));
			heapify(k-1,0);
		}
		secondstage = false;
		this.makeFrame(makeData());
	}

	public void heapify(int iEnd, int iRoot) throws IOException {
		this.makeFrame(makeData_heapify(iRoot,iEnd));
		if (isRoot(iRoot, iEnd)) {
			return;
		} else { //a[iRoot] does not form a max-heap and has at least one child node
			int j;
			if (2*iRoot + 2 >= iEnd) { //a[iRoot] as one child node and it's greater than its parent
				j = 2*iRoot + 1;
			} else { //a[iRoot] has two children nodes and at least one of them is greater than their parent
				j = (a[2*iRoot + 1]>=a[2*iRoot+2]?2*iRoot+1:2*iRoot+2);
			}
			this.makeFrame(makeData_heapify(iRoot,j,iEnd));
			int tmp = a[iRoot]; a[iRoot] = a[j]; a[j] = tmp;
			this.makeFrame(makeData_heapify(iRoot,j,iEnd));
			heapify(iEnd,j);
		}
	}

	private SortData makeData() {
		return makeData(std_bg,std_fg);
	}

	private SortData makeData(Color bg, Color fg) {
		Bar[] bars = new Bar[a.length];
		for (int i = 0; i < a.length; i++) {
			bars[i] = new Bar(i,a[i],bg,fg);
		}
		List<Line> lines = new ArrayList<Line>();
		if (secondstage) {
			lines.add(new VerticalLine(k_vl,2,k-1));
			for (int i = k-1; i < a.length; i++) {
				bars[i] = new Bar(i,a[i],std_bg,std_fg);
			}
		}
		return new SortData(bars,lines,9,1,3);
	}

	private SortData makeData_heapify(int root, int end) {
		SortData d = makeData(unsel_bg,unsel_fg);
		Bar[] bars = d.getBars();
		bars[root].setBg(std_bg);
		bars[root].setFg(parent_fg);
		if (2 * root + 1 < end) {
			bars[2 * root + 1].setBg(std_bg);
			bars[2 * root + 1].setFg(child_fg);
			if (2 * root + 2 < end) {
				bars[2 * root + 2].setBg(std_bg);
				bars[2 * root + 2].setFg(child_fg);
			}
		}
		return d;
	}
	
	private SortData makeData_heapify(int root, int j, int end) {
		SortData d = makeData_heapify(root,end);
		Bar[] bars = d.getBars();
		bars[root].setFg(swap_fg);
		bars[j].setFg(swap_fg);
		return d;
	}
	
	private SortData makeData_swap(int swap0, int swap1) {
		SortData d = makeData(unsel_bg,unsel_fg);
		Bar[] bars = d.getBars();
		bars[swap0].setFg(swap_fg);
		bars[swap1].setFg(swap_fg);
		return d;
	}

	private boolean isRoot(int root, int end) {
		if (2*root + 1 >= end) { // 0 child nodes
			return true;
		}
		if (2*root + 2 >= end) { // 1 child node
			return (a[root] >= a[2*root + 1]);
		}
		//2 child nodes
		return (a[root] >= a[2*root + 1] && a[root] >= a[2*root+2] && isRoot(2*root+1,end) && isRoot(2*root + 2, end));
	}

}
