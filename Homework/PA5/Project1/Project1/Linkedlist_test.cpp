#include "listFuncs.h"

void main() {
	ListType test = new Node("Sam",100);
	listAdd(test, "Jiazhi", 100);

	listAdd(test, "Sam", 200);
	listAdd(test, "Jiazhi",300);

	listRemove(test, "Sam");
	listRemove(test, "Jiazhi");
	listRemove(test, "hhh");

	int s = 100;
}