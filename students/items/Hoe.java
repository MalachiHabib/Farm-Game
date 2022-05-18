package students.items;

public class Hoe extends Tools {

	static int HasHoe;
	{
		HasHoe++;
	}

	public Hoe() {
		super(0, 0, 1);
	}

	public String toString() {
		return "Hoe purchased!";
	}

	public int getValue() {
		return monetaryValue;
	}

	// used to check if the user has instantiated a hoe
	public static boolean hasHoe() {
		if (HasHoe > 0) {
			return true;
		}
		return false;
	}
}