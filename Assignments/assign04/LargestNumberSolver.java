package assign04;

import java.io.Reader;
import java.math.BigInteger;
import java.util.*;

public class LargestNumberSolver {

    public static <T> void insertionSort(T[] arr, Comparator<? super T> cmp){

            int i = 0, j = 1;
            T tmp;
        while(j<arr.length){
            if(cmp.compare(arr[i],arr[j]) > 0){
                tmp = arr[j];
                arr[j] = arr[i];
                arr[i] = tmp;
            }
        }

    }

    /**
     * Finds the largest number that can be made from an array of integers.
     * @param arr - array of integers.
     * @return Largest Number in form of BigInteger.
     */
    public static BigInteger findLargestNumber(Integer[] arr){

        Integer[] sortedArray = arr.clone();
        insertionSort(sortedArray, new LargeNumberComparator<>());
        return buildInt(sortedArray);
    }

    public static Integer[] findLargestArray(Integer[] arr){
        Integer[] sortedArray = arr.clone();
        insertionSort(sortedArray, new LargeNumberComparator<>());
        return sortedArray;
    }

    /**
     * Builds a string from an array of Integers.
     * @param arr array to be made into a string.
     * @return string made from array.
     */
    public static String buildString(Integer[] arr){
        StringBuilder builder = new StringBuilder();
        for (Integer num: arr) {
            builder.append(Integer.toString(num));
        }
        return builder.toString();
    }

    public static BigInteger buildInt(Integer[] arr){
        return new BigInteger(buildString(arr));
    }
    private static boolean isBiggerThanInt(BigInteger number){
        return number.bitCount() > Integer.SIZE;
    }
    private static boolean isBiggerThanLong(BigInteger number){
        return number.bitCount() > Long.SIZE;
    }


    /**
     * Finds the largest possible integer representation from an array of ints.
     * @param arr array of integer.
     * @return largest possible value.
     * @throws OutOfRangeException if there is no combination of numbers in the int array that are small enough to be an int, throw.
     */
    public static int findLargestInt(Integer[] arr) throws OutOfRangeException{

        BigInteger largestNumber = findLargestNumber(arr);
        Integer[] sortedArr = findLargestArray(arr);
        if(isBiggerThanInt(largestNumber)){
            BigInteger largestInt = largestNumber;
            for(int k = 0; k < arr.length; k++) {
                int j = 1;
                for (int i = 0; i < arr.length - 1; i++) {
                    Integer tmp = sortedArr[i];
                    sortedArr[i] = sortedArr[j];
                    sortedArr[j] = tmp;
                    largestInt = buildInt(sortedArr);
                    if (!isBiggerThanInt(largestInt)) {
                        return largestInt.intValue();
                    }
                    j++;
                }
            }
            throw new OutOfRangeException("No Value is small enough to be in Integers.");
        }
        else{
            return largestNumber.intValueExact();
        }

    }
    public static long findLargestLong(Integer[] arr) throws OutOfRangeException{

        BigInteger largestNumber = findLargestNumber(arr);
        Integer[] sortedArr = findLargestArray(arr);
        if(isBiggerThanLong(largestNumber)){
            BigInteger largestInt = largestNumber;
            for(int k = 0; k < arr.length; k++) {
                int j = 1;
                for (int i = 0; i < arr.length - 1; i++) {
                    Integer tmp = sortedArr[i];
                    sortedArr[i] = sortedArr[j];
                    sortedArr[j] = tmp;
                    largestInt = buildInt(sortedArr);
                    if (!isBiggerThanLong(largestInt)) {
                        return largestInt.longValue();
                    }
                    j++;
                }
            }
            throw new OutOfRangeException("No Value is small enough to be in Integers.");
        }
        else{
            return largestNumber.intValueExact();
        }
    }

    public static BigInteger sum(List<Integer[]> list){
        BigInteger sum = new BigInteger("0");
        for(Integer[] arr: list){
            sum.add(findLargestNumber(arr));
        }
        return sum;
    }

    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException{

        if(k > list.size()) throw new IllegalArgumentException("k too large");
        ArrayList<Integer[]> intList = new ArrayList<>();
        for (Integer[] intArr : list){
            intList.add(findLargestArray(intArr));
        }

//        insertionSort(intList.toArray(), Comparator.comparing(BigInteger));
        return intList.get(k);
    }


    public static List<Integer[]> readFile(String filename){


        Scanner fileScanner = new Scanner(filename);
        List<Integer[]> listOfIntArrays = new ArrayList<>();
        while (fileScanner.hasNextLine()){

            String line = fileScanner.nextLine();
            String[] splitLine = line.split(" ");
            Integer[] intArr = new Integer[splitLine.length];
            for (int i = 0; i < splitLine.length; i++){
                intArr[i] = Integer.parseInt(splitLine[i]);
            }
            listOfIntArrays.add(intArr);
        }

        return listOfIntArrays;
    }
}
