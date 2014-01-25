package uk.ac.cam.jhw51.sortgif;

import java.awt.Color;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BinaryInsertionSorter extends Sorter {

	int a[];

	Color std_bg = new Color(232,232,232);
	Color std_fg = new Color(0,0,0);
	Color unsel_bg = new Color(255,255,255);
	Color unsel_fg = new Color(209,209,209);
	Color sel_fg = new Color(255,10,10);
	Color comp_fg = new Color(71,237,221);
	Color found_fg = new Color(111,26,214);

	Color vl = new Color(143,232,0);
	Color hl = new Color(225,39,242);

	public BinaryInsertionSorter(int[] is, FrameBuilder fb) {
		this.a = is;
		this.mFB = fb;

	}

	@Override
	public void sort() throws IOException {
		insertSort();
		this.endGif();
	}

	private void insertSort() throws IOException {		
		this.makeFrame(this.makeData(0));
		for (int k = 1; k < a.length; k++) {
			int i = BinarySearch(0, k,a[k], k);
			if (i != k) {
				int tmp = a[k];
				for (int j = k - 1; j > i - 1; j--) {
					a[j+1] = a[j];
					this.makeFrame(makeData(k+1));
				}
				a[i] = tmp;
				this.makeFrame(makeData_scroll(k+1,i));
			}
		}
		this.makeFrame(this.makeData(a.length));
	}

	private int BinarySearch (int low, int high, int key, int k) throws IOException {
		//source: http://jeffreystedfast.blogspot.co.uk/2007/02/binary-insertion-sort.html
		if (low == high) { 
			this.makeFrame(makeData_search(k,low,true));
			return low; }
		int mid = low + ((high-low) / 2); //not (high+low)/2 lest we get an integer overflow
		this.makeFrame(makeData_search(k,mid,false));
		if (key > a[mid]) {
			return BinarySearch(mid+1, high, key, k);
		} else if (key < a[mid]) {
			return BinarySearch(low,mid,key, k);
		}
		this.makeFrame(makeData_search(k,mid,true));
		return mid;
	}

	private SortData makeData_scroll(int k, int j) {
		SortData d = makeData(k);
		d.getBars()[j].setFg(sel_fg);
		return d;
	}

	private SortData makeData_search(int k, int search, boolean found) {
		SortData d = makeData(k);
		Bar[] bars = d.getBars();
		bars[k].setFg(sel_fg);
		if (found) {
			bars[search].setFg(found_fg);
			if (search > 0) {
				bars[search-1].setFg(found_fg);
			}
		} else {
			bars[search].setFg(comp_fg);
		}
		d.getLines().add(new HorizontalLine(hl,1,a[k]));
		return d;
	}

	private SortData makeData(int i) {
		Bar[] bars = new Bar[a.length];
		Color bg = std_bg;
		Color fg = std_fg;
		for (int k = 0; k < a.length; k++) {
			if (k==i) {
				bg = unsel_bg;
				fg = unsel_fg;
			}
			bars[k] = new Bar(k,a[k],bg,fg);
		}
		List<Line> lines = new LinkedList<Line>();
		if (i > 0 && i < a.length) {
			lines.add(new VerticalLine(vl,2,i));
		}
		return new SortData(bars,lines,9,1,3);
	}

}
