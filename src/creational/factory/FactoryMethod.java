package creational.factory;

class Point {
	double x;
	double y;
	
	private Point(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	// this following method is Factory Method
	protected static Point createCartesianPoint(double x, double y) {
		return new Point(x, y);
	}
	
	// this following method is Factory Method
	protected static Point createPolarPoint(double rho, double theta) {
		return new Point(rho*Math.cos(theta), rho*Math.sin(theta));
	}
}

class FactoryMethodDemo {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		Point samePoint = Point.createCartesianPoint(2, 3);
	}
}