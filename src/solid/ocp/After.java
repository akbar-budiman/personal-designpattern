package solid.ocp;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

public class After {

	enum Color {RED, GREEN, BLUE}
	
	enum Size {SMALL, MEDIUM, LARGE, HUGE}

	static class Product {
		public String name;
		public Color color;
		public Size size;
		public BigDecimal price;
		
		public Product(String name, Color color, Size size, BigDecimal price) {
			this.name = name;
			this.color = color;
			this.size = size;
			this.price = price;
		}
	}
	
	interface Specification<T> {
		boolean isSatisfied(T item);
	}

	interface Filter<T> {
		Stream<T> filter(List<T> items, Specification<T> spec);
	}

	static class MultipleSpecification<T> implements Specification<T> {

		private List<Specification<T>> specificationList;
		private boolean satisfied = true;

		public MultipleSpecification(List<Specification<T>> specificationList) {
			this.specificationList = specificationList;
		}

		@Override
		public boolean isSatisfied(T item) {
			this.specificationList.forEach(spec -> this.satisfied = this.satisfied && spec.isSatisfied(item));
			return this.satisfied;
		}

	}

	static class ColorSpecification implements Specification<Product> {
		private Color color;

		public ColorSpecification(Color color) {
			this.color = color;
		}

		@Override
		public boolean isSatisfied(Product p) {
			return p.color.equals(this.color);
		}
	}

	static class SizeSpecification implements Specification<Product> {
		private Size size;

		public SizeSpecification(Size size) {
			this.size = size;
		}

		@Override
		public boolean isSatisfied(Product p) {
			return p.size.equals(this.size);
		}
	}

	static class MinimumPriceSpecification implements Specification<Product> {
		private BigDecimal minimumPrice;

		public MinimumPriceSpecification(BigDecimal minimumPrice) {
			this.minimumPrice = minimumPrice;
		}

		@Override
		public boolean isSatisfied(Product item) {
			return item.price.compareTo(this.minimumPrice) >= 0;
		}
	}

	static class MaximumPriceSpecification implements Specification<Product> {
		private BigDecimal maximumPrice;

		public MaximumPriceSpecification(BigDecimal maximumPrice) {
			this.maximumPrice = maximumPrice;
		}

		@Override
		public boolean isSatisfied(Product item) {
			return item.price.compareTo(this.maximumPrice) <= 0;
		}
	}
	
//	new specification implementation goes here as a new class implementing Specification

	static class BetterFilter implements Filter<Product> {
		@Override
		public Stream<Product> filter(List<Product> items, Specification<Product> spec) {
			return items.stream().filter(p -> spec.isSatisfied(p));
		}
	}

	public static void main(String[] args) {
		Product pen = new Product("Pen", Color.RED, Size.SMALL, new BigDecimal(2000));
	    Product book = new Product("Book", Color.GREEN, Size.MEDIUM, new BigDecimal(5000));
	    Product laptop = new Product("Laptop", Color.BLUE, Size.LARGE, new BigDecimal(5000000));

	    List<Product> products = List.of(pen, book, laptop);
	    
		BetterFilter bf = new BetterFilter();
		System.out.println("Green products (new):");
		bf.filter(products, new ColorSpecification(Color.GREEN))
				.forEach(p -> System.out.println(" - " + p.name + " is green"));

		System.out.println("Large products:");
		bf.filter(products, new SizeSpecification(Size.LARGE))
				.forEach(p -> System.out.println(" - " + p.name + " is large"));

		System.out.println("Large blue items:");
		bf.filter(
				products,
				new MultipleSpecification<>(
						List.of(new MinimumPriceSpecification(BigDecimal.ZERO), 
								new MaximumPriceSpecification(new BigDecimal(10000)))))
		  .forEach(p -> System.out.println(" - " + p.name + "'s price is between 0 and 10.000"));
	}
	
}
