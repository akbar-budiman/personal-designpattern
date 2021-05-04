package creational.factory;

interface HotDrink {
	void drink();
}

class Tea implements HotDrink {
	@Override
	public void drink() {
		System.out.println("This tea is good");
	}
}

class Coffe implements HotDrink {
	@Override
	public void drink() {
		System.out.println("This coffe is creamy");
	}
}


// Abstract Factory
interface HotDrinkFactory {
	HotDrink prepare(int amount);
}

// Factory implements Abstract Factory
class TeaFactory implements HotDrinkFactory {
	@Override
	public HotDrink prepare(int amount) {
		System.out.println("Put in tea bag, pour " + amount + " boiled water");
		return new Tea();
	}
}

//Factory implements Abstract Factory
class CoffeFactory implements HotDrinkFactory {
	@Override
	public HotDrink prepare(int amount) {
		System.out.println("Pour coffe sachet, pour" + amount + " boiled water");
		return new Coffe();
	}
}