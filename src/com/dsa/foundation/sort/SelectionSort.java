package com.dsa.foundation.sort;

import java.util.Arrays;

public class SelectionSort {

    // The outer loop iterates over the elements to be sorted, and it ends after the second-last element.
    // When this element is sorted, the last element is automatically sorted as well. The loop variable
    // i always points to the first element of the right, unsorted part.
    // In each loop cycle, the first element of the right part is initially assumed as the smallest
    // element min; its position is stored in minPos.
    // The inner loop then iterates from the second element of the right part to its end and
    // reassigns min and minPos whenever an even smaller element is found.
    // After the inner loop has been completed, the elements of positions i (beginning of the right part)
    // and minPos are swapped (unless they are the same element).

    // Time complexity: O(nxn)
    // Space Complexity: O(1)
    public void simpleSelectionSort(int[] elements){


        for (int i=0; i < elements.length-1; i++){
            int minPos = i;
            int minValue = elements[i];


            for (int j=i+1; j < elements.length; j++){

                if (elements[j] < minValue){
                    minPos = j;
                    minValue = elements[j];
                }
            }

            // Swap minValue with element at pos i unless they are the same element in the array
            if (minPos != i){
                elements[minPos] = elements[i];
                elements[i] = minValue;
            }
        }

    }

    public static void main(String[] args) {
        int[] arr = {64, 34, 25, 12, 90, 11, 89};
        System.out.print("Unsorted array: ");
        System.out.println(Arrays.toString(arr));
        SelectionSort sort = new SelectionSort();

        sort.simpleSelectionSort(arr);

        System.out.print("Sorted array using simple Selection Sort: ");
        System.out.println(Arrays.toString(arr));

    }
}
