package com.dsa.foundation.logicpuzzles;

// Reference: https://leetcode.com/problems/reverse-integer/

public class ReverseInteger {

    public int reverse2(int n) {

        long res = 0;
        int powerIndex;

        String number = String.valueOf(n);

        if (number.charAt(0) == '-'){
            powerIndex = number.length()-2;
        } else {
            powerIndex = number.length()-1;
        }


        while (n!=0 && powerIndex>=0){
            int rem = n % 10;
            res += rem * Math.pow(10,powerIndex);
            powerIndex--;
            n /= 10;
        }

        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) res;
    }

    //Description: To reverse the integer, repeatedly divide `n` by 10 until `n` is equal to zero. Every
    //time `n` divided by 10 use the mod operator to get last digit which is added to the reversed number
    //Reversed number is multiplied by 10 to reflect each 10's digit.
    //Used long type to record the answer and then check against overflow or underflow of MAX and MIN
    //int respectively. If overflow or underflow then return 0, otherwise cast the long to an int.
    //Time Complexity: O (log n)
    //Space Complexity: O (1)
    public int reverse(int n) {

        long res = 0;
        while (n != 0) {
            res = res * 10 + n % 10;
            n /= 10;
        }

        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE) {
            return 0;
        }
        return (int) res;
    }

    //Convert the number into an absolute number and then into a string using StringBuffer. Use
    //the StringBuffer reverse method to reverse string. Convert string into a long for potential
    //overflow or underflow of integer. Check if long is > than MAX int and < MIn int, if so return
    //zero otherwise return int of long value.
    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public int reverse3(int n) {

        long res = 0;
        String reversed = new StringBuilder().append(Math.abs(n)).reverse().toString();

       // if (reversed.charAt(0) == '0'){
       //     reversed = reversed.substring(1);
       // }

        try {
            res = Long.parseLong(reversed);
        } catch (NumberFormatException nfe){
            System.out.println(nfe.toString());
        }

        if (n < 0){
            res *= -1;
        }

        if (res > Integer.MAX_VALUE || res < Integer.MIN_VALUE){
            return 0;
        }

        return (int) res;
    }
    public static void main(String[] args) {
        ReverseInteger ri = new ReverseInteger();
        int n = 120;
        System.out.println("Reverse value of "+n+" = "+ri.reverse(n));
        System.out.println("Reverse value of "+n+" = "+ri.reverse2(n));
        System.out.println("Reverse value of "+n+" = "+ri.reverse3(n));
    }


}
