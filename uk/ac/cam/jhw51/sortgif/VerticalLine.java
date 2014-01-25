package uk.ac.cam.jhw51.sortgif;
import java.awt.Color;


public class VerticalLine extends Line {
	private int x;

	public int getX() {
		return x;
	}
	
	public VerticalLine(Color c, int thickness, int x) {
		this.c = c;
		this.thickness = thickness;
		this.x = x;
	}
}
