
//Name: Jiazhi Li
//USC NetID: jiazhil 
//USC NetID: 5715388761
//CSCI 455 PA3
//Spring 2019

import java.util.Random;

/**
 * MineField class with locations of mines for a game. This class is mutable,
 * because we sometimes need to change it once it's created. mutators:
 * populateMineField, resetEmpty includes convenience method to tell the number
 * of mines adjacent to a location.
 */
public class MineField {

	// use 1 to represent that there is a mine and 0 to represent there is not a
	// mine
	private static final int MINE = 1;
	private static final int NOTMINE = 0;

	// Add the zero side for each edges of minedata and the sizes of minedata change
	// from ROW * COLUMN to (ROW+2) * (COLUMN+2)
	private final int ZEROPADDING_INDEX = 2;
	// If we want to access (x,y) outside, then we will get (x+1,y+1) in zeropadding
	// minedata
	private final int TRANSFORMATION_RATIO = 1;

	private int ROWS;
	private int COLUMNS;
	private int NUMOFMINES;
	private int[][] MINEDATA_ZEROPADDING;

	/**
	 * Create a minefield with same dimensions as the given array, and populate it
	 * with the mines in the array such that if mineData[row][col] is true, then
	 * hasMine(row,col) will be true and vice versa. numMines() for this minefield
	 * will corresponds to the number of 'true' values in mineData.
	 * 
	 * @param mineData the data for the mines; must have at least one row and one
	 *                 col.
	 */
	public MineField(boolean[][] mineData) {
		ROWS = mineData.length;
		COLUMNS = mineData[0].length;
		MINEDATA_ZEROPADDING = new int[ROWS + ZEROPADDING_INDEX][COLUMNS + ZEROPADDING_INDEX];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				if (mineData[i][j]) {
					MINEDATA_ZEROPADDING[Index_transformation(i)][Index_transformation(j)] = MINE;
					NUMOFMINES++;
				}
			}
		}
	}

	/**
	 * Create an empty minefield (i.e. no mines anywhere), that may later have
	 * numMines mines (once populateMineField is called on this object). Until
	 * populateMineField is called on such a MineField, numMines() will not
	 * correspond to the number of mines currently in the MineField.
	 * 
	 * @param numRows  number of rows this minefield will have, must be positive
	 * @param numCols  number of columns this minefield will have, must be positive
	 * @param numMines number of mines this minefield will have, once we populate
	 *                 it. PRE: numRows > 0 and numCols > 0 and 0 <= numMines < (1/3
	 *                 of total number of field locations).
	 */
	public MineField(int numRows, int numCols, int numMines) {
		assert numRows > 0 && numCols > 0 && numMines >= 0 && numMines < (double) numRows * numCols / 3;
		ROWS = numRows;
		COLUMNS = numCols;
		NUMOFMINES = numMines;
		MINEDATA_ZEROPADDING = new int[numRows + ZEROPADDING_INDEX][numCols + ZEROPADDING_INDEX];
	}

	/**
	 * Removes any current mines on the minefield, and puts numMines() mines in
	 * random locations on the minefield, ensuring that no mine is placed at (row,
	 * col).
	 * 
	 * @param row the row of the location to avoid placing a mine
	 * @param col the column of the location to avoid placing a mine PRE:
	 *            inRange(row, col)
	 */
	public void populateMineField(int row, int col) {
		assert inRange(row, col);
		MINEDATA_ZEROPADDING = new int[ROWS + ZEROPADDING_INDEX][COLUMNS + ZEROPADDING_INDEX];
		int row_inside = Index_transformation(row);
		int col_inside = Index_transformation(col);
		Random generator = new Random();
		int count = NUMOFMINES;
		while (count != 0) {
			int row_mine = Index_transformation(generator.nextInt(ROWS));
			int col_mine = Index_transformation(generator.nextInt(COLUMNS));
			if (row_mine != row_inside && col_mine != col_inside
					&& MINEDATA_ZEROPADDING[row_mine][col_mine] == NOTMINE) {
				MINEDATA_ZEROPADDING[row_mine][col_mine] = MINE;
				count--;
			}
		}
	}

	/**
	 * Reset the minefield to all empty squares. This does not affect numMines(),
	 * numRows() or numCols() Thus, after this call, the actual number of mines in
	 * the minefield does not match numMines(). Note: This is the state the
	 * minefield is in at the beginning of a game.
	 */
	public void resetEmpty() {
		MINEDATA_ZEROPADDING = new int[ROWS + ZEROPADDING_INDEX][COLUMNS + ZEROPADDING_INDEX];
	}

	/**
	 * Returns the number of mines adjacent to the specified mine location (not
	 * counting a possible mine at (row, col) itself). Diagonals are also considered
	 * adjacent, so the return value will be in the range [0,8]
	 * 
	 * @param row row of the location to check
	 * @param col column of the location to check
	 * @return the number of mines adjacent to the square at (row, col) PRE:
	 *         inRange(row, col)
	 */
	public int numAdjacentMines(int row, int col) {
		assert inRange(row, col);
		int row_inside = Index_transformation(row);
		int col_inside = Index_transformation(col);
		return MINEDATA_ZEROPADDING[row_inside - 1][col_inside] + MINEDATA_ZEROPADDING[row_inside + 1][col_inside]
				+ MINEDATA_ZEROPADDING[row_inside][col_inside - 1] + MINEDATA_ZEROPADDING[row_inside][col_inside + 1]
				+ MINEDATA_ZEROPADDING[row_inside + 1][col_inside + 1]
				+ MINEDATA_ZEROPADDING[row_inside + 1][col_inside - 1]
				+ MINEDATA_ZEROPADDING[row_inside - 1][col_inside + 1]
				+ MINEDATA_ZEROPADDING[row_inside - 1][col_inside - 1];
	}

	/**
	 * Returns true iff (row,col) is a valid field location. Row numbers and column
	 * numbers start from 0.
	 * 
	 * @param row row of the location to consider
	 * @param col column of the location to consider
	 * @return whether (row, col) is a valid field location
	 */
	public boolean inRange(int row, int col) {
		//assert inRange(row, col);
		if (row >= 0 && row < ROWS && col >= 0 && col < COLUMNS) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Returns the number of rows in the field.
	 * 
	 * @return number of rows in the field
	 */
	public int numRows() {
		return ROWS;
	}

	/**
	 * Returns the number of columns in the field.
	 * 
	 * @return number of columns in the field
	 */
	public int numCols() {
		return COLUMNS;
	}

	/**
	 * Returns whether there is a mine in this square
	 * 
	 * @param row row of the location to check
	 * @param col column of the location to check
	 * @return whether there is a mine in this square PRE: inRange(row, col)
	 */
	public boolean hasMine(int row, int col) {
		assert inRange(row, col);
		int row_inside = Index_transformation(row);
		int col_inside = Index_transformation(col);
		if (MINEDATA_ZEROPADDING[row_inside][col_inside] == MINE) {
			return true;
		}
		return false;
	}

	/**
	 * Returns the number of mines you can have in this minefield. For mines created
	 * with the 3-arg constructor, some of the time this value does not match the
	 * actual number of mines currently on the field. See doc for that constructor,
	 * resetEmpty, and populateMineField for more details.
	 * 
	 * @return
	 */
	public int numMines() {
		return NUMOFMINES;
	}

	// Print for testing
	public void tostring() {
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				System.out.print(MINEDATA_ZEROPADDING[Index_transformation(i)][Index_transformation(j)]);
			}
			System.out.println();
		}
		System.out.println();
	}

	// <put private methods here>

	// If we want to access (x,y) outside, then we will get (x+1,y+1) in zeropadding
	// minedata
	private final int Index_transformation(int index) {
		return index + TRANSFORMATION_RATIO;
	}
}
