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
import java.util.function.Function;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import bingo.card.Card;
import bingo.card.Letter;

public class PDFOutput implements Function<Card, PDPage> {

    private static final int PAGE_HEIGHT = 792;
    private static final int PAGE_WIDTH = 612;
    private static final int LINE_WIDTH = 2;
    private static final int OUTER_BOX_WIDTH = 100;
    private static final int INNER_BOX_WIDTH = OUTER_BOX_WIDTH - LINE_WIDTH;
    private static final int OFFSET_X = (PAGE_WIDTH - Letter.values().length * OUTER_BOX_WIDTH) / 2;
    private static final int OFFSET_Y = (PAGE_HEIGHT - OUTER_BOX_WIDTH - Letter.values().length * OUTER_BOX_WIDTH) / 2;
    private static final int FONT_SIZE = 50;
    private static final int OFFSET_FONT = (INNER_BOX_WIDTH - FONT_SIZE) / 2;

    private final PDDocument document;

    public PDFOutput(PDDocument document) {
        this.document = document;
    }

    @Override
    public PDPage apply(final Card card) {

        PDPage page = new PDPage();

        PDPageContentStream contentStream;
        try {
            contentStream = new PDPageContentStream(document, page);

            createGrid(contentStream, card.numRows(), Letter.values().length);

            contentStream.setFont(PDType1Font.HELVETICA, FONT_SIZE);
            contentStream.setNonStrokingColor(Color.black);

            printHeader(contentStream);

            for (int i = 0; i < card.numRows(); i++) {
                printRow(contentStream, card, i);
            }

            contentStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return page;
    }

    private void printHeader(PDPageContentStream stream) throws IOException {
        int offsetY = PAGE_HEIGHT - OFFSET_Y;
        int i = 0;
        for (Letter letter : Letter.values()) {
            int offsetX = OFFSET_X + OUTER_BOX_WIDTH * i;
            stream.beginText();
            stream.newLineAtOffset(offsetX + OFFSET_FONT, offsetY + OFFSET_FONT);
            stream.showText(letter.name().toString());
            stream.endText();
            i++;
        }
    }

    private void printRow(PDPageContentStream stream, Card card, int row) throws IOException {
        int offsetY = PAGE_HEIGHT - OFFSET_Y - OUTER_BOX_WIDTH - OUTER_BOX_WIDTH * row;
        int i = 0;
        for (Letter letter : Letter.values()) {
            int offsetX = OFFSET_X + OUTER_BOX_WIDTH * i;
            stream.beginText();
            stream.newLineAtOffset(offsetX + OFFSET_FONT, offsetY + OFFSET_FONT);
            stream.showText(card.getSpace(letter, row).toString());
            stream.endText();
            i++;
        }

    }

    private void createGrid(PDPageContentStream stream, int numRows, int numCols) throws IOException {
        stream.setNonStrokingColor(Color.white);
        stream.setStrokingColor(Color.black);
        stream.setLineWidth(LINE_WIDTH);

        int offsetY = PAGE_HEIGHT - OFFSET_Y - OUTER_BOX_WIDTH;
        for (int i = 0; i < numRows; i++) {
            int offsetX = OFFSET_X;
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
