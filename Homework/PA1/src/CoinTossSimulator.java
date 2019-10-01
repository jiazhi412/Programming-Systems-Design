// Name: Jiazhi Li
// USC NetID: jiazhil 
// USC ID : 5715388761
// CS 455 PA1
// Spring 2019

/**
 * class CoinTossSimulator
 * 
 * Simulates trials of repeatedly tossing two coins and allows the user to access the
 * cumulative results.
 * 
 * NOTE: we have provided the public interface for this class.  Do not change
 * the public interface.  You can add private instance variables, constants, 
 * and private methods to the class.  You will also be completing the 
 * implementation of the methods given. 
 * 
 * Invariant: getNumTrials() = getTwoHeads() + getTwoTails() + getHeadTails()
 * 
 */
import java.util.Random;

public class CoinTossSimulator {
	
	private int NumTwoHeads;
	private int NumTwoTails;
	private int NumHeadTails;
	private int NumTrials;
    private Random generate;
	
   /**
      Creates a coin toss simulator with no trials done yet.
   */
   public CoinTossSimulator() {	 
	   NumTwoHeads = 0;
	   NumTwoTails = 0;
	   NumHeadTails = 0;
	   NumTrials = 0;
	   generate = new Random();
   }


   /**
      Runs the simulation for numTrials more trials. Multiple calls to this method
      without a reset() between them *add* these trials to the current simulation.
      
      @param numTrials  number of trials to for simulation; must be >= 1
    */
   public void run(int numTrials) {
	   NumTrials += numTrials;
	   for(int i = 0; i < numTrials; i++) {
		   // 1 represents head and 0 represents tail 
		   int first_toss;
		   first_toss  = generate.nextInt(2);
		   int second_toss;
		   second_toss = generate.nextInt(2);
		   if(first_toss == 1 && second_toss == 1) {
			   NumTwoHeads++;
		   }else if(first_toss == 0 && second_toss == 0) {
			   NumTwoTails++;
		   }else {
			   NumHeadTails++;
		   }
	   }
   }


   /**
      Get number of trials performed since last reset.
   */
   public int getNumTrials() {
       return NumTrials; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Get number of trials that came up two heads since last reset.
   */
   public int getTwoHeads() {
       return NumTwoHeads; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up two tails since last reset.
   */  
   public int getTwoTails() {
       return NumTwoTails; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
     Get number of trials that came up one head and one tail since last reset.
   */
   public int getHeadTails() {
       return NumHeadTails; // DUMMY CODE TO GET IT TO COMPILE
   }


   /**
      Resets the simulation, so that subsequent runs start from 0 trials done.
    */
   public void reset() {
	   NumTwoHeads = 0;
	   NumTwoTails = 0;
	   NumHeadTails = 0;
	   NumTrials = 0;
   }

}
