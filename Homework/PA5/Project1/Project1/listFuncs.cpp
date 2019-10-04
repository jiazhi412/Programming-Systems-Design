// Name:Jiazhi Li
// USC NetID: jiazhil
// USC ID : 5715388761
// CSCI 455 PA5
// Spring 2019


#include <iostream>

#include <cassert>

#include "listFuncs.h"

using namespace std;

Node::Node(const string &theKey, int theValue) {
   key = theKey;
   value = theValue;
   next = NULL;
}

Node::Node(const string &theKey, int theValue, Node *n) {
   key = theKey;
   value = theValue;
   next = n;
}




//*************************************************************************
// put the function definitions for your list functions below

// Remove one single element based on key
bool listRemove(ListType & list, std::string target) {
	ListType p = list;
	// empty bucket
	if (p == NULL) {
		return false;
	}
	// head is the target
	if (p->key == target) {
		list = list->next;
		return true;
	}
	// head is not the target
	while (p->next != NULL) {
		if (p->next->key == target) {
			p->next = p->next->next;
			return true;
		}
		p = p->next;
	}
	// target not found
	return false;
}

// Add one single element given key and value
bool listAdd(ListType & list, std::string key, int value) {
	ListType p = list;
	// empty bucket - insert directly
	if (p == NULL) {
		list = new Node(key, value);
		return true;
	}
	// head is insert key - no change
	if (p->key == key) {	
		return false;
	}
	// head is not the insert key but there is key somewhere - no change
	while (p->next != NULL) {
		if (p->next->key == key) {		
			return false;
		}
		p = p->next;
	}
	// Otherwise - insert
	ListType element = new Node(key,value);
	p->next = element;
	return true;
}

// Look up a element based on key and we can modify the value using return pointer
int * listLookup(ListType & list, std::string target) {
	ListType p = list;
	// empty bucket - insert directly
	if (p == NULL) {
		return NULL;
	}
	// head is target
	if (p->key == target) {
		return &(p->value);
	}
	// head is not the target
	while (p->next != NULL) {
		if (p->next->key == target) {			
			return &(p->next->value);
		}
		p = p->next;
	}
	// target not found
	return NULL;
}

// Go through the node one by one and print each other in order
void listPrint(ListType list) {
	ListType p = list;
	while (p != NULL) {
		cout << p->key << " " << p->value << endl;
		p = p->next;
	}
}

// Get the length of the linked list
int length(ListType list) {
	ListType p = list;
	int res = 0;
	while (p != NULL) {
		res++;
		p = p->next;
	}
	return res;
}