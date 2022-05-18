package students.items;

public class UntilledSoil extends Item {

	public UntilledSoil() {
		// MAX_VALUE indicates the item cannot die
		super(Integer.MAX_VALUE, Integer.MAX_VALUE, -1);
	}

	public String toString() {
		return "/";
	}

}