package bingo.card;

public enum Letter {

	B(1, 16),
	I(16, 31),
	N(31, 46),
	G(46, 61),
	O(61, 76);
	
	private int rangeStart;
	private int rangeEnd;
	
	Letter(int rangeStart, int rangeEnd) {
		this.rangeStart = rangeStart;
		this.rangeEnd = rangeEnd;
	}
	
	public int rangeStart() {
		return rangeStart;
	}
	
	public int rangeEnd() {
		return rangeEnd;
	}
	
	public static Letter getLetter(int value) {
		if (B.rangeStart <= value && value < B.rangeEnd) {
			return B;
		}
		else if (I.rangeStart <= value && value < I.rangeEnd) {
			return I;
		}
		else if (N.rangeStart <= value && value < N.rangeEnd) {
			return N;
		}
		else if (G.rangeStart <= value && value < G.rangeEnd) {
			return G;
		}
		else if (O.rangeStart <= value && value < O.rangeEnd) {
			return O;
		}
		throw new RuntimeException("Invalid value for Bingo board");
	}
	
}
