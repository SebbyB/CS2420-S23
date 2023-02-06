package assign04;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

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
    public static BigInteger findLargestNumber(Integer[] arr){

        insertionSort(arr, new LargeNumberComparator<>());


        BigInteger largestNumber = new BigInteger(buildString(arr));
        return largestNumber;
    }

    public static String buildString(Integer[] arr){
        StringBuilder builder = new StringBuilder();
        for (Integer num: arr) {
            builder.append(Integer.toString(num));
        }
        return builder.toString();
    }

    public static int findLargestInt(Integer[] arr) throws OutOfRangeException{

        BigInteger largestNumber = findLargestNumber(arr);
        if(largestNumber.bitLength() > Integer.SIZE){
            LinkedList<Integer> integerLinkedList = new LinkedList<>();
            for(Integer num: arr){
                integerLinkedList.add(num);
            }
            return 1;
        }
        else{
            return largestNumber.intValueExact();
        }

    }
    public static long findLargestLong(Integer[] arr) throws OutOfRangeException{


        BigInteger largestNumber = findLargestNumber(arr);
        if(largestNumber.bitLength() > Long.SIZE){
            LinkedList<Integer> integerLinkedList = new LinkedList<>();
            for(Integer num: arr){
                integerLinkedList.add(num);
            }
            return 1;
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

    private static void mergeSort(List<BigInteger> list){

        List<BigInteger> sortedList = sort(list);
        list.clear();
        list.addAll(sortedList);
    }

    private static List<BigInteger> merge(List<BigInteger> childLeftSublist, List<BigInteger> childRightSublist){

        childLeftSublist.addAll(childRightSublist);

        return childLeftSublist;
    }

    private static List<BigInteger> sort(List<BigInteger> list){

        if(list.size() > 2){
            List<BigInteger> leftSublist = new ArrayList<>();
            List<BigInteger> rightSublist = new ArrayList<>();
            leftSublist = list.subList(0,list.size()/2 - 1);
            rightSublist = list.subList(list.size()/2,list.size());
            return merge(leftSublist,rightSublist);
        }
        else {
            if(list.get(0).compareTo(list.get(2)) >= 0){
                return list;
            } else {
                BigInteger tmp = list.get(0);
                list.set(0,list.get(1));
                list.set(1,tmp);
                return list;
            }
        }
    }
    public static Integer[] findKthLargest(List<Integer[]> list, int k) throws IllegalArgumentException{

        if(k > list.size()) throw new IllegalArgumentException("k too large");

        ArrayList<BigInteger> numList = new ArrayList<>();
        for(Integer[] arr : list){
            numList.add(findLargestNumber(arr));
        }

//        insertionSort(numList.toArray(), Comparator.comparing(BigInteger));
        return list.get(k);
    }


}
