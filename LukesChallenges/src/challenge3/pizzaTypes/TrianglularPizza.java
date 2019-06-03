package challenge3.pizzaTypes;


public class TrianglularPizza extends Pizza {
	
	public double	base;
	public double	height;
	
	
	public TrianglularPizza(double baseWidth, double height, double cost) {
		super(cost);
		this.base = baseWidth;
		this.height = height;
		pizzaType = TRIANGLE;
	}
	
	
	@Override
	public double getArea() {
		return 0.5 * base * height;
	}
	
	
	@Override
	public double getPricePerSquareInch() {
		// TODO
		return 0;
	}
	
	
	@Override
	public String getPizzaInfo() {
		return base + "in base and " + height + "in height for $" + cost;
	}
	
}
