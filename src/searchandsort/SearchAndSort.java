package searchandsort;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

/**
 * The SearchAndSort class contains methods for searching for an integer and
 * sorting a list of integers.
 * <p>
 * Within this class are methods for sequential search, binary search, 
 * selection sort, insertion sort, and merge sort. There are also methods to 
 * create a list of integers and print a list of integers.
 * 
 * @author Brian Cochran
 * @version 3/29/2020
 */
public class SearchAndSort {

    private static final Random rng = new Random();
    private static final int SIZE_THRESHOLD = 12;

    /**
     * Creates a list of random integers.
     * <p>
     * This method creates a list of random integers with a length based on the 
     * parameter size.
     * 
     * @param size size of the list of integers to be created
     * @return list of integers.
     */
    public static List<Integer> makeList(int size) {
        List<Integer> result = new ArrayList<>();

        for (int i = 0; i < size; i++) {
            int n = rng.nextInt(90);
            result.add(n);
        } // else
        return result;
    } //makeList
    
    /**
     * Prints a list of integers.
     * <p>
     * This method prints a list of integers in one of two ways. If the length 
     * of the list is larger than or equal to the constant SIZE_THRESHOLD, the 
     * list is printed with one element per line. If the list is less than the 
     * constant SIZE_THRESHOLD, the list is printed on one line.
     * 
     * @param values list of integers to be printed
     */
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

    /**
     * Searches a list of integers for a specified value using the sequential 
     * search algorithm.
     * <p>
     * This method starts with the first element of the list and compares each
     * element to the target integer. If and element of the list is found to 
     * match the target integer, the index of that element is returned and the 
     * search algorithm stops.
     * 
     * @param values list of integers to be searched
     * @param target integer value being searched for
     * @return index of first element matching target or -1 if target does not 
     *         match any element
     */
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

    /**
     * Searches a list of integers for a specified value using the binary 
     * search algorithm.
     * <p>
     * This method starts by finding the indexes of the first and last elements 
     * of the list. Then the middle index is found and the target is compared to 
     * the element at that index. If the target is equal to that element, it is 
     * returned, if the target is smaller than that element the process is 
     * repeated with the first half of the list, and if the target is larger 
     * than that element the process is repeated with the last half of the list. 
     * The target is also compared to the last element of the list because that 
     * index is never the middle value.
     * 
     * @param values list of integers to be searched
     * @param target integer value to be searched for
     * @return index of first element matching target or -1 if target does not
     *         match any element
     */
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

    /**
     * Sorts a list of integers using the selection sort algorithm.
     * <p>
     * This method starts with the first index and sets the element at that 
     * index to be the minimum. It then compares each value of the list to the 
     * minimum. If the value is smaller, it is set to be the minimum. Once the 
     * entire list has been searched through and the smallest value is at the 
     * first index, the process is repeated, this time leaving out the first 
     * index. The process is repeated for each index in the list except the last
     * index.
     * 
     * @param values list of integers to be sorted
     * @return sorted list of integers
     */
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

    /**
     * Sorts a list of integers using the insertion sort algorithm.
     * <p>
     * This method compares elements of the list that are next to each other. If 
     * the element at the larger index is smaller than the element at the smaller 
     * index, the elements swap indexes. With each new comparison of elements, 
     * the algorithm works backward through the previous indexes to ensure that 
     * the smallest element ends up at the first index.
     * 
     * @param values list of integers to be sorted
     * @return sorted list of integers
     */
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

    /**
     * Takes two sorted lists of integers and merges them in a sorted manner.
     * <p>
     * This method first compares the two lists and orders the elements. Then it 
     * adds any extra elements from either list to the end of the merged list to 
     * be returned.
     * 
     * @param left first list of integers to be merged
     * @param right second list of integers to be merged
     * @return merged list of integers
     */
    public static List<Integer> merge(List<Integer> left, List<Integer> right) {
        List<Integer> result = new ArrayList<>();
        int index1 = 0;
        int index2 = 0;
        while (index1 < left.size() && index2 < right.size()) {
            if (left.get(index1) < right.get(index2)) {
                result.add(left.get(index1));
                index1++;
            } // if
            else {
                result.add(right.get(index2));
                index2++;
            } // else
        } // while
        if (index1 < left.size()) {
            result.addAll(left.subList(index1, left.size()));
        } // if
        else {
            result.addAll(right.subList(index2, right.size()));
        } // else
        return result;
    } // mergeSort

    /**
     * Divides a list of integers into lists of length one 
     * to be merged.
     * <p>
     * This method gets the middle value of the list and creates two new lists 
     * using each half of the original list. It then recursively calls itself to 
     * perform these operations until each list is just one element in length. 
     * Each of these lists is then sent to the merge method.
     * 
     * @param values list of integers to be sorted
     * @return sorted list of integers
     */
    public static List<Integer> mergeSort(List<Integer> values) {
        if (values.size() <= 1) {
            return values;
        } // if
        int mid = values.size() / 2;
        List<Integer> left = values.subList(0, mid);
        List<Integer> right = values.subList(mid, values.size());
        left = mergeSort(left);
        right = mergeSort(right);
        return merge(left, right);
    } // mergeSort
    
    /**
     * Tests search and sort methods.
     * <p>
     * This method asks the user to choose which method to test. Then that 
     * method is called and the output is printed for the user to see. This 
     * method also calls the makeList function to create a random list of 
     * integers to use for testing purposes. This unsorted list is also printed.
     * 
     * @param args string array containing command-line arguments
     */
    public static void main(String[] args) {

        System.out.println("Searching and sorting algorithms\n");

        List<Integer> data = makeList(50);
        System.out.println("Unsorted list:");
        printList(data);

        System.out.println("\nChoose method to test:");
        System.out.println("1. Sequential Search");
        System.out.println("2. Binary Search");
        System.out.println("3. Selection Sort");
        System.out.println("4. Insertion Sort");
        System.out.println("5. Merge Sort");
        
        Scanner input = new Scanner(System.in);
        int number = input.nextInt();       
        switch (number) {
            case 1: {
                System.out.println("Choose an integer to search for");
                int target = input.nextInt();
                System.out.println("Index of target integer: " + sequentialSearch(data, target));
                break;
            } // case 1
            case 2: {
                System.out.println("Choose an integer to search for");
                int target = input.nextInt();
                Collections.sort(data);
                System.out.println("Index of target integer: " + binarySearch(data, target));
                break;
            } // case 2
            case 3: {
                System.out.println("Sorted list:");
                printList(selectionSort(data));
                break;
            } // case 3
            case 4: {
                System.out.println("Sorted list:");
                printList(insertionSort(data));
                break;
            } // case 4
            case 5: {
                System.out.println("Sorted list:");
                printList(mergeSort(data));
                break;
            } // case 5
            default: {
                System.out.println("Invalid input");
            } // default
        } // switch
    } // main
} // SearchAndSort