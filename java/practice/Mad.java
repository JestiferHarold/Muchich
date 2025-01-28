import java.util.Arrays;

public class Mad{

	public static void main(String[] args){
	
		//int[] fstring = new int[10];
		int[] fstring = {13,2,2,41,12,31,412,312,31231,31231};
		Arrays.sort(fstring);
		int m = Arrays.binarySearch(fstring, 2);
		System.out.println(m);
		System.out.println(Arrays.toString(fstring));
	}
}
