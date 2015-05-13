package bingo.simulation;

import bingo.caller.Caller;
import bingo.card.CardFactory;
import bingo.checkers.CardChecker;

public interface SimulationConfig {

	int getNumGames();
	
	int getNumPlayers();
	
	CardFactory getCardFactory();
	
	Caller getCaller();
	
	CardChecker getCardChecker();
	
}
