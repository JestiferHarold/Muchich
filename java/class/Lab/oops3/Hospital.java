import java.util.Scanner;

final class Patient {

	private String patientName;
	private int patientId;
	private int patientAge;
	private String dieaseName;

	public Patient() {}

	public Patient(
		String name,
		int id,
		int age,
		String diease
		) {

		patientName = name;
		patientId = id;
		patientAge = age;
		dieaseName = name;
	}

	public String getPatientName() {
		return patientName;
	}

	public int getPatientId() {
		return patientId;
	}

	public int getPatientAge() {
		return patientAge;
	}

	public String getDieaseName() {
		return dieaseName;
	}

	@Override 
	public String toString() {
		return patientName + " " + patientId + " " + patientAge + " " + dieaseName;
	}
}

final class Department {

	private String departmentName;
	private static Patient[] patients = new Patient[100];
	private static int patientCounter = 0;

	public Department(
			String departmentName
			) {
		this.departmentName = departmentName;
	}

	public Patient[] getPatients() {
		return patients;
	}

	public void addPatient(Patient patient) {
		patients[patientCounter ++] = patient;
	}

	public String getDepartmentName() {
		return departmentName;
	}

	public void displayPatients() {
		System.out.println(departmentName + " has " + patients.length + " number of patients");
		for (Patient patient : patients) {
			System.out.println(patient.getPatientName() + "'s Id is " + patient.getPatientId() + " aged " + patient.getPatientAge() + " has diease " + patient.getDieaseName());
		}
	}

	//Get it via name
	public Patient getPatient(String target) {
		for (Patient patient : patients) {
			if (patient.getPatientName().equals(target)) {
				return patient;
			}
		}
		return new Patient();
	}
}

final public class Hospital {
	
	private static Scanner prompt = new Scanner(System.in);	
	private static Department departments = new departments[100];
	private static int departmentCounter = 0;

	public static void displayAllDepartmentsAndPatients() {
		for (Department department : departments) {
			if (department == null) {
				return;
			}
			department.displayPatients();
		}
	}

	public static void displayPatientsOfACertainDepartment() {
		System.out.println("Enter the name of the department");
		String target = prompt.nextLine();
		for (Department department : deparments) {
			if (department.getDepartmentName().equals(target)) {
				department.displayPatients();
			}
		}
	}

	public static Patient getPatientDetails() {
		System.out.println("Enter the name of the department");
		String departmentName = prompt.nextLine();
		System.out.println("Enter the name of the Patient");
		String patientName = prompt.nextLine();
		for (Department deparment : deparments) {
			if (deparment.getDeparmentName().equals(departmentName)) {
				for(Patient patient : department.getPatients()) {
					if (patient.getPatientName().equals(target)) {
						return patient;
					}
				}
			}
		}
		return new Patient();
	}
		
	public static void addDepartment() {
		System.out.println("Enter the name of the department");
		String name = prompt.nextLine();
		departments[departmentCounter] = new Department(name); 
		do {
			System.out.println("Enter the name of the patient");
			String patientName = prompt.nextLine();
			System.out.println("Enter the id of the patient");
			int id = prompt.nextInt();
			System.out.println("Enter the age of the patient");
			int age = prompt.nextInt();
			System.out.println("Enter the diease of the patient");
			String diease = prompt.nextLine();
			departments[departmentCounter].addPatient(
					patientName,
					id,
					age,
					diease);
			System.out.println("To exit enter 0");
		} while (prompt.nextInt() != 0);
		departmentCounter ++;
	}
			
	public static void main(String[] args) {
		System.out.println("Welcome to Hospital Management System");
		System.out.println("Enter your option");
		System.out.println("1. Add a department\n2. Display patients of a certain department\n3. Get a patient details of a certain department\n4. Get details of all the departments\n5. Exit");

		switch (prompt.nextInt()) {
			case 1:
				addDepartment();
				break;
			case 2:
				displayPatientsOfACertainDepartment();
				break;
			case 3:
				Patient patient = getPatientDetails();
				if (patient.getName() != null) {
					System.out.println(patient.toString());
				} else {
					System.out.println("Patient Not found");
				}
				break;
			case 4:
				displayAllDepartmentsAndPatients();
				break;
			default:
				System.exit(0);
		}
	}
}

