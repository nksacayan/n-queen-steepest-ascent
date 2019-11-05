import java.util.Random;

// Index is column
// Value is row
// Always an 8x8 board
// 7 x x x x x x x x
// 6 x x x x x x x x
// 5 x x x x x x x x
// 4 x x x x x x x x
// 3 x x x x x x x x
// 2 x x x x x x x x
// 1 x x x x x x x x
// 0 x x x x x x x x
// X 0 1 2 3 4 5 6 7

public class Board {
	int[] state = new int[8];

	public Board() {
		generateBoard();
	}
	
	public Board(Board board, int col, int newRow) {
		for (int i = 0; i < state.length; i++)
			state[i] = board.state[i];
		state[col] = newRow;
	}
	
	void generateBoard() {
		Random rand = new Random();
		int[] newBoard = new int[8];
		for (int i = 0; i < newBoard.length; i++)
			newBoard[i] = rand.nextInt(8);
		this.state = newBoard;
	}

	int getH() {
		int h = 0;
		for (int i = 0; i < state.length; i++) { // index of current
			for (int j = i + 1; j < state.length; j++) { // index of next
				if (state[i] == state[j]) // Queens in same row
					h++;
				int offset = j - i;
				// Check diagonally
				if (state[i] == state[j] - offset || state[i] == state[j] + offset)
					h++;
			}
		}
		return h;
	}
	
	void printArray() {
		for (int i = 0; i < state.length; i++)
			System.out.print(state[i] + " ");
	}
	
	void printBoard() {
		for (int row = 0; row < state.length; row++) {
			for (int col = 0; col < state.length; col++) {
				if (state[col] == row)
					System.out.print("Q ");
				else
					System.out.print("- ");
			}
			System.out.println();
		}
	}

}
