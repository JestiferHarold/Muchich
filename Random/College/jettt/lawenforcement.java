import java.util.Scanner;

public class lawenforcement{
	public static void main(String[] args){
		Scanner prompt = new Scanner(System.in);
		int rows = prompt.nextInt(),
		    f = 1;
		for(int i = 1; i <= rows; i++){
			for(int j = 1; j <= i; j++ , f++){
				System.out.print(f % 2);
			}
			System.out.println("");
		}
	}
}
