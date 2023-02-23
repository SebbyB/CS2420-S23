package assign04;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class LargeNumberSolverTester {


    int n = 1000;


    Integer[] copyArrList(ArrayList<Integer> list){
        Integer[] intList = new Integer[list.size()];
        for (int i = 0 ; i < list.size(); i++){

            intList[i] = list.get(i);
        }

        return intList;

    }
    @Test
    void nDigitsBiggestNumber(){
        ArrayList<Integer> numList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for(int i = n; i > 0; i--){
           numList.add(i);
           builder.append(i);
        }
        assertEquals(new BigInteger(builder.toString()),LargestNumberSolver.findLargestNumber(copyArrList(numList)));
    }

    @Test
    void largestIntThrows(){
        Integer[] arr = new Integer[]{2147483647,n};

        assertThrows(OutOfRangeException.class, ()->LargestNumberSolver.findLargestInt(arr));
    }
    @Test
    void largestLongThrows(){
        Integer[] arr = new Integer[]{9,223372036,854775807,n};

        assertThrows(OutOfRangeException.class, ()->LargestNumberSolver.findLargestLong(arr));
    }

    @Test
    void readEmpty(){
        assertTrue(LargestNumberSolver.readFile("").isEmpty());
    }

    @Test
    void readFromFile(){
        assertFalse(LargestNumberSolver.readFile("Assignments/assign04/scratch.txt").isEmpty());

        Integer[] e1 = new Integer[] {1,1,1,1,1};
        Integer[] e2 = new Integer[] {2,2,2,2,2};
        Integer[] e3 = new Integer[] {3,3,3,3,3};
        Integer[] e4 = new Integer[] {4,4,4,4,4};
        Integer[] e5 = new Integer[] {5,5,5,5,5};
        Integer[] e6 = new Integer[] {6,6,6,6,6};
        Integer[] e7 = new Integer[] {9,8,7,6,5};
        ArrayList<Integer[]> list = new ArrayList<>();
        list.add(e1);
        list.add(e2);
        list.add(e3);
        list.add(e4);
        list.add(e5);
        list.add(e6);
        list.add(e7);
        List<Integer[]> tl = LargestNumberSolver.readFile("Assignments/assign04/scratch.txt");
        for (int i = 0; i < 7; i++) {
            assertArrayEquals(list.get(i),tl.get(i));
        }
    }


}
