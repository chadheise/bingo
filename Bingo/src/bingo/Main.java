package bingo;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;
import java.util.Random;

import bingo.checkers.BlackoutChecker;
import bingo.checkers.CardChecker;
import bingo.output.CompositeOutput;
import bingo.output.FrequenciesCSVOutput;
import bingo.output.FrequenciesHistogramOutput;
import bingo.output.Output;
import bingo.simulation.RandomSimulationConfig;
import bingo.simulation.Simulator;
import bingo.simulation.SimulationConfig;
import bingo.simulation.SimulatorImpl;

public class Main {

	private static final Random RANDOM = new Random();
	private static final int MIN_NUM_PLAYERS = 1;
	private static final int MAX_NUM_PLAYERS = 100;
	private static final int NUM_GAMES = 100000;
	private static final CardChecker CARD_CHECKER = new BlackoutChecker();
	private static final Simulator SIMULATOR = new SimulatorImpl();
	private static final int IMAGE_WIDTH = 640;
	private static final int IMAGE_HEIGHT = 480;

	private static final String OUTPUT_PATH = "/Users/chadheise/Documents/programming/bingo/results/";
	private static final String CSV_FOLDER = "data/";
	private static final String CSV_FILE_EXTENSION = ".csv";
	private static final String GRAPH_FOLDER = "graphs/";
	private static final String GRAPH_FILE_EXTENSION = ".png";

	public static void main(String[] args) throws IOException {

		// For each number of players
		for (int numPlayers = MIN_NUM_PLAYERS; numPlayers <= MAX_NUM_PLAYERS; numPlayers++) {
			// Create different output destinations
			Collection<Output<Map<Integer, Integer>>> outputs = new ArrayList<Output<Map<Integer, Integer>>>();
			outputs.add(new FrequenciesCSVOutput(getCSVPath(numPlayers)));
			outputs.add(new FrequenciesHistogramOutput(
					getHistogramPath(numPlayers),
					getHistogramTitle(numPlayers), IMAGE_WIDTH, IMAGE_HEIGHT));
			Output<Map<Integer, Integer>> output = new CompositeOutput<Map<Integer, Integer>>(
					outputs);

			// Configure simulation
			SimulationConfig config = new RandomSimulationConfig(NUM_GAMES,
					numPlayers, CARD_CHECKER, RANDOM);
			
			// Run simulation and output results
			output.output(SIMULATOR.runSimulation(config));

		}
		System.exit(0);
	}

	private static final String getCSVPath(int numPlayers) {
		return OUTPUT_PATH + CSV_FOLDER + "data_" + numPlayers + "_" + NUM_GAMES + CSV_FILE_EXTENSION;
	}
	
	private static final String getHistogramPath(int numPlayers) {
		return OUTPUT_PATH + GRAPH_FOLDER + "histogram_" + numPlayers + "_" + NUM_GAMES + GRAPH_FILE_EXTENSION;
	}

	private static final String getHistogramTitle(int numPlayers) {
		return numPlayers + " Bingo Players, " + NUM_GAMES + " Games Played";
	}
}