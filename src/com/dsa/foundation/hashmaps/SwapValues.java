package com.dsa.foundation.hashmaps;


//Swapping pairs make sum equal: https://practice.geeksforgeeks.org/problems/swapping-pairs-make-sum-equal4142/1


import java.util.HashMap;

public class SwapValues {

    int findSwapValues(int A[], int n, int  B[], int m)
    {
        // Your code goes here
        int sum1=0;
        int sum2=0;
        HashMap<Integer,Integer> map2=new HashMap<>();
        for(int i=0;i<n;i++){
            sum1 +=A[i];
        }

        for(int i=0;i<m;i++){
            sum2 += B[i];
            if(!map2.containsKey(B[i]))
                map2.put(B[i],1);
        }

        int diff=sum1-sum2;

        for(int i=0;i<n;i++){
            int e1=A[i];

            if((2*e1-diff)%2==0 && map2.containsKey((2*e1-diff)/2))
                return 1;
        }

        return -1;
    }


    public static void main(String[] args) {

        SwapValues sv = new SwapValues();
        int[] A = {4, 1, 2, 1, 1, 2};
        int[] B = {3, 6, 3, 3};

        int result = sv.findSwapValues(A, A.length,B,B.length);
        System.out.println(result);

    }
}
