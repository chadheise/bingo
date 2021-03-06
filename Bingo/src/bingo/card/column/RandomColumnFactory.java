package bingo.card.column;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import bingo.card.Letter;
import bingo.card.space.FreeSpace;
import bingo.card.space.Space;
import bingo.card.space.SpaceImpl;

public class RandomColumnFactory implements ColumnFactory {

	private final Random random;
	private final int numRows;
	
	public RandomColumnFactory(int numRows, Random random) {
		this.numRows = numRows;
		this.random = random;
	}
	
	@Override
	public Column createColumn(Letter letter) {
		List<Space> spaces = new ArrayList<Space>();
		Set<Integer> usedNumbers = new HashSet<Integer>();
		for (int i=0; i < numRows; i++) {
			Space space;
			if (Letter.N.equals(letter) && i == (numRows - 1)/2) {
				space = new FreeSpace();
			}
			else { 
				int value = getRandomNumber(letter, usedNumbers);
				usedNumbers.add(value);
				space = new SpaceImpl(value);
			}
			spaces.add(space);
		}
		
		return new ColumnImpl(spaces);
		
	}

	/**
	 * Method which will get a random number and avoid duplicates.
	 * @param letter the column (used to get the range of possible numbers)
	 * @param usedNumbers a set of numbers that have already been used
	 * @return a random number in the range that has not yet been used
	 */
	private Integer getRandomNumber(Letter letter, Collection<Integer> usedNumbers) {
		Integer value;
		do {
			value = letter.rangeStart() + random.nextInt(letter.rangeEnd() - letter.rangeStart());
		} while(null != value && usedNumbers.contains(value));
		return value;
	}
	
	private class ColumnImpl implements Column {

		private List<Space> spaces;

		private ColumnImpl(List<Space> spaces) {
			this.spaces = spaces;
		}
		
		@Override
		public boolean isCovered(int i) {
			return spaces.get(i).isCovered();
		}

		@Override
		public boolean cover(int value) {
			for (Space space : spaces) {
				if (space.getValue() == value) {
					space.cover();
					return true;
				}
			}
			return false;
		}

		@Override
		public Space getSpace(int row) {
			return spaces.get(row);
		}
		
	}
	
}
