
//Name: Jiazhi Li
//USC NetID: jiazhil 
//USC NetID: 5715388761
//CSCI 455 PA4
//Spring 2019

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.File;
import java.util.Scanner;

/**
 * A dictionary of all anagram sets. Note: the processing is case-sensitive; so
 * if the dictionary has all lower case words, you will likely want any string
 * you test to have all lower case letters too, and likewise if the dictionary
 * words are all upper case.
 */

public class AnagramDictionary {

	// The instance variable to store all words in dictionary in "String":
	private ArrayList<String> DICTIONARY_ORI;
	// The instance variable to store all words in dictionary in "class Word":
	private ArrayList<Word> DICTIONARY_MULTISET;

	/**
	 * Create an anagram dictionary from the list of words given in the file
	 * indicated by fileName. PRE: The strings in the file are unique.
	 * 
	 * @param fileName the name of the file to read from
	 * @throws FileNotFoundException if the file is not found
	 */
	public AnagramDictionary(String fileName) throws FileNotFoundException {
		File inFile = new File(fileName);
		Scanner in = new Scanner(inFile);
		readValue(in);
	}

	/**
	 * Get all anagrams of the given string. This method is case-sensitive. E.g.
	 * "CARE" and "race" would not be recognized as anagrams.
	 * 
	 * @param s string to process
	 * @return a list of the anagrams of s
	 */
	public ArrayList<String> getAnagramsOf(String s) {
		Word w = new Word(s);
		ArrayList<String> Anagrams = new ArrayList<String>();
		// Find all the anagrams of one String in time linear
		for (int i = 0; i < DICTIONARY_MULTISET.size(); i++) {
			if (w.equals(DICTIONARY_MULTISET.get(i))) {
				Anagrams.add(DICTIONARY_ORI.get(i));
			}
		}
		return Anagrams;
	}

	/**
	 * Read all strings into DICTIONARY_ORI. Convert all words from "String" to
	 * "class word" and store them in DICTIONARY_MULTISET
	 * 
	 * @param in Scanner to scan all strings in file
	 */
	private void readValue(Scanner in) {
		DICTIONARY_ORI = new ArrayList<String>();
		DICTIONARY_MULTISET = new ArrayList<Word>();
		while (in.hasNext()) {
			String word = in.next();
			DICTIONARY_ORI.add(word);
			DICTIONARY_MULTISET.add(new Word(word));
		}
	}

}
