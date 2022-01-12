package com.dsa.foundation.basics.twodarray;

import java.util.Arrays;

//Reference: https://www.geeksforgeeks.org/multidimensional-arrays-in-java/

public class TwoDArray {

    public int[][] init2Darray(int rowLen,int colLen,int fillValue){
        int[][] nums = new int[rowLen][colLen];

        for (int[] num : nums)
            Arrays.fill(num,fillValue);

        return nums;
    }

    public int[][] init2DarrayWithKnownValues(){
        int[][] nums = {{1,1,5},
                        {1,2,10},
                        {2,2,15},
                        {2,0,20},
                        {3,1,25},
                        {3,4,30}};

        return nums;
    }

    public void print(int[][] nums){

        int rowLen = nums.length;
        int colLen = nums[0].length;

        for (int i=0; i < rowLen; i++){
            for (int j=0; j < colLen; j++){
                System.out.println("["+i+"]["+j+"] = "+nums[i][j]);
            }
        }
    }

    public static void main(String[] args) {
        TwoDArray twoD = new TwoDArray();

        int[][] nums = twoD.init2Darray(3,2,-1);
        twoD.print(nums);
        nums = twoD.init2DarrayWithKnownValues();
        twoD.print(nums);


    }
}
