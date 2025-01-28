import java.util.Arrays;

public class vid{

	public static void main(String[] args){

		int[] numbers = {9, 8, 1, 2, 3, 4, 5, 6, 7};
		Arrays.sort(numbers);
		System.out.println(Arrays.toString(numbers));
		int index = Arrays.binarySearch(numbers, 10);
		System.out.println(index);
	}
}
