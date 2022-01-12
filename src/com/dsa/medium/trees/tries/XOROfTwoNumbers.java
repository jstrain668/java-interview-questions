package com.dsa.medium.trees.tries;


import java.util.HashSet;

//Question: https://leetcode.com/problems/maximum-xor-of-two-numbers-in-an-array/

public class XOROfTwoNumbers {

    class TrieBit{
        TrieBit[] children;
        boolean isLeaf;

        public TrieBit(){
            children = new TrieBit[2];
        }
    }

    public void insert (TrieBit root,int num){

        TrieBit curr = root;
        for (int i = 31; i >= 0; i--) {
            int bit = (num >> i) & 1;
            if (curr.children[bit] == null) {
                curr.children[bit] = new TrieBit();
            }
            curr = curr.children[bit];
        }
        curr.isLeaf = true;

    }


    public int findMaxXOR(int[] nums){
        int maxXOR = 0;
        TrieBit root = new TrieBit();

        for (int num : nums){
            insert(root,num);
        }

        for(int num:nums){
            TrieBit curr = root;
            int currMaxXor = 0;
            for(int i=31; i >= 0; i--){
                int complimentBit = 1-((num>>i) & 1);
                if(curr.children[complimentBit] != null) {
                    currMaxXor += (1 << i);
                }
                else {
                    complimentBit=1 - complimentBit;
                }
                curr=curr.children[complimentBit];
            }
            maxXOR=Math.max(maxXOR,currMaxXor);
        }
        return maxXOR;

    }


    public int findMaximumXOR(int[] nums) {

        int max=0,mask=0;
        for(int i=31;i>=0;i--){
            mask=mask|(1<<i);
            HashSet<Integer> set=new HashSet<Integer>();
            for(int num:nums){
                set.add(mask&num);
            }
            int temp=max|(1<<i);
            for(int prefix:set){
                if(set.contains(temp^prefix))
                    max=temp;
            }
        }
        return max;
    }


    public static void main(String[] args) {

        XOROfTwoNumbers xor = new XOROfTwoNumbers();
        int[] nums = {14,70,53,83,49,91,36,80,92,51,66,70};

        System.out.println("Maximum XOR of Two Numbers in an Array: "+xor.findMaximumXOR(nums));

        System.out.println("Maximum XOR of Two Numbers in an Array: "+xor.findMaxXOR(nums));


    }
}
