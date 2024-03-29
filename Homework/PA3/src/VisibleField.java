
//Name: Jiazhi Li
//USC NetID: jiazhil 
//USC NetID: 5715388761
//CSCI 455 PA3
//Spring 2019

/**
 * VisibleField class This is the data that's being displayed at any one point
 * in the game (i.e., visible field, because it's what the user can see about
 * the minefield), Client can call getStatus(row, col) for any square. It
 * actually has data about the whole current state of the game, including the
 * underlying mine field (getMineField()). Other accessors related to game
 * status: numMinesLeft(), isGameOver(). It also has mutators related to actions
 * the player could do (resetGameDisplay(), cycleGuess(), uncover()), and
 * changes the game state accordingly.
 * 
 * It, along with the MineField (accessible in mineField instance variable),
 * forms the Model for the game application, whereas GameBoardPanel is the View
 * and Controller, in the MVC design pattern. It contains the MineField that
 * it's partially displaying. That MineField can be accessed (or modified) from
 * outside this class via the getMineField accessor.
 */
public class VisibleField {
	// ----------------------------------------------------------
	// The following public constants (plus numbers mentioned in comments below) are
	// the possible states of one
	// location (a "square") in the visible field (all are values that can be
	// returned by public method
	// getStatus(row, col)).

	// Covered states (all negative values):
	public static final int COVERED = -1; // initial value of all squares
	public static final int MINE_GUESS = -2;
	public static final int QUESTION = -3;

	// Uncovered states (all non-negative values):

	// values in the range [0,8] corresponds to number of mines adjacent to this
	// square

	public static final int MINE = 9; // this loc is a mine that hasn't been guessed already (end of losing game)
	public static final int INCORRECT_GUESS = 10; // is displayed a specific way at the end of losing game
	public static final int EXPLODED_MINE = 11; // the one you uncovered by mistake (that caused you to lose)

	// ----------------------------------------------------------

	// <put instance variables here>
	private static int[][] VISIBLEFIELD;
	private static int ROWS;
	private static int COLUMNS;
	private static int NUMOFMINES; // Total number of mines
	private static MineField MINES;	

	// Variables to record the value of necessary parameters
	private static int NUMOFLEFT; // Number of left mines
	private static int NUMOFUNCOVER; // Number of uncovered square
	
	// Flag for failure of success
	private static boolean isFailure;
	private static boolean isSuccess;

