package bingo.simulation;

import java.util.Map;
import java.util.Observable;
import java.util.Observer;
import java.util.TreeMap;

import bingo.game.Game;
import bingo.game.GameImpl;

public class SimulatorImpl implements Simulator {
	
	@Override
	public Map<Integer, Integer> runSimulation(SimulationConfig config) {
		// Maps the number of calls before someone wins to the number of simulations where that was the case
		Map<Integer, Integer> results = new TreeMap<Integer, Integer>();
		
		for (int i=0; i < config.getNumGames(); i++) {
			Game game = new GameImpl(config.getNumPlayers(), config.getCardFactory(), config.getCaller(), config.getCardChecker());
			int callsBeforeWinner = game.play().numCalls();
			if (results.containsKey(callsBeforeWinner)) {
				results.put(callsBeforeWinner, results.get(callsBeforeWinner) + 1);
			}
			else {
				results.put(callsBeforeWinner, 1);
			}
		}
		
		return results;
	}

}
