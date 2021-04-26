package solid.dip;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.javatuples.Triplet;

/*
 * #5 SOLID : Dependency Inversion Principle (DIP)
 * "High-level modules should not depend on low-level modules.
 * Both should depend on abstractions."
 */

class After {
	static enum Relationship {
		PARENT, CHILD, SIBLING
	}

	static class Person {
		public String name;

		public Person(String name) {
			this.name = name;
		}
	}

	interface RelationshipBrowser {
		List<Person> findAllChildrenOf(String name);
	}

	static class Relationships implements RelationshipBrowser {
		private List<Triplet<Person, Relationship, Person>> relations = new ArrayList<>();

		public List<Triplet<Person, Relationship, Person>> getRelations() {
			return relations;
		}

		public void addParentAndChild(Person parent, Person child) {
			relations.add(new Triplet<>(parent, Relationship.PARENT, child));
			relations.add(new Triplet<>(child, Relationship.CHILD, parent));
		}

		@Override
		public List<Person> findAllChildrenOf(String name) {
			return relations.stream()
				      .filter(x -> Objects.equals(x.getValue0().name, name) && x.getValue1() == Relationship.PARENT)
				      .map(Triplet::getValue2)
				      .collect(Collectors.toList());
		}
	}

	static class Research {
		public Research(RelationshipBrowser browser) {
		    List<Person> children = browser.findAllChildrenOf("John");
		    for (Person child : children)
		      System.out.println("John has a child called " + child.name);
		}
	}

	static class DIPDemoAfter {
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
