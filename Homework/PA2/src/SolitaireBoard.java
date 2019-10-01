// Name: Jiazhi Li
// USC NetID: jiazhil
// USC ID: 5715388761
// CSCI455 PA2
// Spring 2019

import java.util.ArrayList;
import java.util.TreeMap;
import java.util.Random;

/*
  class SolitaireBoard
  The board for Bulgarian Solitaire.  You can change what the total number of cards is for the game
  by changing NUM_FINAL_PILES, below.  Don't change CARD_TOTAL directly, because there are only some values
  for CARD_TOTAL that result in a game that terminates.
  (See comments below next to named constant declarations for more details on this.)
*/


public class SolitaireBoard {
   
   public static final int NUM_FINAL_PILES = 9;
   // number of piles in a final configuration
   // (note: if NUM_FINAL_PILES is 9, then CARD_TOTAL below will be 45)
   
   public static final int CARD_TOTAL = NUM_FINAL_PILES * (NUM_FINAL_PILES + 1) / 2;
   // bulgarian solitaire only terminates if CARD_TOTAL is a triangular number.
   // see: http://en.wikipedia.org/wiki/Bulgarian_solitaire for more details
   // the above formula is the closed form for 1 + 2 + 3 + . . . + NUM_FINAL_PILES

   // Note to students: you may not use an ArrayList -- see assgt description for details.
   
   
   /**
      Representation invariant:
      1. number of piles should be greater than 0 and less than or equal to number of cards
      2. the sum of cards in each piles should be equal to CARD_TOTAL
      3. the num of cards in each piles should be greater than 0 and less than or equal to CARD_TOTAL
      <put rep. invar. comment here>

   */
   
   // <add instance variables here>
   private int[] cards = new int[CARD_TOTAL];
   int numofpiles = 0;
  
 
   /**
      Creates a solitaire board with the configuration specified in piles.
      piles has the number of cards in the first pile, then the number of cards in the second pile, etc.
      PRE: piles contains a sequence of positive numbers that sum to SolitaireBoard.CARD_TOTAL
   */
   public SolitaireBoard(ArrayList<Integer> piles) {
	   numofpiles = piles.size();
	   for(int i = 0;i < piles.size();i++) {
		   cards[i] = piles.get(i);
	   }
      assert isValidSolitaireBoard();   // sample assert statement (you will be adding more of these calls)
   }
 
   
   /**
      Creates a solitaire board with a random initial configuration.
   */
   public SolitaireBoard() {
	   Random rand = new Random();
	   int sum = 0;
	   int i = 0;
	   while(i < CARD_TOTAL-1 && sum < CARD_TOTAL) {
		   cards[i] = rand.nextInt(CARD_TOTAL + 1 - sum);
		   if(cards[i] != 0) {
			   sum += cards[i];
			   i++;			   
		   }		
	   }
	   numofpiles = i;
	   assert isValidSolitaireBoard();  
   }
  
   
   /**
      Plays one round of Bulgarian solitaire.  Updates the configuration according to the rules
      of Bulgarian solitaire: Takes one card from each pile, and puts them all together in a new pile.
      The old piles that are left will be in the same relative order as before, 
      and the new pile will be at the end.
   */
   public void playRound() {
	   // it does not allocate a new array
	   int new_pile = numofpiles;
	   // use a offset number to implement in O(n)
	   int offset = 0;
	   for(int i = 0;i < numofpiles;i++) {		  
		   cards[i - offset] = cards[i] - 1;		  
		   if(cards[i-offset] == 0) {
			   offset++;
		   }		   
	   }
	   numofpiles -= offset;
	   cards[numofpiles] = new_pile;	  
	   numofpiles++;
	   assert isValidSolitaireBoard();  
   }
   
   /**
      Returns true iff the current board is at the end of the game.  That is, there are NUM_FINAL_PILES
      piles that are of sizes 1, 2, 3, . . . , NUM_FINAL_PILES, in any order.
   */
   
   public boolean isDone() {
	   TreeMap<Integer,Integer> tm = new TreeMap<Integer,Integer>();
	   boolean flag = true;
	   // numofpiles == NUM_FINAL_PILES and there is no same number so that the solitaire is over
	   // By this way, the method execute in O(n)
	   if(numofpiles == NUM_FINAL_PILES) {
		   for(int i = 0;i < numofpiles;i++) {
			   if(tm.get(cards[i]) == null) {
				   tm.put(cards[i], 0);
			   }
			   tm.put(cards[i],tm.get(cards[i])+1);
			   if(tm.get(cards[i])>1) {
				   flag = false;
				   break;
			   }
		   }		 
	   }
	   else flag = false;
	   assert isValidSolitaireBoard();  
	   return flag;     
   }

   
   /**
      Returns current board configuration as a string with the format of
      a space-separated list of numbers with no leading or trailing spaces.
      The numbers represent the number of cards in each non-empty pile.
   */
   public String configString() {
	   String config = "";
	   for(int i = 0;i < numofpiles - 1;i++) {
		   config = config + cards[i] + " ";
	   }
	   config += cards[numofpiles - 1];
      return config;   // dummy code to get stub to compile
   }
   
   
   /**
      Returns true iff the solitaire board data is in a valid state
      (See representation invariant comment for more details.)  
   Representation invariant:
   1. number of piles should be greater than 0 and less than or equal to number of cards
   2. the sum of cards in each piles should be equal to CARD_TOTAL
   3. the num of cards in each piles should be greater than 0 and less than or equal to CARD_TOTAL  
   */
   private boolean isValidSolitaireBoard() {
      boolean flag = true;
      int sum = 0;
      if(numofpiles > 0 && numofpiles <= CARD_TOTAL) {
    	  for(int i = 0; i < numofpiles; i++) {    	  
        	  if(cards[i] < 0 || cards[i] > CARD_TOTAL) {
        		  flag = false;
        	  }    	
        	  sum += cards[i];    	  
          }
          if(sum != CARD_TOTAL) {
        	  flag = false;
          }
      }
      else flag = false;          
      return flag;  
   }
   

   // <add any additional private methods here>


}
