package bingo.simulation;

import java.util.Random;

import bingo.caller.Caller;
import bingo.caller.RandomCaller;
import bingo.card.CardFactory;
import bingo.card.RandomCardFactory;
import bingo.checkers.CardChecker;

public class RandomSimulationConfig implements SimulationConfig {

	private final int numGames;
	private final int numPlayers;
	private final CardChecker cardChecker;
	private final CardFactory cardFactory;
	
	public RandomSimulationConfig(int numGames, int numPlayers, CardChecker cardChecker, Random random) {
		this.numGames = numGames;
		this.numPlayers = numPlayers;
		this.cardChecker = cardChecker;
		this.cardFactory = new RandomCardFactory(random);
	}
	
	@Override
	public int getNumGames() {
		return numGames;
	}

	@Override
	public int getNumPlayers() {
		return numPlayers;
	}

	@Override
	public CardFactory getCardFactory() {
		return cardFactory;
	}

	@Override
	public Caller getCaller() {
		return new RandomCaller();
	}

	@Override
	public CardChecker getCardChecker() {
		return cardChecker;
	}

}
