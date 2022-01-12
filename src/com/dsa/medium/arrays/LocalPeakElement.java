package com.dsa.medium.arrays;

import java.util.Arrays;

//Reference: https://leetcode.com/problems/find-peak-element/

public class LocalPeakElement {


    //See below for solution approach.
    // Time complexity : O(log_2(n)). Reduce the search space in half at every step. Thus, the total search space will
    // be consumed in log_2(n) steps. Here, n refers to the size of nums array.
    // Space complexity : O(1). Constant extra space is used.

    public int findPeakElement(int[] nums) {
        int l = 0, r = nums.length - 1;

        while (l < r) {
            int mid = l + (r - l) / 2;
            if (nums[mid] > nums[mid + 1])
                r = mid;
            else
                l = mid + 1;
        }
        return l;
    }

    //We can view any given sequence in nums array as alternating ascending and descending sequences. By making use
    // of this, and the fact that we can return any peak as the result, we can make use of Binary Search to find the
    // required peak element.
    //
    //In case of simple Binary Search, we work on a sorted sequence of numbers and try to find out the required number
    // by reducing the search space at every step. In this case, we use a modification of this simple Binary Search to
    // our advantage. We start off by finding the middle element, mid from the given nums array. If this element happens
    // to be lying in a descending sequence of numbers. or a local falling slope(found by comparing nums[i] to its
    // right neighbour), it means that the peak will always lie towards the left of this element. Thus, we reduce the
    // search space to the left of mid(including itself) and perform the same process on left subarray.
    //
    //If the middle element, mid lies in an ascending sequence of numbers, or a rising slope  (found by comparing
    // nums[i] to its right neighbour), it obviously implies that the peak lies towards the right of this element.
    // Thus, we reduce the search space to the right of mid and perform the same process on the right subarray.
    //
    //In this way, we keep on reducing the search space till we eventually reach a state where only one element is
    // remaining in the search space. This single element is the peak element.
    //
    //To see how it works, let's consider the three cases discussed above again.
    //
    //Case 1. In this case, we firstly find 33 as the middle element. Since it lies on a falling slope, we reduce the
    // search space to [1, 2, 3]. For this subarray, 2 happens to be the middle element, which again lies on a falling
    // slope, reducing the search space to [1, 2]. Now, 1 acts as the middle element and it lies on a falling slope,
    // reducing the search space to [1] only. Thus, 1 is returned as the peak correctly.
    //
    //Case 2. In this case, we firstly find 3 as the middle element. Since it lies on a rising slope, we reduce the
    // search space to [4, 5]. Now, 4 acts as the middle element for this subarray and it lies on a rising slope,
    // reducing the search space to [5] only. Thus, 5 is returned as the peak correctly.
    //
    //Case 3. In this case, the peak lies somewhere in the middle. The first middle element is 44. It lies on a rising
    // slope, indicating that the peak lies towards its right. Thus, the search space is reduced to [5, 1]. Now, 5
    // happens to be the on a falling slope (relative to its right neighbour), reducing the search space to [5] only.
    // Thus, 5 is identified as the peak element correctly.
    // Time complexity : O(log_2(n)): Reduce the search space in half at every step.
    // Thus, the total search space will be consumed in log_2(n) (n) steps. Here, n refers to the size of num array.
    //
    //Space complexity : O(log_2(n)). Reduce the search space in half at every step. Thus, the total search space will
    // be consumed in log_2(n) (n) steps. Thus, the depth of recursion tree will go upto log_2(n)

    public int findPeakElementRecursive(int[] nums) {
        return search(nums, 0, nums.length - 1);
    }

    public int search(int[] nums, int l, int r) {
        if (l == r)
            return l;
        int mid = (l + r) / 2;
        if (nums[mid] > nums[mid + 1])
            return search(nums, l, mid);
        return search(nums, mid + 1, r);
    }

    public static void main(String[] args) {
        LocalPeakElement lpe = new LocalPeakElement();
        int[] nums = {1,2,1,3,5,6,4};
        System.out.println("Input array: "+Arrays.toString(nums));
        System.out.println("Peak element: "+lpe.findPeakElement(nums));
        System.out.println("Peak element: "+lpe.findPeakElementRecursive(nums));

    }
}
