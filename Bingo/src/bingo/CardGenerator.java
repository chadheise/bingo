package bingo;

import java.io.IOException;
import java.util.Random;

import bingo.card.RandomCardFactory;
import bingo.output.pdf.PDFOutput;

public class CardGenerator {

    public static void main(String[] args) throws IOException {

        RandomCardFactory cardFactory = new RandomCardFactory(new Random());
        PDFOutput pdfOutput = new PDFOutput();
        pdfOutput.output(cardFactory.createCard());

        System.out.println("Done");
    }

}
