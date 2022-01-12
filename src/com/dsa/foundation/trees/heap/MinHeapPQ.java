package com.dsa.foundation.trees.heap;

import java.util.PriorityQueue;

public class MinHeapPQ {

    PriorityQueue<Integer> minHeap;

    public MinHeapPQ() {
        minHeap = new PriorityQueue<>(10);
    }

    public void print() {
        System.out.print("Min Heap : ");
        for(Integer i:minHeap)
            System.out.print(i+" ");
        System.out.println("\n");
    }

    public static void main(String args[]) {

        MinHeapPQ heap = new MinHeapPQ();

        // Adding elements using add()
        heap.minHeap.add(8);
        heap.minHeap.add(5);
        heap.minHeap.add(13);
        heap.minHeap.add(2);
        heap.minHeap.add(23);
        heap.minHeap.add(16);

        // Displaying minHeap elements
        heap.print();

        // Find number of elements using size()
        System.out.println("Size of heap = " + heap.minHeap.size());

        // View head using peek()
        System.out.println("Head = " + heap.minHeap.peek());

        // Remove head and modify Heap using poll()
        heap.minHeap.poll();
        System.out.println("Heap after removing head");
        heap.print();

        // Remove specific element using remove()
        heap.minHeap.remove(8);
        System.out.println("Heap after removing 8");
        heap.print();

        // Check if an element is present using contains()
        boolean flag = heap.minHeap.contains(15);
        System.out.println("Does heap contain element 15? " + flag);

        System.out.println("Size of heap = " + heap.minHeap.size());
    }
}
