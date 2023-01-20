package lab01;

/**
 * This class contains a utility method for finding the smallest difference.
 * 
 * @author Erin Parker & ??
 * @version January 21, 2022
 */
public class DiffUtil {


	public static int currDiff(int s, int t) {
		return Math.abs(s - t);
	}
	/**
	 * Computes and returns the smallest difference (absolute value of subtraction) 
	 * among every pair of integers in the input array.
	 * @param arr - input array of integers
	 * @throws IllegalArgumentException - if the array contains less than two items
	 */
	public static int findSmallestDiff(int[] arr) {
		int smallistDiff = 1000, currDiff;

		for(int i = 0; i < arr.length; i++){
			for(int j = 0; j < arr.length; j++){
				currDiff = currDiff(arr[i],arr[j]);
				if(currDiff < smallistDiff){
					smallistDiff = currDiff;
				}
			}
		}


		return smallistDiff;
	}	
}
