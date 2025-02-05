import java.util.Scanner;

final class Patient {
	
	private String patientName;
	private int patientId;
	private int patientAge;
	private String patientDiease;

	public String getName() {
		return patientName;
	}

	public int getId() {
		return patientId;
	}

	public int getAge() {
		return patientAge;
	}

	public String getDiease() {
		return patientDiease;
	}

	public Patient(
		String name,
		int id,
		int age,
		String diease
		) {

		patientName = name;
		patientId = id;
		patientAge = age;
		patientDiease = diease;
	}

	public Patient() {}
}

final class Department {

	private string departmentName;
	private Patient[] patients;

	public Department(){}

	public Department(
		String name,
		int numberOfPatients
		) {

		departmentName = name;
		patients = new Patient[numberOfPatients];
	}

	public String getDeparmentName() {
		return departmentName;
	}

	public void addPatient(Scanner prompt) {
	}

}

final public class Hospital {

 
