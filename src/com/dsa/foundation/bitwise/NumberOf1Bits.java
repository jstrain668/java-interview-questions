package com.dsa.foundation.bitwise;

//Reference: https://leetcode.com/problems/number-of-1-bits/

public class NumberOf1Bits {


    //Description: We check each of the 32bits of the number. If the bit is 1, we add
    // one to the number of 1-bits. We can check the ith bit of a number using a bit mask.
    // We start with a mask m=1 because
    // the binary representation of 1 is, 0000 0000 0000 0000 0000 0000 0000 0001
    // Clearly, a logical AND between any number and the mask 1 gives us the least significant
    // bit of this number. To check the next bit, we shift the mask to the left by one.
    // 0000 0000 0000 0000 0000 0000 0000 0010. And so on.
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    public int hammingWeight(int n) {

        int mask = 1;
        int bitCount = 0;

        for (int i=0; i < 32; i++){
            if ((n&mask) != 0){
                bitCount++;
            }
            mask <<= 1;
        }
        return bitCount;
    }

    //Repeatedly flip the least-significant 1-bit of the number to 0, and add 1 to the sum. As soon
    //as the number becomes 0, we know that it does not have any more 1-bits, and we return the sum.
    //In the binary representation, the least significant 1-bit in nn always corresponds to a 0-bit in
    //n−1. Therefore, anding the two numbers n and n - 1 always flips the least significant 1-bit in n
    //to 0, and keeps all other bits the same.
    //Time Complexity: O(1)
    //Space Complexity: O(1)
    public int hammingWeightAlt(int n) {

        int sum = 0;

        while (n != 0){

            sum++;
            n &= (n-1);
        }
        return sum;
    }

    public static void main(String[] args) {
        NumberOf1Bits numberOf1Bits = new NumberOf1Bits();
        int n = 799;
        System.out.println("Input value is: "+n);
        System.out.println("Input value as binary: "+Integer.toBinaryString(n));
        System.out.println("Number of 1 bits: "+numberOf1Bits.hammingWeight(n));
    }
}
