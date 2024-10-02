package main.java.ee.taltech.iti0210;

import java.security.KeyPair;
import java.util.Set;

/**
 * Base class for initialisation strategies, extend it with your own strategies.
 *
 * This class is used by QueenProblemSolver to generate a board with enough queens to start solving from.
 */
public abstract class InitialisationStrategy
{
	/**
	 * @return Set of @a size positions to place queens at.
	 *
	 * Positions' coordinates are guaranteed to be within [0, size).
	 * It is NOT necessary to return same positions on each call. (opposite may even be expected of some strategies)
	 */
	public abstract Set<Position> getQueenPositions();

	/**
	 * @param size size of the board and amount of queens to generate.
	 */
	public InitialisationStrategy(int size, int queenCount)
	{
		this.size = size;
		this.queenCount = queenCount;
	}

	public int getQueenCount()
	{
		return this.queenCount;
	}

	public int getSize()
	{
		return this.size;
	}

	private int size;

	private int queenCount;
}
