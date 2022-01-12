package com.dsa.foundation.sort;


import org.apache.commons.lang3.ArrayUtils;

public class QuickSort {

    private void quickSort(int[] elements, int left, int right) {

        // Base case: End of recursion reached?
        if (left >= right)
            return;

        int pivotPos = partition(elements, left, right);
        quickSort(elements, left, pivotPos - 1);
        quickSort(elements, pivotPos + 1, right);
    }

    private int partition(int[] elements, int left, int right) {
        int pivot = elements[right];

        int i = left;
        int j = right - 1;
        while (i < j) {
            // Find the first element >= pivot
            while (elements[i] < pivot) {
                i++;
            }

            // Find the last element < pivot
            while (j > left && elements[j] >= pivot) {
                j--;
            }

            // If the greater element is left of the lesser element, switch them
            if (i < j) {
                ArrayUtils.swap(elements, i, j);
                i++;
                j--;
            }
        }

        // i == j means we haven't checked this index yet.
        // Move i right if necessary so that i marks the start of the right array.
        if (i == j && elements[i] < pivot) {
            i++;
        }

        // Move pivot element to its final position
        if (elements[i] != pivot) {
            ArrayUtils.swap(elements, i, right);
        }
        return i;
    }


    /* print array */
    private void printArray(int[] arr) {
        for (int value : arr) System.out.print(value + " ");
        System.out.println();
    }

    public static void main(String[] args) {
        int[] numbers = {99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0};
        QuickSort qs = new QuickSort();

        qs.quickSort(numbers, 0, numbers.length - 1);
        qs.printArray(numbers);
    }
}
