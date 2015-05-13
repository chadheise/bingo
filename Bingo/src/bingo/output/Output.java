package bingo.output;

import java.io.IOException;

/**
 * Interface for outputting an object
 * @author chadheise
 *
 * @param <T> the type of object to output
 */
public interface Output<T> {

	void output(T object) throws IOException;
	
}
