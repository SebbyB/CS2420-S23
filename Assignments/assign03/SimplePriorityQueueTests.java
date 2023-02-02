package assign03;

/**
 * This Java class tests The SimplePriorityQueue class.
 *
 * @author Joshua Schell and Sebastian Barney
 * @version February 2nd, 2023
 */
import jdk.jfr.Category;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class SimplePriorityQueueTests {

    //feel free to adjust to whatever parameters you want. Beware, your computer only has so much memory.
    int n = 1000;




    /**
     * Full Testing Suite for insert method. Shows it works with primitives and objects;
     * Meaning if it passes the method works as intended. 
     * It should also show that the order things are inserted into the queue doesn't impact the success of the insertion, but does effect the time.
     */

    /**
     * Inserts n number of  into a test queue in ascending order.
     * Since the size grows post insertion, integers get inserted into the queue.
     */
    @Test
    void insertingNComparator(){
        SimplePriorityQueue<HashMap<Integer,LinkedList<String>>> testQueue = new SimplePriorityQueue<>(new Comparator<HashMap<Integer, LinkedList<String>>>() {
            @Override
            public int compare(HashMap<Integer, LinkedList<String>> o1, HashMap<Integer, LinkedList<String>> o2) {

                if (o1.size() > o2.size()) {
                    return 1;
                } else if (o1.size() < o2.size()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        for(int i = 0; i < n; i++){
            assertEquals(testQueue.size(),i);
            testQueue.insert(new HashMap<>());
            assertEquals(testQueue.size(),i+1);
        }
    }
    /**
     * Inserts n number of integers into a test queue in ascending order.
     * Since the size grows post insertion, integers get inserted into the queue.
     */
    @Test
    void insertingNIntegersAscending(){
        SimplePriorityQueue<Integer> testQueue = new SimplePriorityQueue<>();
        for(int i = 0; i < n; i++){
            assertEquals(testQueue.size(),i);
            testQueue.insert(i);
            assertEquals(testQueue.size(),i+1);
        }
    }
    /**
     * Inserts n number of Strings into a test queue in ascending order.
     * Since the size grows post insertion, Strings get inserted into the queue.
     */
    @Test
    void insertingNStringAscending(){
        SimplePriorityQueue<String> testQueue = new SimplePriorityQueue<>();
        for(int i = 0; i < n; i++){
            assertEquals(testQueue.size(),i);
            testQueue.insert(Integer.toBinaryString(i));
            assertEquals(testQueue.size(),i+1);
        }
    }


    /**
     * Inserts n number of integers into a test queue in descending order.
     * Since the size grows post insertion, integers get inserted into the queue.
     */
    @Test
    void insertingNIntegersDescending(){
        SimplePriorityQueue<Integer> testQueue = new SimplePriorityQueue<>();
        int j = 0;
        for(int i = n; i > 0; i--){
            assertEquals(testQueue.size(),j);
            testQueue.insert(i);
            j++;
            assertEquals(testQueue.size(),j);
        }
    }
    /**
     * Inserts n number of Strings into a test queue in descending order.
     * Since the size grows post insertion, Strings get inserted into the queue.
     */
    @Test
    void insertingNStringDescending(){
        SimplePriorityQueue<String> testQueue = new SimplePriorityQueue<>();
        int j = 0;
        for(int i = n; i > 0; i--){
            assertEquals(testQueue.size(),j);
            testQueue.insert(Integer.toBinaryString(i));
            assertEquals(testQueue.size(),j+1);
            j++;
        }
    }


    /**
     * Full Testing Suite for insertAll method.
     * Shows it works with generics.
     * Shows that insertAll has the same properties as insert.
     */




    /**
     * Creates a collection of Integers and inserts that collection into the queue.
     * Shows that order doesn't impact success but does impact insert times.
     */
    @Test
    void insertAllNIntegers(){

        SimplePriorityQueue<Integer> testQueue = new SimplePriorityQueue<>();
        ArrayList<Integer> testList = new ArrayList<>();
        //Ascending
        for(int i = 0; i < n; i++){
            assertEquals(i,testList.size());
            testList.add(i);
            assertEquals(i+1,testList.size());
        }
        assertEquals(0,testQueue.size());
        testQueue.insertAll(testList);
        assertEquals(n,testQueue.size());



        testQueue.clear();
        testList.clear();
        //Descending
        int j = 0;
        for(int i = n; i > 0; i--){
            assertEquals(j,testList.size());
            testList.add(i);
            j++;
            assertEquals(j,testList.size());
        }
        assertEquals(0,testQueue.size());
        testQueue.insertAll(testList);
        assertEquals(n,testQueue.size());
    }
    /**
     * Creates a collection of Strings and inserts that collection into the queue.
     * Shows that order doesn't impact success but does impact insert times.
     */
    @Test
    void insertAllNStrings(){

        SimplePriorityQueue<String> testQueue = new SimplePriorityQueue<>();
        ArrayList<String> testList = new ArrayList<>();
        //Ascending
        for(int i = 0; i < n; i++){
            assertEquals(i,testList.size());
            testList.add(Integer.toString(i));
            assertEquals(i+1,testList.size());
        }
        assertEquals(0,testQueue.size());
        testQueue.insertAll(testList);
        assertEquals(n,testQueue.size());



        testQueue.clear();
        testList.clear();
        //Descending
        int j = 0;
        for(int i = n; i > 0; i--){
            assertEquals(j,testList.size());
            testList.add(Integer.toString(i));
            j++;
            assertEquals(j,testList.size());
        }
        assertEquals(0,testQueue.size());
        testQueue.insertAll(testList);
        assertEquals(n,testQueue.size());
    }


    /**
     * Testing Suite for clear, isEmpty, and size methods.
     * Shows size changes.
     * Shows clear works as intended.
     * Shows isEmpty works as intended.
     */

    /**
     * Shows that Items added to a SimplePriorityQueue change the size of the queue.
     */
    @Test
    void changingSizeInts(){

        //Creates a testQueue which is empty to start.
        SimplePriorityQueue<Integer> testQueue = new SimplePriorityQueue<>();
        ArrayList<Integer> testList = new ArrayList<>();
        assertEquals(0,testQueue.size());
        //Populates the queue and shows the size changes.
        for(int i = 1; i <= n; i++){
            testQueue.insert(i);
            testList.add(i);
            assertEquals(i,testList.size());
            assertEquals(i,testQueue.size());
        }
        //empties the queue one by one to show the size changes.
        assertEquals(n,testQueue.size());
        for(int j = n-1; j >= 0; j--){
            testQueue.deleteMax();
            assertEquals(j,testQueue.size());
        }
        assertEquals(0,testQueue.size());
        //Populates the empty queue with a list and asserts the size.
        testQueue.insertAll(testList);
        assertEquals(testList.size(),testQueue.size());
        //clears it to show the size is 0.
        testQueue.clear();
        assertEquals(0,testQueue.size());
    }

    /**
     * Shows that Items added to a SimplePriorityQueue change the size of the queue.
     */
    @Test
    void changingSizeString(){

        //Creates a testQueue which is empty to start.
        SimplePriorityQueue<String> testQueue = new SimplePriorityQueue<>();
        ArrayList<String> testList = new ArrayList<>();
        assertEquals(0,testQueue.size());
        //Populates the queue and shows the size changes.
        for(int i = 1; i <= n; i++){
            testQueue.insert(Integer.toString(i));
            testList.add(Integer.toString(i));
            assertEquals(i,testList.size());
            assertEquals(i,testQueue.size());
        }
        //empties the queue one by one to show the size changes.
        assertEquals(n,testQueue.size());
        for(int j = n-1; j >= 0; j--){
            testQueue.deleteMax();
            assertEquals(j,testQueue.size());
        }
        assertEquals(0,testQueue.size());
        //Populates the empty queue with a list and asserts the size.
        testQueue.insertAll(testList);
        assertEquals(testList.size(),testQueue.size());
        //clears it to show the size is 0.
        testQueue.clear();
        assertEquals(0,testQueue.size());
    }

    /**
     * Shows that a queue can be cleared.
     */
    @Test
    void queueClearing(){

        //Creates a testQueue which is empty to start.
        SimplePriorityQueue<String> testQueue = new SimplePriorityQueue<>();
        ArrayList<String> testList = new ArrayList<>();
        assertEquals(0,testQueue.size());
        //Populates the queue and shows the size changes.
        for(int i = 1; i <= n; i++){
            testQueue.insert(Integer.toString(i));
            testList.add(Integer.toString(i));
            assertEquals(i,testList.size());
            assertEquals(i,testQueue.size());
        }
        testQueue.clear();
        assertEquals(0,testQueue.size());
        //Populates the empty queue with a list and asserts the size.
        testQueue.insertAll(testList);
        assertEquals(testList.size(),testQueue.size());
        //clears it to show the size is 0.
        testQueue.clear();
        assertEquals(0,testQueue.size());
    }

    /**
     * Shows that a queue can be cleared.
     */
    @Test
    void queueIsEmpty(){

        //Creates a testQueue which is empty to start.
        SimplePriorityQueue<String> testQueue = new SimplePriorityQueue<>();
        ArrayList<String> testList = new ArrayList<>();
        assertEquals(0,testQueue.size());
        assertTrue(testQueue.isEmpty());
        //Populates the queue and shows the size changes.
        for(int i = 1; i <= n; i++){
            testQueue.insert(Integer.toString(i));
            testList.add(Integer.toString(i));
            assertEquals(i,testList.size());
            assertEquals(i,testQueue.size());
            assertFalse(testQueue.isEmpty());

        }
        assertFalse(testQueue.isEmpty());
        testQueue.clear();
        assertTrue(testQueue.isEmpty());
        assertEquals(0,testQueue.size());
        //Populates the empty queue with a list and asserts the size.
        testQueue.insertAll(testList);
        assertEquals(testList.size(),testQueue.size());
        assertFalse(testQueue.isEmpty());
        //clears it to show the size is 0.
        testQueue.clear();
        assertTrue(testQueue.isEmpty());
        assertEquals(0,testQueue.size());
    }

    /**
     * Testing Suite for findMax.
     * Shows a max stays a max when smaller items are added to the queue.
     * Shows a max changes when bigger items are added to the queue.
     * Shows it works generically.
     * Shows it throws an exception if the queue is empty.
     */




    /**
     * Shows that the maximum value in a queue is dynamic.
     */
    @Test
    void maxIncreasesIntegers(){

        //Creates a testQueue which is empty to start.
        SimplePriorityQueue<Integer> testQueue = new SimplePriorityQueue<>();
        assertEquals(0,testQueue.size());
        assertTrue(testQueue.isEmpty());

        assertThrows(NoSuchElementException.class, () -> {testQueue.findMax();});
        //Populates the queue and shows the max changes every time.
        for(int i = 1; i <= n; i++){
            testQueue.insert(i);
            assertEquals(i,testQueue.findMax());

        }

    }

    /**
     * Shows the maximum of a queue is static when smaller items are added.
     */
    @Test
    void staticMaxInts(){
        //Creates a testQueue which is empty to start.
        SimplePriorityQueue<Integer> testQueue = new SimplePriorityQueue<>();
        assertEquals(0,testQueue.size());
        assertTrue(testQueue.isEmpty());

        assertThrows(NoSuchElementException.class, () -> {testQueue.findMax();});
        //Populates the queue and shows the max stays the same every time.
        for(int i = n; i >= 0; i--){
            testQueue.insert(i);
            assertEquals(n,testQueue.findMax());

        }
    }

    /**
     * Shows that findMax throws an exception when invoked on an empty queue, and doesn't when the queue is not empty.
     */
    @Test
    void findMaxExceptionHandling() {
        //Creates a testQueue which is empty to start.
        SimplePriorityQueue<Integer> testQueue = new SimplePriorityQueue<>();
        assertEquals(0, testQueue.size());
        assertTrue(testQueue.isEmpty());

        assertThrows(NoSuchElementException.class, () -> {
            testQueue.findMax();
        });
        testQueue.insert(1);
        assertDoesNotThrow(() -> {
            testQueue.findMax();
        });
    }

    /**
     * Testing Suite for deleteMax
     * Shows that deleting the max from a queue changes size.
     * Shows the deleted element is no longer in the list.
     * Shows it works generically.
     * Shows that exceptions are handled.
     */

    /**
     * Shows that deleteMax throws an exception when invoked on an empty queue, and doesn't when the queue is not empty.
     */
    @Test
    void deleteMaxExceptionHandling() {
        //Creates a testQueue which is empty to start.
        SimplePriorityQueue<Integer> testQueue = new SimplePriorityQueue<>();
        assertEquals(0, testQueue.size());
        assertTrue(testQueue.isEmpty());

        assertThrows(NoSuchElementException.class, () -> {
            testQueue.deleteMax();
        });
        testQueue.insert(1);
        assertDoesNotThrow(() -> {
            testQueue.deleteMax();
        });
    }

    /**
     * Shows Strings will be deleted from the queue
     */
    @Test
    void deleteStrings(){

        //Creates a testQueue which is empty to start.
        SimplePriorityQueue<String> testQueue = new SimplePriorityQueue<>();
        assertEquals(0,testQueue.size());
        assertTrue(testQueue.isEmpty());

        assertThrows(NoSuchElementException.class, () -> {testQueue.findMax();});
        //Populates the queue and shows the max changes every time.
        for(int i = 1; i <= n; i++){
            testQueue.insert(Integer.toString(i));
            assertFalse(testQueue.isEmpty());
            assertTrue(testQueue.contains(Integer.toString(i)));
            assertEquals((Integer.toString(i)),testQueue.deleteMax());
            assertFalse(testQueue.contains(Integer.toString(i)));
            assertTrue(testQueue.isEmpty());


        }

    }

    /**
     * Shows that Ints will be deleted from the queue.
     */
    @Test
    void deleteIntegers(){

        //Creates a testQueue which is empty to start.
        SimplePriorityQueue<Integer> testQueue = new SimplePriorityQueue<>();
        assertEquals(0,testQueue.size());
        assertTrue(testQueue.isEmpty());

        assertThrows(NoSuchElementException.class, () -> {testQueue.findMax();});
        //Populates the queue and shows the max changes every time.
        for(int i = 1; i <= n; i++){
            testQueue.insert(i);
            assertFalse(testQueue.isEmpty());
            assertTrue(testQueue.contains(i));
            assertEquals(i,testQueue.deleteMax());
            assertFalse(testQueue.contains(i));
            assertTrue(testQueue.isEmpty());

        }

    }

    /**
     * Testing suite for contains.
     * Shows contains() works as intended.
     * Shows contains() works on empty queues.
     * Shows it works generically.
     */


    /**
     * Shows that the contains function works as intended.
     */
    @Test
    void containsInts(){

        SimplePriorityQueue<Integer> testQueue = new SimplePriorityQueue<>();

        for(int i = 0; i < n; i++){
            assertFalse(testQueue.contains(i));
            testQueue.insert(i);
            assertTrue(testQueue.contains(i));
        }
    }

    /**
     * Shows that the contains function works as intended.
     */
    @Test
    void containsStrings(){

        SimplePriorityQueue<String> testQueue = new SimplePriorityQueue<>();

        for(int i = 0; i < n; i++){
            assertFalse(testQueue.contains(Integer.toString(i)));
            testQueue.insert(Integer.toString(i));
            assertTrue(testQueue.contains(Integer.toString(i)));
        }
    }

    /**
     * Shows that the contains method works on empty queues.
     */
    @Test
    void containsOnEmpty(){
        SimplePriorityQueue<String> testQueue = new SimplePriorityQueue<>();

        assertDoesNotThrow(()->testQueue.contains("fortnite"));
        assertFalse(testQueue.contains("coolstringthatisn'tinthequeue"));
    }
}
