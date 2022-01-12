package com.dsa.foundation.trees.heap;

public class ConvertToMaxHeap {

    // To heapify a subtree with root at given index
    public void MaxHeapify(int arr[], int i, int n)
    {
        int l = 2*i + 1;
        int r = 2*i + 2;
        int largest = i;
        if (l < n && arr[l] > arr[i])
            largest = l;
        if (r < n && arr[r] > arr[largest])
            largest = r;
        if (largest != i)
        {
            // swap arr[i] and arr[largest]
            int temp = arr[i];
            arr[i] = arr[largest];
            arr[largest] = temp;
            MaxHeapify(arr, largest, n);
        }
    }

    // This function basically builds max heap
    public void convertMaxHeap(int arr[], int n)
    {
        // Start from bottommost and rightmost
        // internal mode and heapify all internal
        // modes in bottom up way
        for (int i = (n-2)/2; i >= 0; --i)
            MaxHeapify(arr, i, n);
    }

    // A utility function to print a given array
    // of given size
    public void printArray(int arr[], int size)
    {
        for (int i = 0; i < size; ++i)
            System.out.print(arr[i]+" ");

        System.out.println();
    }

    // driver program
    public static void main (String[] args)
    {
        ConvertToMaxHeap heap = new ConvertToMaxHeap();
        // array representing Min Heap
        int arr[] = {3, 5, 9, 6, 8, 20, 10, 12, 18, 9};
        int n = arr.length;

        System.out.print("Min Heap array : ");
        heap.printArray(arr, n);

        heap.convertMaxHeap(arr, n);

        System.out.print("Max Heap array : ");
        heap.printArray(arr, n);
    }
}
