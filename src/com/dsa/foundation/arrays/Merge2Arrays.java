package com.dsa.foundation.arrays;

//Reference: https://www.geeksforgeeks.org/merge-two-sorted-arrays/

public class Merge2Arrays {

    //Description: Create an array arr3[] of size n1 + n2.
    //Simultaneously traverse arr1[] and arr2[].
    //Pick smaller of current elements in arr1[] and arr2[], copy this smaller element to next position in arr3[] and
    // move ahead in arr3[] and the array whose element is picked.
    //If there are remaining elements in arr1[] or arr2[], copy them also in arr3[].
    //Time Complexity: O(n1 + n2)
    //Auxiliary Space : O(n1 + n2)
    public int[] mergeArrays(int[] arr1, int[] arr2) {

        int arrLen1 = arr1.length;
        int arrLen2 = arr2.length;
        int[] arr3 = new int[arrLen1+arrLen2];
        int index1 = 0, index2 = 0, index3 = 0;

        while (index1 < arrLen1 && index2 < arrLen2) {

            if (arr1[index1] < arr2[index2]) {
                arr3[index3++] = arr1[index1++];
            } else {
                arr3[index3++] = arr2[index2++];
            }

        }

        while (index1 < arrLen1) {
            arr3[index3++] = arr1[index1++];
        }

        while (index2 < arrLen2) {
            arr3[index3++] = arr2[index2++];
        }

        return arr3;
    }

    public static void main(String[] args) {
        Merge2Arrays ma = new Merge2Arrays();
        int[] arr1 = {1, 3, 5, 7};
        int n1 = arr1.length;

        int[] arr2 = {2, 4, 6, 8};
        int n2 = arr2.length;

        int[] arr3 = ma.mergeArrays(arr1, arr2);

        System.out.println("Array after merging");
        for (int i=0; i < n1+n2; i++)
            System.out.print(arr3[i] + " ");
    }
}
