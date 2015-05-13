package bingo.checkers;

import java.util.Collection;
import java.util.LinkedList;

import bingo.card.Card;

public class StandardChecker implements CardChecker {

	private final CardChecker checker;
	
	public StandardChecker() {
		Collection<CardChecker> checkerCollection = new LinkedList<CardChecker>();
		checkerCollection.add(new VerticalChecker());
		checkerCollection.add(new HorizontalChecker());
		checkerCollection.add(new DiagonalChecker());
		
		checker = new CompositeOrChecker(checkerCollection);
	}
	
	@Override
	public boolean hasWon(Card card) {
		return checker.hasWon(card);
	}

}
