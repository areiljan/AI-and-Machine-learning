package main.java.ee.taltech.iti0210;

import java.util.*;

/**
 * Helper class to verify if the solution is correct.
 */
public class SolutionVerifier
{
	public static boolean verifySolution(Set<Position> positions, int size)
	{
		return
			eachAtOwnRowWithinBoard(positions, size)
			&& eachAtOwnColumnWithinBoard(positions, size)
			&& atMostOneAtEveryDiagonal(positions, size)
			&& atMostOneAtEveryAntiDiagonal(positions, size)
			;
	};

	//public static void visualiseSolution(Set<Position> positions)
	//{
	//	System.out.println(new Board(new StaticInitialisationStrategy(positions)));
	// }

	private static boolean eachAtOwnColumnWithinBoard(Set<Position> positions, int size)
	{
		Set<Integer> used = new HashSet<>();
		for (Position p: positions)
		{
			int col = p.getColumn();
			if (col < 0 || col >= size)
			{
				return false;
			}
			used.add(col);
		}
		return used.size() == size;
	}

	private static boolean eachAtOwnRowWithinBoard(Set<Position> positions, int size)
	{
		Set<Integer> used = new HashSet<>();
		for (Position p: positions)
		{
			int row = p.getRow();
			if (row < 0 || row >= size)
			{
				return false;
			}
			used.add(row);
		}
		return used.size() == size;
	}

	private static boolean atMostOneAtEveryDiagonal(Set<Position> positions, int size)
	{
		Set<Position> startingPositions = new HashSet<>();
		for (int i = 0; i < size; ++i)
		{
			startingPositions.add(new Position(0, i));
			startingPositions.add(new Position(i, 0));
		}

		for (Position cur: startingPositions)
		{
			int queensSeen = 0;
			while (Board.positionWithinBoardOfSize(cur, size))
			{
				if (positions.contains(cur))
				{
					++queensSeen;
				}
				cur.incrementRow();
				cur.incrementColumn();
			}
			if (queensSeen > 1)
			{
				return false;
			}
		}
		return true;
	}

	private static boolean atMostOneAtEveryAntiDiagonal(Set<Position> positions, int size)
	{
		Set<Position> startingPositions = new HashSet<>();
		for (int i = 0; i < size; ++i)
		{
			startingPositions.add(new Position(i, 0));
			startingPositions.add(new Position(size-1, i));
		}

		for (Position cur: startingPositions)
		{
			int queensSeen = 0;
			while (Board.positionWithinBoardOfSize(cur, size))
			{
				if (positions.contains(cur))
				{
					++queensSeen;
				}
				cur.decrementRow();
				cur.incrementColumn();
			}
			if (queensSeen > 1)
			{
				return false;
			}
		}
		return true;
	}
}
