package solid.isp;

/*
 * #4 SOLID : Interface Segregation Principle (ISP)
 * "Client should not be forced to depend on methods it does not use."
 */

public class After {

	static class Document {
	};
	
	static interface Printer {
		void print(Document d);
	}
	
	static interface Scanner {
		void scan(Document d);
	}
	
	static interface FaxMachine {
		void fax(Document d);
	}
	
	static class MultiFunctionDevice implements Printer, Scanner, FaxMachine {
		@Override
		public void print(Document d) {
			// implementation goes here
		}
		
		@Override
		public void scan(Document d) {
			// implementation goes here
		}

		@Override
		public void fax(Document d) {
			// implementation goes here
		}
	}
	
	static class OldFashionPrinter implements Printer {
		@Override
		public void print(Document d) {
			// implementation goes here
		}
	}
	
	static interface PhotocopyMachine extends Printer, Scanner {
	}
	
	static class PhotocopyDevice implements PhotocopyMachine {
		@Override
		public void print(Document d) {
			// implementation goes here
		}

		@Override
		public void scan(Document d) {
			// implementation goes here
		}
	}
	
}
