package students;

import java.util.Arrays;
import java.util.Random;

import students.items.Apples;
import students.items.Axe;
import students.items.Grain;
import students.items.Hoe;
import students.items.Item;
import students.items.Reaper;
import students.items.Soil;
import students.items.Tools;
import students.items.UntilledSoil;
import students.items.Weed;

public class Field {

	// new 2d array field of Items
	Item[][] arrField;
	// used for random number generation
	Random rand = new Random();

	public Field(int height, int width) {

		// creates a 2d array with height and width
		arrField = new Item[height][width];

		// defines each element in the 2d array to a new Soil object
		for (int row = 0; row < arrField.length; row++) {
			for (int col = 0; col < arrField[row].length; col++) {
				arrField[row][col] = new Soil();
			}
		}
	}

	// tick method calls tick on each Item at that given point in the 2d array
	public void tick() {
		for (int row = 0; row < arrField.length; row++) {
			for (int col = 0; col < arrField[row].length; col++) {
				if (arrField[row][col] instanceof Apples | arrField[row][col] instanceof Grain) {
					if (arrField[row][col].died()) {
						arrField[row][col] = new UntilledSoil();
					}

				}
				if (arrField[row][col] instanceof Soil) {
					int randNum = rand.nextInt(101);
					if (randNum <= 20) {
						arrField[row][col] = new Weed();
					}
				}
				arrField[row][col].tick();
			}
		}
	}

	// toString method converts the 2d array to string, checking each index and
	// calling the item at that index's toString method also
	public String toString() {
		// checks if height or width defined are larger than 10
		if (arrField.length > 10 | arrField[0].length > 10) {
			System.out.println("Grid size cannot be larger than 10");
		}

		// prints column numbers
		else {
			if (arrField[0].length >= 1) {
				for (int i = 0; i < arrField[0].length; i++) {
					if (i < 1) {
						System.out.printf("%5s", i + 1);
						i++;
					}
					if (i > 8) {
						System.out.print(" ");
					}
					System.out.printf("%3s", i + 1);
				}
				// prints row and new field populated with soil
				System.out.println("");

				for (int row = 0; row < arrField.length; row++) {
					if (row < 9) {
						System.out.print(row + 1 + " ");
					} else {
						System.out.print(row + 1);
					}
					for (int col = 0; col < arrField[row].length; col++) {
						System.out.printf("%3s", arrField[row][col]);
					}
					System.out.println("");
				}
			}
		}
		return "";
	}

	// till method converts the parsed x,y coordinate to a new Soil Item
	public void till(int x, int y) {
		arrField[x][y] = new Soil();
	}

	// used to set current index to untilled soil
	public void setUntilledSoil(int x, int y) {
		arrField[x][y] = new UntilledSoil();
	}

	// plant method checks if the parsed item type is either apple or grain and sets
	// the current array index to that type of Item
	public void plant(int x, int y, Item item) {
		if (item instanceof Apples) {
			arrField[x][y] = new Apples();
		} else if (item instanceof Grain) {
			arrField[x][y] = new Grain();
		}
	}

	// get method returns the item at the parsed x,y coordinate
	public Item get(int x, int y) {
		return arrField[x][y];

	}

	// get value returns the total value of all field items
	public int getValue() {
		int fieldTotal = 0;
		for (int row = 0; row < arrField.length; row++) {
			for (int col = 0; col < arrField[row].length; col++) {
				fieldTotal = fieldTotal + arrField[row][col].getValue();
			}
		}
		return fieldTotal;
	}

	//getSummary prints the summary of each item on the field along with the count of each item and the total value 
	public void getSummary() {
		int grain = 0;
		int apples = 0;
		int soil = 0;
		int untilled = 0;
		int weed = 0;
		int total = 0;

		for (int row = 0; row < arrField.length; row++) {
			for (int col = 0; col < arrField[row].length; col++) {
				if (arrField[row][col] instanceof Soil) {
					soil = soil + 1;
				} else if (arrField[row][col] instanceof UntilledSoil) {
					untilled = untilled + 1;
					total = total + arrField[row][col].getValue();
				} else if (arrField[row][col] instanceof Weed) {
					weed = weed + 1;
					total = total + arrField[row][col].getValue();
				}

				else if (arrField[row][col] instanceof Grain) {
					grain = grain + 1;
					if (arrField[row][col].getValue() > 0 & !arrField[row][col].died()) {
						total = total + arrField[row][col].getValue();
					}

				} else if (arrField[row][col] instanceof Apples) {
					apples = apples + 1;
					if (arrField[row][col].getValue() > 0 & !arrField[row][col].died()) {
						total = total + arrField[row][col].getValue();
					}
				}
			}
		}

		System.out.print("\nSUMMARY ");
		System.out.printf("\n%-16s  %-10s%n", "Apples: ", apples);
		System.out.printf("%-16s  %-10s%n", "Grain: ", grain);
		System.out.printf("%-16s  %-10s%n", "Soil: ", soil);
		System.out.printf("%-16s  %-10s%n", "Untilled: ", untilled);
		System.out.printf("%-16s  %-10s%n", "Weed: ", weed);
		System.out.printf("%-16s  %-10s%n", "For a total of $", total);
		System.out.printf("%-22s  %-10s%n", "Total apples created: ", Apples.getGenerationCount() / 2);
		System.out.printf("%-22s  %-10s%n", "Total grain created: ", Grain.getGenerationCount() / 2 + "\n");
	}

	//ADDITONAL FEATURE buyTools is used when a user wants to buy a certain tool 
	public void buyTools(String item) {

		if (item.equals("Axe")) {
			new Axe();
		} else if (item.equals("Hoe")) {
			new Hoe();
		} else if (item.equals("Reaper")) {
			new Reaper();
		}
	}
	
	//ADDITONAL FEATURE hasTool checks to see if a user has the certain tool in order to harvest / till 
	public boolean hasTool(String item) {
		if (item.equals("Axe") && Axe.hasAxe() == true) {
			return true;
		} else if (item.equals("Hoe") && Hoe.hasHoe() == true) {
			return true;
		} else if (item.equals("Reaper") && Reaper.hasReaper() == true) {
			return true;
		}
		return false;
	}
}
