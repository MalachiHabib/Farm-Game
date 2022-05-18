package students.items;

public class Axe extends Tools {

	static int hasAxe;
	{
		hasAxe++;
	}

	public Axe() {
		super(0, 0, 4);
	}

	public String toString() {
		return "Axe purchased!";
	}

	public int getValue() {
		return monetaryValue;
	}
	// used to check if the user has instantiated an axe 
	public static boolean hasAxe() {
		if (hasAxe > 0) {
			return true;
		}
		return false;
	}
}
