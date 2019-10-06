import java.util.ArrayList;
import java.lang.Integer;
/**
   Stores a sequence of integer data values and supports some computations
   with it.

   CS 455 Lab 4.
*/
public class Nums {
   
   private ArrayList<Integer> nums;


   /**
      Create an empty sequence of nums.
   */
   public Nums () {
      nums = new ArrayList<Integer>();
   }

   /**
      Add a value to the end of the sequence.
   */
   public void add(int value) {
      nums.add(value);
   }


   /**
      Return the minimum value in the sequence.
      If the sequence is empty, returns Integer.MAX_VALUE
   */
   public int minVal() {
      int min = Integer.MAX_VALUE;
      for(int element : nums) {
         if(element < min) {
            min = element;
         }
      }
      return min;    
   }

   /**
      Prints out the sequence of values as a space-separated list 
      on one line surrounded by parentheses.
      Does not print a newline.
      E.g., "(3 7 4 10 2 7)", for empty sequence: "()"
   */
   public void printVals() {
      System.out.print("(");
      for(int i = 0; i < nums.size(); i++) {
         System.out.print(nums.get(i));
         if(i < nums.size() - 1) {
            System.out.print(" ");
         }
      }
      System.out.print(")");
   }

   /**
      Returns a new Nums object with all the values from this Nums
      object that are above the given threshold.  The values in the
      new object are in the same order as in this one.
      E.g.: call to myNums.valuesGT(10) where myNums = (3 7 19 4 21 19 10)
      returns      (19 21 19)
      myNums after call:  (3 7 19 4 21 19 10)
      The method does not modify the object the method is called on.
   */
   public Nums valuesGT(int threshold) {
      Nums res = new Nums();      
      for(int i = 0; i < nums.size(); i++) {
         if(nums.get(i) > threshold) {
            res.add(nums.get(i));
         }
      }      
      return res;  // stub code to get it to compile
   }

    
}
