
//Name: Jiazhi Li
//USC NetID: jiazhil 
//USC NetID: 5715388761
//CSCI 455 PA4
//Spring 2019

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

/**
 * This class have a main that's responsible for processing the command-line
 * argument, and handling any error processing. It will probably also have the
 * main command loop. Most of the other functionality will be delegated to other
 * object(s) created in main and their methods.
 */

public class WordFinder {

	private static Scanner sc; // Scanner for getting the input rack

	/**
	 * The program will run in a loop on the console, printing the prompt "Rack? "
	 * and reading and processing each rack you enter, until you tell it to exit by
	 * typing in "." at the prompt (i.e., a period).
	 * 
	 * @param args There will be an optional command-line argument for the
	 *             dictionary file name. If that argument is left off, it will use
	 *             the Scrabble dictionary file sowpods.txt from the same directory
	 *             as you are running your program. If the dictionary file specified
	 *             (either explicitly or the default one) does not exist, the
	 *             program will print an informative error message (that includes
	 *             the file name) and exit.
	 */
	public static void main(String[] args) {
		// Load Dictionary
		String FileName;
		if (args.length == 0) {
			// Default dictionary
			FileName = "G:\\455\\Homework4\\PA4\\bin\\testFiles\\sowpods.txt";
		} else {
			// Given dictionary
			FileName = args[0];
		}

		// Construct AnagramDictionary and error detection
		AnagramDictionary ADictionary;
		try {
			ADictionary = new AnagramDictionary(FileName);
		} catch (FileNotFoundException e) {
			System.out.println("File is not found! " + FileName);
			return;
		}

		// Starting to print the prompt
		System.out.println("Type . to quit.");
		System.out.print("Rack? ");
		sc = new Scanner(System.in);
		String Rack = sc.next();
		// Exit until typing in "." at the prompt
		while (!Rack.equals(".")) {
			// Print the Scrabble word according to the current rack and AnagramDictionary
			Print(Rack, ADictionary);
			System.out.print("Rack? ");
			Rack = sc.next();
		}
	}

	/**
	 * Find all subsets of the corresponding rack. And then, find all Anagrams for
	 * all subsets according to AnagramDictionary. After that, we sort all words in
	 * decreasing order by score. For words with the same scrabble score, they may
	 * appear in alphabetical order.
	 * 
	 * @param s           the current rack in "String"
	 * @param ADictionary the current AnagramDictionary as reference
	 */
	private static void Print(String s, AnagramDictionary ADictionary) {
		Rack r = new Rack(s);
		ArrayList<String> Subsets = r.getAllSubsets();
		ArrayList<Word> words = new ArrayList<Word>();
		for (int i = 0; i < Subsets.size(); i++) {
			ArrayList<String> Anagrams = ADictionary.getAnagramsOf(Subsets.get(i));
			for (int j = 0; j < Anagrams.size(); j++) {
				words.add(new Word(Anagrams.get(j)));
			}
		}

		// Sorting
		Collections.sort(words, new Comparator<Word>() {

			@Override
			public int compare(Word i, Word j) {
				ScoreTable table = new ScoreTable();
				String iString = i.toString();
				String jString = j.toString();
				// For words with the same scrabble score
				if (table.getScore(jString) - table.getScore(iString) == 0) {
					int trials = (jString.length() > iString.length()) ? iString.length() : jString.length();
					for (int t = 0; t < trials; t++) {
						if (iString.charAt(t) != jString.charAt(t)) {
							return iString.charAt(t) - jString.charAt(t);
						}
					}
					return 0;
				}
				// For words with the different scrabble score
				else {
					return table.getScore(j.toString()) - table.getScore(i.toString());
				}
			}
		});

		// Print out
		System.out.println("We can make " + words.size() + " words from \"" + s + "\"");
		if (words.size() > 0) {
			System.out.println("All of the words with their scores (sorted by score):");
		}
		for (int i = 0; i < words.size(); i++) {
			Word word = words.get(i);
			ScoreTable table = new ScoreTable();
			System.out.println(table.getScore(word.toString()) + ": " + word.toString());
		}
	}
}
