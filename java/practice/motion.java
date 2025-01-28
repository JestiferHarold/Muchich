class NoEngineFoundException extends Exception {
	public NoEngineFoundException(String m) {
		super(m);
	}
}

final class Engine {

	private String type;

	public Engine(String type) {
		this.type = type;
	}

	public void startUp() {
		System.out.println("The " + type + " is starting");
	}

	public String getType() {
		return type;
	}
}

final class Car {

	private String model;
	private Engine engine;

	public Car(String model, String type) {
		this.model = model;
		this.engine = new Engine(type);
	}

	public void startTheCar() {
		System.out.println("The Car is starting");
		engine.startUp();
	}

	public void showDetails() {
		System.out.println("Car Model : " + model);
		System.out.println("Engine Type : " + engine.getType());
	}

	public void garbageCollectEngine() {
		engine = null;
		System.gc();
	}

	public Engine getEngine() {
		return engine;
	}
}

final public class motion {
	public static void main(String[] args) {
		Car car1 = new Car("Tesla", "V6");
		car1.startTheCar();
		car1.showDetails();
		car1.garbageCollectEngine();
		try {
			if (car1.getEngine() == null){
				throw new NoEngineFoundException("Can't invoke method as no engine is found");
			}
			car1.startTheCar();
		}
		
		catch(NoEngineFoundException e) {
			System.out.println(e.getMessage());
		}
	}
}
