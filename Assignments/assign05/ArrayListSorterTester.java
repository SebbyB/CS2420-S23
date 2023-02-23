package assign05;

import org.junit.jupiter.api.Test;

import java.lang.reflect.Array;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;


public class ArrayListSorterTester {


    int n = 100;

    private ArrayList<Integer> ascendingIntList(int size){
        ArrayList<Integer> ascendingList = new ArrayList<>();
        for (int i = 0; i < size; i++){
            ascendingList.add(i);
        }
        return ascendingList;
    }
    private ArrayList<Integer> descendingIntList(int size){
        ArrayList<Integer> descendingList = new ArrayList<>();
        for (int i = size - 1; i >= 0 ; i--){
            descendingList.add(i);
        }
        return descendingList;
    }

    @Test
    public void mergeSortThreeElements() {
        ArrayList<Integer> arr = new ArrayList<>();
        arr.add(6);
        arr.add(12);
        arr.add(3);
        ArrayListSorter.mergesort(arr);
        assertEquals(arr.get(0), 3);
        assertEquals(arr.get(1), 6);
        assertEquals(arr.get(2), 12);
    }

    @Test
    public void mergeSortTwentyElementsDescending() {
        ArrayList<Integer> arr = descendingIntList(20);
        ArrayListSorter.mergesort(arr);
        for(int i = 0; i < 20; i++) {
            assertEquals(i, arr.get(i));
        }
    }

    @Test
    public void mergeSortTwentyElementsAscending() {
        ArrayList<Integer> arr = new ArrayList<>();
        for(int i = 1; i <= 20; i++) {
            arr.add(i);
        }
        ArrayListSorter.mergesort(arr);
        for(int i = 0; i < 20; i++) {
            assertEquals(i + 1, arr.get(i));
        }
    }
    
    
    
    @Test
    public void mergeSortNElements(){

        ArrayList<Integer> arr = ascendingIntList(n),arr2 = descendingIntList(n);
        ArrayListSorter.mergesort(arr);
        ArrayListSorter.mergesort(arr2);
        for(int i = 0; i < n; i++){
            assertEquals(arr.get(i),i);
            assertEquals(arr.get(i),i);
        }
        assertEquals(arr,arr2);
    }

    @Test
    public void quickSortNElementsDefault(){

        ArrayList<Integer> arr = ascendingIntList(n),arr2 = descendingIntList(n);
        ArrayListSorter.setPivotType(0);
        ArrayListSorter.quicksort(arr);
        ArrayListSorter.quicksort(arr2);
        for(int i = 0; i < n; i++){
            assertEquals(arr.get(i),i);
            assertEquals(arr.get(i),i);
        }
        assertEquals(arr,arr2);


    }

    @Test
    public void quickSortNElementsPivot1(){

        ArrayList<Integer> arr = ascendingIntList(n),arr2 = descendingIntList(n);
        ArrayListSorter.setPivotType(1);
        ArrayListSorter.quicksort(arr);
        ArrayListSorter.quicksort(arr2);
        for(int i = 0; i < n; i++){
            assertEquals(arr.get(i),i);
            assertEquals(arr.get(i),i);
        }
        assertEquals(arr,arr2);


    }
    @Test
    public void quickSortNElementsPivotRandom(){

        ArrayList<Integer> arr = ascendingIntList(n),arr2 = descendingIntList(n);
        ArrayListSorter.setPivotType(2);
        ArrayListSorter.quicksort(arr);
        ArrayListSorter.quicksort(arr2);
        for(int i = 0; i < n; i++){
            assertEquals(arr.get(i),i);
            assertEquals(arr.get(i),i);
        }
        assertEquals(arr,arr2);


    }
    @Test
    public void InsertionSortNElements(){

        ArrayList<Integer> arr = ascendingIntList(n),arr2 = descendingIntList(n);
        ArrayListSorter.insertionSort(arr,0, arr.size()-1);
        ArrayListSorter.insertionSort(arr2,0, arr2.size()-1);
        for(int i = 0; i < n; i++){
            assertEquals(arr.get(i),i);
            assertEquals(arr.get(i),i);
        }
        assertEquals(arr,arr2);


    }



}
