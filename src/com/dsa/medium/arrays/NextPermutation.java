package com.dsa.medium.arrays;

//Question: https://leetcode.com/problems/next-permutation/

//Reference: https://iq.opengenus.org/lexicographical-next-permutation/
//Reference: https://leetcode.com/problems/next-permutation/solution/

import java.util.Arrays;

public class NextPermutation {

    //Best Algorithm in O(N)
    //Step 1 : Find the largest index i such that array[i − 1] < array[i].
    //        (If no such i exists, then this is already the last permutation.)
    //Step 2 : Find largest index j such that j ≥ i and array[j] > array[i − 1].
    //Step 3 : Swap array[j] and array[i − 1].
    //Step 4 : Reverse the suffix starting at array[i].
    //Time Complexity: O(n) there is 3 traversals however drop the constant
    //Space Complexity:O(1)
    public void nextPermutation(int[] nums) {
        int i = nums.length - 2;
        while (i>=0 && nums[i+1]<=nums[i]){
            i--;
        }

        if (i >=0){
            int j = nums.length-1;
            while (nums[j] <= nums[i]){
                j--;
            }
            swap(nums,i,j);
        }
        reverse(nums,i+1);

    }

    private void reverse(int[] nums, int start) {
        int i = start, j = nums.length - 1;
        while (i < j) {
            swap(nums, i, j);
            i++;
            j--;
        }
    }

    private void swap(int[] nums, int i, int j) {
       int temp = nums[i];
       nums[i] = nums[j];
       nums[j] = temp;
    }

    public static void main(String[] args) {

        NextPermutation np = new NextPermutation();
        int[] nums = {1,2,3};
        System.out.println("Input array: "+Arrays.toString(nums));
        np.nextPermutation(nums);
        System.out.println("Next permutation array: "+Arrays.toString(nums));
     }
}
