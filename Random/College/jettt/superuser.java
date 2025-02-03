import java.util.Scanner;

public class superuser{
	public static void main(String[] args){
		Scanner prompt = new Scanner(System.in);
		int number = prompt.nextInt();
		for(int i = 2; i < number; i++){
			if ((i * i) + (number - 1) * (number -1) < number * number){
				continue;
			}
			for(int k = number - 1; k > i; k--){
				if ((i * i) + (k * k) == (number * number)){
					System.out.println(number + " is a pythagoran number with " + i + " and " + k);
					System.exit(0);
				}
			}
		}
		System.out.println(number + " is not an pythagorian number");
	}
}
