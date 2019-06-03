package challenge3;


import java.util.ArrayList;

import challenge3.pizzaTypes.CircularPizza;
import challenge3.pizzaTypes.OvalPizza;
import challenge3.pizzaTypes.Pizza;
import challenge3.pizzaTypes.RectangularPizza;
import challenge3.pizzaTypes.SquarePizza;
import challenge3.pizzaTypes.TrianglularPizza;


public class Main {
	
	/*
	 * INSTRUCTIONS to Challenge 3: Read these first
	 * 
	 * The purpose of today's challenge is to take in an array of differently shaped and sized
	 * pizzas and figure out which ones are the best deal.
	 * 
	 * To do this you'll have to successfully do a couple things:
	 * 
	 * 1) In each class that directly extends the Abstract Class Pizza (OvalPizza, RectangularPizza,
	 * and TriangularPizza), you'll need to fill out the method called "getPricePerSquareInch()".
	 * Right now it's mostly empty and marked with a "//TODO" All the information you need to
	 * calculate that is readily available to you. (Hint 1 relates to this). These Classes can be
	 * found inside the sub-package called "pizzaTypes". The Classes SquarePizza, CircularPizza, and
	 * Pizza do not need to be modified as they either dont need it (in the case of "Pizza") or
	 * inherit it from their SuperClass (Which is the case for SquarePizza and CircularPizza, which
	 * are child classes of RectangularPizza and OvalPizza respectively).
	 * 
	 * 2) You'll need to devise a way to compare the pricePerSquareInch of pizzas so that you know
	 * which ones are the best deal. I would write this in a seperate method. (Hint 2 relates with
	 * this)
	 * 
	 * 3) You'll need to compare the pizzas and stick them into the sortedPizzas ArrayList from
	 * least cost to most cost. (Hints 3 and 4 Relate to this)
	 */
	
	
	public Main() {
		// None of this method needs to be modified.
		Pizza[] pizzas = getArrayOfPizzas();
		
		Pizza[] sortedPizzas = sortPizzas(pizzas);
		
		
		System.out.println("Here are the pizzas in order from best deal to worst deal:");
		for (int i = 0; i < sortedPizzas.length; i++) {
			System.out.println("The " + sortedPizzas[i].getType() + " pizza (" + sortedPizzas[i].getPizzaInfo() + ") at $" + sortedPizzas[i].getPricePerSquareInch() + " per square inch.");
		}
		
	}
	
	
	private Pizza[] sortPizzas(Pizza[] pizzas) {
		// I set up the ArrayList for you. Don't modify this.
		ArrayList<Pizza> sortedPizzas = new ArrayList<Pizza>();
		
		
		// TODO: Compare the pricePerSquareInch of the pizzas in the "pizzas" array, compare them,
		// and add them to the "sortedPizzas" ArrayList in order from lowest pricePerSquareInch to
		// highest PPSI
		
		
		
		// This line is correct, don't modify it.
		return (Pizza[]) (sortedPizzas.toArray());
	}
	
	
	
	
	
	
	private Pizza[] getArrayOfPizzas() {
		// This method is just used to create some example pizzas. Don't modify it.
		Pizza[] pizzas = new Pizza[8];
		pizzas[0] = new SquarePizza(12.0, 14.99);
		pizzas[1] = new RectangularPizza(12.0, 14.0, 15.49);
		pizzas[2] = new CircularPizza(12.0, 12.99);
		pizzas[3] = new OvalPizza(10.0, 15.0, 16.00);
		pizzas[4] = new TrianglularPizza(12.0, 13.0, 12.49);
		pizzas[5] = new CircularPizza(14.0, 15.99);
		pizzas[6] = new SquarePizza(10.0, 11.99);
		pizzas[7] = new OvalPizza(25.0, 18.0, 35.99);
		
		return pizzas;
	}
	
	
	public static void main(String[] args) {
		new Main();
	}
	
}
