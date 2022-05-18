package students.items;

public abstract class Item {

	// instance variables
	int age = 0;
	int maturationAge;
	int deathAge;
	int monetaryValue;

	// constructor for Item
	Item(int matureAge, int dAge, int monValue) {
		maturationAge = matureAge;
		deathAge = dAge;
		monetaryValue = monValue;
	}

	//tick method
	public void tick() {
		age++;
	}

	//setAge method 
	public void setAge(int updatedAge) {
		age = updatedAge;
	}

	//died method, returns true if the age of an item is higher than that items defined deathAge
	public boolean died() {
		if (age > deathAge) {
			return true;
		}

		return false;
	}

	//returns the value of an item if it is passed its maturationAge;
	public int getValue() {
		if (age < maturationAge) {
			return 0;
		}
		return monetaryValue;
	}
	
	//equals method that is specific to item objects
	public boolean equals(Item obj) {

		final Item item = (Item) obj;

		if (this.age != item.age) {
			return false;
		}

		if (this.deathAge != item.deathAge) {
			return false;
		}

		if (this.maturationAge != item.maturationAge) {
			return false;
		}

		if (this.monetaryValue != item.monetaryValue) {
			return false;
		}

		return true;
	}

	// abstract toString method / definition in subclasses
	public abstract String toString();
}
