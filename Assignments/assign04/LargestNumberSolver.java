package assign04;

import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.*;

/**
 *
 * @author Josh Schell
 */
public class LargestNumberSolver {

    /**
     * InsertionSort
     *
     * @param arr array to be sorted
     * @param cmp comparator object to compare items within the array
     * @param <T> type of array and items held within said array
     */
    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp) {

        if(cmp == null)
            throw new IllegalArgumentException("no cmp");

        for(int i = 1; i < arr.length; i++) {
            for(int j = i; j > 0; j--) {
                if(cmp.compare(arr[j - 1], arr[j]) > 0) {
                    T tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }
                else
                    break;
            }
        }
    }

    /**
     * This method returns the largest number that can be formed by arranging the integers of the given array, in any order.
     * If the array is empty, the largest number that can be formed is 0.
     * This method must not alter the given array and must call your insertionSort method
     * with a Comparator or lambda expression that you design.
     *
     * @param arr array with random Integer object values
     * @return a single BigInteger of the biggest possible number that can be formed from array
     */
    public static BigInteger findLargestNumber(Integer[] arr) {

        Integer[] temp = arr.clone();

        insertionSort(temp, (o1, o2) -> {
            StringBuilder lhs = new StringBuilder();
            StringBuilder rhs = new StringBuilder();
            return Integer.compare(lhs.append(o1).append(o2).compareTo(rhs.append(o2).append(o1)), 0);
        });

        StringBuilder sb = new StringBuilder();
        for(int i = temp.length - 1; i >= 0; i--) {
            sb.append(temp[i]);
        }

        return new BigInteger(sb.toString());
    }

    /**
     * This method returns the largest int value that can be formed by arranging the integers of the given array, in any order.
     * An OutOfRangeException thrown if the largest number that can be formed is too large for the int data type.
     * Logic for solving the problem of determining the largest number should not appear again in this method
     * — call an existing public method or a helper method.
     * This method must not alter the given array.
     *
     * @param arr array with random Integer object values
     * @return the largest int value that can be formed by arranging the integers of the given array, in any order
     * @throws OutOfRangeException if return is too large for data type
     */
    public static int findLargestInt(Integer[] arr) throws OutOfRangeException {
        BigInteger temp = findLargestNumber(arr);
        if(temp.compareTo(new BigInteger("2147483647")) > 0)
            throw new OutOfRangeException("Big Boy");
        return temp.intValue();
    }

    /**
     * This method behaves the same as the previous method, but for data type long instead of data type int.
     *
     * @param arr array with random Integer object values
     * @return the largest long value that can be formed by arranging the integers of the given array, in any order
     * @throws OutOfRangeException if return is too large for data type
     */
    public static long findLargestLong(Integer[] arr) throws OutOfRangeException {
        BigInteger temp = findLargestNumber(arr);
        if(temp.compareTo(new BigInteger("" + Long.MAX_VALUE)) > 0)
            throw new OutOfRangeException("Big Boy");
        return temp.longValue();
    }

    /**
     * This method sums the largest numbers that can be formed by each array in the given list.
     * This method must not alter the given list.
     *
     * @param list list of random Integer objects
     * @return sum of all largest numbers
     */
    public static BigInteger sum(List<Integer[]> list) {
        BigInteger sum = findLargestNumber(list.get(0));
        BigInteger temp = new BigInteger("0");
        for(int i = 1; i < list.size(); i++) {
            temp = sum.add(findLargestNumber(list.get(i)));
            sum  = temp;
        }
        return sum;
    }

    /**
     * This method determines the kth the largest number that can be formed by each array in the given list.
     * E.g., if k=0 returns the largest overall, if k=list.size()-1 returns the smallest overall.
     * This method returns the original array that represents the kth the largest number, not the kth the largest number itself.
     * An IllegalArgumentException thrown if k is not a valid position in the list.
     * This method must not alter the given list and must call your insertionSort method with a Comparator
     * or lambda expression that you design.
     *
     * @param list Integer array to form largest k from
     * @param k the largest number that can be formed by each array in the given list.
     * @return the original array that represents the kth the largest number, not the kth the largest number itself.
     * @throws IllegalArgumentException if k is not a valid position in the list.
     */
    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException {

        if(k > list.size() - 1)
            throw new IllegalArgumentException("OUtta pocket");

        BigInteger[] temp = new BigInteger[list.size()];

        for(int i = 0; i < list.size(); i++)
            temp[i] = findLargestNumber(list.get(i));

        insertionSort(temp, new Comparator<BigInteger>() {
            @Override
            public int compare(BigInteger o1, BigInteger o2) {
                return o1.compareTo(o2);
            }
        });

        for(Integer[] arr : list) {
            if(findLargestNumber(arr).equals(temp[temp.length - (k + 1)]))
                return arr;
        }

        return new Integer[0];
    }




    private static BigInteger convert(Integer[] arr){

        StringBuilder builder = new StringBuilder();
        for (Integer num : arr){
            builder.append(num);
        }
        return new BigInteger(builder.toString());
    }
    public static Integer[] findKthLargestJava(List<Integer[]> list, int k) throws IllegalArgumentException {

        if(k > list.size() - 1)
            throw new IllegalArgumentException("OUtta pocket");

        BigInteger[] temp = new BigInteger[list.size()];

        for(int i = 0; i < list.size(); i++)
            temp[i] = findLargestNumber(list.get(i));

        Comparator<Integer[]> cmp = new Comparator<>(){
            @Override
            public int compare(Integer[] o1, Integer[] o2) {

                BigInteger n1 = convert(o1);
                BigInteger n2 = convert(o2);

                return n1.compareTo(n2);
            }
        };
        list.sort(cmp);
//        insertionSort(temp, new Comparator<BigInteger>() {
//            @Override
//            public int compare(BigInteger o1, BigInteger o2) {
//                return o1.compareTo(o2);
//            }
//        });

        for(Integer[] arr : list) {
            if(findLargestNumber(arr).equals(temp[temp.length - (k + 1)]))
                return arr;
        }

        return new Integer[0];
    }

    /**
     * This method generates list of integer arrays from an input file,
     * such that each line corresponds to one array of integers separated by blank spaces,
     * and returns an empty list if the file does not exist.
     *
     * @param filename input file to read from
     * @return list of Integers within the file
     */
    public static List<Integer[]> readFile(String filename) {
        List<Integer[]> intList = new ArrayList<>();

        File file = new File(filename);
        try{
            Scanner lineScanner = new Scanner(file);
            while(lineScanner.hasNextLine()) {

                String[] stringArr;
                stringArr = lineScanner.nextLine().split(" ");
                ArrayList<Integer> arr = new ArrayList<>();

                for(String string : stringArr)
                    arr.add( Integer.parseInt(string));

                Integer[] integers = new Integer[arr.size()];
                for(int i = 0; i < arr.size(); i++){
                    integers[i] = arr.get(i);
                }

                intList.add(integers);
            }

            return intList;
        }

        catch (Exception e){
            return new ArrayList<>();
        }
    }

}
