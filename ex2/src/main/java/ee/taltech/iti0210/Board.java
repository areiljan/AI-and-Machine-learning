package main.java.ee.taltech.iti0210;

import java.lang.reflect.Array;
import java.util.*;

/**
 * Class to represent the playing board and provide simple operations on it.
 *
 * You will probably want to extend with operations you will find necessary.
 */
public class Board
{
	public Board(InitialisationStrategy strat)
	{
		this.size = strat.getSize();
		this.positions = new int[size];
	};

	public Board(Board b)
	{
		this.size = b.size;
		this.positions = new int[size];
	}

	private int size;
	private int[] positions;

	public int getSize()
	{
		return this.size;
	}

	public void clear() {
		positions = new int[size];
	}

	public void addPosition(int row, int col) {
		positions[row] = col;
	}

	public int[] getPositions() {
		return positions;
	}

	/**
	 * @return whether position is within a board of given size.
	 */
	public static boolean positionWithinBoardOfSize(int columnNumber, int size)
	{
		return
				size >= valPositiveWithinSize(columnNumber)
			;
	}

	private static int valPositiveWithinSize(int val)
	{
		return val >= 0 ? val : Integer.MAX_VALUE;
	}

	/**
	 * Creates a string representation of the board where (0,0) is bottom left.
	 */
	@Override
	public String toString()
	{
		List<List<Boolean>> arrayRepresentation = this.toList();

		StringBuilder sb = new StringBuilder();
		for (int idx = 1; idx <= this.getSize(); ++idx)
		{
			List<Boolean> row = arrayRepresentation.get(this.size - idx);
			for (Boolean col: row)
			{
				char c = '.';
				if (col)
				{
					c = 'Q';
				}
				sb.append(c);
			}
			sb.append("\n");
		}
		return sb.toString();
	}

	/**
	 * Creates a list representation of the board to ease printing the board out.
	 */
	private List<List<Boolean>> toList()
	{
		ArrayList<List<Boolean>> arrayRepresentation = new ArrayList<>();
		for (int col : this.positions) {
			ArrayList<Boolean> row = new ArrayList<>();
			for (int i = 0; i < size; i++) {
            	row.add(false);
        	}
			// set the place where the queen is to true
			row.set(col, true);
			arrayRepresentation.add(row);
		}
		return arrayRepresentation;
	}
}
