package bingo.output;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Map.Entry;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.statistics.SimpleHistogramBin;
import org.jfree.data.statistics.SimpleHistogramDataset;
import org.jfree.data.xy.IntervalXYDataset;

public class FrequenciesHistogramOutput implements FrequenciesOutput {

	// We only have 1 series so the name doesn't matter
	private static final String SERIES_KEY = "data";
	private static final boolean INCLUDE_LOWER_BOUND = false;
	private static final boolean INCLUDE_UPPER_BOUND = true;
	private static final String X_AXIS_LABEL = "Number of Calls Before a Blackout";
	private static final String Y_AXIS_LABEL = "Number of Games";
	private static final double Y_AXIS_PERCENT = 0.35;
	
	private final String outputFilePath;
	private final String title;
	private final int width;
	private final int height;

	public FrequenciesHistogramOutput(final String outputFilePath, final String title, final int width, final int height) {
		this.outputFilePath = outputFilePath;
		this.title = title;
		this.width = width;
		this.height = height;
	}
	
	@Override
	public void output(Map<Integer, Integer> object) throws IOException {
		JFreeChart histogram = ChartFactory.createHistogram(
				title, X_AXIS_LABEL, Y_AXIS_LABEL,
				getDataset(object), PlotOrientation.VERTICAL, false, false,
				false);

		histogram.getXYPlot().getDomainAxis().setRange(40, 75);
		histogram.getXYPlot().getRangeAxis().setRange(0, Y_AXIS_PERCENT*getTotal(object));
		
		File file = new File(outputFilePath);
		ChartUtilities.saveChartAsPNG(file, histogram, width, height);
	}

	private static final IntervalXYDataset getDataset(final Map<Integer, Integer> map) {
		SimpleHistogramDataset dataset = new SimpleHistogramDataset(SERIES_KEY);
		for (Entry<Integer, Integer> entry : map.entrySet()) {
			double lowerBound = entry.getKey() - 1;
			double upperBound = entry.getKey();
			SimpleHistogramBin bin = new SimpleHistogramBin(lowerBound,
					upperBound, INCLUDE_LOWER_BOUND, INCLUDE_UPPER_BOUND);
			bin.setItemCount(entry.getValue());
			dataset.addBin(bin);
		}
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
