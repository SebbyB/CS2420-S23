package assign03;

import jdk.jfr.Category;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SimplePriorityQueueTests {

    int n = 1000;

    /**
     * Full Testing Suite for insert method. Shows it works with primitives and objects;
     * Meaning if it passes the method works as intended.
     */
    @Test
    void insertionMethodTests(){
        insertingNIntegers();
        insertingNString();
        insertingNObjects();
    }

    /**
     * Inserts n number of integers into a test queue.
     * Since the size grows post insertion, integers get inserted into the queue.
     */
    @Test
    void insertingNIntegers(){
        SimplePriorityQueue<Integer> testQueue = new SimplePriorityQueue<>();
        for(int i = 0; i < n; i++){
//            assertEquals(testQueue.size(),i);
            testQueue.insert(i);
            assertEquals(testQueue.size(),i+1);
        }
    }
    /**
     * Inserts n number of chars into a test queue.
     * Since the size grows post insertion, chars get inserted into the queue.
     */
    @Test
    void insertingNString(){
        SimplePriorityQueue<String> testQueue = new SimplePriorityQueue<>();
        for(int i = 0; i < n; i++){
            assertEquals(testQueue.size(),i);
            testQueue.insert(Integer.toBinaryString(i));
            assertEquals(testQueue.size(),i+1);
        }
    }
    /**
     * Inserts n number of chars into a test queue.
     * Since the size grows post insertion, chars get inserted into the queue.
     */
    @Test
    void insertingNObjects(){
        SimplePriorityQueue<Object> testQueue = new SimplePriorityQueue<>();
        for(int i = 0; i < n; i++){
            Object o = new Object();
            assertEquals(testQueue.size(),i);
            testQueue.insert(o);
            assertEquals(testQueue.size(),i+1);
        }
    }




}
