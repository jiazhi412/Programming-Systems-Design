// Name: Jiazhi Li
// USC NetID: jiazhil 
// USC ID : 5715388761
// CS 455 PA1
// Spring 2019

// we included the import statements for you
import java.awt.Color;
import java.lang.Math;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.font.FontRenderContext;
import java.awt.geom.Rectangle2D;

/**
 * Bar class
 * A labeled bar that can serve as a single bar in a bar graph.
 * The text for the label is centered under the bar.
 * 
 * NOTE: we have provided the public interface for this class. Do not change
 * the public interface. You can add private instance variables, constants,
 * and private methods to the class. You will also be completing the
 * implementation of the methods given.
 * 
 */
public class Bar {
	
	private int Bottom;
	private int Left;
	private int Width;
	private int BarHeight;
	private double Scale;
	private Color Color;
	private String Label;

   /**
      Creates a labeled bar.  You give the height of the bar in application
      units (e.g., population of a particular state), and then a scale for how
      tall to display it on the screen (parameter scale). 
  
      @param bottom  location of the bottom of the label
      @param left  location of the left side of the bar
      @param width  width of the bar (in pixels)
      @param barHeight  height of the bar in application units
      @param scale  how many pixels per application unit
      @param color  the color of the bar
      @param label  the label at the bottom of the bar
   */
   public Bar(int bottom, int left, int width, int barHeight,
              double scale, Color color, String label) {
	   Bottom = bottom;
	   Left = left;
	   Width = width;
	   BarHeight = (int) Math.round(scale * barHeight);
	   Scale = scale;
	   Color = color;
	   Label = label;
   }
   
   /**
      Draw the labeled bar. 
      @param g2  the graphics context
   */
   public void draw(Graphics2D g2) {
	   final int font_size_default = 12;
	   
	   //Draw bar
	   g2.setColor(Color);
	   Rectangle rec = new Rectangle(Left,Bottom - BarHeight,Width,BarHeight);
	   g2.fill(rec);
	   
	   //Draw label
	   g2.setColor(Color.black);
	   g2.setFont(new Font(Label,0,(int) Math.round(Scale * font_size_default)));
	   Font font = g2.getFont();
	   FontRenderContext context = g2.getFontRenderContext();
	   Rectangle2D labelBounds = font.getStringBounds(Label, context);
	   int widthOfLabel = (int) labelBounds.getWidth();
	   int heightOfLabel = (int) labelBounds.getHeight();		 
	   g2.drawString(Label,Left + Width / 2 - widthOfLabel / 2, (int) Math.round(Bottom + Scale * 15));
   }
}
