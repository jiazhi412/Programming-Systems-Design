// Name:Jiazhi Li
// USC NetID: jiazhil
// USC ID : 5715388761
// CSCI 455 PA6
// Spring 2019
  
   //See ecListFuncs.h for specification of each function.
 


// for NULL
#include <cstdlib>
#include <stack>
#include "ecListFuncs.h"

using namespace std;


int lastIndexOf(ListType list, int val) {
	ListType p = list;
	int res = -1;
	int count = 0;
	while (p != NULL) {
		if (p->data == val) {
			res = count;
		}
		p = p->next;
		count++;
	}
   return res;  // stub code to get it to compile
}


int longestNondecreasingSequence(ListType list) {
	ListType p = list;
	if (p == NULL) {
		return 0;
	}
	int res = 1;
	int previous = p->data;
	p = p->next;
	int count = 1;
	while (p != NULL) {
		if (previous <= p->data) {
			count++;
			if (count > res) {
				res = count;
			}
		}
		else {
			count = 1;
		}
		previous = p->data;
		p = p->next;
	}
	return res;
}


void removeAdjacentEvens(ListType & list) {
	ListType p = list;
	while (p != NULL) {
		if (p->data % 2 == 0) {
			ListType head = p;
			while (p != NULL && p->data % 2 == 0) {
				p = p->next;
			}
			head->next = p;
		}
		else {
			p = p->next;
		}
	}
}


void mirror(ListType & list) {
	stack<int> ori;
	ListType p = list;
	ListType previous = list;
	while (p != NULL) {
		ori.push(p->data);
		previous = p;
		p = p->next;
	}
	while (!ori.empty()) {
		previous->next = new Node(ori.top());
		ori.pop();
		previous = previous->next;
	}	
}


void rotateLeft(ListType & list, int k) {
	int length = 0;
	ListType p = list;
	while (p != NULL) {
		length++;
		p = p->next;
	}
	if (k < 0 || k >= length) {
		return;
	}
	ListType connected = list;
	ListType previous = list;
	int second = k;
	while (second != 0) {
		previous = list;
		list = list->next;
		second--;
	}
	previous->next = NULL;
	ListType p2 = list;
	while (p2->next != NULL) {
		p2 = p2->next;
	}
	p2->next = connected;
}
