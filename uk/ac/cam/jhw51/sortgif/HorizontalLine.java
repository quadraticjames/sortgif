//written by James Wright, jhw51@cam.ac.uk, 2014-01-24

package uk.ac.cam.jhw51.sortgif;
import java.awt.Color;


public class HorizontalLine extends Line {
	private int height;

	public int getHeight() {
		return height;
	}
	
	public HorizontalLine(Color c, int thickness, int height) {
		this.c = c;
		this.thickness = thickness;
		this.height = height;
	}
}
