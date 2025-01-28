import java.util.*;

class Student {

	String name;
	Teacher teacher;
	
	public Student(String name) {
		this.name = name;
	}

	public void addTeacher(Teacher tea) {
		this.teacher = teacher;
	}

	public String getStudentName() {
		return this.name;
	}

	public String getTeacherName() {
		return this.teacher != null ? this.teacher.getTeacherName() : "No teacher Assigned";
}

class Teacher {

	List<String> students = new ArrayList<>();
	String name;

	public Teacher(String name) {
		this.name = name;
	}

	public String getTeacherName() {
		return this.name;
	}

	public void addStudent(String stud) {
		this.students.add(stud);
	}

	public void allTheStudents() {
		System.out.println(this.name);		
		for(String stud : this.students) {
			System.out.println(stud);
		}
	}
}

public class Dominant {
	public static void main(String[] args) {
		Teacher teach = new Teacher("Shee");
		teach.addStudent(new Student("Sanjay").getStudentName());
		teach.addStudent(new Student("Priyan").getStudentName());
		teach.allTheStudents();
	}
}
