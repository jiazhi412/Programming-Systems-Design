// Name: Jiazhi Li
// USC NetID: jiazhil 
// USC ID : 5715388761
// CS 455 PA1
// Spring 2019

import java.awt.Color;
import javax.swing.JComponent;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.lang.Math;
import java.util.Random;
import javax.swing.JFrame;

public class CoinSimComponent extends JComponent
{
	private int NumTwoHeads;
	private int NumTwoTails;
	private int NumHeadTails;
	private int NumTrials;
	private int windowheight;
	private int windowwidth;
	
	public void paintComponent(Graphics g) {
		 Graphics2D g2 = (Graphics2D) g;
		 
		 //Fixed parameter	
		 final int MaxBarHeight = 350;
		 final double scale = windowheight / 500.0;
		 final int Bottom = 400 * windowheight / 500;		 
		 final int BarWidth = 50 * windowwidth / 800;		 
		 final int left_TwoHeads = 150 * windowwidth / 800;
		 final int left_HeadTails = 350 * windowwidth / 800;
		 final int left_TwoTails = 550 * windowwidth / 800;
	
		 //Draw TwoHeads bar
		 double Scale_TwoHeads = (double) NumTwoHeads / NumTrials;
		 int height_TwoHeads = (int) Math.round(Scale_TwoHeads * MaxBarHeight);
		 String label_TwoHeads = "Two Heads: " + NumTwoHeads + " (" + Math.round(Scale_TwoHeads * 100) + "%)";
	     Bar bar1 = new Bar(Bottom,left_TwoHeads,BarWidth,height_TwoHeads,scale,Color.RED,label_TwoHeads);	     
	     bar1.draw(g2);
	     	     
	     //Draw HeadTails bar
	     double Scale_HeadTails = (double) NumHeadTails / NumTrials;
		 int height_HeadTails = (int) Math.round(Scale_HeadTails * MaxBarHeight);
		 String label_HeadTails = "A Head and a Tail: " + NumHeadTails + " (" + Math.round(Scale_HeadTails * 100) + "%)";
	     Bar bar2 = new Bar(Bottom,left_HeadTails,BarWidth,height_HeadTails,scale,Color.BLUE,label_HeadTails);	     
	     bar2.draw(g2);

	     //Draw TwoTails bar
	     double Scale_TwoTails = (double) NumTwoTails / NumTrials;
		 int height_TwoTails = (int) Math.round(Scale_TwoTails * MaxBarHeight);
		 String label_TwoTails = "Two Tails: " + NumTwoTails + " (" + (100 - Math.round(Scale_TwoHeads * 100)-Math.round(Scale_HeadTails * 100)) + "%)";
	     Bar bar3 = new Bar(Bottom,left_TwoTails,BarWidth,height_TwoTails,scale,Color.GREEN,label_TwoTails);	     
	     bar3.draw(g2);	     
	}	
	
	//Communication between viewer and component for CoinTossSimulator 
	public void getSimulator(CoinTossSimulator s) {
		NumTwoHeads = s.getTwoHeads();
		NumTwoTails = s.getTwoTails();
		NumHeadTails = s.getHeadTails();
		NumTrials = s.getNumTrials();
	}
	
	//Communication between viewer and component for JFrame
	public void get(JFrame f) {
		windowheight = f.getBounds().height;
		windowwidth = f.getBounds().width;
	}
	
}
