package bingo.checkers;

import bingo.card.Card;
import bingo.card.Letter;

public class VerticalChecker implements CardChecker {

	@Override
	public boolean hasWon(Card card) {
		for (Letter letter : Letter.values()) {
			int row;
			for (row = 0; row < card.numRows(); row++) {
				if (!card.getSpace(letter, row).isCovered()) {
					break;
				}
			}
			if (row == card.numRows() - 1) {
				return true;
			}
		}
		return false;
	}

	
	
}
