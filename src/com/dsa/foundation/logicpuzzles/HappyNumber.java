package com.dsa.foundation.logicpuzzles;

import java.util.HashSet;


public class HappyNumber {

    //To sum the squares of each digit in a number. Each digit in the number is attained by dividing by 10
    // using the modulus operator to get the remainder. The remainder is squared. The next digit is attained by
    // dividing by 10 and the process is repeated until the division produces zero (end of digits). The squared
    // values are summed over iterations of the loop and returned upon exit of loop.
    private int numSquareSum(int n){

        int squareSum = 0;
        while (n!= 0)
        {
            squareSum += (n % 10) * (n % 10);
            n /= 10;
        }
        return squareSum;
    }

    //Description: A number is called happy if it leads to 1 after a sequence of steps wherein each
    //step number is replaced by the sum of squares of its digit that is if we start with Happy Number
    //and keep replacing it with digits square sum, we reach 1.
    //A number will not be a Happy Number when it makes a loop in its sequence that is it touches a number
    //in sequence which already been touched.
    //So to check whether a number is happy or not, we can keep a set, if the same number occurs again
    //we flag result as not happy
    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public boolean isHappy(int n){

        HashSet<Integer> set = new HashSet<>();
        while (true)
        {
            n = numSquareSum(n);
            if (n == 1)
                return true;
            if (set.contains(n))
                return false;
            set.add(n);
        }

    }

    public static void main(String[] args) {
        HappyNumber hn = new HappyNumber();
        int n1 = 89;
        System.out.println("Is "+n1+" a happy number: "+hn.isHappy(n1));
    }
}
