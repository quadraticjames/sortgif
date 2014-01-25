//written by James Wright, jhw51@cam.ac.uk, 2014-01-24

package uk.ac.cam.jhw51.sortgif;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GifDemo {
	public static void main(String[] args) throws IOException {
		//args[0] = number of integers in array to be sorted; args[1] = output filename
		List<Integer> l = new LinkedList<Integer>();
		for (int i = 0; i < Integer.parseInt(args[0]); i++) { 
			l.add(i+5);
		}
		Collections.shuffle(l);
		int[] i = new int[l.size()];
		for (int j = 0; j < l.size(); j++) {
			i[j] = l.get(j);
		}
		Sorter s = new HeapSorter(i,new FrameBuilder(new OutputGif(args[1])));
		s.sort();
	}
}
