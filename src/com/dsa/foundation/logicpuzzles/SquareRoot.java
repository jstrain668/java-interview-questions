package com.dsa.foundation.logicpuzzles;

//Reference: https://leetcode.com/problems/sqrtx/

public class SquareRoot {

    //Description: The Newton Iteration is a mathematical approach that finds optimal answers for square root
    // gradually by approaching The f'(x) derivative function of x^2 is 2x. Starting at x/2 for the guess of
    // the square root of x, we can approach a better answer by r’=(r+x/r)/2 until the r’^2 is smaller or
    // equal to x. To reduce iterations introduce a precision on the doubles specifically temp - squareRoot
    // Time Complexity : Almost constant time O(1)
    // Space Complexity : O(1)
    public int mySqrt(int x) {

        if (x < 0){
            throw new IllegalArgumentException("Negative number not allowed");
        }

        if (x == 0 || x==1){
            return x;
        }

        double squareRoot = x / 2;
        double temp;
        do {
            temp = squareRoot;
            squareRoot = (temp + (x / temp)) / 2;
        } while ((temp - squareRoot) != 0);


        return (int) Math.floor(squareRoot);
    }

    // Description: To determine the range of values to search against using binary search, the end of the
    // range is x/2 as the square root of `x` cannot be more than `x/2` for `x > 1`. The start point is 1
    // as dealing with positive integers. Calculate mid point = (start + end) / 2 and
    // calculate square (use longs) to determine equal to x or not. If equal return mid otherwise
    // determine sqr < x (increase start by one and record mid as the candidate answer) else sqr > x
    // then decrease end by 1. Continue looping until left > end which returns candidate answer (not a
    // perfect square) or the perfect square is found.
    // Time Complexity: O(log n)
    // Space Complexity: O(1)

    int binSearch(int x)
    {
        // the square root of `x` cannot be more than `x/2` for `x > 1`
        int start = 1 , end = x / 2;
        int answer = 0;

        while(start <= end)
        {
            // Get the middle element
            int mid = (start + end) / 2;
            // mid * mid can be large, so use long
            long sqr = (long)mid * (long)mid;

            //The perfect square?
            if(sqr == x) {
                return mid;
            }
            // If mid * mid is less than x
            if(sqr < x)
            {
                answer = mid;
                start = mid + 1;
            } // mid * mid is greater than x
            else {
                end = mid - 1;
            }
        }
        return answer;
    }

    public int binSearchSqRoot(int x){

        if (x < 0){
            throw new IllegalArgumentException("Negative number not allowed");
        }

        if (x == 0 || x==1){
            return x;
        }

        return binSearch(x);

    }

    public int mySqrt2(int x){

        if (x < 0){
            throw new IllegalArgumentException("Negative number not allowed");
        }

        if (x == 0 || x==1){
            return x;
        }

        double precision = 0.0001;
        double squareRoot = x / 2;
        double temp;

        while (true){
            temp = squareRoot;
            squareRoot = (temp + (x/temp)) / 2;

            if (((temp - squareRoot)) < precision){
                break;
            }
        }

        return (int) Math.floor(squareRoot);
    }
    public static void main(String[] args) {
        SquareRoot sr = new SquareRoot();
        int x = 10;
        System.out.println("Square root of "+x+ " = "+sr.mySqrt2(x));
        System.out.println("Square root of "+x+ " = "+sr.mySqrt2(x));
        System.out.println("Square root of "+x+ " = "+sr.binSearchSqRoot(x));
    }
}
