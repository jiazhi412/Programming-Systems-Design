//Name: Jiazhi Li
//USC NetID: jiazhil 
//USC NetID: 5715388761
//CSCI 455 PA3
//Spring 2019

public class VisibleFieldTester {

	private static boolean[][] smallMineField = { { false, false, false, false }, { true, false, false, false },
			{ false, true, true, false }, { false, true, false, true } };

	private static boolean[][] emptyMineField = { { false, false, false, false }, { false, false, false, false },
			{ false, false, false, false }, { false, false, false, false } };

	private static boolean[][] almostEmptyMineField = { { false, false, false, false }, { false, false, false, false },
			{ false, false, false, false }, { false, true, false, false } };

	public static void main(String[] args) {
		int rows = 7;
		int cols = 7;
		int numofmines = 9;
		// MineField test = new MineField(rows,cols,numofmines);
		// test.populateMineField(5, 5);
		MineField Mine_test = new MineField(smallMineField);
		VisibleField visible_test = new VisibleField(Mine_test);
		Mine_test.tostring();
		visible_test.tostring();
		visible_test.uncover(0, 3);
		visible_test.tostring();
		visible_test.uncover(3, 0);
		visible_test.tostring();
	}
}
