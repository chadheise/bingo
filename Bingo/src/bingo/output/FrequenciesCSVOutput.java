package bingo.output;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Outputs a map of frequencies to a CSV file.
 * 
 * @author chadheise
 *
 */
public class FrequenciesCSVOutput implements FrequenciesOutput {

	String outputFilePath;

	public FrequenciesCSVOutput(String outputFilePath) {
		this.outputFilePath = outputFilePath;
	}

	@Override
	public void output(Map<Integer, Integer> frequencies) throws IOException {
		OutputStream outputStream;

		outputStream = new FileOutputStream(outputFilePath);
		
		outputStream.write("NumberOfSimulations,CallsBeforeBlackout".getBytes());

		for (Entry<Integer, Integer> entry : frequencies.entrySet()) {
			String outputLine = entry.getKey() + "," + entry.getValue() + "\n";
			outputStream.write(outputLine.getBytes());
		}

		outputStream.close();

	}

}
