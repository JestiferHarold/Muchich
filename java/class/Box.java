import java.util.*;

public class Box {

	private double height;
	private double width;
	
	public Box() {
		Scanner prompt = new Scanner(System.in);
		System.out.println("Enter the height of the box");
		this.height = prompt.nextDouble();
		System.out.println("Enter the width of the box");
		this.width = prompt.nextDouble();
	}
	
	public double areaOfTheBox() {
		return this.height * this.width;
	}

	public double getWidth() {
		return this.width;
	}

	public double getHeight() {
		return this.Height;
	}
}
