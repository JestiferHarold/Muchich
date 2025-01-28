public class Engine  {
	
	public void start() {
		System.out.println("The car has been started");
	}

	public void top() {
		System.out.println("The car has been stopped");
	}
}

class Car {
	private Engine engine;

	public Car(Engine engine) {
		this.engine = new Engine();
	}

	public void start() {
		engine.start();
	}
}

class Main {

	public static void main(String[] args) {
		Engine engine = new Engine();
		Car car = new Car(engine);
		car.start();
	}
}
