final class Square {
    private double length;

    public Square(double length) {
        this.length = length;
    }

    public double calculateArea() {
        return this.length * this.length;
    }
}

final class Employee {
	public String name;
	private int Id;
	private double BP,
		       HRA,
		       DA,
		       TA;

	public Employee(
		String name,
		int Id,
		double BP,
		double HRA,
		double DA,
		double TA
		) {
		
		this.name = name;
		this.Id = Id;
		this.BP = BP;
		this.HRA = HRA;
		this.DA = DA;
		this.TA = TA;
	}

	public int getId() {
		return Id;
	}

	public double getBP() {
		return BP;
	}

	public double getHRA() {
		return HRA;
	}

	public double getDA() {
		return DA;
	}

	public double getTA() {
		return TA;
	}

	public double calculateGrossSalary() {
		return getBP() + getHRA() + getDA() + getTA();
	}
                   
	public void employeeWithHighestGrossSalary(Employee employees[]) {
		Employee holder = this;
		for(Employee employee : employees) {
			holder = employee.calculateGrossSalary() > holder.calculateGrossSalary() ? employee : holder;
		}
		System.out.println("The employee " + holder.name + " has the highest gross salary of " + holder.calculateGrossSalary());
	}

	@Override 
	public String toString() {
		return name + " has a total salary of " + calculateGrossSalary();
	}
}

final class BankAccount {
    private int AccountNumber;
    private double Balance;
    public String AccountType,
                  Name;
    
    public BankAccount(String Name,
                       String AccountType,
                       int AccountNumber,
                       double Balance) {
        this.AccountNumber = AccountNumber;
        this.Balance = Balance;
        this.AccountType = AccountType;
        this.Name = Name;
    }

    public void deposite(float amount) {
        this.Balance += amount;
    }

    public boolean withDraw(float amount) {
        if (amount > this.Balance) {
            return false;
        }

        this.Balance -= amount;
        return true;
    }

    public String displayBalance() {
        return this.Balance + "₹ is the balance";
    }
}

final class Library {
    public String bookName,
                  authorName;
        
    public double price;
    public int numberOfCopies;

    public void setDetails(
        String bookName,
        String authorName,
        double price,
        int numberOfCopies
        ) {

        this.authorName = authorName;
        this.bookName = bookName;
        this.price = price;
        this.numberOfCopies = numberOfCopies;
    }
    
    public void issueBook() {
        this.numberOfCopies --;
    }

    public void returnBook() {
        this.numberOfCopies ++;
    }

    @Override 
    public String toString() {
	    return authorName + "'s book " + bookName + " has " + numberOfCopies + " which sell at the low price of " + price + " rupees";
    }
}

final class Car {
    public String Brand,
                  Model;
    
    public double milage,
                  price;

    public void setDetails
        (
        String Brand,
        String Model,
        double milage,
        double price
        ) {
        
        this.Brand = Brand;
        this.Model = Model;
        this.milage = milage;
        this.price = price;
    }

    // @Override
    // public int hashCode() {

    // }

    @Override
    public String toString() {
        return "The Milage is " + this.milage;
    }

    public void compareMilage(Car[] cars) {
        Car holder = this;

        // double milage = 0.0;
        for(Car car : cars) {
            holder = car.milage > holder.milage ? car : holder;
            // if (holder == car) {
            //     milage = car.milage;
            // }
        }
	System.out.println(holder.Brand + " " + holder.Model + " has higher milage of " + holder.milage );
    }
}

final public class Run {
	
//	private static Employee emp1, emp2;
//	final private static Employee[] employees = {emp1, emp2};

	//static Car car1, car2;
//	final private static Car[] cars = {car1, car2};

	public static void squareClassDemo() {
		System.out.println("This is the demo for square Class");
		Square square = new Square(10);
		System.out.println("The area of the square is " + square.calculateArea());
		System.out.println("End of the Square class demo");
	}

	public static void bankAccountClassDemo() {
		System.out.println("This is the demo for BankAccount class");
		BankAccount acc = new BankAccount(
					"P Sanjay",
					"Savings",
					8073123,
					1000.00);
		System.out.println(acc.displayBalance() + " is the money before deposit");
		acc.deposite(102);
		System.out.println(acc.displayBalance() + " is the money after deposite");
		if (acc.withDraw(1000)) {
			System.out.println("1000 Rupees has been debited");
		}

		System.out.println(acc.displayBalance());
		System.out.println("The end of BankAccount class demo");
	}

	public static void libraryClassDemo() {
		System.out.println("This is a demo for library class");	
		Library book = new Library();
		book.setDetails
			(
			"Sanjay",
			"communist manifesto",
			100.23,
			12
			);
		System.out.println("An book has been issued");	
		System.out.println(book.toString());
		book.issueBook();
		System.out.println(book.toString());
		System.out.println("An book has been returned");
		book.returnBook();
		System.out.println(book.toString());
		System.out.println("The end of Library class demo");
	}

	public static void carClassDemo() {
		System.out.println("This is the demo of the car class");
		Car car1 = new Car();
		car1.setDetails(
			"Toyoto",
			"Corolla",
			52.00,
			475000.00
			);
		Car car2 = new Car();
		car2.setDetails(
			"Toyoto",
			"Fortuner",
			66.00,
			4890000.00
			);
		Car[] cars = {car1, car2};
		System.out.println(car1.toString());	
		System.out.println(car2.toString());
		car1.compareMilage(cars);
		System.out.println("The end of car class demo");
	}

	public static void employeeClassDemo() {
		System.out.println("This is the demo of Employee class");
		Employee emp1 = new Employee(
			"Sanjay",
			100,
			10000,
			2323,
			12341,
			515);	
		Employee emp2 = new Employee(
				"Priyan",
				101,
				10002,
				2133,
				414,
				51515);
		Employee[] employees = {emp1, emp2};
		System.out.println(emp1.toString());
		System.out.println(emp2.toString());
		emp1.employeeWithHighestGrossSalary(employees);
		System.out.println("The end of employee class demo");
	}

	public static void main(String[] args) {
		squareClassDemo();
		employeeClassDemo();
		bankAccountClassDemo();
		libraryClassDemo();
		carClassDemo();
	}
}
