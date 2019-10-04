// Name:Jiazhi Li
// USC NetID: jiazhil
// USC ID : 5715388761
// CSCI 455 PA5
// Spring 2019

/*
 * grades.cpp
 * A program to test the Table class.
 * How to run it:
 *      grades [hashSize]
 *
 * the optional argument hashSize is the size of hash table to use.
 * if it's not given, the program uses default size (Table::HASH_SIZE)
 *
 */

#include "Table.h"

 // cstdlib needed for call to atoi
#include <cstdlib>

using namespace std;

void printCmdSummary() {
	cout << "insert name score" << endl;
	cout << "      Insert this name and score in the grade table.If this name was already present, print a message to that effect, and don't do the insert." << endl;
	cout << "change name newscore" << endl;
	cout << "      Change the score for name.Print an appropriate message if this name isn't present." << endl;
	cout << "lookup name" << endl;
	cout << "      Lookup the name, and print out his or her score, or a message indicating that student is not in the table." << endl;
	cout << "remove name" << endl;
	cout << "      Remove this student.If this student wasn't in the grade table, print a message to that effect." << endl;
	cout << "print" << endl;
	cout << "      Prints out all names and scores in the table." << endl;
	cout << "size" << endl;
	cout << "      Prints out the number of entries in the table." << endl;
	cout << "stats" << endl;
	cout << "      Prints out statistics about the hash table at this point. (Calls hashStats() method)" << endl;
	cout << "help" << endl;
	cout << "      Prints out a brief command summary." << endl;
	cout << "quit" << endl;
	cout << "      Exits the program." << endl;	
}

int main(int argc, char * argv[]) {

	// gets the hash table size from the command line

	int hashSize = Table::HASH_SIZE;

	Table * grades;  // Table is dynamically allocated below, so we can call
	// different constructors depending on input from the user.

	if (argc > 1) {
		hashSize = atoi(argv[1]);  // atoi converts c-string to int

		if (hashSize < 1) {
			cout << "Command line argument (hashSize) must be a positive number"
				<< endl;
			return 1;
		}

		grades = new Table(hashSize);

	}
	else {   // no command line args given -- use default table size
		grades = new Table();
	}

	grades->hashStats(cout);

	// add more code here
	// Reminder: use -> when calling Table methods, since grades is type Table*


	bool keepgoing = true;
	std::string command;
	std::string name = "";
	int score = 0;
	do {
		cout << "cmd>";
		//cin >> command >> name >> score;
		cin >> command;

		if (cin.fail()) {
			cout << "ERROR: input stream failed." << endl;
			keepgoing = false;
		}
		else {
			if (command == "insert") {
				//cout << "Please input in this format (Name Score): ";
				cin >> name >> score;
				if (!grades->insert(name, score)) {
					cout << "The name was already present!" << endl;
				}
			}
			else if (command == "change") {
				//cout << "Please input in this format (Name Newscore): ";
				cin >> name >> score;
				if (grades->lookup(name) == NULL) {
					cout << "This name isn't present!" << endl;
				}
				else *(grades->lookup(name)) = score;
			}
			else if (command == "lookup") {
				//cout << "Please input name: ";
				cin >> name;
				if (grades->lookup(name) == NULL) {
					cout << "This name isn't present!" << endl;
				}
				else cout << "The score is " << *(grades->lookup(name)) << endl;
			}
			else if (command == "remove") {
				//cout << "Please input name: ";
				cin >> name;
				if (!grades->remove(name)) {
					cout << "This student wasn't in the grade table!" << endl;
				}
			}
			else if (command == "print") {
				grades->printAll();
			}
			else if (command == "size") {
				cout << "number of entries: " << grades->numEntries() << endl;
			}
			else if (command == "stats") {
				grades->hashStats(cout);
			}
			else if (command == "help") {
				printCmdSummary();
			}
			else if (command == "quit") {
				keepgoing = false;
			}
			else {
				cout << "ERROR: invalid command" << endl;
				printCmdSummary();
			}
		}
	} while (keepgoing);
	
	return 0;
}
