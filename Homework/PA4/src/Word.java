
//Name: Jiazhi Li
//USC NetID: jiazhil 
//USC NetID: 5715388761
//CSCI 455 PA4
//Spring 2019

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * This is a class designed to store the letters of one word. Inside the class,
 * HashMap is used to store the number for each letter for a word. By this way,
 * this class implements kind of functions of MultiSet by access the number for
 * one single letter.
 */

public class Word {

	private String DATA_ORI; // the origin word in "string"
	private String UNIQUE; // a string of unique letters
	private int[] MULT; // the multiplicity of each letter from unique

	// The instance variable in HashMap to store the number for each letter
	// key represents the letter from A to Z
	// value represents the number of each letter
	private HashMap<Character, Integer> DATA;

	// Constructor for word
	Word(String word) {
		DATA_ORI = word;
		// Store the number for each letter into HashMap
		DATA = new HashMap<Character, Integer>();
		for (int i = 0; i < word.length(); i++) {
			Character letter = word.charAt(i);
			if (DATA.get(letter) == null) {
				DATA.put(letter, 1);
			} else {
				DATA.put(word.charAt(i), DATA.get(letter) + 1);
			}
		}
		// Get UNIQUE and MULT
		Iterator<Map.Entry<Character, Integer>> iter = DATA.entrySet().iterator();
		MULT = new int[DATA.size()];
		UNIQUE = "";
		int index = 0;
		while (iter.hasNext()) {
			Map.Entry<Character, Integer> curr = iter.next();
			MULT[index] = curr.getValue();
			UNIQUE += curr.getKey().toString();
			index++;
		}
	}

	/**
	 * Compare two words
	 * 
	 * @param w the word to compare with
	 * @return if two word are equal, then return true. Otherwise, return false
	 */

	public boolean equals(Word w) {
		return this.DATA.equals(w.DATA);
	}

	// Accesser for UNIQUE
	public String getUnique() {
		return UNIQUE;
	}

	// Accesser for MULT
	public int[] getMult() {
		return MULT;
	}

	// Accesser for origin word
	public String toString() {
		return DATA_ORI;
	}

}
