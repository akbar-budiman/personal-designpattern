package solid.ocp;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Stream;

public class Before {

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
		
	static class ProductFilter {
		public Stream<Product> filterByColor(List<Product> products, Color color) {
			return products.stream().filter(p -> p.color == color);
		}

		public Stream<Product> filterBySize(List<Product> products, Size size) {
			return products.stream().filter(p -> p.size == size);
		}

		public Stream<Product> filterBySizeAndColor(List<Product> products, Size size, Color color) {
			return products.stream().filter(p -> p.size == size && p.color == color);
		}
		
//		new specification implementation goes here as a new method
	}
	
	public static void main(String[] args) {
		Product pen = new Product("Pen", Color.RED, Size.SMALL, new BigDecimal(2000));
	    Product book = new Product("Book", Color.GREEN, Size.MEDIUM, new BigDecimal(5000));
	    Product laptop = new Product("Laptop", Color.BLUE, Size.LARGE, new BigDecimal(5000000));

	    List<Product> products = List.of(pen, book, laptop);

	    ProductFilter pf = new ProductFilter();
	    System.out.println("Green products (old):");
	    pf.filterByColor(products, Color.GREEN)
	      .forEach(p -> System.out.println(" - " + p.name + " is green"));
	}
	
}
