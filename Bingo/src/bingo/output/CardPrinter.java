package bingo.output;

import bingo.card.Card;
import bingo.card.Letter;

public class CardPrinter implements Output<Card> {

	@Override
	public void output(Card card) {
		System.out.println(" B  I  N  G  O ");
		for (int i = 0; i < card.numRows(); i++) {
			System.out.println(getRow(card, i));
		}
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
