package bingo.simulation;

import java.util.Map;

public interface Simulator {

	/**
	 * Runs a simulation and returns a map of the number of calls it took before
	 * someone won to the number of simulations where that was the case.
	 * 
	 * @return
	 */
	Map<Integer, Integer> runSimulation(SimulationConfig config);

}
