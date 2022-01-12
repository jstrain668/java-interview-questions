package com.dsa.foundation.logicpuzzles;

import java.util.Arrays;

//Background: Langford sequence, is a permutation of the sequence of 2n numbers (1, 1, 2, 2, ..., n, n) in which the
//two ones are one unit apart, the two twos are two units apart, and more generally the two copies of each number k are
//k units apart.
//
//Langford’s Problem L(n) Given the bag (array) of numbers {1,1,2,2,3,3,. . . ,n,n}, can
//they be arranged in a sequence such that for 1 ≤ i ≤ n there are i numbers between the two occurrences of i?

//Question: Write methods, that given an input n, find a Langford pairing, or declare there isn't one. Langford paring
//doesn't exist for n=4m-2, 4m-3 where m >= 1, but surely exists for n=4m, 4m-1.

//Reference: https://cafeaffe.wordpress.com/2017/01/10/langford-pairs/
//Reference: https://github.com/gagan405/Algorithms/blob/master/algorithms/src/in/cafeaffe/algo/LangfordPairs.java

public class LangfordPairing {

    public boolean canMakePairs(int[] pairs,int n){

        for(int i=0; i + n + 1 < pairs.length; i++) {

            if(pairs[i]==0 && pairs[i + n +1]==0) {
                pairs[i]= n;
                pairs[i + n +1]= n;

                if(n ==1){
                    return true;
                }

                if(canMakePairs(pairs,n-1)){
                    return true;
                }

                pairs[i]=0;
                pairs[i + n +1]=0;
            }
        }

        return false;
    }

    //mimic what you would do to solve solve the case n=4. Try and backtrack while respecting the rule of the Langford
    //pairing (exactly i positions apart for the two i's). Start with zero filled array, pick position 0 and 2 to put in
    // two 1's, and then try to put in two 2's, two 3's etc.. until you get stuck, and backtrack.
    //T(n, i) <= (2n - i - 1) * T(n, i - 1) + O(n)
    public int[] makeLangfordPairs(int n){

        if (n < 3){
            return null;
        }

        int[] pairs = new int[n*2];

        if (canMakePairs(pairs,n)){
            return pairs;
        }

        return null;
    }

    private int findPos(int[] nums, int start, int n) {
        for (int i = start; i + n + 1 < nums.length; i++){
            if(nums[i] == 0 && nums[i + n + 1] == 0){
                return i;
            }
        }
        return -1;
    }

    private boolean hasSolutionRecursive(int[] nums, int n) {
        int nextPos = findPos(nums, 0, n);
        if (nextPos == -1) {
            return false;
        }

        while(nextPos < nums.length) {
            nums[nextPos] = n;
            nums[nextPos + n + 1] = n;

            if (n == 1) {
                return true;
            }

            if(!hasSolutionRecursive(nums, n - 1)) {
                nums[nextPos] = 0;
                nums[nextPos + n + 1] = 0;
                nextPos = findPos(nums, nextPos + 1, n);

                if(nextPos == -1) {
                    return false;
                }
            } else {
                return true;
            }
        }
        return false;
    }

    private boolean isSolutionFeasible(int n) {
        return ((n % 4) == 3 || (n % 4) == 0) ? true : false;
    }

    public int[] getLangfordPairsRecursive(int n){

        if (n < 3)
        {
            return null;
        }

        int[] nums = new int[n*2];
        if (hasSolutionRecursive(nums,n)){
            return nums;
        }

        return null;
    }

    public static void main(String[] args) {
        LangfordPairing lp = new LangfordPairing();

        //int[] pairs = lp.makeLangfordPairs(5 );
        int[] pairs = lp.getLangfordPairsRecursive(5);
        if (pairs != null){
            System.out.println("Langford pairs: "+ Arrays.toString(pairs));
        } else{
            System.out.println("No langford pairs");
        }

    }
}
