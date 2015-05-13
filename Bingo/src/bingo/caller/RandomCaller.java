package bingo.caller;

import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;

import bingo.card.Letter;

public class RandomCaller implements Caller {
	
	private LinkedList<Integer> notCalled = new LinkedList<Integer>();
	
	public RandomCaller() {
		// Add all numbers to the lit
		for (int i = Letter.B.rangeStart(); i < Letter.O.rangeEnd(); i++) {
			notCalled.add(i);
		}
		// Randomly shuffle the list
		Collections.shuffle(notCalled, new Random());
	}
	
	@Override
	public int nextNumber() {
		return notCalled.pop();
	}

}
