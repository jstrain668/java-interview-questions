package com.dsa.foundation.logicpuzzles;

//Reference: https://leetcode.com/problems/factorial-trailing-zeroes/

public class FactorialZeros {

    public int bfTrailingZeroes(int n) {

        long result = 1;
        int multiplier = 1;

        while (multiplier <= n){
            result *= multiplier;
            multiplier++;
        }

        System.out.println(+n+" factorial is "+result);
        int count = 0;
        while (result%10 == 0){
            count++;
            result /= 10;
        }

        return count;
    }

    // You need to find how many powers of ten in a factorial, not calculate a factorial and then find
    // the number of trailing zeros. The simplest solution is to count the number of powers of five.
    // The reason you only need to count powers of five is that there is plenty of even numbers in
    // between then to make a 10. For example, 5! has one 0, 10! has 2, 15! has three, 20! has four, and 25!
    // has not five but six as 25 = 5 * 5.
    // In short you only need calculate the number of powers of five between 1 and N.
    // Time Complexity: O (log n)
    // Space Complexity: O (1)
    public int trailingZeroes(int n){

        int sum = 0;
        while (n >= 5) {
            n /= 5;
            sum += n;
        }
        return sum;
    }

    public static void main(String[] args) {
        FactorialZeros fz = new FactorialZeros();
        int n = 20;
        System.out.println("Number of trailing zeros in "+n+" factorial is: "+fz.bfTrailingZeroes(n));
        System.out.println("Number of trailing zeros in "+n+" factorial is: "+fz.trailingZeroes(n));
    }
}
