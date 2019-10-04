// Name:Jiazhi Li
// USC NetID: jiazhil
// USC ID : 5715388761
// CSCI 455 PA5
// Spring 2019

// pa5list.cpp
// a program to test the linked list code necessary for a hash table chain

// You are not required to submit this program for pa5.

// We gave you this starter file for it so you don't have to figure
// out the #include stuff.  The code that's being tested will be in
// listFuncs.cpp, which uses the header file listFuncs.h

// The pa5 Makefile includes a rule that compiles these two modules
// into one executable.

#include <iostream>
#include <string>
#include <cassert>

using namespace std;

#include "listFuncs.h"



int main() {

	cout << "step 1" << endl;
	ListType test = new Node("Sam", 100);
	listAdd(test, "Jiazhi", 100);
	listPrint(test);
	cout << endl;

	cout << "step 2" << endl;
	listAdd(test, "Sam", 200);
	listPrint(test);
	cout << endl;
	cout << "step 3" << endl;
	listAdd(test, "Jiazhi", 300);
	listPrint(test);
	cout << endl;

	cout << "step 4" << endl;
	listRemove(test, "Sam");
	listPrint(test);
	cout << endl;

	cout << "step 5" << endl;
	listRemove(test, "Jiazhi");
	listPrint(test);
	cout << endl;

	listRemove(test, "hhh");



	cout << "The length of this linked list is " << length(test) << endl;


	return 0;
}
