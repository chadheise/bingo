package bingo.checkers;

import java.util.Collection;

import bingo.card.Card;

public class CompositeOrChecker implements CardChecker {

	Collection<CardChecker> checkers;
	
	public CompositeOrChecker(Collection<CardChecker> checkers) {
		this.checkers = checkers;
	}
	
	@Override
	public boolean hasWon(Card card) {
		for (CardChecker checker : checkers) {
			if (checker.hasWon(card)) {
				return true;
			}
		}
		return false;
	}

	
	
}
