class InvalidAgeException extends Exception {

	public InvalidAgeException(String m) {
		super(m);
	}
}

class InterNetErr extends Exception {

	public InterNetErr(String m) {
		super(m);
	}
}

public class Moff {

	public static void Validate(int age) throws InvalidAgeException {
		
		if (age < 18) {
			throw new InvalidAgeException("CykaBalyat");
		}
	}

	public static void main(String[] args) {
		
		try {
			InvalidAgeException ie = new InvalidAgeException("What the hell");
			ie.initCause(new NullPointerException("Cykabalyay"));
			throw ie;
		}
		catch (InvalidAgeException e) {
			System.out.println("The message is " + e.getMessage());
			System.out.println("Caught exception " + e);
			System.out.println("The root cause " + e.getCause());
		}
	}
}

