package creational.prototype;

import java.io.Serializable;
import java.util.Arrays;

import org.apache.commons.lang3.SerializationUtils;

class AddressSerializable implements Serializable {
	private static final long serialVersionUID = 1L;
	public String streetName;
	public int houseNumber;
	
	public AddressSerializable(String streetName, int houseNumber) {
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
}

class PersonSerializable implements Serializable {
	private static final long serialVersionUID = 1L;
	public String [] names;
	public AddressSerializable address;
	
	public PersonSerializable(String[] names, AddressSerializable address) {
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
}

class CopyThroughSerializationDemo {
	public static void main(String[] args) {
		PersonSerializable john = new PersonSerializable(
				new String[]{"John", "Smith"},
				new AddressSerializable("London Road", 123)
		);

		// jane is the girl next door
		PersonSerializable jane = SerializationUtils.roundtrip(john);
		jane.names[0] = "Jane";
		jane.address.houseNumber = 124;
		
		System.out.println(john);
		System.out.println(jane);
	}
}
