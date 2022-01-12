package com.dsa.medium.trees.heap;


public class ArrayIsMinHeap {

    public boolean checkMinHeap(int[] nums){
        if (nums == null || nums.length == 0){
            return false;
        }

        for (int i=0; i < (nums.length - 2) / 2; i++){
            if (nums[i] > nums[2*1 +1] || (nums[i] > nums[2*i + 2] && nums.length != 2*1 +2)){
                return false;
            }
        }
        return true;
    }

    public static void main(String[] args)
    {
        ArrayIsMinHeap heap = new ArrayIsMinHeap();
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
