package com.dsa.medium.trees.heap;

import java.util.*;

//Reference: https://medium.com/outco/how-to-merge-k-sorted-arrays-c35d87aa298e
//Another version of same question: https://www.techiedelight.com/merge-m-sorted-lists-containing-n-elements/

//Can solve this problem in O(N.log(M)) time by using a min-heap. The idea is to construct a min-heap of size M and
// insert the first element of each list into it. Then pop the root element (minimum element) from the heap and insert
// the next element from the “same” list as the popped element. Repeat this process till the heap is exhausted.
// Depending upon the requirement, either print the popped element or store it in an auxiliary list.

//The complexity of the code to merge k sorted arrays is O(nklogk) along with space complexity of O(k)

public class MergeKSortedLists {

    private class HeapNode {
        public int listNum;
        public int index;
        public int value;

        public HeapNode(int listNum, int index, int value) {
            this.listNum = listNum;
            this.index = index;
            this.value = value;
        }
    }

    public List<Integer> mergeKSortedArrays(List<List<Integer>> lists) {

        List<Integer> result = new ArrayList<>();
        if (lists == null || lists.isEmpty()){
            return result;
        }

        //Create min-heap sorted by the values in the k-sorted arrays
        PriorityQueue<HeapNode> pq =
                new PriorityQueue<>(new Comparator<>() {
                    @Override
                    public int compare(HeapNode o1, HeapNode o2) {
                        return o1.value - o2.value;
                    }
                });

        //add first elements in the array to this heap
        for (int i = 0; i < lists.size(); i++) {
            pq.add(new HeapNode(i, 0, lists.get(i).get(0)));
        }

        //Complexity O(n * k * log k)
        // run till min-heap is empty
        while (!pq.isEmpty())
        {
            // extract the minimum node from the min-heap
            HeapNode min = pq.poll();

            // print or add the minimum element
            result.add(min.value);

            // take the next element from the "same" list and insert it into the
            // min-heap
            if (min.index + 1 < lists.get(min.listNum).size())
            {
                min.index++;
                min.value = lists.get(min.listNum).get(min.index);
                pq.add(min);
            }
        }

        return result;
    }


    //https://www.algorithmsandme.com/merge-k-sorted-arrays/

    public int[] mergeKSortedArrays(int[][] arrays) {

        if (arrays == null) return null;

        PriorityQueue<HeapNode> minHeap =
                new PriorityQueue<>(arrays.length, new Comparator<HeapNode>() {
                    @Override
                    public int compare(HeapNode o1, HeapNode o2) {
                        return o1.value - o2.value;
                    }
                });

        int size = 0;
        for (int i = 0; i < arrays.length; i++) {
            size += arrays[i].length;
        }
        int[] result = new int[size]; // k * n

        //add first elements in the array to this heap
        for (int i = 0; i < arrays.length; i++) {
            minHeap.add(new HeapNode(i, 0, arrays[i][0]));
        }

        //Complexity O(n * k * log k)
        int index = 0;
        while (!minHeap.isEmpty()){
            HeapNode node = minHeap.poll();

            result[index++] = node.value;

            // take the next element from the "same" list and insert it into the
            // min-heap
            if (node.index + 1 < arrays[node.listNum].length)
            {
                node.index++;
                node.value = arrays[node.listNum][node.index];
                minHeap.add(node);
            }

        }
        return result;
    }

    public static void main(String[] args)
    {
        MergeKSortedLists heap = new MergeKSortedLists();
        List<List<Integer>> lists = Arrays.asList(
                Arrays.asList(10, 20, 30, 40),
                Arrays.asList(15, 25, 35),
                Arrays.asList(27, 29, 37, 48, 93),
                Arrays.asList(32, 33)
        );

        List<Integer> result = heap.mergeKSortedArrays(lists);
        System.out.println(result);

        int[][] arrays = { {3,4,6,7,9,10},
                {6,9,10,18,20,25},
                {5,7,9,11}};
        int[] res = heap.mergeKSortedArrays(arrays);
        System.out.println(Arrays.toString(res));

    }
}
