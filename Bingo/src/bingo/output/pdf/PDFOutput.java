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

import java.io.IOException;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.font.PDType1Font;

import bingo.card.Card;
import bingo.card.Letter;
import bingo.output.Output;

public class PDFOutput implements Output<Card> {

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

}
