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
	
	Color vl = new Color(143,232,0);
	
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
		for (int i = 1; i < a.length; i++) {
			boolean run_once = false;
			this.makeFrame(this.makeData(i));
			int j = i - 1;
			this.makeFrame(this.makeData(i+1,j+1));
			while (j >= 0 && a[j] > a[j+1]) {
				if (run_once) {	this.makeFrame(this.makeData(i+1,j+1)); } else { run_once = true; }
				int tmp = a[j]; a[j] = a[j+1]; a[j+1] = tmp;
				j--;
			}
			this.makeFrame(this.makeData(i+1,j+1));
		}
		this.makeFrame(this.makeData(a.length));
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
	
	private SortData makeData(int i, int j) {
		SortData d = makeData(i);
		Bar[] bars = d.getBars();
		bars[j].setFg(sel_fg);
		return d;
	}
	

}
