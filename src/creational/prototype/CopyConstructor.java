package creational.prototype;

import java.util.Arrays;

class AddressCopy {
	public String streetName;
	public int houseNumber;
	
	public AddressCopy(String streetName, int houseNumber) {
		this.streetName = streetName;
		this.houseNumber = houseNumber;
	}
	
	public AddressCopy(AddressCopy other) {
		this(other.streetName, other.houseNumber);
	}
	
	@Override
	public String toString() {
		return "Address{" +
				"streetName='" + streetName + '\'' +
				", houseNumber=" + houseNumber +
				'}';
	}
}

class PersonCopy {
	public String [] names;
	public AddressCopy address;
	
	public PersonCopy(String[] names, AddressCopy address) {
		this.names = names;
		this.address = address;
	}
	
	public PersonCopy(PersonCopy other) {
		this(other.names.clone(), new AddressCopy(other.address));
	}
	
	@Override
	public String toString() {
		return "Person{" +
				"names=" + Arrays.toString(names) +
				", address=" + address +
				'}';
	}
}

class CopyConstructorDemo {
	public static void main(String[] args) {
		PersonCopy john = new PersonCopy(
				new String[]{"John", "Smith"},
				new AddressCopy("London Road", 123)
		);

		// jane is the girl next door
		PersonCopy jane = new PersonCopy(john);
		jane.names[0] = "Jane";
		jane.address.houseNumber = 124;
		
		System.out.println(john);
		System.out.println(jane);
	}
}