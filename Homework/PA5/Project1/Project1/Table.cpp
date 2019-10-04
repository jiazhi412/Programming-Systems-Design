// Name:Jiazhi Li
// USC NetID: jiazhil
// USC ID : 5715388761
// CSCI 455 PA5
// Spring 2019

// Table.cpp  Table class implementation


#include "Table.h"

#include <iostream>
#include <string>
#include <cassert>

using namespace std;


// listFuncs.h has the definition of Node and its methods.  -- when
// you complete it it will also have the function prototypes for your
// list functions.  With this #include, you can use Node type (and
// Node*, and ListType), and call those list functions from inside
// your Table methods, below.

#include "listFuncs.h"


//*************************************************************************

Table::Table() {
	hashSize = HASH_SIZE;
	data = new ListType[hashSize]();
	numofEntries = 0;
}

Table::Table(unsigned int hSize) {
	hashSize = hSize;
	data = new ListType[hashSize]();
	numofEntries = 0;
}

int * Table::lookup(const string &key) {
	return listLookup(data[hashCode(key)], key); 
}

bool Table::remove(const string &key) {
	if (listRemove(data[hashCode(key)], key)) {		
		numofEntries--;
		return true;
	}
	else return false; 
}

bool Table::insert(const string &key, int value) {
	if (listAdd(data[hashCode(key)], key, value)) {
		numofEntries++;
		return true;
	}
	else return false; 
}

int Table::numEntries() const {
	return 	numofEntries;
}

void Table::printAll() const {
	for (int i = 0; i < hashSize; i++) {
		listPrint(data[i]);
	}
}

void Table::hashStats(ostream &out) const {
	out << "number of buckets: " << hashSize << endl;
	out << "number of entries: " << numofEntries << endl;
	out << "number of non-empty buckets: " << numNonemptybucket() << endl;
	out << "longest chain: " << longestChain() << endl;
}

// add definitions for your private methods here

 // Count the number of non-empty bucket
int Table::numNonemptybucket() const{
	int res = 0;
	for (int i = 0; i < hashSize; i++) {
		if (data[i] != NULL)
			res++;
	}
	return res;
}

// Find and compare the longest chain
int Table::longestChain() const{
	int max = 0;
	for (int i = 0; i < hashSize; i++) {
		int l = length(data[i]);
		max = (max > l) ? max : l;
	}
	return max;
}