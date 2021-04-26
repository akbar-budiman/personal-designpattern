package creational.builder;

class Employee {
	String name;
	Integer age = 0;
	
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

class EmployeeBuilder {
	private Employee employee = new Employee();
	
	EmployeeBuilder withName(String name) {
		this.employee.name = name;
		return this;
	}
	
	EmployeeBuilder withAge(int age) {
		this.employee.age = age;
		return this;
	}
	
	Employee build() {
		Employee employeeForResponse = this.employee;
		this.employee = new Employee();
		return employeeForResponse;
	}
}

class DemoFluentExcludedBuilder {
	public static void main(String[] args) {
		EmployeeBuilder eb = new EmployeeBuilder();
		
		Employee abay = eb.withName("abay")
						  .withAge(25)
						  .build();
		System.out.println(abay);
		
		Employee moya = eb.withName("moya")
						  .build();
		System.out.println(moya);
		
		Employee someone = eb.withAge(50)
							 .build();
		System.out.println(someone);
	}
}