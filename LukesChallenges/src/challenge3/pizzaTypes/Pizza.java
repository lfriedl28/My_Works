package challenge3.pizzaTypes;


public abstract class Pizza {
	
	public static final int	NO_TYPE		= -1;
	public static final int	OVAL		= 0;
	public static final int	RECTANGLE	= 1;
	public static final int	TRIANGLE	= 2;
	
	
	public double			cost;
	public int				pizzaType;
	
	
	public Pizza(double cost) {
		this.cost = cost;
		pizzaType = NO_TYPE;
	}
	
	
	public abstract double getArea();
	
	
	public abstract double getPricePerSquareInch();
	
	
	public abstract String getPizzaInfo();
	
	
	public String getType() {
		if (pizzaType == NO_TYPE) {
			return "NO TYPE";
		} else if (pizzaType == OVAL) {
			return "oval";
		} else if (pizzaType == RECTANGLE) {
			return "rectangle";
		} else if (pizzaType == TRIANGLE) {
			return "triangle";
		} else {
			return "ERROR";
		}
	}
}
