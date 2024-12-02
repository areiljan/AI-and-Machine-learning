package main.java.ee.taltech.iti0210;

/**
 * Interface that you should implement for your solution.
 */
public interface QueenProblemSolver
{
	/**
	 * Solve the board for NQueens problem.
	 *
	 * @param strategy strategy to use to get initial board (also for restarts)
	 * @return solved NxN board where N = \a strategy.getSize().
	 *
	 * Board is considered "solved" for size N if the following are all true:
	 * * Board has precisely N queens
	 * * No two queens are placed at the same position
	 * * No queen can capture other queen
	 * * No queens are placed outside the board
	 */
	public Board solve(InitialisationStrategy strategy);
};
