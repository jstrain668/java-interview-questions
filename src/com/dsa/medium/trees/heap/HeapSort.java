package com.dsa.medium.trees.heap;

//Question: https://www.techiedelight.com/heap-sort-place-place-implementation-c-c/

//Heapsort is an in-place, comparison-based sorting algorithm and can be thought of as an improved selection sort as it
//divides the input into a sorted and an unsorted region. It iteratively shrinks the unsorted region by extracting the
// largest/smallest element and moving that to the sorted region. The improvement consists of using a heap data structure
// rather than a linear-time search to find the maximum. Heapsort does not produce a stable sort, which means that the
// implementation does not preserve the input order of equal elements in the sorted output. It is generally slower than
// other O(n.log(n)) sorting algorithms like quicksort, merge sort.

//The heapsort algorithm can be divided into two parts:
//
//In the first step, a heap is built out of the input data. We can do this in O(n) time.
//In the second step, a sorted array is created by repeatedly removing the largest/smallest element from the heap (the
// root of the heap) and inserting it into the array. The heap is updated (heapify-down is called on the root node)
// after each removal to maintain the heap property. Once all elements have been removed from the heap, we get a sorted
// array. This is done in O(n.log(n)) time since we need to pop n elements, where each removal involves a constant
// amount of work and a single heapify operation takes O(log(n)) time.

//1. In-place Heapsort Implementation
//Heapsort can be performed in place. We can do this by
//
//Using a max-heap instead of min-heap (to sort in ascending order),
//Using an input array for constructing heap (instead of using heap own storage)
//The idea is to split the array into two parts – the heap and the sorted array. As each pop operation free space to the
// end of the array in a binary heap, move the popped item to the free space. So, the first popped item (maximum
// element) will go to the last position in the array, the second popped item (next maximum element) will go to the
// second last position in the array, and so on… finally, when all items are popped from the heap, we will get an array
// sorted in ascending order.

import java.util.Arrays;

public class HeapSort {

    // return left child of `A[i]`
    private int LEFT(int i) {
        return (2*i + 1);
    }

    // return right child of `A[i]`
    private int RIGHT(int i) {
        return (2*i + 2);
    }

    // Utility function to swap two indices in the array
    private void swap(int[] A, int i, int j)
    {
        int temp = A[i];
        A[i] = A[j];
        A[j] = temp;
    }

    // Recursive heapify-down algorithm. The node at index `i` and
    // its two direct children violates the heap property
    private void heapify(int[] A, int i, int size)
    {
        // get left and right child of node at index `i`
        int left = LEFT(i);
        int right = RIGHT(i);

        int largest = i;

        // compare `A[i]` with its left and right child
        // and find the largest value
        if (left < size && A[left] > A[i]) {
            largest = left;
        }

        if (right < size && A[right] > A[largest]) {
            largest = right;
        }

        // swap with a child having greater value and
        // call heapify-down on the child
        if (largest != i)
        {
            swap(A, i, largest);
            heapify(A, largest, size);
        }
    }

    // Function to remove an element with the highest priority (present at the root)
    public int pop(int[] A, int size)
    {
        // if the heap has no elements
        if (size <= 0) {
            return -1;
        }

        int top = A[0];

        // replace the root of the heap with the last element
        // of the array
        A[0] = A[size-1];

        // call heapify-down on the root node
        heapify(A, 0, size - 1);

        return top;
    }

    //The time complexity of the above algorithm is O(n.log(n)), where n is the input size and requires O(n) implicit
    // space for the call stack.
    // Function to perform heapsort on array `A` of size `n`
    public void heapsort(int[] A)
    {
        // build a priority queue and initialize it by the given array
        int n = A.length;

        // Build-heap: Call heapify starting from the last internal
        // node all the way up to the root node
        int i = (n - 2) / 2;
        while (i >= 0) {
            heapify(A, i--, n);
        }

        // repeatedly pop from the heap till it becomes empty
        while (n > 0)
        {
            A[n - 1] = pop(A, n);
            n--;
        }
    }

    // Heapsort algorithm implementation in Java
    public static void main(String[] args)
    {
        HeapSort hs = new HeapSort();
        int[] A = { 6, 4, 7, 1, 9, -2 };

        // perform heapsort on the array
        hs.heapsort(A);

        // print the sorted array
        System.out.println(Arrays.toString(A));
    }
}
