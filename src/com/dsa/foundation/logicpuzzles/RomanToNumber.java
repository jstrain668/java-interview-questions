package com.dsa.foundation.logicpuzzles;

//Reference: https://leetcode.com/problems/roman-to-integer/

public class RomanToNumber {
    // This function returns
    // value of a Roman symbol
    int value(char r)
    {
        switch(r){
            case 'I':
                return 1;
            case 'V':
                return 5;
            case 'X':
                return 10;
            case 'L':
                return 50;
            case 'C':
                return 100;
            case 'D':
                return 500;
            case 'M':
                return 1000;
            default:
                return -1;
        }
    }

    // Finds decimal value of a given roman numeral
    // Convert roman numerals to integers and determine if adding or subtracting. If next symbol is greater in value then subtract
    // current value from it otherwise add.
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    int romanToDecimal(String str)
    {
        // Initialize result
        int res = 0;

        for (int i = 0; i < str.length(); i++)
        {
            // Getting value of symbol str[i]
            int s1 = value(str.charAt(i));

            // Check if there is one or more elements still to be processed before reaching end of array
            // Get next value to determine if adding the current value or subtracting it from next value
            if (i + 1 < str.length()){

                // Getting value of symbol str[i+1]
                int s2 = value(str.charAt(i+1));


                // Value of current symbol
                // is greater or equal to
                // the next symbol then add
                if (s1 >= s2){
                    res += s1;
                } // When current symbol is less than next symbol then subtract and increment for loop index
                else {
                    res += s2 - s1;
                    i++;
                }
            } else //Processing last value in string
                {
                res += s1;
            }
        }
        return res;
    }

    // Driver Code
    public static void main(String[] args)
    {
        RomanToNumber ob = new RomanToNumber();

        // Considering inputs given are valid
        String str = "IV";
        System.out.println("Integer form of Roman Numeral"
                + " is "
                + ob.romanToDecimal(str));
    }
}
