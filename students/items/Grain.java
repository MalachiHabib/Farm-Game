package students.items;

public class Grain extends Food {

	// counts every Grain object instantiated
	static int noOfGrain;
	{
		noOfGrain++;
	}

	public Grain() {
		super(2, 6, 2);
	}

	// toString method definition for Grain
	public String toString() {
		if (age < maturationAge) {
			return "g";
		}
		return "G";
	}

	// returns number of Grain objects instantiated
	public static int getGenerationCount() {
		return noOfGrain;
	}

}
