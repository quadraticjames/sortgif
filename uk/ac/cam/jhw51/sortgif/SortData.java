package uk.ac.cam.jhw51.sortgif;
import java.util.List;


public class SortData {
	private Bar[] bars;
	private List<Line> lines;
	private int barwidth;
	private int gapwidth; // must be less than barwidth / 2
	private int unitheight;
	
	public void setBars(Bar[] bars) {
		this.bars = bars;
	}

	public void setLines(List<Line> lines) {
		this.lines = lines;
	}

	public void setBarwidth(int barwidth) {
		this.barwidth = barwidth;
	}

	public void setGapwidth(int gapwidth) {
		this.gapwidth = gapwidth;
	}

	public void setUnitheight(int unitheight) {
		this.unitheight = unitheight;
	}

	public Bar[] getBars() {
		return bars;
	}
	
	public List<Line> getLines() {
		return lines;
	}
	
	public int getBarwidth() {
		return barwidth;
	}
	
	public int getGapwidth() {
		return gapwidth;
	}
	
	public int getUnitheight() {
		return unitheight;
	}
	
	public SortData(Bar[] bars, List<Line> lines, int barwidth, int gapwidth, int unitheight) {
		this.bars = bars;
		this.lines = lines;
		this.barwidth = barwidth;
		this.gapwidth = gapwidth;
		this.unitheight = unitheight;
	}
}
