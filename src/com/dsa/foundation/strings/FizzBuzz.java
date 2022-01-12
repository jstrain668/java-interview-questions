package com.dsa.foundation.strings;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//Reference: https://leetcode.com/problems/fizz-buzz/

public class FizzBuzz {

    // Description: Loop from 1 to n, diving each value to find number is divisible by 3 and 5, 3 only, 5 only and not satisfying
    // any of the above conditions. Add the string for each case to ArrayList. When adding the index starts from zero, thus the decrement of 1.
    // Time Complexity: O(n)
    // Space complexity of O(n)
    public List<String> fizzBuzz(int n){

        if (n <=0)
            return Collections.emptyList();

        List<String> strings = new ArrayList<>();

        for (int i = 1; i <= n; i++){

            if (i % 3 == 0 && i % 5 == 0)
                strings.add(i-1,"FizzBuzz");
            else if (i % 3 == 0)
                strings.add(i-1, "Fizz");
            else if (i % 5 == 0)
                strings.add(i-1,"Buzz");
            else
                strings.add(i-1,String.valueOf(i));
        }

        return strings;
    }

    public static void main(String[] args) {
        FizzBuzz fb = new FizzBuzz();
        int n = 15;

        System.out.println("Integer number "+n);
        List<String> strings = fb.fizzBuzz(n);
        for (String str: strings){
            System.out.println(str);
        }
    }
}
