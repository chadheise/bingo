package bingo.card;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import bingo.card.column.Column;
import bingo.card.column.RandomColumnFactory;
import bingo.card.space.Space;

public class RandomCardFactory implements CardFactory {

	private static final int NUM_ROWS = 5;
	private final RandomColumnFactory columnFactory;
	
	public RandomCardFactory(Random random) {
		columnFactory = new RandomColumnFactory(NUM_ROWS, random);
	}
	
	@Override
	public Card createCard() {
		
		Map<Letter, Column> columns = new HashMap<Letter, Column>();
		for (Letter letter : Letter.values()) {
			columns.put(letter, columnFactory.createColumn(letter));
		}
		
		return new CardImpl(columns);
		
	}

	private class CardImpl implements Card {
		
		Map<Letter, Column> columns = new HashMap<Letter, Column>();

		private CardImpl(Map<Letter, Column> columns) {
			this.columns = columns;
		}

		@Override
		public boolean coverSpace(int value) {
			Letter letter = Letter.getLetter(value);
			return columns.get(letter).cover(value);

		}

		@Override
		public Space getSpace(Letter letter, int row) {
			return columns.get(letter).getSpace(row);
		}

		@Override
		public int numRows() {
			return NUM_ROWS;
		}

	}

}
