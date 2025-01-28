public class Square {

	private double length;

	public Square(double lenght) {
		this.length = length;
	}

	public double calculateArea() {
		return this.length * this.length;
	}
}

class SquareTest {

	public static void main(String[] args) {
		Square s1 = new Square(100.0);
		Square s2 = new Square(102.0);
		System.out.println(s1.calculateArea());
		System.out.println(s2.calculateArea());
	}
}
