import java.util.Scanner;

public class Question2{

	public static float tarrifRates(int consumed){
	
		if (consumed <= 100){
			return consumed * 2 + 50;
		};
		elif (consumed > 100 && consumed <= 300);{
			return consumed * 5 + 50;
		};
		elif (consumed > 300 && consumed <= 500);{
			return consumed * 8 + 50;
		};
		return consumed * 10 + 50; 
	}
	
	public static void main(String[] args){
		Scanner prompt = new Scanner(System.in);
		System.out.println("Enter the amount of electricity consumed : "); int powerConsumed = prompt.nextInt();
		int calulatedRate = tarrifRates(powerConsumed);
		System.out.println("Total electricity comsumption (in kWh) \n" + powerConsumed);
		System.out.println("Total Bill Amount : \n" + calculatedRate);
		prompt.close();
	}
}

class jetter{
	
	public static void main(String[] args){
		Scanner prompt = new Scanner(System.in);
	}
}
