package com.dsa.foundation.hashmaps;

//Question: Remove duplicate characters in a string and return the string in alphabetical order

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class RemoveDuplicateLetters {

    //Use HashSet to only record the distinct characters in a string. Sort the resultant string using the char array
    //sort method.
    //Time Complexity: O(nlogn) + O(n) = keep the dominant term O(nlogn) where n is the number of chars in string
    //Space Complexity: O(n) for the HashSet
    public String removeDuplicateLetters(String s) {

        if (s == null || s.length() == 0 || s.length() == 1){
            return s;
        }

        Set<Character> hSet = new HashSet<>();
        StringBuilder sb = new StringBuilder();

        for (int i=0; i < s.length(); i++){
            char ch = s.charAt(i);

            if (!hSet.contains(ch)){
                hSet.add(ch);
                sb.append(ch);
            }
        }

        char[] charArray = sb.toString().toCharArray();
        Arrays.sort(charArray);

        return new String(charArray);
    }

    public static void main(String[] args) {
     RemoveDuplicateLetters rdl = new RemoveDuplicateLetters();
     String s = "bcabc";

     System.out.println("String "+s+" with duplicate letters removed and in lexicographical order: "+rdl.removeDuplicateLetters(s));

     s = "cbacdcbc";
     System.out.println("String "+s+" with duplicate letters removed and in lexicographical order: "+rdl.removeDuplicateLetters(s));
    }
}
