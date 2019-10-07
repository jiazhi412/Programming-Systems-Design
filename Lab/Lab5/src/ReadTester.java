import java.util.Scanner;
import java.lang.Integer;
import java.util.ArrayList;

public class ReadTester {
   public static void main(String[] args) {
      
     while(true) {
        System.out.print("Enter a space separated list of numbers: ");
        ArrayList<Integer> myArrlist = new ArrayList<Integer>();
        Scanner sc = new Scanner(System.in);
        
//        while(sc.hasNextLine()) {
//           String line = nextLine();
//           
//           for(int i = 0;i < line.size();i++) {
//              Character.isDigit(line.charAt(i));
//              if(
//           
//           myArrlist.add(Integer.parseInt(num));
//        }
        
        while (sc.hasNextInt())   {           
           myArrlist.add(sc.nextInt());    
           String s = sc.nextLine(); 
        }
        
        System.out.print("The numbers were: [");
        
        for(int i = 0;i < myArrlist.size(); i++) {           
           System.out.print(myArrlist.get(i));
           if(i < myArrlist.size() - 1) {
              System.out.print(", ");
           }
        }
                   
        System.out.print("]");
        System.out.println();
     }
   }      
}