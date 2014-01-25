package uk.ac.cam.jhw51.sortgif;
import java.io.IOException;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class GifDemo {
	public static void main(String[] args) throws IOException {
		//args[0] = number of integers in array to be sorted; args[1] = output directory
		List<Integer> l = new LinkedList<Integer>();
		for (int i = 0; i < Integer.parseInt(args[0]); i++) { 
			l.add(i+5);
		}
		Collections.shuffle(l);
		int[] i = new int[l.size()];
		for (int j = 0; j < l.size(); j++) {
			i[j] = l.get(j);
		}
		Sorter s = new BinaryInsertionSorter(i.clone(),new FrameBuilder(new OutputGif(args[1] + "binaryinsert.gif")));
		s.sort();
		s = new BubbleSorter(i.clone(),new FrameBuilder(new OutputGif(args[1] + "bubble.gif")));
		s.sort();
		s = new HeapSorter(i.clone(),new FrameBuilder(new OutputGif(args[1] + "heap.gif")));
		s.sort();
		s = new QuickSorter(i.clone(),new FrameBuilder(new OutputGif(args[1] + "quick.gif")));
		s.sort();
		s = new InsertionSorter(i.clone(),new FrameBuilder(new OutputGif(args[1] + "insert.gif")));
		s.sort();
		
	}
}
