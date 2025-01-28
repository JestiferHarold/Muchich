class Passport {
	private int passportNumber;

	public Passport(int passportNumber) {
		this.passportNumber = passportNumber;
	}

	public int returnPassportNumber() {
		return passportNumber;
	}
}

class Person {
	
	public String name;
	private Passport passport;

	public Person(String name, Passport passport) {
		this.name = name;
		this.passport = passport;
	}

	@Override 
	public String toString() {
		return name + "'s passport number is " + passport.returnPassportNumber();
	}
}

public class Mosfet {
	public static void main(String[] args) {
		Passport pass1 = new Passport(100);
		Person pers1 = new Person("Sanjay", pass1);
		System.out.println(pers1.toString());
	}
}
