package com.dsa.foundation.logicpuzzles;

//Reference: https://leetcode.com/problems/divide-two-integers/

import java.math.BigDecimal;
import java.math.RoundingMode;

import static java.lang.Math.abs;

public class DivideNumbers {

    // Function to perform division `x/y` of two numbers `x` and `y`
    // without using the division operator in the code
    //Description: Fastest  approach using shift operators. First convert int to longs to
    // avoid underflow/overflows with ints. Handle special cases, div by zero, div by 1, and
    // dividend == 0. Also handle special case where dividend = Integer.MIN and sign =1.
    // Convert x and y into absolute values dividend and divisor respectively.
    // Division is accomplished by usage of shit operators, << 1 = x2 and >> 1 = /2.
    // Two part process where a bit mask is created which covers the dividend bit pattern and the
    // divisor is multiplied by 2 until >= dividend. This is accomplished by shifting the divisor and
    // mask left by 1 until the divisor is equal or greater than the dividend.
    // In the second part the quotient is calculated by dividing the mask and divisor by 2 (shift right)
    // while the mask is great than 1. Check if the dividend >= divisor, if yes then subtract divisor
    // from dividend and the quotient is bit OR operation of the quotient and mask.
    // Time Complexity: O(log x - log y) + O(log x - log y)
    // Space Complexity: O(1)
    public int divide4(int x, int y)
    {
        // handle divisibility by 0
        if (y == 0){
            throw new IllegalArgumentException("Cannot divide by zero");
        }

        // store sign of the result
        long dividend = x;
        long divisor = y;

        long sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;

        // convert both dividend and divisor to positive
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        if (divisor == 1){
            if (sign == 1 && x == Integer.MIN_VALUE){
                return (Integer.MAX_VALUE);
            }  else if (sign == -1 && x == Integer.MIN_VALUE){
                return (Integer.MIN_VALUE);
            }
            return (int) (sign * dividend);
        }

        if (dividend == 0){
            return 0;
        }

        long mask = 1;
        long quotient = 0;

        while (divisor <= dividend)
        {
            divisor <<= 1;
            mask <<= 1;
        }

        while (mask > 1)
        {
            divisor >>= 1;
            mask >>= 1;
            if (dividend >= divisor)
            {
                dividend -= divisor;
                quotient |= mask;
            }
        }

        return (int) (sign * quotient);
    }

    //Description: This approach uses multiply operator. First convert int to longs to avoid underflow/overflows with ints
    // Handle special cases, div by zero, div by 1, and dividend == 0. Also handle special case
    // where dividend = Integer.MIN and sign =1. Convert x and y into absolute values dividend and
    // divisor respectively.
    // Divisions is accomplished in a two fold process; First part multiply the denominator (divisor)
    // by 2 until its greater than or equal to the dividend while at the same time, double the quotient
    // value for each iteration of the loop. Second part while the denominator is greater than the
    // dividend, subtract the divisor from the denominator and decrement quotient.
    // Time Complexity: O(log x) - O(log y) + O(denominator/dividend)
    // Space Complexity: O(1)
    public int divide3(int x, int y)
    {
        // handle divisibility by 0
        if (y == 0){
            throw new IllegalArgumentException("Cannot divide by zero");
        }

        // store sign of the result
        long dividend = x;
        long divisor = y;

        long sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;

        // convert both dividend and divisor to positive
        dividend = Math.abs(dividend);
        divisor = Math.abs(divisor);

        if (divisor == 1){
            if (sign == 1 && x == Integer.MIN_VALUE){
                return (Integer.MAX_VALUE);
            }  else if (sign == -1 && x == Integer.MIN_VALUE){
                return (Integer.MIN_VALUE);
            }
            return (int) (sign * dividend);
        }

        if (dividend == 0){
            return 0;
        }

        // initialize denominator by `y`
        long denominator = divisor;

        // initialize quotient by 1
        long quotient = 1;

        // Double denominator and quotient value until denominator is more than
        // dividend `x`
        while (dividend > denominator)
        {
            denominator *= 2;
            quotient *= 2;
        }

        // Subtract divisor `y` from the denominator and reduce quotient by 1 until
        // the denominator is less than dividend `x`
        while (denominator > dividend)
        {
            denominator -= divisor;
            quotient -= 1;
            // printf("%d %d\n", denominator, quotient);
        }

        return (int) (sign * quotient);
    }


