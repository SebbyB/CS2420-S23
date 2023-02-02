package assign03;

/**
 * This Java class represents a Priority Queue.
 *
 * @author Joshua Schell and Sebastian Barney
 * @version February 2nd, 2023
 */
import java.util.Collection;
import java.util.Comparator;
import java.util.NoSuchElementException;

/**
 *
 * @param <E>
 */
public class SimplePriorityQueue<E> implements PriorityQueue<E> {

    private E[] arr;
    private int size;
    private Comparator<E> cmp;

    /**
     * Compares ordering of two objects.
     * @param o1 Object 1 to compare.
     * @param o2 Object 2 to compare.
     * @return if o1>o2 or o1<o2 or o1=o2
     */
    @SuppressWarnings("unchecked")
    public int compare(E o1, E o2) {
        // Uses comparable compareTo to compare o1 to o2
        if(cmp == null)
            return ((Comparable<? super E>)o1).compareTo(o2);
            // Uses the comparator's compare method to compare o1 to o2
        else
            return cmp.compare(o1, o2);
    }

    /**
     * Performs a binary search for an item on an array.
     * @param arr - Array to search.
     * @param target - Thing being searched for.
     * @return Index of the item if it is in the array, index it would be at if it isn't in the array.
     */
    public int binarySearch(E[] arr, E target) {
        // Find the end and begin of arr
        int end    = size - 1;
        int start  = 0;
        int middle = 0;

        // Loops until middle == target or until no more items to check
        while(start <= end) {
            // Finds the middle of current arr or sub arr
            middle = start + ((end - start)/2);
            // If we found the target, return index location
            if(compare(arr[middle], target) == 0)
                return middle;
                // If target is smaller than middle, check lower half
            else if(compare(arr[middle], target) > 0)
                end = middle - 1;
                // If target is bigger than middle, check upper half
            else if(compare(arr[middle], target) < 0)
                start = middle + 1;
        }
        // Does not contain target
        return -middle;
    }


    /**
     * Basic Constructor for a SimplePriorityQueue
     */
    @SuppressWarnings("unchecked")
    public SimplePriorityQueue() {
        size = 0;
        arr = (E[]) new Object[10];
    }

    /**
     * Constructor for a SimplePriorityQueue that uses a comparator.
     * @param cmp - comparator object.
     */
    @SuppressWarnings("unchecked")
    public SimplePriorityQueue(Comparator<? super E> cmp) {
        size = 0;
        arr = (E[]) new Object[10];
        this.cmp = (Comparator<E>) cmp;
    }

    /**
     * Finds the object with the highest order in the priorityQueue.
     * @return - max object.
     * @throws NoSuchElementException If the queue is empty throw an exception.
     */
    @Override
    public E findMax() throws NoSuchElementException {
        // Throws exception if there are no items
        if(size == 0)
            throw new NoSuchElementException("No items in arr");
        return arr[size - 1];
    }

    /**
     * Deletes the object with the highest order in the priorityQueue
     * @return - max object
     * @throws NoSuchElementException If the queue is empty throw an exception.
     */
    @Override
    public E deleteMax() throws NoSuchElementException {
        // Throws exception if there are no items
        if(size == 0)
            throw new NoSuchElementException("No items in arr");
        // Deletes Max
        E returnVal = arr[size - 1];
        arr[size - 1] = null;
        size--;
        return returnVal;
    }

    /**
     * Inserts an Item into the priorityQueue while preserving ordering.
     * @param item - the element to insert
     */
    @SuppressWarnings("unchecked")
    @Override
    public void insert(E item) {
        // insert and no shift if array is empty
        if(size == 0) {
            arr[0] = item;
            size++;
            return;
        }
        // Doubles arr size, if arr is full
        if(size == arr.length) {
            E[] temp = (E[]) new Object[(arr.length*2)];
            System.arraycopy(arr, 0, temp, 0, arr.length);
            arr = temp;
        }

        // search if item is in array, if index is negative, then it is not in array.
        int index = binarySearch(arr, item);
        if(index <= 0) {
            for(int i = size; i > -index; i--) {
                arr[i] = arr[i - 1];
            }
            if(compare(arr[-index], item) < 0)
                arr[-index + 1] = item;
            else
                arr[-index] = item;
        }
        // adds item in array, shifts item down
        else {
            for(int i = size; i > index; i--) {
                arr[i] = arr[i - 1];
            }
            arr[index] = item;
        }
        size++;
    }

    /**
     * Inserts a collection of items into the priorityQueue while preserving the ordering.
     * @param coll - the collection of elements to insert
     */
    @Override
    public void insertAll(Collection<? extends E> coll) {
        for(E item : coll)
            insert(item);
    }

    /**
     * Checks if the Priority Queue contains an Item.
     * @param item - the element to be checked for containment in this priority queue
     * @return true if it contains the item, false otherwise.
     */
    @Override
    public boolean contains(E item) {
        if(isEmpty()){return false;}
        if(binarySearch(arr, item) > 0)
            return true;
        return compare(arr[0], item) == 0;
    }

    /**
     * Gives the size of the Priority Queue
     * @return size of queue
     */
    @Override
    public int size() { return size; }

    /**
     * Checks if the queue is empty.
     * @return true if it is, false otherwise.
     */
    @Override
    public boolean isEmpty() { return size == 0; }

    /**
     * Clears the Priority Queue.
     */
    @SuppressWarnings("unchecked")
    @Override
    public void clear() {
        arr = (E[]) new Object[10];;
        size = 0;
    }
}