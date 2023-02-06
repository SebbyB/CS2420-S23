package assign04;

import assign04.LargestNumberSolver;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.ToDoubleFunction;
import java.util.function.ToIntFunction;
import java.util.function.ToLongFunction;

public class LargeNumberComparator<Integer> implements Comparator {

    public LargeNumberComparator(){

    }

    @Override
    public int compare(Object o1, Object o2) {

        if((o1 instanceof java.lang.Integer) && (o2 instanceof java.lang.Integer)){

            if((int)o1 > (int)o2){
                return 1;
            } else if ((int)o1<(int)o2) {
                return -1;
            }
            else{return 0;}
        }else {
            throw new IllegalArgumentException();
        }
    }
}
