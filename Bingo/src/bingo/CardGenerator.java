package bingo;

import java.io.IOException;
import java.util.Random;
import java.util.stream.IntStream;

import org.apache.pdfbox.pdmodel.PDDocument;

import bingo.card.RandomCardFactory;
import bingo.output.pdf.PDFOutput;

public class CardGenerator {

    private static final int NUM_CARDS = 10;

    public static void main(String[] args) throws IOException {

        RandomCardFactory cardFactory = new RandomCardFactory(new Random());

        PDDocument document = new PDDocument();
        document.getDocumentInformation().setTitle("Bingo Cards");

        PDFOutput pdfOutput = new PDFOutput(document);

        IntStream.range(0, NUM_CARDS)
                .mapToObj(i -> cardFactory.createCard())
                .map(pdfOutput)
                .forEach(page -> document.addPage(page));

        document.save("/Users/chadheise/Documents/programming/bingo/results/bingoCards.pdf");
        document.close();

        System.out.println("Done");
    }

}
