package bingo.game;

import java.util.Collection;

import bingo.card.Card;

public class GameResultsImpl implements GameResults {

	private Collection<Card> winningCards;
	private int numCalls = 0;
	
	public void setWinningCards(Collection<Card> cards) {
		winningCards = cards;
	}
	
	public void incrementNumCalls() {
		numCalls++;
	}
	
	@Override
	public Collection<Card> getWinningCards() {
		return winningCards;
	}

	@Override
	public int numCalls() {
		return numCalls;
	}

}
