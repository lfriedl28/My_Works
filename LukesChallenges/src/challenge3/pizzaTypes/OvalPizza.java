package challenge3.pizzaTypes;


public class OvalPizza extends Pizza {
	
	public double	radius1;
	public double	radius2;
	
	
	public OvalPizza(double radius1, double radius2, double cost) {
		super(cost);
		this.radius1 = radius1;
		this.radius2 = radius2;
		pizzaType = OVAL;
	}
	
	
	@Override
	public double getArea() {
		return Math.PI * radius1 * radius2;
	}
	
	
	@Override
	public double getPricePerSquareInch() {
		// TODO
		return 0;
	}
	
	
	@Override
	public String getPizzaInfo() {
		return radius1 + "in width-radius and " + radius2 + "in height-radius for $" + cost;
	}
	
}
