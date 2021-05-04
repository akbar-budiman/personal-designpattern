package creational.factory;

// another implementation of Factory besides what has been explained in FactorySeparated.java
class PointWithFactory {
	double x;
	double y;
	
	private PointWithFactory(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	
	public static class Factory {
		protected static PointWithFactory createCartesianPoint(double x, double y) {
			return new PointWithFactory(x, y);
		}
		
		protected static PointWithFactory createPolarPoint(double rho, double theta) {
			return new PointWithFactory(rho*Math.cos(theta), rho*Math.sin(theta));
		}		
	}
}