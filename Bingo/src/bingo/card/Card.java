package bingo.card;

import bingo.card.space.Space;


public interface Card {

	/**
	 * Cover the space for the provided value if it exists.
	 * @param value the number to cover
	 * @return true if a space with that number exists and it was covered
	 */
	boolean coverSpace(int value);
	
	/**
	 * Returns the space located in the column and row.
	 * @param letter the letter indicating the column
	 * @param row the row in the column
	 * @return the Space object located in that column and row
	 */
	Space getSpace(Letter letter, int row);
	
	/**
	 * Returns the number of rows in a column. A standard board has 5 rows.
	 * @return the number of rows in a column.
	 */
	int numRows();
	
}
