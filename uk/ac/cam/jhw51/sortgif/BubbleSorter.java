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
		
	}

}
