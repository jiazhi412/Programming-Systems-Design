/**
   To represent some number of dollars, quarters, dimes, etc.
*/
public class Change {

   public static final int DOLLAR_VALUE = 100;
   public static final int QUARTER_VALUE = 25;
   public static final int DIME_VALUE = 10;
   public static final int NICKEL_VALUE = 5;
   public static final int PENNY_VALUE = 1;

   private int dollars;
   private int quarters;
   private int dimes;
   private int nickels;
   private int pennies;

   /**
      Create change object with no money in it.
   */
   public Change() {
      this(0,0,0,0,0); // calls the 5-param constructor on this object.
      // Calling one constructor from another is
      // is described in Special Topic 3.1 of Big Java, 6th Ed.
   }

   /**
      Create change object with given number of dollars, etc.
   */
   public Change(int numDollars, int numQuarters, 
                 int numDimes, int numNickels, int numPennies) {

      dollars = numDollars;
      quarters = numQuarters;
      dimes = numDimes;
      nickels = numNickels;
      pennies = numPennies;
   }

   /**
      gets a string representation of the value
   */
   public String toString() {
      return "Change[dollars=" + dollars
         + ",quarters=" + quarters
         + ",dimes=" + dimes
         + ",nickels=" + nickels
         + ",pennies=" + pennies
         + "]";
   }

   /**
      total value of change in cents
   */
   public int totalValue() {
      return dollars * DOLLAR_VALUE
         + quarters * QUARTER_VALUE
         + dimes * DIME_VALUE
         + nickels * NICKEL_VALUE
         + pennies * PENNY_VALUE;
   }


}
