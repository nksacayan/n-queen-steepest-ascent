import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class SteepestAscent {

	public static void main(String[] args) throws IOException {
		FileWriter timeWriter = new FileWriter("runtime.txt");
		FileWriter costWriter = new FileWriter("searchCost.txt");
		int numSolutions = 0;
		int searchCost = 0;
		
		for (int i = 0; i < 200; i++) {
			long startTime = System.nanoTime();
			Board current = new Board();
			int bestH = Integer.MAX_VALUE;
			ArrayList<Board> actions = new ArrayList<Board>();
			ArrayList<Board> bestActions = new ArrayList<Board>();
			System.out.println("Initial board: ");
			current.printBoard();
			System.out.println();

			while (bestH != 0) {

				bestActions.clear();
				actions.clear();
				// Generate all actions (skip current state)
				for (int col = 0; col < 8; col++) // 56 actions
					for (int row = 0; row < 8; row++)
						if (current.state[col] != row) {
							actions.add(new Board(current, col, row));
							searchCost++;
						}
				// Put actions with lowest H in bestActions
				for (Board board : actions) {
					if (board.getH() < bestH)
						bestActions.add(board);
				}

				// Find best action
				Collections.sort(bestActions, new Comparator<Board>() {
					public int compare(Board b1, Board b2) {
						return b1.getH() - b2.getH();
					}
				});

				// Break if no better actions
				if (bestActions.isEmpty())
					break;
				else // Update bestH
				{
					current = bestActions.get(0);
					bestH = current.getH();
				}
			}
			costWriter.write(searchCost + "\n");
			searchCost = 0;

			long endTime = System.nanoTime();
			long duration = (endTime - startTime); // divide by 1000000 to get milliseconds.
			if (bestH == 0) {
				System.out.println("Solution found");
				numSolutions++;
			} else
				System.out.println("Solution not found");

			current.printBoard();

			timeWriter.write(duration + "\n");
		}
		System.out.println(numSolutions + " successes");
		timeWriter.close();
		costWriter.close();
	}
}
