package students.items;

public class Reaper extends Tools{
	
	static int HasReaper;
	{
		HasReaper++;
	}

	public Reaper() {
		super(0, 0, 2);
	}

	public String toString() {
		return "Reaper purchased!";
	}

	public int getValue() {
		return monetaryValue;
	}
	// used to check if the user has instantiated a reaper 
	public static boolean hasReaper() {
		if (HasReaper > 0) {
			return true;
		}
		return false;
	}
}
