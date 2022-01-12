package com.dsa.foundation.trees.avl;

import java.util.*;

//Question: Given an array and an integer K, find the maximum for each and every contiguous subarray of size k

//Approach:  use an AVL Tree that will return the maximum element in a log N time.
//https://geekstocode.com/maximum-of-all-subarrays-of-size-k/ - Method 3

public class MaxSubArraySizeK {

    //This method uses the uses the Self-Balancing BST(AVL Tree) to solve the above problem.
    //Traverse through the array and keep k elements in the BST and print the maximum in every iteration.
    //BST Tree is a suitable data structure for lookup, insertion, and deletion. It takes O(log n) time in
    //both the average and worst cases, where N is the number of nodes in the tree.

    //First, we create a Self-balancing BST (AVL tree) to store and find the maximum element of the array.
    //The next step is to traverse through the array from start to end.
    //Then we insert the element in the AVL tree.
    //If the loop counter is greater than or equal to k then we delete i-k th element from the BST(AVL Tree)
    //Last but not least we print the maximum element of the BST.

    //Time Complexity: O(N log k), each operation of BST of size K costs O(logk). Also, Insertion, deletion, and search take log k time in an AVL tree.
    //Space Complexity: The space required to store k elements in a BST O(k)

    public int[] maxSlidingWindowBST(int[] arr, int k) {
        int n = arr.length, j = 0;
        int[] ans = new int[n - k + 1];
        TreeMap<Integer, Integer> bst = new TreeMap<>();
        for (int i = 0; i < n; i++) {
            bst.put(arr[i], bst.getOrDefault(arr[i], 0) + 1);
            if (i + 1 >= k) {
                ans[j++] = bst.lastKey(); // return max element in BST
                removeElement(bst, arr[i+1-k]);
            }
        }
        return ans;
    }

    private void removeElement(TreeMap<Integer, Integer> bst, int x) {
        bst.put(x, bst.getOrDefault(x, 0) - 1);
        if (bst.get(x) == 0) bst.remove(x);
    }

    //Time Complexity: O(N * Log k).
    //Insertion, deletion and search takes log k time in a AVL tree. So the overall time Complexity is O(N * log k)
    //Space Complexity: O(k).
    //The space required to store k elements in a BST is O(k).

    //Create a Self-balancing BST (AVL tree) to store and find the maximum element.
    //Traverse through the array from start to end.
    //Insert the element in the AVL tree.
    //If the loop counter is greater than or equal to k then delete i-k th element from the BST
    //Print the maximum element of the BST.

    public List<Integer> findKMaxElement(int[] arr,int k)
    {
        // creating the max heap ,to get max element always
        PriorityQueue<Integer> queue = new PriorityQueue<>(
                Collections.reverseOrder());

        int n = arr.length;
        ArrayList<Integer> res = new ArrayList<>();

        int i=0;
        for (; i < k; i++)
            queue.add(arr[i]);

        // adding the maximum element among first k elements
        res.add(queue.peek());

        // removing the first element of the array
        queue.remove(arr[0]);

        // iterating for the next elements
        for (; i < n; i++) {

            // adding the new element in the window
            queue.add(arr[i]);

            // finding & adding the max element in the
            // current sliding window
            res.add(queue.peek());

            // finally removing the first element from front
            // end of queue
            queue.remove(arr[i - k + 1]);
        }

        return res;
    }

    // Driver code
    public static void main(String[] args)
    {
        MaxSubArraySizeK bst = new MaxSubArraySizeK();
        int[] arr = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 };
        int k = 3;
        int[] result = bst.maxSlidingWindowBST(arr, k);
        System.out.println("Max value for each K subarray: "+ Arrays.toString(result)); //3 4 5 6 7 8 9 10

        int[] arr2 = { 8, 5, 10, 7, 9, 4, 15, 12, 90, 13 };
        k = 4;

        List<Integer> res = bst.findKMaxElement(arr2, k); //10 10 10 15 15 90 90
        for (int x : res)
            System.out.print(x + " ");
    }
}
