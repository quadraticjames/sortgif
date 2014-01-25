package uk.ac.cam.jhw51.sortgif;

import java.io.IOException;

public class BubbleSorter extends Sorter {
	
	int[] a;

	public BubbleSorter(int[] is, FrameBuilder fb) {
		this.a = is;
		this.mFB = fb;
	}

	@Override
	public void sort() throws IOException {
		bubbleSort();
		this.endGif();
	}
	
	private void bubbleSort() {
		boolean didSwaps = true;
		int passes = 0;
		while (didSwaps) {
			//ASSERT: [a.length - (1 + passes):a.length] is sorted
			didSwaps = false;
			for (int k = 0; k < a.length - (1 + passes); k++) {
				if (a[k] > a[k+1]) {
					int tmp = a[k]; a[k] = a[k+1]; a[k+1] = tmp;
					didSwaps = true;
				}
			}
		}
	}

}
