package uk.ac.cam.jhw51.sortgif
import java.awt.Color;

public class Bar {
	//a bar is one data point in the array that is being sorted
	//it is represented in the GIF as a vertical line
	private int x; //zero-based position within the array
	private int height; 
	private Color bg; //background colour
	private Color fg; //foreground colour
	
	public void setPos(int pos) {
		this.x = pos;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public void setBg(Color bg) {
		this.bg = bg;
	}
	public void setFg(Color fg) {
		this.fg = fg;
	}
	
	public int getHeight() {
		return height;
	}
	public Color getBg() {
		return bg;
	}
	public Color getFg() {
		return fg;
	}
	public int getPos() {
		return x;
	}
	
	public Bar(int x, int h, Color b, Color f) {
		this.x = x;
		this.height = h;
		this.bg = b;
		this.fg = f;
	}
}
