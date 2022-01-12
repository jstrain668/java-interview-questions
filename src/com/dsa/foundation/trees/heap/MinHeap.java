package com.dsa.foundation.trees.heap;

//Question and Reference: https://algorithms.tutorialhorizon.com/binary-min-max-heap/

//https://medium.com/competitive-programming-zero-to-hero/understanding-heaps-with-java-implementation-70cc0ce9dc9d
//https://www.techiedelight.com/min-heap-max-heap-implementation-in-java/

import java.util.Arrays;
import java.util.NoSuchElementException;

public class MinHeap {

    private int capacity;
    private int size = 0;
    private int[] heap;

    private MinHeap(int capacity) {
        this.capacity = capacity;
        this.heap = new int[capacity];
    }


    private int getLeftChildIndex(int parentIndex) {
        return 2 * parentIndex + 1;
    }

    private int getRightChildIndex(int parentIndex) {
        return 2 * parentIndex + 2;
    }

    private int getParentIndex(int childIndex) {
        return (childIndex - 1) / 2;
    }

    private boolean hasLeftChild(int index) {
        return getLeftChildIndex(index) < size;
    }

    private boolean hasRightChild(int index) {
        return getRightChildIndex(index) < size;
    }

    private boolean hasParent(int index) {
        return getParentIndex(index) >= 0;
    }

    private int leftChild(int parentIndex) {
        return heap[getLeftChildIndex(parentIndex)];
    }

    private int rightChild(int parentIndex) {
        return heap[getRightChildIndex(parentIndex)];
    }

    private int parent(int childIndex) {
        return heap[getParentIndex(childIndex)];
    }

    private void swap(int index1, int index2) {
        int element = heap[index1];
        heap[index1] = heap[index2];
        heap[index2] = element;
    }

    private void ensureCapacity() {
        if (size == capacity) {
            heap = Arrays.copyOf(heap, capacity * 2);
            capacity = capacity * 2;
        }
    }

    // Time Complexity : O(1)
    private int peek() {
        if (size == 0) {
            throw new NoSuchElementException();
        }
        return heap[0];
    }

    // Time Complexity : O( Log n)
    private int poll() {
        if (size == 0) {
            throw new NoSuchElementException();
        }

        int element = heap[0];

        heap[0] = heap[size - 1];
        size--;
        heapifyDown();
        return element;
    }

    // Time Complexity : O( Log n)
    public void add(int item) {
        ensureCapacity();
        heap[size] = item;
        size++;
        heapifyUp();
    }

    private void heapifyUp() {
        int index = size - 1;

        while (hasParent(index) && parent(index) > heap[index]) {
            swap(getParentIndex(index), index);
            index = getParentIndex(index);
        }
    }

    private void heapifyDown() {
        int index = 0;

        while (hasLeftChild(index)) {
            int smallestChildIndex = getLeftChildIndex(index);

            if (hasRightChild(index) && rightChild(index) < leftChild(index)) {
                smallestChildIndex = getRightChildIndex(index);
            }

            if (heap[index] < heap[smallestChildIndex]) {
                break;
            } else {
                swap(index, smallestChildIndex);
            }
            index = smallestChildIndex;
        }
    }

    private void printHeap() {
        for (int i = 0; i < size; i++) {
            System.out.print(heap[i] + " ");
        }
        System.out.println();
    }

    public static void main(String[] args){

        MinHeap mh = new MinHeap(10);
        mh.add(14);
        mh.printHeap();
        mh.add(10);
        mh.printHeap();
        mh.add(3);
        mh.printHeap();
        mh.add(5);
        mh.printHeap();
        mh.add(2);
        mh.printHeap();
        mh.poll();
        mh.printHeap();
        mh.poll();
        mh.printHeap();
        mh.poll();
        mh.printHeap();


    }
}
