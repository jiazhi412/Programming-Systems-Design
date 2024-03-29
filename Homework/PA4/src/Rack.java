
//Name: Jiazhi Li
//USC NetID: jiazhil 
//USC NetID: 5715388761
//CSCI 455 PA4
//Spring 2019

import java.util.ArrayList;

/**
 * A Rack of Scrabble tiles
 * 
 * Inside "class Rack", the "class Word" is used to represent the string of the
 * current rack.
 */

public class Rack {

	// The instance variable in "class Word" to store the string of the current
	// rack:
	private static Word WORD_MULTISET;

	// Constructor for rack
	Rack(String s) {
		WORD_MULTISET = new Word(s);
	}

	/**
	 * Finds all subsets of the multiset of the current rack. unique and mult
	 * describe a multiset such that mult[i] is the multiplicity of the char
	 * unique.charAt(i).
	 *
	 * @return all subsets of the indicated multiset.
	 */
	public ArrayList<String> getAllSubsets() {
		int k = 0; // The smallest index of unique and mult to consider
		return allSubsets(WORD_MULTISET.getUnique(), WORD_MULTISET.getMult(), k);
	}

	/**
	 * Finds all subsets of the multiset starting at position k in unique and mult.
	 * unique and mult describe a multiset such that mult[i] is the multiplicity of
	 * the char unique.charAt(i). PRE: mult.length must be at least as big as
	 * unique.length() 0 <= k <= unique.length()
	 * 
	 * @param unique a string of unique letters
	 * @param mult   the multiplicity of each letter from unique.
	 * @param k      the smallest index of unique and mult to consider.
	 * @return all subsets of the indicated multiset. Unlike the multiset in the
	 *         parameters, each subset is represented as a String that can have
	 *         repeated characters in it.
	 * @author Claire Bono
	 */
	private static ArrayList<String> allSubsets(String unique, int[] mult, int k) {
		ArrayList<String> allCombos = new ArrayList<>();

		if (k == unique.length()) { // multiset is empty
			allCombos.add("");
			return allCombos;
		}

		// get all subsets of the multiset without the first unique char
		ArrayList<String> restCombos = allSubsets(unique, mult, k + 1);

		// prepend all possible numbers of the first char (i.e., the one at position k)
		// to the front of each string in restCombos. Suppose that char is 'a'...

		String firstPart = ""; // in outer loop firstPart takes on the values: "", "a", "aa", ...
		for (int n = 0; n <= mult[k]; n++) {
			for (int i = 0; i < restCombos.size(); i++) { // for each of the subsets
															// we found in the recursive call
				// create and add a new string with n 'a's in front of that subset
				allCombos.add(firstPart + restCombos.get(i));
			}
			firstPart += unique.charAt(k); // append another instance of 'a' to the first part
		}

		return allCombos;
	}

}
