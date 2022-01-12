package com.dsa.foundation.bitwise;

//Reference: https://leetcode.com/problems/reverse-bits/

public class ReverseIntegerBits {

    //Description: Move each bit of input `n` to bit 0 to test if bit is set or not. Bit is AND with 1. Each bit is
    //moved by 1 to the right over 32 loops (32 bits in an integer). If bit 0 is set in `n` then bit 0 is set in `ans`
    // and `ans` shifted to the left.
    //Time Complexity: O(1) as the 32 iterations is a constant
    //Space Complexity: O(1)

    public int reverseBits(int n){
        int ans = 0;

        for(int i=0; i<32; i++){
            ans = ans << 1;
            if((n & 1) == 1){
                ans = ans | 1;
            }
            n = n >>1;
        }
        return ans;
    }

    public static void main(String[] args) {
        ReverseIntegerBits ri = new ReverseIntegerBits();
        int n = 11;
        int output = ri.reverseBits(n);
        System.out.println("Binary string value of original number "+n+ " = "+Integer.toBinaryString(n));
        System.out.println("Integer "+n+" reversed "+output);
        System.out.println("Binary string value of reversed number "+output+ " = "+Integer.toBinaryString(output));
    }
}
