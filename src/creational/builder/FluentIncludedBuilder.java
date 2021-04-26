package creational.builder;

class Person {
	String name;
	Integer age = 0;

	public Person withName(String name) {
		this.name = name;
		return this;
	}

	public Person withAge(Integer age) {
		this.age = age;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Hello,");
		if (this.name != null)
			sb.append(String.format(" I am %s.", this.name));
		if (this.age > 0)
			sb.append(String.format(" I am %d years old.", this.age));
		return sb.toString();
	}
}

class DemoFluentIncludedBuilder {
	public static void main(String[] args) {
		System.out.println("DemoFluentIncludedBuilder");
		
		Person abay = new Person().withName("abay")
								  .withAge(25);
		System.out.println(abay);

		Person moya = new Person().withName("moya");
		System.out.println(moya);

		Person someone = new Person().withAge(50);
		System.out.println(someone);
	}
}
