import java.util.ArrayList;
import java.util.List;

final class Student {
	
	public String name;
	private List<Course> courses = new ArrayList<>();

	public Student(String name) {
		this.name = name;
	}

	public void enrollCourse(Course course) {
		courses.add(course);
		course.addStudent(this);	
	}

	public String getName() {
		return name;
	}

	public void getCourses() {
		System.out.println(name + " has enrolled in");
		for(Course course : courses) {
			System.out.println("- " + course.getName());
		}
	}
}

final class Course {

	private String name;
	private List<Student> students = new ArrayList<>();
	
	public Course(String name) {
		this.name = name;
	}

	public void addStudent(Student stud) {
		students.add(stud);
	}

	public String getName() {
		return name;
	}

	public void getStudents() {
		System.out.println(name + " has the following Students");
		for(Student student : students) {
			System.out.println("- " + student.getName());
		}
	}
}

final public class Courtsy {

	public static void main(String[] args) {
		Course maths = new Course("Discrete Maths");
		Student sanjay = new Student("Sanjay");
		sanjay.enrollCourse(maths);
		sanjay.getCourses();
		maths.getStudents();
	}
}
