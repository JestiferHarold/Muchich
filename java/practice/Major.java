import java.util.ArrayList;

final class Employee {

	private String name;
	
	public Employee(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}

final class Department {
 
	private String name;
	private ArrayList<Employee> employees = new ArrayList<>();
	
	public Department(String name) {
		this.name = name;
	}

	public void addEmployee(Employee e) {
		employees.add(e);
	}

	public void showEmployees() {
		System.out.println(name + " has the following Employees");
		for(Employee employee : employees) {
			System.out.println("- " + employee.getName());
		}
	}
}

final public class Major {
	public static void main(String[] args) {
		Employee emp1 = new Employee("Sanjay");
		Employee emp2 = new Employee("Priyan");
		Department maths = new Department("Mathematics");
		maths.addEmployee(emp1);
		maths.addEmployee(emp2);
		maths.showEmployees();
	}
}
