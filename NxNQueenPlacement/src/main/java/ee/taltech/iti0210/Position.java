package main.java.ee.taltech.iti0210;

public class Position
{
	public Position(Position p)
	{
		this.row = p.getRow();
		this.col = p.getColumn();
	}

	public Position(int row, int column)
	{
		this.row = row;
		this.col = column;
	}

	public int getRow()
	{
		return this.row;
	}

	public int getColumn()
	{
		return this.col;
	}

	public void setRow(int row)
	{
		this.row = row;
	}

	public void setColumn(int column)
	{
		this.col = column;
	}

	public void incrementColumn()
	{
		++this.col;
	}

	public void incrementRow()
	{
		++this.row;
	}

	public void decrementColumn()
	{
		--this.col;
	}

	public void decrementRow()
	{
		--this.row;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (!(obj instanceof Position))
		{
			return super.equals(obj);
		}

		Position other = (Position) obj;

		return
			this.getRow() == other.getRow()
			&& this.getColumn() == other.getColumn()
			;
	}

	/**
	 * This is extra override needed to make this class work correctly with standard containers like HashSet.
	 *
	 * You do not need to understand this.
	 */
	@Override
	public int hashCode()
	{
		int hash = 17;
		hash = 31 * hash + Integer.valueOf(this.getRow()).hashCode();
		hash = 31 * hash + Integer.valueOf(this.getColumn()).hashCode();
		return hash;
	}


	@Override
	public String toString()
	{
		return new StringBuilder()
			.append("(")
			.append(this.getRow())
			.append(", ")
			.append(this.getColumn())
			.append(")")
			.toString()
		;
	}


	private int row;
	private int col;
}
