package lab04;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Comparator;

public class CrackedOutWackyMedianLabSolver {


    public <T extends Comparable<T>> T median(T[] arr){


        Arrays.sort(arr);

        return arr[arr.length/2 + arr.length % 2];
    }





    public <T> T median(Comparator<T> cmp, T[] arr){

        Arrays.sort(arr,cmp);
        return arr[arr.length/2];
    }

//    public void ssdfalkdkfakd;fjlka;df;lkjadlfkjaldkjf
//    int n = 1000;
//    int[] integerArr = new int[n];
//    for(int i = 0; < n; i++)
}//    i wanna fortnite kill myself