    //Description: Simplest approach convert int to longs to avoid underflow/overflows with ints
    // Handle special cases, div by zero, div by 1, and dividend == 0. Also handle special case
    // where dividend = Integer.MIN and sign =1. Convert x and y into absolute values dividend and
    // divisor respectively.
    // Division is accomplished by a loop subtracting the divisor from the dividend until divisor is
    // greater or equal to dividend. The number of iterations of this loop is the quotient (result)
    // of the division. The value to return is the quotient multiplied by the sign.
    // Time Complexity: O(x/y) = O(quotient)
    // Space Complexity: O(1)
    public int divide(int x, int y){

        if (y == 0){
            throw new IllegalArgumentException("Cannot divide by zero");
        }

        long dividend = x;
        long divisor = y;

        long sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;

        // Remove signs of
        // dividend and divisor
        dividend = abs(dividend);
        divisor = abs(divisor);

        if (divisor == 1){
            if (sign == 1 && x == Integer.MIN_VALUE){
                return (Integer.MAX_VALUE);
            }  else if (sign == -1 && x == Integer.MIN_VALUE){
                return (Integer.MIN_VALUE);
            }
            return (int) (sign * dividend);
        }

        if (dividend == 0){
            return 0;
        }

        long quotient = 0;
        while (dividend >= divisor){

            dividend -= divisor;
            quotient++;
        }

        return (int) (sign*quotient);
    }

    public String truncate(String value, int places) {
        return new BigDecimal(value)
                .setScale(places, RoundingMode.DOWN)
                .stripTrailingZeros()
                .toString();
    }

    // Approach of using logarithms needs to be reviewed. Need to check about rules for rounding
    // up and down will resolve issues with this approach.
    public int divide2(int x, int y){

        if (y == 0){
            throw new IllegalArgumentException("Cannot divide by zero");
        }

        long dividend = x;
        long divisor = y;

        long sign = (dividend < 0) ^ (divisor < 0) ? -1 : 1;

        // Remove signs of
        // dividend and divisor
        dividend = abs(dividend);
        divisor = abs(divisor);

        if (divisor == 1){
            if (sign == 1 && x == Integer.MIN_VALUE){
                return (Integer.MAX_VALUE);
            }  else if (sign == -1 && x == Integer.MIN_VALUE){
                return (Integer.MIN_VALUE);
            }
            return (int) (sign * dividend);
        }

        if (dividend == 0){
            return 0;
        }

        double result = Math.floor(sign * (Math.exp(Math.log(dividend) - Math.log(divisor))));
        String value = truncate(String.valueOf(sign * (Math.exp(Math.log(dividend) - Math.log(divisor)))),1);

        int decimalIndex = value.indexOf('.');

        if (decimalIndex == -1){
            return Integer.parseInt(value);
        } else{
            value = value.substring(0,decimalIndex);
            return Integer.parseInt(value);
        }
        //return (int) result;
        //long quotient = (long) Math.floor(sign * (Math.exp(Math.log(dividend) - Math.log(divisor))));

        //return (int) (quotient);
    }

    public static void main(String[] args) {
        DivideNumbers dn = new DivideNumbers();
        int x = -2147483648;
        //int x = -56;
        //int y = 9;
        //int y = -2;
        //int x = -2147483648;
        //int y = -2;
        //int x = -231;
        //int y = 3;
        //int x = 9;
        //int y = -3;
        //int x = 2147483647;
        //int y = 2;
        int y = -2147483648;

        System.out.println("Min Integer value: "+Integer.MIN_VALUE);
        System.out.println("Max Integer value: "+Integer.MAX_VALUE);
        System.out.println("X: "+x+" divided by "+y+" = "+dn.divide4(-2147483648,-2147483648));
    }
}
