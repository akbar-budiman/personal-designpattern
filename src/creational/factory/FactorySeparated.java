package creational.factory;

// Factory
// a (separated) class dedicated to create instance of certain class
// the class is PointWithoutFactoryMethod in this case which can be found below
class PointFactory {
	protected static PointWithoutFactoryMethod createCartesianPoint(double x, double y) {
		return new PointWithoutFactoryMethod(x, y);
	}
	
	protected static PointWithoutFactoryMethod createPolarPoint(double rho, double theta) {
		return new PointWithoutFactoryMethod(rho*Math.cos(theta), rho*Math.sin(theta));
	}
}


class PointWithoutFactoryMethod {
	double x;
	double y;

	// notice that this constructor should be opened (public)
	// because the Factory is separated class and need to access this
	public PointWithoutFactoryMethod(double x, double y) {
		this.x = x;
		this.y = y;
	}
}

// pros : can be done when you have no access to the source code of the class
// cons : there are multiple ways/factory methods