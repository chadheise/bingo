package bingo.checkers;

import bingo.card.Card;
import bingo.card.Letter;

public class HorizontalChecker implements CardChecker {

	@Override
	public boolean hasWon(Card card) {
		for (int row = 0; row < card.numRows(); row++) {
			int coveredCount = 0;
			for (Letter letter : Letter.values()) {
				if (!card.getSpace(letter, row).isCovered()) {
					break;
				}
				coveredCount++;
			}
			if (coveredCount == Letter.values().length) {
				return true;
			}
		}
		return false;
	}

}
