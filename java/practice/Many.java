class School {

	public String name;

	public School(String name) {
		this.name = name;
	}

	public String getSchoolName() {
		return name;
	}
}

class Student {

	public String name;
	public School school;

	public Student(String name, School school) {
		this.name = name;
		this.school = school;
	}

	@Override
	public String toString() {
		return name + " belongs to this " + school.getSchoolName();
	}
}

public class Many {
	public static void main(String[] args) {
		final School campion = new School("Campion");
		final Student sanjay = new Student("P Sanjay", campion);
		final Student sarang = new Student("Sarang S", campion);
		final Student roshin = new Student("Roshin", campion);
		final Student abhishik = new Student("Abhishek Anil", campion);
		System.out.println(sanjay.toString());
	}
}
