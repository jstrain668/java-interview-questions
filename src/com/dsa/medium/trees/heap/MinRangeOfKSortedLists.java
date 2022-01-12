package com.dsa.medium.trees.heap;



import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

//Question: https://www.techiedelight.com/merge-m-sorted-lists-variable-length/

//Solution: https://leetcode.com/problems/smallest-range-covering-elements-from-k-lists/discuss/1540558/JAVA-Solution-using-Priority-Queue-Readable-Code-with-Explanation

public class MinRangeOfKSortedLists {

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

    // Function to compute the minimum range that includes at least one element
    // from each of the given `M` lists
    public int[] findMinimumRange(List<List<Integer>> lists)
    {
        // invalid input
        if (lists == null || lists.size() == 0) {
            return new int[0];
        }

        // create an empty min-heap
        PriorityQueue<HeapNode> pq = new PriorityQueue<>(new Comparator<HeapNode>() {
            @Override
            public int compare(HeapNode o1, HeapNode o2) {
                return o1.value - o2.value;
            }
        });

        //max of each k element
        int max = 0;
        for(int i = 0 ; i < lists.size(); i++){
            max = Math.max(max, lists.get(i).get(0));
            pq.add(new HeapNode(i, 0, lists.get(i).get(0)));
        }

        int[] ans = new int[2];
        //range
        int range = Integer.MAX_VALUE;
        while(pq.size() > 0){
            HeapNode p = pq.poll();

            //updating range if smaller
            if(max - p.value < range){
                ans[0] = p.value;
                ans[1] = max;
                range = max - p.value;
            }

            //checking the validity
            if(p.index + 1 < lists.get(p.listNum).size()){
                max = Math.max(lists.get(p.listNum).get(p.index + 1), max);
                pq.add(new HeapNode(p.listNum, p.index + 1, lists.get(p.listNum).get(p.index + 1)));
            }else break;
        }
        return ans;
    }

    public static void main(String[] args)
    {
        MinRangeOfKSortedLists heap = new MinRangeOfKSortedLists();
        List<List<Integer>> lists = Arrays.asList(
                Arrays.asList(3, 6, 8, 10, 15),
                Arrays.asList(1, 5, 12),
                Arrays.asList(4, 8, 15, 16),
                Arrays.asList(2, 6)
        );

        System.out.println("The minimum range is " + Arrays.toString(heap.findMinimumRange(lists)));
    }

}
