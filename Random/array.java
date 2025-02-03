public class array{
	
	private static int[] array1 = {1,2,3,4,5,6,7,8};

	public static void sumAverage(){
		int sum = 0,
		    average;
		for (int i : array1){
			sum += i;
		}
		average = sum / array1.length;
	}

	public static void sumOfEvenNumbers(){
		int evenSum = 0;
		for (int i : array1){
			if (i % 2 == 0){
				evenSum += i;
			}
		}
		System.out.println(evenSum);
	}

	public static boolean search(int element){
		
		for (int i : array1){
			if (i == element){
				return true;
			}
		}
		return false;
	}

	public static void printAlt(){
		int i = 0;
		for (int j : array1){
			if (i % 2 == 0){
				System.out.println(j);
			}
			i ++;
		}
	
	public static void minMax(){
		int min = 0,
		    max = 0;
		for (int i : array1){
			if (i < min){
				min = i;
			}if (i > max){
				max = i;
			}
		}
		System.out.println(min + " Min " + max " Max");
		System.out.println(array1.MIN_INDEX)
	}

	public static void secondMinSecondMax(){
		int[] sudo = array1.