	/**
	 * Create a visible field that has the given underlying mineField. The initial
	 * state will have all the mines covered up, no mines guessed, and the game not
	 * over.
	 * 
	 * @param mineField the minefield to use for for this VisibleField
	 */
	public VisibleField(MineField mineField) {
		MINES = mineField;
		ROWS = MINES.numRows();
		COLUMNS = MINES.numCols();
		NUMOFLEFT = MINES.numMines();
		NUMOFMINES = MINES.numMines();
		NUMOFUNCOVER = 0;
		isFailure = false;
		isSuccess = false;
		VISIBLEFIELD = new int[ROWS][COLUMNS];
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				VISIBLEFIELD[i][j] = COVERED;
			}
		}
	}

	/**
	 * Reset the object to its initial state (see constructor comments), using the
	 * same underlying MineField.
	 */
	public void resetGameDisplay() {
		ROWS = MINES.numRows();
		COLUMNS = MINES.numCols();
		NUMOFLEFT = MINES.numMines();
		NUMOFMINES = MINES.numMines();
		NUMOFUNCOVER = 0;
		isFailure = false;
		isSuccess = false;
		for (int i = 0; i < ROWS; i++) {
			for (int j = 0; j < COLUMNS; j++) {
				VISIBLEFIELD[i][j] = COVERED;
			}
		}
	}

	/**
	 * Returns a reference to the mineField that this VisibleField "covers"
	 * 
	 * @return the minefield
	 */
	public MineField getMineField() {
		return MINES;
	}

	/**
	 * Returns the visible status of the square indicated.
	 * 
	 * @param row row of the square
	 * @param col col of the square
	 * @return the status of the square at location (row, col). See the public
	 *         constants at the beginning of the class for the possible values that
	 *         may be returned, and their meanings. PRE: getMineField().inRange(row,
	 *         col)
	 */
	public int getStatus(int row, int col) {
		assert getMineField().inRange(row, col);
		return VISIBLEFIELD[row][col];
	}

	/**
	 * Returns the the number of mines left to guess. This has nothing to do with
	 * whether the mines guessed are correct or not. Just gives the user an
	 * indication of how many more mines the user might want to guess. So the value
	 * can be negative, if they have guessed more than the number of mines in the
	 * minefield.
	 * 
	 * @return the number of mines left to guess.
	 */
	public int numMinesLeft() {
		return NUMOFLEFT;
	}

	/**
	 * Cycles through covered states for a square, updating number of guesses as
	 * necessary. Call on a COVERED square changes its status to MINE_GUESS; call on
	 * a MINE_GUESS square changes it to QUESTION; call on a QUESTION square changes
	 * it to COVERED again; call on an uncovered square has no effect.
	 * 
	 * @param row row of the square
	 * @param col col of the square PRE: getMineField().inRange(row, col)
	 */
	public void cycleGuess(int row, int col) {
		assert getMineField().inRange(row, col);
		if (VISIBLEFIELD[row][col] == COVERED) {
			VISIBLEFIELD[row][col] = MINE_GUESS;
			NUMOFLEFT--;
		} else if (VISIBLEFIELD[row][col] == MINE_GUESS) {
			VISIBLEFIELD[row][col] = QUESTION;
			NUMOFLEFT++;
		} else if (VISIBLEFIELD[row][col] == QUESTION) {
			VISIBLEFIELD[row][col] = COVERED;
		}
	}

	/**
	 * Uncovers this square and returns false iff you uncover a mine here. If the
	 * square wasn't a mine or adjacent to a mine it also uncovers all the squares
	 * in the neighboring area that are also not next to any mines, possibly
	 * uncovering a large region. Any mine-adjacent squares you reach will also be
	 * uncovered, and form (possibly along with parts of the edge of the whole
	 * field) the boundary of this region. Does not uncover, or keep searching
	 * through, squares that have the status MINE_GUESS. Note: this action may cause
	 * the game to end: either in a win (opened all the non-mine squares) or a loss
	 * (opened a mine).
	 * 
	 * @param row of the square
	 * @param col of the square
	 * @return false iff you uncover a mine at (row, col) PRE:
	 *         getMineField().inRange(row, col)
	 */
	public boolean uncover(int row, int col) {
		assert getMineField().inRange(row, col);
		if (MINES.hasMine(row, col)) {
			Label_Failure();
			VISIBLEFIELD[row][col] = EXPLODED_MINE;
			isFailure = true;
			return false;
		}
		NUMOFUNCOVER += OPENAREA(row, col);
		if(NUMOFUNCOVER + NUMOFMINES == ROWS * COLUMNS) {
			Label_Success();
			isSuccess = true;
		}
		return true;
	}

	/**
	 * Returns whether the game is over. (Note: This is not a mutator.)
	 * 
	 * @return whether game over
	 */
	public boolean isGameOver() {
		if (isFailure || isSuccess) {
			return true;
		} else
			return false;
	}

	/**
	 * Returns whether this square has been uncovered. (i.e., is in any one of the
	 * uncovered states, vs. any one of the covered states).
	 * 
	 * @param row of the square
	 * @param col of the square
	 * @return whether the square is uncovered PRE: getMineField().inRange(row, col)
	 */
	public boolean isUncovered(int row, int col) {
		assert getMineField().inRange(row, col);
		if (VISIBLEFIELD[row][col] < 0) {
			return false;
		}
		return true;
	}
	
	// Print for testing
		public void tostring() {
			for (int i = 0; i < ROWS; i++) {
				for (int j = 0; j < COLUMNS; j++) {
					System.out.print(VISIBLEFIELD[i][j]);
				}
				System.out.println();
			}
			System.out.println();
		}

	// <put private methods here>
	
	// Recursion to find the connected domain
	private int OPENAREA(int row, int col) {
		assert getMineField().inRange(row, col);
		// out of range: return
		if (row < 0 || row >= ROWS || col < 0 || col >= COLUMNS) {
			return 0;
		}
		// has mines surrounding: return
		if (MINES.numAdjacentMines(row, col) != 0 && VISIBLEFIELD[row][col] == COVERED) {
			VISIBLEFIELD[row][col] = MINES.numAdjacentMines(row, col);
			return 1;
		}
		if (MINES.numAdjacentMines(row, col) == 0 && VISIBLEFIELD[row][col] == COVERED) {
			VISIBLEFIELD[row][col] = 0;
			return OPENAREA(row + 1, col) + OPENAREA(row - 1, col) + OPENAREA(row, col + 1) + OPENAREA(row, col - 1) +
			OPENAREA(row + 1, col+1) + OPENAREA(row + 1, col-1) + OPENAREA(row - 1, col-1) + OPENAREA(row - 1, col+1)+ 1;
		}
		return 0;
	}


	// Failure : Label incorrect guesses with incorrect and hidden mines with mine  
	private void Label_Failure() {
		for(int i =0;i < ROWS;i++) {
			for(int j =0 ; j < COLUMNS;j++) {
				if(!MINES.hasMine(i, j) && VISIBLEFIELD[i][j] == MINE_GUESS) {
					VISIBLEFIELD[i][j] = INCORRECT_GUESS;
				}
				else if(MINES.hasMine(i, j) && VISIBLEFIELD[i][j] != MINE_GUESS) {
					VISIBLEFIELD[i][j] = MINE;
				}
			}
		}
	}
	
	// Success : Label hidden mines with guess  
		private void Label_Success() {
			for(int i =0;i < ROWS;i++) {
				for(int j =0 ; j < COLUMNS;j++) {
					if(MINES.hasMine(i, j)) {
						VISIBLEFIELD[i][j] = MINE_GUESS;
					}					
				}
			}
		}	
}
