package bingo.checkers;

import bingo.card.Card;
import bingo.card.Letter;

public class DiagonalChecker implements CardChecker {

	@Override
	public boolean hasWon(Card card) {
		int row = 0;
		for (Letter letter : Letter.values()) {
			if (!card.getSpace(letter, row).isCovered()) {
				break;
			}
			row++;
		}
		if (row == Letter.values().length) {
			return true;
		}
		
		row = Letter.values().length;
		for (Letter letter : Letter.values()) {
			if (!card.getSpace(letter, row).isCovered()) {
				break;
			}
			row--;
		}
		if (row == 0) {
			return true;
		}
		
		return false;
	}

}
