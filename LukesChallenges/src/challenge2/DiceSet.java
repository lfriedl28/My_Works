package challenge2;


import java.util.ArrayList;


public class DiceSet {
	
	
	private ArrayList<Die[]> allDice;
	
	
	
	// Directly below this comment is what's called a Java-doc comment. It's the documentation that
	// shows up when you use intellesense/auto-complete for functions, or when you hover over a
	// function's name. Try hovering over "DiceSet" in the line: public DiceSet(String dice) {
	
	/**
	 * @param dice
	 *            a string that must be in the following format: </b> "xdy,xdy,xdy"</br>
	 *            Where:</br>
	 *            "x" represents a number of dice,</br>
	 *            "d" represents a sub-separator,</br>
	 *            "y" represents the number of sides of the dice,</br>
	 *            "," represents a separator between dice types.</br>
	 *            </br>
	 *            Example: "3d8,2d6,1d4"</br>
	 *            Spaces are irrelevant and will be stripped off. Any other characters will cause an
	 *            error.
	 * 
	 */
	public DiceSet(String dice) {
		allDice = new ArrayList<Die[]>();
		dice = dice.replaceAll(" ", "");
		String[] diceSeperated = dice.split(",");
		for (int i = 0; i < diceSeperated.length; i++) {
			diceSeperated[i] = diceSeperated[i].replaceAll(",", "");
		}
		
		for (String s : diceSeperated) {
			addDiceToAllDice(s.split("d"));
		}
		
	}
	
	
	/**
	 * This function takes the sub-selections of dice (i.e. "4d8") and turns them into arrays of
	 * dice to be held onto by the dice set.
	 * 
	 * @param diceSubSeperated
	 */
	private void addDiceToAllDice(String[] diceSubSeperated) {
		for (int i = 0; i < diceSubSeperated.length; i++) {
			diceSubSeperated[i] = diceSubSeperated[i].replaceAll("d", "");
		}
		
		int numDice = Integer.parseInt(diceSubSeperated[0]);
		int numSides = Integer.parseInt(diceSubSeperated[1]);
		
		Die[] darray = new Die[numDice];
		for (int i = 0; i < darray.length; i++) {
			darray[i] = new Die(numSides);
		}
		
		allDice.add(darray);
	}
	
	
	/**
	 * This function returns an int[] that contains all the types of dice contained in the dice set.
	 * (i.e. if there were 4 sided, 6 sided and 8 sided dice in the set, the function would return
	 * [4, 6, 8])
	 */
	public int[] getTypesOfDice() {
		int[] diceTypes = new int[allDice.size()];
		for (int i = 0; i < diceTypes.length; i++) {
			diceTypes[i] = allDice.get(i)[0].numberOfSides;
		}
		return diceTypes;
	}
	
	
	/**
	 * This function returns the number of dice in the dice set that match the number of sides
	 * passed in. (i.e. if the dice set contained 2d4 and 8d6 and you called this function
	 * getNumberOfDice(6) it would return 8 because there are 8 dice with 6 sides. The function
	 * returns 0 if there are no dice with the specified number of sides.
	 * 
	 * @param numSides
	 *            - The number of sides of the dice that you want to check for.
	 */
	public int getNumberOfDice(int numSides) {
		int numberOfDice = 0;
		for (int i = 0; i < allDice.size(); i++) {
			if (numSides == allDice.get(i)[0].numberOfSides) {
				numberOfDice = allDice.get(i).length;
			}
		}
		return numberOfDice;
	}
	
	
	/**
	 * This function rolls every dice in the dice set and returns the total value of them.
	 */
	public int rollAllDice() {
		int total = 0;
		for (Die[] dies : allDice) {
			for (Die die : dies) {
				total += die.roll();
			}
		}
		
		return total;
	}
	
	
	/**
	 * This function rolls every dice in the dice set that have the specified number of sides and
	 * returns the total value of them.
	 */
	public int rollAllDice(int numberOfSides) {
		int total = 0;
		for (Die[] dies : allDice) {
			if (dies[0].numberOfSides == numberOfSides) {
				for (Die die : dies) {
					total += die.roll();
				}
			}
		}
		return total;
	}
	
	
	/**
	 * This function prints out the entire dice set to the console so you can see what it's storing
	 * inside.
	 */
	public void printDiceSet() {
		System.out.println("The diceSet contains:\n[");
		for (int i = 0; i < allDice.size(); i++) {
			if (i == allDice.size() - 1) {
				System.out.println("and " + allDice.get(i).length + " " + allDice.get(i)[0].numberOfSides + "-sided dice");
			} else {
				System.out.println(allDice.get(i).length + " " + allDice.get(i)[0].numberOfSides + "-sided dice");
			}
		}
		System.out.println("]");
	}
	
	
}
