/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package bingo.output.pdf;

import java.awt.Color;
import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import bingo.card.Card;
import bingo.card.Letter;
import bingo.output.Output;

public class PDFOutput implements Output<Card> {

    private static final int PAGE_HEIGHT = 792;
    private static final int PAGE_WIDTH = 612;
    private static final int LINE_WIDTH = 2;
    private static final int OUTER_BOX_WIDTH = 100;
    private static final int INNER_BOX_WIDTH = OUTER_BOX_WIDTH - LINE_WIDTH;

    @Override
    public void output(Card card) throws IOException {
        PDDocument document = new PDDocument();

        document.getDocumentInformation().setTitle("Bingo Cards");

        PDPage page = new PDPage();
        document.addPage(page);

        PDPageContentStream contentStream = new PDPageContentStream(document, page);

        contentStream.beginText();
        contentStream.setFont(PDType1Font.HELVETICA, 44);

        contentStream.newLineAtOffset(25, 700);
        contentStream.setLeading(46.5f);
        contentStream.showText(" B  I  N  G  O ");
        contentStream.newLine();

        for (int i = 0; i < card.numRows(); i++) {
            contentStream.showText(getRow(card, i));
            contentStream.newLine();
        }

        contentStream.endText();

        createGrid(contentStream, card.numRows(), 5);

        contentStream.close();

        document.save("/Users/chadheise/Documents/programming/bingo/results/pdfs/file.pdf");

        document.close();
    }

    private String getRow(Card card, int row) {
        StringBuilder builder = new StringBuilder();
        for (Letter letter : Letter.values()) {
            builder.append(card.getSpace(letter, row));
            builder.append(" ");

        }
        return builder.toString();
    }

    private void createGrid(PDPageContentStream stream, int numRows, int numCols) throws IOException {
        // Setting the non stroking color
        stream.setNonStrokingColor(Color.white);
        stream.setStrokingColor(Color.black);
        stream.setLineWidth(LINE_WIDTH);

        int offsetXDefault = 25;
        int offsetY = PAGE_HEIGHT - OUTER_BOX_WIDTH - 25;
        for (int i = 0; i < numRows; i++) {
            int offsetX = offsetXDefault;
            for (int j = 0; j < numCols; j++) {
                stream.addRect(offsetX, offsetY,
                        INNER_BOX_WIDTH, INNER_BOX_WIDTH);
                stream.fillAndStroke();
                offsetX += OUTER_BOX_WIDTH;
            }
            offsetY -= OUTER_BOX_WIDTH;
        }

    }

}
