package com.dsa.foundation.logicpuzzles;

//Reference: https://leetcode.com/problems/power-of-three/

public class PowerOfThree {

    private final static double EPSILON = 0.00001;
    //Description: Solve by dividing the number n by 3 until the remainder of the division is non-zero
    //If the final remainder is 1 then the number n is a power of 3 otherwise false
    //Time Complexity: O(log n) where the base is 3
    //Space Complexity: O(1)
    public boolean isPowerOfThree(int n){
        if (n<1){
            return false;
        }

        while (n%3 == 0){
            n /= 3;
        }

        return n==1;
    }

    //n=3 to the power of i
    //i=log (base 3) (n)
    //i = log (n) / log (3) where the base is 10
    //n is a power of three if and only if i is an integer. In Java, we check if a number is an integer
    //by taking the decimal part (using % 1) and checking if it is 0.
    //This solution is problematic because using doubles, which means, subject to precision errors.
    // This means, we should never use == when comparing doubles. That is because the result of
    // Math.log10(n) / Math.log10(3) could be 5.0000001 or 4.9999999. This effect can be observed by
    // using the function Math.log() instead of Math.log10().
    // In order to fix that, we need to compare the result against an epsilon
    // Time Complexity: O(1)
    // Space Complexity: O(1)

    public boolean isPowerOfThreeMath(int n){
        if (n<1){
            return false;
        }

        return (Math.log10(n) / Math.log10(3) + EPSILON) % 1 <= 2 * EPSILON;
    }

    public static void main(String[] args) {
        PowerOfThree pot = new PowerOfThree();
        int n = 9;
        System.out.println(n+" is power of three: "+pot.isPowerOfThree(n));
        System.out.println(n+" is power of three: "+pot.isPowerOfThreeMath(n));
    }
}
