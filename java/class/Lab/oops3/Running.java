import java.util.Scanner;

final class Product {

	private String name;
	private int productId,
		    quantityPurchased;
	private double price;

	public Product (
		String name,
		int productId,
		int quantityPurchased,
		double price
		) {

		this.name = name;
		this.productId = productId;
		this.quantityPurchased = quantityPurchased;
		this.price = price;
	}

	public String getName() {
		return name;
	}

	public int getProductId() {
		return productId;
	}

	public int getQuantityPurchased() {
		return quantityPurchased;
	}

	public double getTotalPrice() {
		return price * quantityPurchased;
	}
}

final class Customer {

	private String name;
	private int customerId;
	private Product[] products;
	private int productCounter = 0;

	public Customer (
		String name,
		int customerId
		) {

		this.name = name;
		this.customerId = customerId;
		this.products = new Product[100];
	}

	public void addProduct(Product product) {
		products[productCounter ++] = product;
	}

	public Product[] getProductsPurchased() {
		return products;
	}

	public double getTotalBill() {
		double total = 0;
		for(Product product : products) {
			total += product.getTotalPrice();
		}
		return total;
	}

	public void displayCustomerDetails() {
		System.out.println("Customer Name : " + name);
		System.out.println("Customer Id : " + customerId);
		System.out.println("_________________________________________________________\nProduct name      | Product ID | QuantityPurchased | TotalPrice");
		for(Product product : products) {
			if (product == null || product == 0) {
				System.out.println("___________________________________________________");
				return;
			}
			System.out.println(product.getName() + " | " + product.getProductId() + " | " + product.getQuantityPurchased() + " | " + product.getTotalPrice());
		}
	}
}

final class ShoppingCart {

	private static Scanner prompt = new Scanner(System.in);
	private static Customer[] customers = new Customer[100];
	private static int customerCount = 0;

	public static void addCustomer() {

		System.out.println("Enter the name of the customer");
		String name = prompt.nextLine();
		System.out.println("Enter the id of the customer");
		int customerId = prompt.nextInt();

		customers[customerCount ++] = new Customer(
						name,
						customerId);
		customerCount --;
		do {
			System.out.println("Enter te name of the product");
			prompt.nextLine();
			String name2 = prompt.nextLine();
			System.out.println("Enter the Id of the product");
			int id = prompt.nextInt();
			System.out.println("Enter the quantity purchased");
			int quantity = prompt.nextInt();
			System.out.println("Enter the rate of the product");
			double price = prompt.nextDouble();
			customers[customerCount].addProduct( new Product (
								name2,
								id,
								quantity,
								price));
			System.out.println("To exit enter 0");
			if (prompt.nextInt() == 0) {
				break;
			}
		} while (true);

		customerCount ++;
	}

	public static void displayAllTheCustomers() {
		System.out.println("-----------CUSTOMERS----------");
		for(Customer customer : customers) {
			if (customer == null) {
				return;
			}
			customer.displayCustomerDetails();
		}
	}

	public static void displayHighestPurchase() {
		if (customerCount == 0) {
			System.out.println("No customers");
			return;
		}

		Customer customer = customers[0];
	
		for(Customer customer2 : customers) {
			customer = customer.getTotalBill() < customer2.getTotalBill() ? customer2 : customer;
		}

		System.out.println("The highest paying customer");
		customer.displayCustomerDetails();
	}

	public static void main(String[] args) {
		addCustomer();
		displayAllTheCustomers();
		displayHighestPurchase();
	}
}


final public class Running {
	public static void main(String[] args) {
	}
}

