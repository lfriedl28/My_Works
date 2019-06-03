package challenge2;


public class Main {
	
	/*
	 * INSTRUCTIONS: Read these first
	 * 
	 * 
	 * The purpose of your challenge today is to take a set of dice and compute their average. For
	 * example: The average of 2d6 and 1d8 is 11.5.
	 * 
	 * As you can see, there's a lot of code already written for you. The DiceSet and Die classes
	 * are both finished. The DiceSet class and the Main class both have some documentation in them
	 * to help you with understanding what they're doing and to help you use their functions
	 * effectively.
	 * 
	 * All of the functions you should need in the DiceSet and Die classes should already be written
	 * for you, but feel free to add more if you need to. Also, you don't necessarily need to use
	 * every function that I wrote inside them; some might be decoys. ;)
	 * 
	 * YOUR TASK:---------------------------------------------------------------- ---------------
	 * Fill in the computeAverage() function in this class without modifying it's structure (i.e.
	 * you can't change the variables that get passed in). You're free to add any functions that you
	 * think will help you complete the program in a more efficient manner.
	 * 
	 * Also, There's a folder/package (They're basically the same thing) inside this project called
	 * "Hints". Inside it are 4 text files that each give a different hint. You don't have to worry
	 * about losing points for using them, I added them in as a way to help you in case you get
	 * stuck without having to worry about it affecting your performance. Try not to use them unless
	 * you get stuck, but don't feel ashamed about using them. If you need further help, send me a
	 * message.
	 * 
	 * I strongly suggest reading all the comments I left in both this class as well as the DiceSet
	 * class first. I think they'll help a lot.
	 * 
	 * When you're ready to begin, let me know and I'll begin the count-down. Good luck.
	 */
	
	
	// The String below contains the set of dice that I would like you to compute the average of to
	// prove your program is working. If you accidentally deleted it, it's "1d4,12d6,4d11,2d106";
	//
	// Feel free to use other strings of dice during testing, just make sure to follow the
	// guidelines in the DiceSet class and also to change the string back to this when you're
	// finished.
	private String diceToCompute = "1d4,12d6,4d11,2d106";
	
	
	public static void main(String[] args) {
		// Getting out of static methods and beginning the program as a new instance of the class
		// Main (i.e. starting at the constructor called "Main()")
		new Main();
	}
	
	
	public Main() {
		// Creating a new instance of the DiceSet class using the string we defined above as a
		// global variable.
		DiceSet ds = new DiceSet(diceToCompute);
		double average = computeAverage(ds);
		System.out.println(average);
	}
	
	
	
	private double computeAverage(DiceSet diceSet) {
		double average = 0.0;
		// TODO: find the average of all of the dice in the diceSet that was passed in and return
		// it.
		
		
		
		return average;
	}
	
	
	
	
	
	
}
