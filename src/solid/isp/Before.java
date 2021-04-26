package solid.isp;

/*
 * #4 SOLID : Interface Segregation Principle (ISP)
 * "Client should not be forced to depend on methods it does not use."
 */

public class Before {

	static class Document {
	};

	static interface Machine {
		void print(Document d);

		void fax(Document d) throws Exception;

		void scan(Document d) throws Exception;
	}

	static class MultiFunctionDevice implements Machine {
		@Override
		public void print(Document d) {
			// implementation goes here
		}

		@Override
		public void fax(Document d) throws Exception {
			// implementation goes here
		}

		@Override
		public void scan(Document d) throws Exception {
			// implementation goes here
		}
	}
	
	static class OldFashionPrinter implements Machine {
		@Override
		public void print(Document d) {
			// implementation goes here
		}

		@Override
		public void fax(Document d) throws Exception {
			// this class should not be able to do this!
		}

		@Override
		public void scan(Document d) throws Exception {
			// this class should not be able to do this!
		}
	}

	static class PhotocopyDevice implements Machine {
		@Override
		public void print(Document d) {
			// implementation goes here
		}

		@Override
		public void fax(Document d) throws Exception {
			// this class should not be able to do this!
		}

		@Override
		public void scan(Document d) throws Exception {
			// implementation goes here
		}
	}
	
}