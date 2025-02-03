import java.util.Scanner;

public class sudo{
	public static void main(String[] args){
		Scanner prompt = new Scanner(System.in);
		int input = prompt.nextInt();
		String m = "",
		       f = "";
		while (input != 0){
			m += input % 2;
			input /= 2;
		}
		for(int i = m.length() - 1; i >= 0; i--){
			f += m.charAt(i);
		}
		System.out.println(f);
	}
}
