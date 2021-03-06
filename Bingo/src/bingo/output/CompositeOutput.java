package bingo.output;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Composite output which will output to all of the outputs it contains.
 * @author chadheise
 *
 * @param <T> the type of object to output
 */
public class CompositeOutput<T> implements Output<T> {

	Collection<Output<T>> outputs = new ArrayList<Output<T>>();
	
	public CompositeOutput(Collection<Output<T>> outputs) {
		this.outputs = outputs;
	}
	
	@Override
	public void output(T object) throws IOException {
		for (Output<T> output : outputs) {
			output.output(object);
		}	
	}

}
