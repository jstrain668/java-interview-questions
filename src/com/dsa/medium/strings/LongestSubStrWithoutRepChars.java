package com.dsa.medium.strings;

import java.util.HashMap;

//Reference: https://leetcode.com/problems/longest-substring-without-repeating-characters/


public class LongestSubStrWithoutRepChars {


    //Description: The idea is to use window sliding. Whenever we see repetition, we remove the previous occurrence and
    // slide the window.
    // Time Complexity: O(n power of 2)
    // Space Complexity: O(1)
    public int lengthOfLongestSubstring(String s) {

        if (s == null || s.length() == 0){
            return 0;
        }

        // Result
        int maxSubStrLen = 0;
        int startInd = 0;
        int endInd = 0;

        for(int i = 0; i < s.length(); i++)
        {

            // Note : Default values in visited are false
            boolean[] visited = new boolean[256];

            for(int j = i; j < s.length(); j++)
            {

                // If current character is visited
                // Break the loop
                if (visited[s.charAt(j)] == true)
                    break;

                    // Else update the result if
                    // this window is larger, and mark
                    // current character as visited.
                else
                {
                    if ((j-i + 1) > maxSubStrLen){
                        maxSubStrLen = j-i + 1;
                        endInd = j;
                        startInd = i;
                    }

                    visited[s.charAt(j)] = true;
                }
            }

            // Remove the first character of previous window
            visited[s.charAt(i)] = false;
        }
        System.out.println("Longest substring without repeating characters: "+s.substring(startInd,endInd+1));
        return maxSubStrLen;
    }

    //Description: This solution uses extra space to store the last indexes of already visited characters. The idea is
    // to scan the string from left to right, keep track of the maximum length Non-Repeating Character Substring seen so
    // far. When we traverse the string, to know the length of current window we need two indexes.
    //Time Complexity: O(n + d) where n is length of the input string and d is number of characters in input string
    // alphabet. For example, if string consists of lowercase English characters then value of d is 26.
    //Auxiliary Space: O(d)
    public int lengthOfLongestSubstringUsingHashmap(String s) {

        if (s == null || s.length() == 0) {
            return 0;
        }

        // Creating a set to store the last positions of occurrence
        HashMap<Character, Integer> seen = new HashMap<>();
        int maximum_length = 0;
        int startInd = 0;
        int endInd = 0;

        // starting the initial point of window to index 0
        int start = 0;

        for(int end = 0; end < s.length(); end++)
        {
            // Checking if we have already seen the element or not
            if(seen.containsKey(s.charAt(end)))
            {
                // If we have seen the number, move the start pointer
                // to position after the last occurrence
                start = Math.max(start, seen.get(s.charAt(end)) + 1);
            }

            // Updating the last seen value of the character
            seen.put(s.charAt(end), end);

            if (end-start + 1 > maximum_length) {
                maximum_length = end-start + 1;
                startInd = start;
                endInd = end;
            }
        }
        System.out.println("Longest substring without repeating characters: "+s.substring(startInd,endInd+1));
        return maximum_length;
    }

    public static void main(String[] args) {
        LongestSubStrWithoutRepChars lsswrc = new LongestSubStrWithoutRepChars();
        String s = "abcabcbb";
        String b = "bbbbb";
        String p = "pwwkew";
        System.out.println("String : "+p);
        System.out.println("Longest sub string length without repeating characters = "+lsswrc.lengthOfLongestSubstringUsingHashmap(p));

    }
}
