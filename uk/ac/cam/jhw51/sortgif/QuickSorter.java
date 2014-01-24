//written by James Wright, jhw51@cam.ac.uk, 2014-01-24

package uk.ac.cam.jhw51.sortgif;
import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class QuickSorter extends Sorter {

	int[] a;
	private final Color unsort_bg = new Color(168,168,168);
	private final Color sort_bg = new Color(214,214,214);
	private final Color unsel_bg = new Color(245,245,245);
	private final Color sel_fg = new Color(0,0,0);
	private final Color unsel_fg = new Color(190,190,190);
	private final Color pivot_fg = new Color(232,9,0);
	private final Color swap_fg = new Color(23,202,230);
	private final Color pivot_hl = new Color(0,165,255);
	private final Color left_vl = new Color(255,0,119);
	private final Color right_vl = new Color(183,0,255);
	private final Color lr_vl = new Color(255,0,255);

	public QuickSorter(int[] is, FrameBuilder fb) {
		this.a = is;
		this.mFB = fb;
	}

	@Override
	public void sort() throws IOException {
		this.makeFrame(makeData());
		qs(0,a.length);
		this.makeFrame(makeData());
		this.endGif();
	}

	private void qs(int iBegin, int iEnd) throws IOException {
		if (iEnd-iBegin == 2) {
			if (a[iBegin] > a[iBegin+1]) {
				this.makeFrame(makeData(iBegin,iEnd,a.length,a.length,a.length,iBegin,iBegin+1));
				int tmp = a[iBegin]; a[iBegin] = a[iBegin + 1]; a[iBegin + 1] = tmp;
				this.makeFrame(makeData(iBegin,iEnd,a.length,a.length,a.length,iBegin,iBegin+1));
			}
		} else if (iEnd-iBegin > 2) {
			int iPivot = iEnd - 1;
			int iLeft = iBegin;
			int iRight = iPivot;
			this.makeFrame(makeData(iBegin,iEnd,iLeft,iRight,iPivot));
			while (iLeft != iRight) {
				while (iLeft < iRight && a[iLeft]<=a[iPivot]) {
					this.makeFrame(makeData(iBegin,iEnd,iLeft,iRight,iPivot));
					iLeft++;
				}
				while (iLeft < iRight && a[iRight-1]>a[iPivot]) {
					this.makeFrame(makeData(iBegin,iEnd,iLeft,iRight,iPivot));
					iRight--;
				}
				if (iLeft != iRight) {
					this.makeFrame(makeData(iBegin,iEnd,iLeft,iRight,iPivot,iLeft,iRight));
					int tmp = a[iLeft]; a[iLeft] = a[iRight-1]; a[iRight-1] = tmp;
					this.makeFrame(makeData(iBegin,iEnd,iLeft,iRight,iPivot,iLeft,iRight));
				}
			}

			this.makeFrame(makeData(iBegin,iEnd,iLeft,iRight,iPivot,iRight,iPivot));
			int tmp = a[iRight]; a[iRight] = a[iPivot]; a[iPivot] = tmp;
			this.makeFrame(makeData(iBegin,iEnd,iLeft,iRight,iRight,iRight,iPivot));
			qs(iBegin,iLeft);
			qs(iRight+1,iEnd);
		}
	}
	private SortData makeData() {
		Bar[] bars = new Bar[a.length];
		for (int i = 0; i < a.length; i++) {
			bars[i] = new Bar(i,a[i],sort_bg,sel_fg);
		}
		return new SortData(bars,new ArrayList<Line>(),9,1,3);
	}
	private SortData makeData(int begin, int end, int left, int right, int pivot) {
		SortData d = makeData();
		Bar[] b = d.getBars();
		for (int i = 0; i < begin; i++) {
			b[i].setBg(unsel_bg);
			b[i].setFg(unsel_fg);
		}
		for (int i = left; i < right; i++) {
			b[i].setBg(unsort_bg);
		}
		for (int i = end; i < a.length; i++) {
			b[i].setBg(unsel_bg);
			b[i].setFg(unsel_fg);
		}
		if (pivot < a.length) {
			b[pivot].setFg(pivot_fg);
			List<Line> l = d.getLines();
			l.add(new HorizontalLine(pivot_hl,1,a[pivot]));
			if (left != right) {
				l.add(new VerticalLine(left_vl,2,left));
				l.add(new VerticalLine(right_vl,2,right));
			} else {
				l.add(new VerticalLine(lr_vl,2,left));
			}
		}
		return d;
	}
	private SortData makeData(int begin, int end, int left, int right, int pivot, int swap0, int swap1) {
		SortData d = makeData(begin,end,left,right,pivot);
		Bar[] b = d.getBars();
		b[swap0].setFg(swap_fg);
		b[swap1].setFg(swap_fg);
		return d;
	}

}
