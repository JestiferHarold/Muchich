import java.util.Arrays;
public class Jeff{
	public static void main(String[] args){
		int[] array1 = {1,2,4,11,51,5,5,1,61,61,61,61};
		Arrays.sort(array1);
		int[] array2 = Arrays.copyOf(array1, array1.length);
		System.out.println(Arrays.toString(array2));
		int[] array3 = Arrays.copyOfRange(array1, 3, 4);
		System.out.println(Arrays.toString(array3));
		int demigod = Arrays.binarySearch(array1, 10);
		System.out.println(Arrays.toString(array1));	
		Arrays.fill(array1, 0);
		System.out.println(Arrays.toString(array1));
		Arrays.fill(array1, 0, 3, 4);
		System.out.println(Arrays.toString(array1));
		System.out.println(demigod == -10);
		System.out.println(demigod);
	}
}
