package bingo.game;

import java.util.Collection;
import java.util.HashSet;

import bingo.caller.Caller;
import bingo.card.Card;
import bingo.card.CardFactory;
import bingo.checkers.CardChecker;
import bingo.output.CardPrinter;

public class GameImpl implements Game {

	private final int numPlayers;
	private final CardFactory cardFactory;
	private final Caller caller;
	private final CardChecker checker;
	
	CardPrinter printer = new CardPrinter();
	
	public GameImpl(int numPlayers, CardFactory cardFactory, Caller caller, CardChecker checker) {
		this.numPlayers = numPlayers;
		this.cardFactory = cardFactory;
		this.caller = caller;
		this.checker = checker;
	}
	
	@Override
	public GameResults play() {
		GameResultsImpl results = new GameResultsImpl();
		
		Collection<Card> cardsInPlay = new HashSet<Card>();
		for (int i=0; i < numPlayers; i++) {
			cardsInPlay.add(cardFactory.createCard());
		}
		
		while(findWinners(cardsInPlay).isEmpty()) {
			coverSpaces(cardsInPlay, caller.nextNumber());
			results.incrementNumCalls();
		}
		
		return results;
		
	}
	
	private Collection<Card> findWinners(Collection<Card> cards) {
		Collection<Card> winners = new HashSet<Card>();
		for (Card card: cards) {
			if (checker.hasWon(card)) {
				winners.add(card);
			}
		}
		return winners;
	}
	
	private void coverSpaces(Collection<Card> cards, int value) {
		for (Card card : cards) {
			card.coverSpace(value);
		}
	}

}
