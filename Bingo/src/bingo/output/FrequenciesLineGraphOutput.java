package bingo.output;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.xy.XYDataset;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;

public class FrequenciesLineGraphOutput implements FrequenciesOutput {

	// We only have 1 series so the name doesn't matter
	private static final String SERIES_KEY = "data";
	private static final String X_AXIS_LABEL = "Number of Calls";
	private static final String Y_AXIS_LABEL = "Probability of having a blackout at or before the number of calls";

	private final String outputFilePath;
	private final String title;
	private final int width;
	private final int height;

	public FrequenciesLineGraphOutput(final String outputFilePath,
			final String title, final int width, final int height) {
		this.outputFilePath = outputFilePath;
		this.title = title;
		this.width = width;
		this.height = height;
	}

	@Override
	public void output(Map<Integer, Integer> object) throws IOException {
		Map<Integer, Double> percentMap = getPercentMap(object);

		JFreeChart lineGraph = ChartFactory.createXYLineChart(title,
				X_AXIS_LABEL, Y_AXIS_LABEL, getDataset(percentMap),
				PlotOrientation.VERTICAL, false, false, false);

		lineGraph.getXYPlot().getDomainAxis().setRange(40, 75);
		lineGraph.getXYPlot().getRangeAxis().setRange(0, 100);
		
		File file = new File(outputFilePath);
		ChartUtilities.saveChartAsPNG(file, lineGraph, width, height);
	}

	/**
	 * Transforms a map of int to frequency to a map of int to percent less than
	 * or equal to
	 * 
	 */
	private static final Map<Integer, Double> getPercentMap(
			final Map<Integer, Integer> map) {
		// number of calls -> percent won with <= that many numbers called
		Map<Integer, Double> output = new HashMap<Integer, Double>();
		int total = getTotal(map);
		int soFar = 0;

		for (Entry<Integer, Integer> entry : map.entrySet()) {
			soFar += entry.getValue();
			double percent = ((double) soFar / (double) total) * 100;
			output.put(entry.getKey(), percent);
		}

		return output;
	}

	private XYDataset getDataset(final Map<Integer, Double> map) {
		XYSeries series = new XYSeries(SERIES_KEY);
		for (Entry<Integer, Double> entry : map.entrySet()) {
			series.add(entry.getKey(), entry.getValue());
		}

		XYSeriesCollection dataset = new XYSeriesCollection();
		dataset.addSeries(series);
		return dataset;
	}

	// TODO: Do this as a lambda
	private static final int getTotal(final Map<Integer, Integer> map) {
		int total = 0;
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			total += entry.getValue();
		}
		return total;
	}

}
