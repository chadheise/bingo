package bingo.output;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageOutputStream;
import javax.imageio.stream.ImageOutputStream;

/**
 * Outputs a collection of file names as an animated gif
 *
 */
public class GifOutput implements Output<List<String>> {
	
	private final String filePath;
	private final int frameDelayMillis;
	
	public GifOutput(String filePath, int frameDelayMillis) {
		this.filePath = filePath;
		this.frameDelayMillis = frameDelayMillis;
	}
	
	@Override
	public void output(List<String> object) throws IOException {
	        // grab the output image type from the first image in the sequence
	        BufferedImage firstImage = ImageIO.read(new File(object.get(0)));

	        // create a new BufferedOutputStream with the last argument
	        ImageOutputStream output = 
	          new FileImageOutputStream(new File(filePath));
	        
	        // create a gif sequence with the type of the first image, 1 second
	        // between frames, which loops continuously
	        GifSequenceWriter writer = 
	          new GifSequenceWriter(output, firstImage.getType(), frameDelayMillis, false);
	        
	        // write out the first image to our sequence...
	        writer.writeToSequence(firstImage);
	        for(String fileName : object) {
	          BufferedImage nextImage = ImageIO.read(new File(fileName));
	          writer.writeToSequence(nextImage);
	        }
	        
	        writer.close();
	        output.close();
	}
}
