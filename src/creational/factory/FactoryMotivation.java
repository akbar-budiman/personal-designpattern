package creational.factory;

/*
 * suppose that we want to create a class representing a POINT,
 * and suppose we want to be able to declare a POINT by :
 * 1. using cartesian coordinate system -> there are x and y
 * 2. using polar coordinate system -> there are rho and theta, where
 * 										x = rho*Math.cos(theta)
 * 										y = rho*Math.sin(theta)
 * 
 * these will provide three steps purpose way until the final solution in the third way
 */


/*
 *  # 1st way
 *  This class provide two constructor, but we face a problem: method can not be overloaded.
 *  The method name can't be changed because they are class constructor,
 *  the method parameters type can't be changed due to main logic
 */
class StupidPoint {
	double x;
	double y;
	
	protected StupidPoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
//	protected StupidPoint(double rho, double theta) {
//		this.x = rho * Math.cos(theta);
//		this.y = rho * Math.sin(theta);
//	}
}

/*
 *  # 2nd way
 *  This class provide one constructor with extra argument: CoordinateSystem enum
 *  This can solve last problem,
 *  but the down side is that the user should understand what is the meaning of constructor parameters a and b
 */
class TroublesomePoint {
	double x;
	double y;
	
	enum CoordinateSystem {
		CARTESIAN,
		POLAR
	}
	
	protected TroublesomePoint(double a, double b, CoordinateSystem cs) {
		switch (cs)
	    {
	      case CARTESIAN:
	        this.x = a;
	        this.y = b;
	        break;
	      case POLAR:
	        this.x = a * Math.cos(b);
	        this.y = a * Math.sin(b);
	        break;
	    }
	}
}

/*
 * 	# 3rd way
 * 	This class force the user not to use constructor class, but two Factory Methods that we've provided
 * 	These factory methods have been having meaningful names so the user have been guided,
 * 	and won't face any problem like in previous classes
 */
class SuitablePoint {
	double x;
	double y;
	
	private SuitablePoint(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	protected static SuitablePoint createCartesianPoint(double x, double y) {
		return new SuitablePoint(x, y);
	}
	
	protected static SuitablePoint createPolarPoint(double rho, double theta) {
		return new SuitablePoint(rho*Math.cos(theta), rho*Math.sin(theta));
	}
}

class FactoryMotivationDemo {
	@SuppressWarnings("unused")
	public static void main(String[] args) {
		TroublesomePoint point = new TroublesomePoint(2, 3, TroublesomePoint.CoordinateSystem.CARTESIAN);
		SuitablePoint samePoint = SuitablePoint.createCartesianPoint(2, 3);
	}
}