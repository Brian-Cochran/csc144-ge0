
package searchandsort;

import java.util.ArrayList;
//import java.util.Collections;
import java.util.List;
import java.util.Random;

public class SearchAndSort {
    
    private static final  Random rng = new Random();
    private static final int SIZE_THRESHOLD = 12;
    public static List<Integer> makeList(int size) {
        List<Integer> result = new ArrayList<>();
        
        for (int i = 0; i < size; i++) {
            int n = rng.nextInt(90);
            result.add(n);
        } // else
        return result;
    } //makeList
    
    public static void printList(List<Integer> values) {
        if (values.size() < SIZE_THRESHOLD) {
            for (int n : values) {
                System.out.printf("%4d", n);
            } // for
            System.out.println();
        } // if
        else {
            for (int n : values) {
                System.out.printf("%4d\n", n);
            } // for
        } // else
    } // printList
    
    // TO-DO: Define a method that determines
    // the index of the first integer in a list
    // of integers that matches a given integer.
    // The method should return -1 if no match is found.
    // Use the sequential search algorithm.
    public static int sequentialSearch(List<Integer> values, int target) {
        int result = -1;        
        int index = 0;
        while (index < values.size() && result < 0) {
            if (target == values.get(index)) {
                result = index;
            } // if
            index += 1;
        } // while
        return result;
    } // sequentialSearch
    
    // TO-DO: Define a method that determines
    // the index of the first integer in a list
    // of integers that matches a given integer.
    // The method should return -1 if no match is found.
    // Use the binary search algorithm.
    public static int binarySearch(List<Integer> values, int target) {
        int result = -1;
        
        int start = 0;
        int end = values.size() - 1;
        
        while (start < end && result < 0) {
            int mid = (end + start) / 2;
            if (target == values.get(mid)) {
                result = mid;
            } // if
            else if (target == values.get(end)) {
                result = end;
            } // else if
            else if (target < values.get(mid)) {
                end = mid - 1;
            } // else if
            else {
                start = mid + 1;
            } // else
        } // while
        return result;
    } // binarySearch

    // TO-DO: Define a method that sorts a list
    // of integers using the selection sort algorithm.
    public static List<Integer> selectionSort(List<Integer> values) {
        for (int i = 0; i < values.size() - 1; i++) {
            int minIndex = i;
            for (int j = i; j < values.size(); j++) {
                if (values.get(j) < values.get(minIndex)) {
                    minIndex = j;                
                } // if
            } // for
            int temp = values.get(i);
            values.set(i, values.get(minIndex));
            values.set(minIndex, temp);
        } // for        
        return values;
    } // selectionSort
    
    // TO-DO: Define a method that sorts a list
    // of integers using the insertion sort algorithm.
    public static List<Integer> insertionSort(List<Integer> values) {
        for (int i = 1; i < values.size(); i++) { 
            int key = values.get(i); 
            int j = i - 1;
            while (j >= 0 && values.get(j) > key) {
                values.set(j + 1, values.get(j));
                j = j - 1;
            } // while
            values.set(j + 1, key);
        }
        return values;
    } // insertionSort
    
    // TO-DO: Define a method that sorts a list
    // of integers using the merge sort algorithm.
    
    public static void main( String [] args ) {
        
        System.out.println( "Searching and sorting algorithms" );
        
        // TO-DO: Add code that tests the searching and sorting
        // methods.
        System.out.println("Experiment.");
        List<Integer> data = makeList(10);
        //Collections.sort(data);
        printList(data);
        
        //System.out.println("index = " + sequentialSearch(data, 39));
        //System.out.println("index = " + binarySearch(data, 50));
        //System.out.println("sorted list: " + selectionSort(data));
        //System.out.println("sorted list: " + insertionSort(data));
        
    } // main
        
} // SearchAndSort
