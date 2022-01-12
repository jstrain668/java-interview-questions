package com.dsa.foundation.bitwise;

public class BitwiseOperations {

    //Reference: https://leetcode.com/problems/sum-of-two-integers/
    //Time Complexity: O(y)
    //Space complexity: O(1)
    public int add(int x, int y){

        // Iterate till there is no carry
        while (y!=0){

            // carry now contains common
            // set bits of x and y
            int carry = x & y;

            // Sum of bits of x and
            // y where at least one
            // of the bits is not set
            x = x ^ y;

            // Carry is shifted by
            // one so that adding it
            // to x gives the required sum
            y = carry << 1;
        }
        return x;
    }

    //Recursive solution for adding two numbers without arithmetic operators
    public int sum(int a, int b)
    {
        int s = a ^ b;
        int carry = a & b;

        if (carry == 0) return s;
        else return sum(s, carry << 1);
    }

    public int subtract(int x, int y){

        // Iterate till there
        // is no carry
        while (y != 0)
        {
            // borrow contains common
            // set bits of y and unset
            // bits of x
            int borrow = (~x) & y;

            // Subtraction of bits of x
            // and y where at least one
            // of the bits is not set
            x = x ^ y;

            // Borrow is shifted by one
            // so that subtracting it from
            // x gives the required sum
            y = borrow << 1;
        }
        return x;
    }

    //Recursive solution for subtracting two numbers without arithmetic operators
    public int minus(int a, int b)
    {
        int m = a ^ b;
        int borrow = ~(a) & b;

        if (borrow == 0) return m;
        else return sum(m, borrow << 1);
    }

    public int multiply(int n, int m)
    {
        int ans = 0, count = 0;
        while (m > 0)
        {
            // check for set bit and left
            // shift n, count times
            if (m % 2 == 1)
                ans += n << count;

            // increment of place
            // value (count)
            count++;
            m /= 2;
        }

        return ans;
    }


    /**
     * Sets a specific bit of an int.
     *
     * @param bit the bit to set. The least significant bit is bit 0
     * @param target the integer where the bit will be set
     * @return the updated value of the target
     */
    public int setBit(int bit, int target) {
        // Create mask
        int mask = 1 << bit;
        // Set bit
        return target | mask;
    }

    /**
     * Gets the specified bit (0-31) from the integer argument.
     *
     * @param num
     *            Integer argument
     * @param p
     *            Position of the bit (0-31)
     */
    private boolean getBit(int num,int p) {

        // Create mask
        int mask = 1 << p;

        //By performing an AND of the mask against num, clear all bits other than the bit at bit p.
        //Compare the result of the AND to 0. If the result is not zero, then bit p must have a 1.
        //Otherwise, bit p is zero
        return (num & mask) != 0;
    }

    /**
     * Gets the specified bit (0-31) from the integer argument.
     *
     * @param num
     *            Integer argument
     * @param p
     *            Position of the bit (0-31)
     */
    private int clearBit(int num,int p) {

        // Create mask for the specified bit position `p`. Since clearing a bit we negate the bit
        int mask = ~(1 << p);

        //By performing an AND of the mask against num, clear the bit in num.
        return (num & mask);
    }


    public static void main(String[] args) {
        BitwiseOperations bo = new BitwiseOperations();
        System.out.println("Adding two numbers using bitwise operators: "+bo.add(5,5));
        System.out.println("Adding two numbers using bitwise operators: "+bo.sum(5,5));
        System.out.println("Subtracting two numbers using bitwise operators: "+bo.subtract(5,5));
        System.out.println("Subtracting two numbers using bitwise operators: "+bo.minus(5,5));
        int bit = 7;
        int target = 128;
        System.out.println("Clear bit: "+bit+ " in target "+target+ " produces value: "+bo.clearBit(target,bit));
    }
}
