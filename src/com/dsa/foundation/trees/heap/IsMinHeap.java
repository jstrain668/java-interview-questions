package com.dsa.foundation.trees.heap;

public class IsMinHeap {

    // Iterative function to check if a given array represents
    // min-heap or not
    public boolean checkMinHeap(int[] A)
    {
        // check for all internal nodes that their left child and
        // right child (if present) holds min-heap property or not

        // start with index 0 (the root of the heap)
        for (int i = 0; i <= (A.length - 2) / 2; i++)
        {
            if (A[i] > A[2*i + 1] || (2*i + 2 != A.length && A[i] > A[2*i + 2])) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args)
    {
        IsMinHeap heap = new IsMinHeap();
        int[] A = {1, 2, 3, 4, 5, 6};

        if (heap.checkMinHeap(A)) {
            System.out.println("The given array is a min-heap");
        }
        else {
            System.out.println("The given array is not a min-heap");
        }

        int[] B = {2,10,4,5,3,15};

        if (heap.checkMinHeap(B)) {
            System.out.println("The given array is a min-heap");
        }
        else {
            System.out.println("The given array is not a min-heap");
        }
    }
}
