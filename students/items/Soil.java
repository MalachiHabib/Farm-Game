package students.items;

public class Soil extends Item {

	public Soil() {
		// MAX_VALUE indicates the item cannot die
		super(Integer.MAX_VALUE, Integer.MAX_VALUE, 0);
	}

	public String toString() {
		return ".";
	}

}