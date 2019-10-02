
//Name: Jiazhi Li
//USC NetID: jiazhil 
//USC NetID: 5715388761
//CSCI 455 PA4
//Spring 2019

import java.util.HashMap;

/**
 * A scoretable has information about Scrabble scores for scrabble letters and
 * words. Here are all the letter values: 
 * (1 point)-A, E, I, O, U, L, N, S, T, R
 * (2 points)-D, G 
 * (3 points)-B, C, M, P 
 * (4 points)-F, H, V, W, Y 
 * (5 points)-K
 * (8 points)- J, X 
 * (10 points)-Q, Z
 */

public class ScoreTable {

	// The instance variable in HashMap to store the score for each letter:
	private static HashMap<Character, Integer> TABLE;

	// key represents the character from A to Z
	// value represents the score of each character corresponding to character
	ScoreTable() {
		TABLE = new HashMap<Character, Integer>();
		// Score 1
		TABLE.put('A', 1);
		TABLE.put('E', 1);
		TABLE.put('I', 1);
		TABLE.put('O', 1);
		TABLE.put('U', 1);
		TABLE.put('L', 1);
		TABLE.put('N', 1);
		TABLE.put('S', 1);
		TABLE.put('T', 1);
		TABLE.put('R', 1);
		// Score 2
		TABLE.put('D', 2);
		TABLE.put('G', 2);
		// Score 3
		TABLE.put('B', 3);
		TABLE.put('C', 3);
		TABLE.put('M', 3);
		TABLE.put('P', 3);
		// Score 4
		TABLE.put('F', 4);
		TABLE.put('H', 4);
		TABLE.put('V', 4);
		TABLE.put('W', 4);
		TABLE.put('Y', 4);
		// Score 5
		TABLE.put('K', 5);
		// Score 8
		TABLE.put('J', 8);
		TABLE.put('X', 8);
		// Score 10
		TABLE.put('Q', 10);
		TABLE.put('Z', 10);
	}

	/**
	 * Calculate the score for the input String s according to ScoreTable
	 * 
	 * @param s a string to calculate score
	 * @return Score of the input String s
	 */
	public int getScore(String s) {
		Word w = new Word(s);
		String Unique = w.getUnique();
		String unique_uppercase = Unique.toUpperCase();
		int[] Mult = w.getMult();
		int score = 0;
		for (int i = 0; i < unique_uppercase.length(); i++) {
			score += TABLE.get(unique_uppercase.charAt(i)) * Mult[i];
		}
		return score;
	}

}
