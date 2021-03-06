package bingo.checkers;

import bingo.card.Card;
import bingo.card.Letter;

public class BlackoutChecker implements CardChecker {

	@Override
	public boolean hasWon(Card card) {
		for (Letter letter : Letter.values()) {
			for (int row=0; row < card.numRows(); row++) {
				if (!card.getSpace(letter, row).isCovered()) {
					return false;
				}
			}
		}
		return true;
	}

}
