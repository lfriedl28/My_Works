package challenge2;


import java.util.Random;


public class Die {
	
	public int		numberOfSides;
	private Random	generator	= new Random();
	
	
	public Die(int numberOfSides) {
		this.numberOfSides = numberOfSides;
	}
	
	
	public int roll() {
		return generator.nextInt(numberOfSides) + 1;
	}
	
}
