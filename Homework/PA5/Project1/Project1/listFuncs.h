// Name:Jiazhi Li
// USC NetID: jiazhil
// USC ID : 5715388761
// CSCI 455 PA5
// Spring 2019


//*************************************************************************
// Node class definition 
// and declarations for functions on ListType

// Note: we don't need Node in Table.h
// because it's used by the Table class; not by any Table client code.

// Note2: it's good practice to not put "using" statement in *header* files.  Thus
// here, things from std libary appear as, for example, std::string

#ifndef LIST_FUNCS_H
#define LIST_FUNCS_H

#include <string>

struct Node {
   std::string key;
   int value;

   Node *next;

   Node(const std::string &theKey, int theValue);

   Node(const std::string &theKey, int theValue, Node *n);
};


typedef Node * ListType;

//*************************************************************************
//add function headers (aka, function prototypes) for your functions
//that operate on a list here (i.e., each includes a parameter of type
//ListType or ListType&).  No function definitions go in this file.

// Remove one single element based on key
bool listRemove(ListType & list, std::string target);
// Add one single element given key and value
bool listAdd(ListType & list, std::string key, int value);
// Look up a element based on key and we can modify the value using return pointer
int * listLookup(ListType & list, std::string target);
// Go through the node one by one and print each other in order
void listPrint(ListType list);
// Get the length of the linked list
int length(ListType list);

// keep the following line at the end of the file
#endif
