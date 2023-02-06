package assign04;

import org.junit.jupiter.api.Test;

import java.math.BigInteger;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class LargeNumberSolverTester {


    int n = 1000;


    @Test
    void nDigitsBiggestNumber(){
        ArrayList<Integer> numList = new ArrayList<>();
        StringBuilder builder = new StringBuilder();
        for(int i = n; i > 0; i--){
           numList.add(i);
           builder.append(i);
        }
        BigInteger testNum = LargestNumberSolver.findLargestNumber(arr);
        assertEquals(new BigInteger(builder.toString()),testNum);
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



}
