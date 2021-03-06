package uk.ac.cam.jhw51.sortgif;
import java.io.IOException;

abstract public class Sorter {
	protected FrameBuilder mFB;
	abstract public void sort() throws IOException;
	protected void makeFrame(SortData d) throws IOException {
		mFB.nextFrame(d);
	}
	protected void endGif() throws IOException {
		mFB.endGif();
	}
}
