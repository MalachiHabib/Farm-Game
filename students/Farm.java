package students;

import java.util.Scanner;

import students.items.Apples;
import students.items.Axe;
import students.items.Grain;
import students.items.Hoe;
import students.items.Reaper;
import students.items.UntilledSoil;

public class Farm {
	Field game;
	int bank;
	private Scanner scan;

	// creates a farm with a given width height and starting fund amount
	public Farm(int fieldWidth, int fieldHeight, int startingFunds) {

		game = new Field(fieldHeight, fieldWidth);
		bank = startingFunds;
	}

	public void run() {

		// input is used for all user input
		String input;
		// is used to reset the while loop

		boolean reset = false;
		int x = 0;
		int y = 0;

		while (true) {
			// at the beginning of every loop reset is set to false in order to prevent
			// infinite loop
			reset = false;
			// prints the field
			game.toString();

			scan = new Scanner(System.in);
			System.out.println("\nBank balance: $" + bank);
			System.out.println("\nEnter your next action:");
			System.out.println("t x y: till");
			System.out.println("h x y: harvest");
			System.out.println("p x y: plant");
			System.out.println("b : buy tools");
			System.out.println("s: field summary");
			System.out.println("w: wait");
			System.out.println("q: quit");

			// gets the users input sets it to all lower case and removes spaces
			input = scan.nextLine();
			input = input.toLowerCase();
			input = input.replaceAll(" ", "");

			// checks if the length of the input is only 1, or if it is not one of the key
			// chars allowed
			if (input.length() == 1 && input.charAt(0) == 'q' || input.charAt(0) == 's' || input.charAt(0) == 'w'
					|| input.charAt(0) == 'b') {
				if (input.charAt(0) == 's') {
					game.getSummary();
				}

				if (input.charAt(0) == 'w') {
					game.tick();
				}

				if (input.charAt(0) == 'q') {
					System.out.println("\nQuitting game. . . \nThanks for playing!");
					break;
				}
				if (input.charAt(0) == 'b') {
					// ADDITIONAL FEATURE this is the buy menu where players must buy certain tools
					// in order to perform actions
					while (reset != true) {
						System.out.println("Enter:\n- 'a' to buy an axe for $4\n- 'h' to a buy hoe for $1\n- 'r' to a "
								+ "buy reaper for $2");
						input = scan.nextLine();

						if (input.charAt(0) == 'a') {
							game.buyTools("Axe");
							bank = bank - 4;
							break;
						} else if (input.charAt(0) == 'h') {
							game.buyTools("Hoe");
							bank = bank - 1;
							break;
						} else if (input.charAt(0) == 'r') {
							game.buyTools("Reaper");
							bank = bank - 2;
							break;
						} else {
							System.out.println("\nInput invalid. . . \nPlease try again. . .\n");
						}
					}
				}
			}

			// checks if the user enters only 1 character, if true, resets loop
			else if (input.length() == 1) {
				System.out.println("\nInput invalid. . . \nPlease try again. . .\n");
				reset = true;
			}

			// if user enter over 1 character total
			else {

				// splits the user input into array elements, the string portion and integer

				String[] part = input.split("(?<=\\D)(?=\\d)");

				// assigns the integer portion of user input to the int num
				int num = Integer.parseInt(part[1]);

				// if the user enters more than character in the beginning of the string, reset

				if (part[0].length() > 1) {
					reset = true;
				}

				// if the user enters 0 at the beginning of the number portion of the input,
				// reset loop
				if (part[1].charAt(0) == '0') {
					reset = true;
					// if length of the number is 3 numbers long and the second number is 0, reset
					// loop
				} else if (part[1].length() == 2) {
					if (part[1].charAt(1) == '0') {
						reset = true;
					} else {
						// else assign values to x and y
						y = Character.getNumericValue(input.charAt(2));
						x = Character.getNumericValue(input.charAt(1));
					}
				}
				// checks for invalid inputs if the length of num is 3, checks if third int
				// entered is 0 as the first 0 is checked for above
				else if (part[1].length() == 3) {
					if (num / 10 == 10 && input.charAt(3) != '0') {
						x = 10;
						y = Character.getNumericValue(input.charAt(3));
					} else if (num % 100 == 10) {
						y = 10;
						x = Character.getNumericValue(input.charAt(1));
					} else {
						reset = true;
					}
				}
				// checks if the first or third number entered in a 4 digit number are 0, if
				// true reset loop
				else if (part[1].length() == 4) {
					if (num % 100 / 10 == 0 || num / 1000 == 0) {
						reset = true;
						// if no invalid input, and 1010 entered, assign x and y to 10
					} else if (num / 100 == 10 && num % 100 == 10) {
						x = 10;
						y = 10;
					}
				} else {
					reset = true;
				}

				if (input.charAt(0) == 't') {
					// if the user has not purchased a hoe
					if (!game.hasTool("Hoe")) {
						System.out.println(
								"\nYou must buy a hoe before tilling land! \nPress 'b' to open the buy menu!\n");
					} else {
						game.till(y - 1, x - 1);
					}
				}

				if (input.charAt(0) == 'h') {
					// if the user attempts to harvest an apple without an axe
					if (game.get(y - 1, x - 1) instanceof Apples && !game.hasTool("Axe")) {
						System.out.println(
								"\nYou must buy an axe before haveresting apples! \nPress 'b' to open the buy menu!\n");
						// if a user attempts to harvest grain without a reaper
					} else if (game.get(y - 1, x - 1) instanceof Grain && !game.hasTool("Reaper")) {
						System.out.println(
								"\nYou must buy a reaper before haveresting grain! \nPress 'b' to open the buy menu!\n");
						// if the user has required tool, check if the entered coordinate is mature, if
						// it is harvest and add money to bank
					} else {
						if (game.get(x - 1, y - 1).getValue() > 0) {
							game.tick();
							bank = bank + game.get(x - 1, y - 1).getValue();
							game.setUntilledSoil(y - 1, x - 1);
						} else {
							game.tick();
							game.setUntilledSoil(y - 1, x - 1);
						}
					}
				}
			}

			if (input.charAt(0) == 'p') {
				// checks if the user is attempting to plant on untilled soil
				if (game.get(x - 1, y - 1) instanceof UntilledSoil) {
					System.out.println("\nYou must first till the land to plant!\n");
				}

				else {
					// loops until the user enters a valid input
					while (!reset) {
						System.out.println("Enter:\n- 'a' to buy an apple for $2\n- 'g' to buy grain for $1");
						input = scan.nextLine();
						// if the user wants to plant an apple, assigns coordinate to apple and deducts
						// amount from bank
						if (input.charAt(0) == 'a') {
							game.tick();
							game.plant(y - 1, x - 1, new Apples());
							bank = bank - 2;
							break;
						} else if (input.charAt(0) == 'g') {
							game.tick();
							game.plant(y - 1, x - 1, new Grain());
							bank = bank - 1;
							break;
						} else {
							System.out.println("\nInput invalid. . . \nPlease try again. . .\n");
						}
					}
				}
			}
			// used to reset if user enters invalid entry
			if (reset == true) {
				System.out.println("\nInput invalid. . . \nPlease try again. . .\n");
			}
		}
	}
}
