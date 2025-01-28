import java.util.HashMap;
import java.util.Arrays;

public class ArrayLevel3{

	public static void moveZerosToTheEnd(){
		
		int[] array = {1, 2, 0, 4, 3, 0, 5, 0};
		for(int i = 0; i < array.length; i++){
			if(array[i] == 0){
				int m = i;
				while(m < array.length - 1){
					int f = array[m + 1];
					array[m + 1] = 0;
					array[m] = f;
					m ++;
				}
			}
		}
		System.out.println(Arrays.toString(array));
	}

	public static void subarrayWithGivenSum(int sum){
		int[] array = {1,2,3,7,5};
		for (int i = 0; i < array.length; i++){
			int summedUp = 0,
			    m = i;
			while (m < array.length - 1){
				summedUp += array[m];
				m++;
				if (summedUp == sum){
					System.out.println("The starting index is " + (i + 1)+ " the ending index is " + (m));
					return;
				}
			}
		}
	}

	public static void smallestMissingNumber(){

		int[] array = {0, 1, 2, 6, 9};
		for(int i = 0; i < array.length; i++){
			if (array[i] != i){
				System.out.println("The smallest missing number is " + i);
				return;
			}
		}
		System.out.println("The smallest missing number is " + array.length);
	}

public static void longestSubArrayWithEqualNumberOfZerosAndOnes() {
    int[] array = {0, 1, 0, 1, 0, 1};

    // Step 1: Transform 0s to -1s
    for (int i = 0; i < array.length; i++) {
        if (array[i] == 0) {
            array[i] = -1;
        }
    }

    // Step 2: Use HashMap to track the first occurrence of each cumulative sum
    Map<Integer, Integer> map = new HashMap<>();
    int maxLength = 0, cumulativeSum = 0;

    // Initialize map for subarray starting at index 0
    map.put(0, -1);

    for (int i = 0; i < array.length; i++) {
        cumulativeSum += array[i];

        if (map.containsKey(cumulativeSum)) {
            // If the cumulative sum is seen before, calculate the subarray length
            maxLength = Math.max(maxLength, i - map.get(cumulativeSum));
        } else {
            // Store the first occurrence of the cumulative sum
            map.put(cumulativeSum, i);
        }
    }

    System.out.println("The longest subarray with equal number of zeros and ones is " + maxLength);
}

	public static void main(String[] args){
		moveZerosToTheEnd();
		subarrayWithGivenSum(12);
		longestSubArrayWithEqualNumberOfZerosAndOnes();
		smallestMissingNumber();
	}
}
