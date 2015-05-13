package bingo.card.column;

import bingo.card.Letter;


public interface ColumnFactory {

	Column createColumn(Letter letter);
	
}
