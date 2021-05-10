package creational.prototype;

import java.util.Arrays;

class AddressCloneable implements Cloneable {
	public String streetName;
	public int houseNumber;
	
	public AddressCloneable(String streetName, int houseNumber) {
		this.streetName = streetName;
		this.houseNumber = houseNumber;
	}
	
	@Override
	public String toString() {
		return "Address{" +
				"streetName='" + streetName + '\'' +
				", houseNumber=" + houseNumber +
				'}';
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new AddressCloneable(streetName, houseNumber);
	}
}

class PersonCloneable implements Cloneable {
	public String [] names;
	public AddressCloneable address;
	
	public PersonCloneable(String[] names, AddressCloneable address) {
		this.names = names;
		this.address = address;
	}
	
	@Override
	public String toString() {
		return "Person{" +
				"names=" + Arrays.toString(names) +
				", address=" + address +
				'}';
	}
	
	@Override
	public Object clone() throws CloneNotSupportedException {
		return new PersonCloneable(
				names.clone(), 
				address instanceof Cloneable ? (AddressCloneable) address.clone() : address);
	}
}

class ImplementsCloneableDemo {
	public static void main(String[] args) throws CloneNotSupportedException {
		PersonCloneable john = new PersonCloneable(
				new String[]{"John", "Smith"},
				new AddressCloneable("London Road", 123)
		);

		// jane is the girl next door
		PersonCloneable jane = (PersonCloneable) john.clone();
		jane.names[0] = "Jane";
		jane.address.houseNumber = 124;
		
		System.out.println(john);
		System.out.println(jane);
	}
}