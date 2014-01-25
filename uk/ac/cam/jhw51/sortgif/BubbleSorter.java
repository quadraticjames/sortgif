package uk.ac.cam.jhw51.sortgif;

import java.awt.Color;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

public class BubbleSorter extends Sorter {
	
	int[] a;
	
	Color std_bg = new Color(232,232,232);
	Color std_fg = new Color(0,0,0);
	Color unsel_bg = new Color(255,255,255);
	Color unsel_fg = new Color(209,209,209);
	Color sel_fg = new Color(255,10,10);
	
	Color vl = new Color(143,232,0);

	public BubbleSorter(int[] is, FrameBuilder fb) {
		this.a = is;
		this.mFB = fb;
	}

	@Override
	public void sort() throws IOException {
		this.makeFrame(makeData(a.length));
		bubbleSort();
		this.makeFrame(makeData(0));
		this.endGif();
	}
	
	private void bubbleSort() throws IOException {
		boolean didSwaps = true;
		int passes = 0;
		while (didSwaps) {
			//ASSERT: [a.length - (1 + passes):a.length] is sorted
			didSwaps = false;
			for (int k = 0; k < a.length - (1 + passes); k++) {
				this.makeFrame(makeData(a.length - passes));
				if (a[k] > a[k+1]) {
					int tmp = a[k]; a[k] = a[k+1]; a[k+1] = tmp;
					didSwaps = true;
				}
			}
			passes++;
		}
	}
	
	private SortData makeData(int i) {
		Bar[] bars = new Bar[a.length];
		Color bg = unsel_bg;
		Color fg = unsel_fg;
		for (int k = 0; k < a.length; k++) {
			if (k==i) {
				bg = std_bg;
				fg = std_fg;
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
