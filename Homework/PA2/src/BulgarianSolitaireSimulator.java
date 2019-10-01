// Name: Jiazhi Li
// USC NetID: jiazhil
// USC ID: 5715388761
// CSCI455 PA2
// Spring 2019

/**
   <add main program comment here>
*/

import java.util.Scanner;
import java.lang.Integer;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;

public class BulgarianSolitaireSimulator {
	
	// Class commments:
//	In the whole class, there are four functions. The first one is the main function which will introduced 
//	in the main class comment. And then, for the two requirement running method such that normal case and 
//	single step case, there are two separable functions that simulate and simulate_single. Also, for user input 
//	and input error checking, there is a function named userinput.

	public static void main(String[] args) throws IOException {
		// main class comment:
//		Checking the flag from args is the first step. After that, according to flag, there are four combination
//		of running method such that (random,single-step), (random,non-single-step), (user-input,single-step) 
//		and (user-input,non-single-step).

		boolean singleStep = false;
		boolean userConfig = false;

		for (int i = 0; i < args.length; i++) {
			if (args[i].equals("-u")) {
				userConfig = true;
			} else if (args[i].equals("-s")) {
				singleStep = true;
			}
		}

		// Choices for four modes
		if (userConfig) {
			ArrayList<Integer> piles = userinput();
			SolitaireBoard Board_Specific = new SolitaireBoard(piles);
			if (singleStep) {
				simulate_single(Board_Specific);
			} else {
				simulate(Board_Specific);
			}
		} else {
			SolitaireBoard Board_Random = new SolitaireBoard();
			if (singleStep) {
				simulate_single(Board_Random);
			} else {
				simulate(Board_Random);
			}
		}
	}

	// Simulate in a long turn
	private static void simulate(SolitaireBoard Board) {
		System.out.print("Initial configuration: ");
		System.out.println(Board.configString());
		int i = 1;
		while (!Board.isDone()) {
			Board.playRound();
			System.out.print("[" + i + "] " + "Current configuration: ");
			System.out.println(Board.configString());
			i++;
		}
		System.out.println("Done!");
	}

	// Simulate in single step mode
	private static void simulate_single(SolitaireBoard Board) throws IOException {
		System.out.print("Initial configuration: ");
		System.out.println(Board.configString());
		int i = 1;
		while (!Board.isDone()) {
			Board.playRound();
			System.out.print("[" + i + "] " + "Current configuration: ");
			System.out.println(Board.configString());
			System.out.print("<Type return to continue>");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String enter = br.readLine();
			i++;
		}
		System.out.println("Done!");
	}

	// User input mode (acquire the input from user)
	private static ArrayList<Integer> userinput() {
		System.out.println("Number of total cards is " + SolitaireBoard.CARD_TOTAL + "\n"
				+ "You will be entering the initial configuration of the cards (i.e., how many in each pile).\n"
				+ "Please enter a space-separated list of positive integers followed by newline:");

		ArrayList<Integer> piles = new ArrayList<Integer>();
		Scanner in = new Scanner(System.in); // only one Scanner object to read from System.in
		while (true) {
			ArrayList<Integer> temp = new ArrayList<Integer>();
			Scanner string = new Scanner(in.nextLine());

			// Two flags for error checking
			boolean flag_chartest = true;
			boolean flag_inttest = true;
			int sum = 0;
			while (string.hasNext()) {
				String s = string.next();
				// Check if the input is character
				for (int i = 0; i < s.length(); i++) {
					if (Character.isLetter(s.charAt(i))) {
						flag_chartest = false;
						break;
					}
				}
				if (!flag_chartest) {
					break;
				} else {
					// Check if the value of single piles is less than 0 or greater than 45
					if (Integer.parseInt(s) < 1 || Integer.parseInt(s) > SolitaireBoard.CARD_TOTAL) {
						flag_inttest = false;
						break;
					}
					temp.add(Integer.parseInt(s));
					// Check the total number of cards is 45
					sum += Integer.parseInt(s);
				}
			}		
			// If all the requirement is followed, then we return. Otherwise, we print error message
			if (sum == SolitaireBoard.CARD_TOTAL && flag_chartest && flag_inttest) {
				piles = temp;
				break;
			} else {
				System.out.println(
						"ERROR: Each pile must have at least one card and the total number of cards must be 45\n"
								+ "Please enter a space-separated list of positive integers followed by newline:");
			}
		}
		return piles;
	}
}
