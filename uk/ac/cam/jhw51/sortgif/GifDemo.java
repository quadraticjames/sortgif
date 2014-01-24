//written by James Wright, jhw51@cam.ac.uk, 2014-01-24

package uk.ac.cam.jhw51.sortgif;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

public class GifDemo {
	public static void main(String[] args) throws IOException {
		List<Integer> l = new LinkedList<Integer>();
		for (int i = 0; i < 60; i++) { 
			l.add(i+5);
		}
		Collections.shuffle(l);
		int[] i = new int[l.size()];
		for (int j = 0; j < l.size(); j++) {
			i[j] = l.get(j);
		}
		String date = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss").format(new Date());
		Sorter s = new QuickSorter(i,new FrameBuilder(new OutputGif("C:\\Users\\James\\Dropbox\\2013j\\SortGif\\gifs\\" + date + ".gif"))); //or wherever you want output to go
		s.sort();
	}
}
