package students.items;

public class Apples extends Food {

	// counts every Apple object instantiated
	static int noOfApples;
	{
		noOfApples++;
	}

	public Apples() {
		super(3, 5, 3);
	}

	// toString method definition for Apple
	public String toString() {
		if (age < maturationAge) {
			return "a";
		}
		return "A";
	}

	// return number of Apple objects instantiated
	public static int getGenerationCount() {
		return noOfApples;
	}
}
