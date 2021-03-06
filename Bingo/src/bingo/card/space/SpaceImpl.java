package bingo.card.space;


public class SpaceImpl implements Space {

	private int value;
	private boolean isCovered = false;
	
	public SpaceImpl(int value) {
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public boolean isCovered() {
		return isCovered;
	}
	
	public void cover() {
		isCovered = true;
	}
	
	@Override
	public String toString() {
		if (isCovered) {
			return " X";
		}
		if (value < 10) {
			return " " + value;
		}
		return Integer.toString(value);
	}
	
}
