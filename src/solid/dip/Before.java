package solid.dip;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Triplet;

/*
 * #5 SOLID : Dependency Inversion Principle (DIP)
 * "High-level modules should not depend on low-level modules.
 * Both should depend on abstractions."
 */

class Before {
	static enum Relationship {
		PARENT, CHILD, SIBLING
	}

	static class Person {
		public String name;

		public Person(String name) {
			this.name = name;
		}
	}

	static class Relationships {
		private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

		public List<Triplet<Person, Relationship, Person>> getRelations() {
			return relations;
		}

		public void addParentAndChild(Person parent, Person child) {
			relations.add(new Triplet<>(parent, Relationship.PARENT, child));
			relations.add(new Triplet<>(child, Relationship.CHILD, parent));
		}
	}

	static class Research {
		public Research(Relationships relationships) {
			// high-level: find all of john's children
			List<Triplet<Person, Relationship, Person>> relations = relationships.getRelations();
			relations.stream().filter(x -> x.getValue0().name.equals("John") && x.getValue1() == Relationship.PARENT)
					.forEach(ch -> System.out.println("John has a child called " + ch.getValue2().name));
		}
//		this method also cause that if the Relationships class is changed, this method/class should be changed too, violates OCP
	}

	static class DIPDemoBefore {
		public static void main(String[] args) {
		    Person parent = new Person("John");
		    Person child1 = new Person("Chris");
		    Person child2 = new Person("Matt");

		    // low-level module
		    Relationships relationships = new Relationships();
		    relationships.addParentAndChild(parent, child1);
		    relationships.addParentAndChild(parent, child2);

		    new Research(relationships);
		}
	}
}
