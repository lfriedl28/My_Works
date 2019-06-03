package challenge3.pizzaTypes;


public class RectangularPizza extends Pizza {
	
	public double	length;
	public double	width;
	
	
	public RectangularPizza(double length, double width, double cost) {
		super(cost);
		this.length = length;
		this.width = width;
		pizzaType = RECTANGLE;
	}
	
	
	@Override
	public double getArea() {
		return length * width;
	}
	
	
	@Override
	public double getPricePerSquareInch() {
		// TODO
		return 0;
	}
	
	
	@Override
	public String getPizzaInfo() {
		return width + "in wide and " + length + "in long for $" + cost;
	}
	
}
