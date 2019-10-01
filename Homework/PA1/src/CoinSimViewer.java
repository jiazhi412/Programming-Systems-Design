// Name: Jiazhi Li
// USC NetID: jiazhil 
// USC ID : 5715388761
// CS 455 PA1
// Spring 2019

import javax.swing.JFrame;
import java.awt.event.*;
import java.util.Scanner;

public class CoinSimViewer {
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		
		//default window size
		frame.setSize(800,500);
		frame.setTitle("CoinSim");
	    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	    //instantiate CoinSimComponent and CoinTossSimulator
	    CoinSimComponent component = new CoinSimComponent();
	    component.get(frame);
	    CoinTossSimulator test = new CoinTossSimulator();
	    	   
	    //Error checking
	    Scanner sc = new Scanner(System.in);
 	    int i;
	    for(;;) {
	    	System.out.println("Enter number of trials: ");
	    	i = sc.nextInt();
	 	    if(i <= 0) {
	 	    	System.out.println("ERROR: Number entered must be greater than 0.");	 	    	
	 	    }
	 	    else break;
	    }
	   
	    //Start simulation
		test.run(i);
		component.getSimulator(test);
		
		//Draw component
        frame.add(component);
        frame.setVisible(true);
        
        //Add a listener to listen the change of window size
        frame.addComponentListener(new ComponentAdapter(){
        	@Override public void componentResized(ComponentEvent e){
        		component.get(frame);
        	}});     
	}
}
