package bingo.card.space;



public class FreeSpace implements Space {

	@Override
	public int getValue() {
		return -1;
	}

	@Override
	public boolean isCovered() {
		return true;
	}

	@Override
	public void cover() {
		// Free space is always covered
	}
	
	@Override
	public String toString() {
		return " F";
	}

}
