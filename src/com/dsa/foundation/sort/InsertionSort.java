package com.dsa.foundation.sort;

import java.util.Arrays;

public class InsertionSort {

    // The outer loop iterates – starting with the second element, since the first element is already
    // sorted – over the elements to be sorted. The loop variable i, therefore, always points to the
    // first element of the right, unsorted part.
    // In the inner while loop, the search for the insert position and the shifting of the elements
    // is combined:
    // Searching in the loop condition: until the element to the left of the search position j is
    // smaller than the element to be sorted,
    // and shifting the sorted elements in the loop body.

    // Time complexity: O(nxn), best is O(n)
    // Space complexity: O(1)
    public void simpleInsertionSort(int[] elements){

        for (int i=0; i < elements.length; i++){

            int j = i;
            int elementToSort = elements[i];

            while (j > 0 && elementToSort < elements[j-1]){
                elements[j] = elements[j-1];
                j--;
            }

            elements[j] = elementToSort;
        }
    }

    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 90, 11, 89};
        System.out.print("Unsorted array: ");
        System.out.println(Arrays.toString(arr));
        InsertionSort sort = new InsertionSort();

        sort.simpleInsertionSort(arr);

        System.out.print("Sorted array using simple Selection Sort: ");
        System.out.println(Arrays.toString(arr));

    }
}
