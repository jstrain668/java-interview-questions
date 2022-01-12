package com.dsa.foundation.trees.heap;

import java.util.Collections;
import java.util.Comparator;
import java.util.PriorityQueue;

public class MaxHeapPQ {

    PriorityQueue<Integer> maxHeap;

    public MaxHeapPQ() {
        maxHeap = new PriorityQueue<>(10, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return o2.compareTo(o1);
            }
        });
    }

    public void print() {
        System.out.print("Max Heap : ");
        for(Integer i:maxHeap)
            System.out.print(i+" ");
        System.out.println("\n");
    }

    public static void main(String args[]) {

        MaxHeapPQ heap = new MaxHeapPQ();

        // Adding elements using add()
        heap.maxHeap.add(8);
        heap.maxHeap.add(5);
        heap.maxHeap.add(13);
        heap.maxHeap.add(2);
        heap.maxHeap.add(23);
        heap.maxHeap.add(16);

        // Displaying minHeap elements
        heap.print();

        // Find number of elements using size()
        System.out.println("Size of heap = " + heap.maxHeap.size());

        // View head using peek()
        System.out.println("Head = " + heap.maxHeap.peek());

        // Remove head and modify Heap using poll()
        heap.maxHeap.poll();
        System.out.println("Heap after removing head");
        heap.print();

        // Remove specific element using remove()
        heap.maxHeap.remove(8);
        System.out.println("Heap after removing 8");
        heap.print();

        // Check if an element is present using contains()
        boolean flag = heap.maxHeap.contains(15);
        System.out.println("Does heap contain element 15? " + flag);

        System.out.println("Size of heap = " + heap.maxHeap.size());
    }
}
