package bingo.game;

import java.util.Collection;

import bingo.card.Card;

public interface GameResults {

	Collection<Card> getWinningCards();
	
	int numCalls();
	
}
