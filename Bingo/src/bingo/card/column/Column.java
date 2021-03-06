package bingo.card.column;

import bingo.card.space.Space;


public interface Column {

	boolean isCovered(int row);
	
	/**
	 * Cover the provided value in the column if it exists.
	 * @param value the number to cover
	 * @return true if the number exists in the column and was covered
	 */
	boolean cover(int value);
	
	Space getSpace(int row);
}
