package uk.ac.cam.jhw51.sortgif;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class FrameBuilder {
	//FrameBuilder builds each GIF up frame by frame
	private OutputGif o;
	private int width = 0; //width of GIF in pixels
	private int height = 0; //height of GIF in pixels
	private List<Bar> bars; 
	private List<Line> lines;
	private int barwidth; //distance from the start of one bar to the start of the next, including gap
	private int gapwidth; //half of the gap between each bar
	private int unitheight; //height in pixels of a bar with height 1
	private int alength; //number of data points
	
	public FrameBuilder(OutputGif o) {
		this.o = o;
	}
	
	private void build(BufferedImage image, Graphics g) {
		//where each frame is drawn
		for (Bar b : bars) {
			int position = b.getPos();
			//background
			g.setColor(b.getBg());
			g.fillRect(barwidth*position,0,barwidth,height);
			//bar
			g.setColor(b.getFg());
			g.fillRect((barwidth*position) + gapwidth,height - (unitheight*b.getHeight()),barwidth - (2*gapwidth),unitheight*b.getHeight());
		}
		for (Line l : lines) {
			g.setColor(l.getColor());
			if (l instanceof HorizontalLine) {
				g.fillRect(0, height - ((unitheight*((HorizontalLine) l).getHeight()) + l.getThickness()), width, l.getThickness());
			} else {
				g.fillRect((barwidth*((VerticalLine) l).getX()) - (l.getThickness()/2),0,l.getThickness(),height);
			}
		}
	}
	
	private void findDimensions() {
		this.alength = bars.size();
		this.width = barwidth * alength;
		this.height = 0;
		for (Bar b : bars) {
			if (b.getHeight() > height) {
				this.height = b.getHeight();
			}
		}
		this.height = unitheight*height + 5; // 5 = space between top of highest bar and top of frame
	}
	
	private void unpackData(SortData d) {
		this.bars = Arrays.asList(d.getBars());
		this.lines = d.getLines();
		this.barwidth = d.getBarwidth();
		this.gapwidth = d.getGapwidth();
		this.unitheight = d.getUnitheight();
	}
	
	public void nextFrame(SortData data) throws IOException {
	  unpackData(data);
	  if (width == 0 || height == 0) {
		  this.findDimensions();
	  }
	  BufferedImage image = new BufferedImage(width,height,BufferedImage.TYPE_INT_RGB);
	  Graphics g = image.getGraphics();
	  build(image,g);
	  g.dispose(); //free up resources used by graphics context
	  o.addFrame(image);
	}
	
	public void endGif() throws IOException {
		o.close();
	}
	
}
