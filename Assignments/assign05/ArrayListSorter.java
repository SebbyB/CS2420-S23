package assign05;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 *
 * @author Josh Schell and Sebstian Barney
 */
public class ArrayListSorter {

    private static int threshold = 3;
    private static int pivotType = 0;


    /**
     * Sets the threshold for insertion sort.
     * @param n desired threshold
     */
    public static void setThreshold(int n){
        threshold = n;
    }

    /**
     * Sets the pivot type.
     * @param n if n is 1 or 2 it will do something, otherwise it goes to a default case.
     */
    public static void setPivotType(int n){
        pivotType = n;
    }

    /**
     * Insertion sort method to be used after a certain threshold in merge sort.
     *
     * @param arr an array that will be sorted with insertion sort
     * @param <T> the data type of the values within the array
     */
    public static <T extends Comparable<? super T>> void insertionSort(ArrayList<T> arr, int start, int end) {
        for(int i = start; i < end; i++) {
            for(int j = i + 1; j > start; j--) {
                if((arr.get(j-1).compareTo(arr.get(j))) > 0) {
                    T tmp = arr.get(j - 1);
                    arr.set(j - 1, arr.get(j));
                    arr.set(j, tmp);
                }
                else
                    break;
            }
        }
    }


    /**
     *MergeSort driver method. Pass in an array to sort it.
     * @param arr - passed in array.
     * @param <T> - Arbitrary type.
     */
    public static <T extends Comparable<? super T>> void mergesort(ArrayList<T> arr) {
        ArrayList<T> temp = new ArrayList<>();
        for(int i = 0; i < arr.size(); i++)
            temp.add(null);
        recursiveMergeSort(arr, temp, 0, arr.size() - 1);
    }

    /**
     * Recursive method for mergesort.
     * @param arr array to be sorted
     * @param temp temp arr.
     * @param start - sort from start.
     * @param end - to end
     * @param <T> - Arbitrary type.
     */
    private static <T extends Comparable<? super T>> void recursiveMergeSort(ArrayList<T> arr, ArrayList<T> temp, int start, int end) {

        if((end-start) < threshold) {
            insertionSort(arr, start, end);
            return;
        }

        int mid = start + (end - start) / 2;
        recursiveMergeSort(arr, temp, start, mid);
        recursiveMergeSort(arr, temp, mid + 1, end);

        merge(arr, temp, start, mid, end);
    }

    /**
     * Merges two arrays
     * @param arr - array to be merged.
     * @param temp - temp array
     * @param start - start index of array
     * @param mid - middle index of array
     * @param end - end index of array
     * @param <T> - Arbitrary type.
     */
    private static <T extends Comparable<? super T>> void merge(ArrayList<T> arr, ArrayList<T> temp, int start, int mid, int end) {

        int i = start;
        int j = mid + 1;
        int pointer = start;

        while(i <= mid && j <= end) {
            if(arr.get(i).compareTo(arr.get(j)) <= 0)
                temp.set(pointer++, arr.get(i++));
            else
                temp.set(pointer++, arr.get(j++));
        }

        for(int l = i; l <= mid; l++) {
            temp.set(pointer++, arr.get(l));
        }
        for(int r = j; r <= end; r++) {
            temp.set(pointer++, arr.get(r));
        }

        for(int x = 0; x < temp.size(); x++) {
            if (temp.get(x) == null)
                break;
            arr.set(x, temp.get(x));
        }
    }

    /**
     * Pivot method.
     * @param arr -
     * @return
     * @param <T>
     */
    private static <T extends Comparable<? super T>> int pivot(ArrayList<T> arr) {
        int index = 0;
        switch(pivotType) {
            // pivot is first element in arr
            case 1:
                break;
            // pivot is a random element in arr
            case 2:
                Random rng = new Random();
                index = rng.nextInt(arr.size() - 1);
                break;
            // pivot is a sampled median of the first, middle, and last elements in arr, unless arr is smaller
            // than three elements, then just makes the first element the pivot
            default:
                if(arr.size() < 3) {
                    break;
                }

                T first  = arr.get(0);
                T middle = arr.get((arr.size() - 1)/2);
                T end    = arr.get(arr.size() - 1);

                if(first.compareTo(middle) > 0) {
                    if(first.compareTo(end) < 0)
                        break;
                    else if (middle.compareTo(end) > 0)
                        index = (arr.size() - 1)/2;
                    else
                        index = arr.size() - 1;
                }
                else {
                    if(first.compareTo(end) > 0)
                        break;
                    else if (middle.compareTo(end) < 0)
                        index = (arr.size() - 1)/2;
                    else
                        index = arr.size() - 1;
                }
        }
        return index;
    }

    /**
     *Quicksorts an array
     * @param arr array to be sorted
     * @param <T> arbitrary type
     */
    public static <T extends Comparable<? super T>> void quicksort(ArrayList<T> arr) {
        recursiveQuicksort(arr, 0, arr.size() - 1);
    }

    /**
     *Recursive part of quicksort alg.
     * @param arr array to be sorted.
     * @param left left most index to be sorted.
     * @param right right most index to be sorted.
     * @param <T> Arbitrary type.
     */
    private static <T extends Comparable<? super T>> void recursiveQuicksort(ArrayList<T> arr, int left, int right) {
        if (right - left < threshold) {
            insertionSort(arr, left, right);
            return;
        }

        int pivotIndex = left + (right - left) / 2;
        T pivotValue = arr.get(pivotIndex);

        int L = left;
        int R = right;
        while (L <= R) {
            while (arr.get(L).compareTo(pivotValue) < 0) {
                L++;
            }
            while (arr.get(R).compareTo(pivotValue) > 0) {
                R--;
            }
            if (L <= R) {
                Collections.swap(arr, L, R);
                L++;
                R--;
            }
        }

        recursiveQuicksort(arr, left, R);
        recursiveQuicksort(arr, L, right);
    }

    /**
     * Generates an ascending list of integerse
     * @param size - size of list.
     * @return list of ints
     */
    public static ArrayList<Integer> generateAscending(int size) {

        ArrayList<Integer> ascending = new ArrayList<>();
        for(int i = 1; i <= size; i++) {
            ascending.add(i);
        }
        return ascending;
    }

    /**
     * Generates a permuted list of integers of a certain size.
     * @param size size of list.
     * @return - list of ints
     */
    public static ArrayList<Integer> generatePermuted(int size) {
        ArrayList<Integer> Permuted = generateAscending(size);
        Collections.shuffle(Permuted);
        return Permuted;
    }

    /**
     * Generates a descending list of integers
     * @param size - size of list.
     * @return list
     */
    public static ArrayList<Integer> generateDescending(int size) {

        ArrayList<Integer> ascending = new ArrayList<>();
        for(int i = size; i > 0; i--) {
            ascending.add(i);
        }
        return ascending;
    }
}
