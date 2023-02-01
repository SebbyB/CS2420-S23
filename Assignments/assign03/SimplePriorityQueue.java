package assign03;

import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 *
 * @param <E>
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E> {

    public E[] arr;
    public int size;
    private int growRate = 2;
    public Comparator<E> cmp;

    /**
     *
     * @param o1
     * @param o2
     * @return
     */
    private int compare(E o1, E o2) {
        // Uses comparable compareTo to compare o1 to o2
        if(cmp == null)
            return ((Comparable<? super E>)o1).compareTo(o2);
        // Uses the comparator's compare method to compare o1 to o2
        else
            return cmp.compare(o1, o2);
    }

    /**
     *
     *
     * @param target
     * @param isInsert
     * @return
     */
    private int binarySearch(E target,boolean isInsert) {
        // Find the end, begin, and middle of arr
        int end    = size - 1;
        int start  = 0;
        int middle = start + (end - start)/2;

        // Loops until middle == target or until no more items to check
        while(start <= end) {
            // If we found the target, return index location
            if(compare(arr[middle], target) == 0)
                return middle;
            // If target is smaller than middle, check lower half
            else if(compare(arr[middle], target) > 0) {
                end    = middle - 1;
                middle = (end - start)/2;
            }
            // If target is bigger than middle, check upper half
            else if(compare(arr[middle], target) < 0) {
                start  = middle + 1;
                middle = (end - start)/2;
            }
        }
        if(isInsert){
            return start;
        }
        else {
            return -1;
        }
    }
    /**
     *
     * @param arr
     * @param target
     * @return
     */
    private int binarySearch(E[] arr, E target) {
        // Find the end, begin, and middle of arr
        int end    = size - 1;
        int start  = 0;
        int middle = start + (end - start)/2;

        // Loops until middle == target or until no more items to check
        while(start <= end) {
            // If we found the target, return index location
            if(compare(arr[middle], target) == 0)
                return middle;
                // If target is smaller than middle, check lower half
            else if(compare(arr[middle], target) > 0) {
                end    = middle - 1;
                middle = (end - start)/2;
            }
            // If target is bigger than middle, check upper half
            else if(compare(arr[middle], target) < 0) {
                start  = middle + 1;
                middle = (end - start)/2;
            }
        }
        return -1;
    }
    @SuppressWarnings("unchecked")
    public SimplePriorityQueue() {
        size = 0;
        arr = (E[]) new Object[10];
    }
    @SuppressWarnings("unchecked")
    public SimplePriorityQueue(Comparator<? super E> cmp) {
        size = 0;
        arr = (E[]) new Object[10];
        this.cmp = (Comparator<E>) cmp;
    }

    @Override
    public E findMax() throws NoSuchElementException {
        // Throws exception if there are no items
        if(size == 0)
            throw new NoSuchElementException("No items in Queue");
        return arr[0];
    }

    @Override
    public E deleteMax() throws NoSuchElementException {
        // Throws exception if there are no items
        if(size == 0)
            throw new NoSuchElementException("No items in Queue");
        // Deletes Max
        E returnVal = arr[0];
        arr[0] = null;
        size--;
        return returnVal;
    }
    @SuppressWarnings("unchecked")
    private void grow(){
        E[]temp = (E[]) new Object[(arr.length*growRate)];
        System.arraycopy(arr, 0, temp, 0, arr.length);
        arr = temp;
    }

    private void swap(int index1, int index2){

        E temp = arr[index1];
        arr[index1] = arr[index2];
        arr[index2] = temp;
    }
    @Override
    public void insert(E item) {

        if(binarySearch(arr, item) != -1){
            return;
        }
        if(size == 0){
            arr[0] = item;
            return;
        }
     size++;
        if(arr.length < size){
            grow();
        }
        arr[size - 1] = item;
        int i = binarySearch(item, true) , j = size -1;
        while( i > j){
            E tmp = arr[j];
            arr[j] = arr[j - 1];
            arr[j-1] = tmp;
            j--;
        }

    }


    @Override
    public void insertAll(Collection<? extends E> coll) {
        for(E item : coll)
            insert(item);
    }

    @Override
    public boolean contains(E item) {
        return binarySearch(item,false) != -1;
    }

    @Override
    public int size() { return size; }

    @Override
    public boolean isEmpty() { return size == 0; }

    @Override
    public void clear() {
        arr = (E[]) new Object[10];;
        size = 0;
    }
}
