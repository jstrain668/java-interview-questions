package com.dsa.foundation.sort;

import java.util.Arrays;

public class BubbleSorting {

    //Time complexity O(nxn)
    public void simpleBubbleSort(int[] elements){
        int temp = 0;
        int count = 0;

        for (int i=0; i < elements.length-1;i++){
            for (int j=0; j < elements.length-1; j++){
                count++;
                if (elements[j] > elements[j+1]){
                    temp = elements[j];
                    elements[j] = elements[j+1];
                    elements[j+1] = temp;
                }
            }
        }
        System.out.println("Counter: "+count);
    }

    // Since the rightmost element will be the largest element after the first iteration of
    // of the inner for loop, there is no for it to be compared for successive iterations.
    // The inner loop iterations are reduced by the value of the outer loop.
    // While reduces the number of iterations the time complexity is still O(nxn)
    public void improvedSimpleBubbleSort(int[] elements){
        int temp = 0;
        int count = 0;

        for (int i=0; i < elements.length-1;i++){
            for (int j=0; j < elements.length-i-1; j++){
                count++;
                if (elements[j] > elements[j+1]){
                    temp = elements[j];
                    elements[j] = elements[j+1];
                    elements[j+1] = temp;
                }
            }
        }
        System.out.println("Counter: "+count);
    }

    //Optimized by stopping the algorithm if inner loop didn’t cause any swap
    public void optimizedSimpleBubbleSort(int[] elements){
        int temp = 0;
        int count = 0;
        boolean swapped = false;

        for (int i=0; i < elements.length-1;i++){
            for (int j=0; j < elements.length-i-1; j++){
                count++;
                if (elements[j] > elements[j+1]){
                    temp = elements[j];
                    elements[j] = elements[j+1];
                    elements[j+1] = temp;
                    swapped = true;
                }
            }

            // IF no two elements were swapped by inner loop,
            // then break out of outer loop
            if (!swapped)
                break;
        }
        System.out.println("Counter: "+count);
    }

    //Time complexity O(nxn)
    public void recursiveBubbleSort(int[] elements, int n){
        int temp = 0;

        //Base condition to exit from recursive calls
        if (n == 1) {
            return;
        }

        for (int i=0; i < n-1;i++){
            if (elements[i] > elements[i+1]){
                temp = elements[i];
                elements[i] = elements[i+1];
                elements[i+1] = temp;
            }
            recursiveBubbleSort(elements,n-1);
        }
    }

    public static void main(String[] args)
    {
        int[] arr = {64, 34, 25, 12, 90, 11, 89};
        System.out.print("Unsorted array: ");
        System.out.println(Arrays.toString(arr));
        BubbleSorting sort = new BubbleSorting();

        sort.simpleBubbleSort(arr);

        System.out.print("Sorted array using simple BubbleSort: ");
        System.out.println(Arrays.toString(arr));

        sort.improvedSimpleBubbleSort(arr);

        System.out.print("Sorted array using improved simple BubbleSort: ");
        System.out.println(Arrays.toString(arr));

        sort.optimizedSimpleBubbleSort(arr);

        System.out.print("Sorted array using optimized simple BubbleSort: ");
        System.out.println(Arrays.toString(arr));

        sort.recursiveBubbleSort(arr,arr.length);

        System.out.print("Sorted array using recursive BubbleSort: ");
        System.out.println(Arrays.toString(arr));
    }
}
