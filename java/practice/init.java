class Library {

	String name;

	public Library(String name) {
		this.name = name;
	}

	public String getLibraryName() {
		return this.name;
	}
}

class Student {

	String name;
	Library library;

	public Student(
		String name,
		Library library
		){
	
	this.name = name;
	this.library = library;
	}

	@Override 
	public String toString() {
		return this.name + " belongs to " + library.getLibraryName() + " the library does not know he exists but the student knows the library exists";
	}
}

public class init {
	public static void main(String[] args) {
		Library lib = new Library("Daisy");
		Student stud = new Student("Sanjay", lib);
		System.out.println(stud.toString());
	}
}

