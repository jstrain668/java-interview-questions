package com.dsa.foundation.strings;

import java.util.HashMap;
import java.util.Map;

//Reference: https://www.geeksforgeeks.org/return-maximum-occurring-character-in-the-input-string/

public class HighestCharOccurrence {

    static final int ASCII_SIZE = 128;

    //Time Complexity: O(n) where n is the length of string
    //Space Complexity: O(n) for HashMap
    public char highestOccurringChar(String s){
        if (s == null || s.isEmpty()){
            return ' ';
        }

        if (s.length() == 1){
            return s.charAt(0);
        }

        Map<Character,Integer> map = new HashMap<>();
        int maxCharCount = 1;
        char maxChar = ' ';

        for (char ch: s.toCharArray()){
            if (map.containsKey(ch)){
                int count = map.get(ch);
                count++;
                map.put(ch,count);
                if (count > maxCharCount){
                    maxCharCount = count;
                    maxChar = ch;
                }
            } else{
                map.put(ch,1);
            }
        }
        return maxChar;
    }

    //Time Complexity: O(n)
    //Space Complexity: O(128) becomes O(1)
    public char highestOccurringChar2(String s){
        if (s == null || s.isEmpty()){
            return ' ';
        }

        if (s.length() == 1){
            return s.charAt(0);
        }

        int[] count = new int[ASCII_SIZE];
        int maxCharCount = -1;
        char maxChar = ' ';

        // Construct character count array from the input
        // string.
        for (int i=0; i < s.length();i++){
            count[s.charAt(i)]++;
        }

        for (int i = 0; i < s.length(); i++) {
            if (maxCharCount < count[s.charAt(i)]) {
                maxCharCount = count[s.charAt(i)];
                maxChar = s.charAt(i);
            }
        }


        return maxChar;
    }

    public static void main(String[] args) {
        HighestCharOccurrence hco = new HighestCharOccurrence();
        String s1 = "aaaaaaaaaaaaaaaaabbbbcddddeeeeee";
        System.out.println("Highest occurring character in "+s1+" is "+hco.highestOccurringChar(s1));
        System.out.println("Highest occurring character in "+s1+" is "+hco.highestOccurringChar2(s1));
    }
}
