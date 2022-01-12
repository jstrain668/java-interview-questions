package com.dsa.medium.strings;

//Question: Given a string, find the length of the longest possible substring in it that has exactly K distinct
// characters. If there is no possible substring then print -1.

//Reference: https://www.callicoder.com/longest-substring-with-k-distinct-characters/

import java.util.HashMap;
import java.util.Map;

public class LongestSubKDistinct {


    //Problem is based on the variable-size sliding window pattern. We have already solved a similar problem Smallest
    // Subarray with a given Sum using this pattern. We will use the same strategy to solve this problem as well.
    //
    //Consider each substring as a sliding window.
    //Start with a sliding window of size 1 (windowStart=0, windowEnd=0).
    //Initialize a HashMap that stores the character count for each character in the current window.
    //Iterate over the string and insert new characters in the HashMap until we have exactly K distinct characters in
    //the HashMap.
    //At this point, we have a window (subarray) that conforms to our criteria of having exactly K unique characters.
    //So remember the length of this window as the longest window so far.
    //However, if the count of distinct characters in the HashMap is greater than K, then we will start shrinking the
    //window from the left until the count becomes smaller than or equal to K. To shrink the window, we will need to
    //discard the leftmost element of the window from the HashMap.


    public int findLengthOfLongestSubstringWithKUniqueCharacters(String s, int k) {
        int n = s.length();

        int maxLen = -1; // Stores the length of the longest substring with k unique characters found so far.
        Map<Character, Integer> windowCharCount = new HashMap<>(); // Stores the character count for each character in the current window
        int windowStart = 0;

        for(int windowEnd = 0; windowEnd < n; windowEnd++) {
            // Add the next character to the sliding window
            char c = s.charAt(windowEnd);
            windowCharCount.put(c, windowCharCount.getOrDefault(c, 0) + 1);

            // Shrink the sliding window, until we have exactly 'k' distinct characters in the window
            while(windowCharCount.size() > k) {
                char leftChar = s.charAt(windowStart);

                // Discard the character at windowStart since we're gonna move it out of the window now.
                windowCharCount.put(leftChar, windowCharCount.get(leftChar) - 1);
                if(windowCharCount.get(leftChar) == 0) {
                    windowCharCount.remove(leftChar);
                }

                windowStart++; // Shrink the window
            }

            if(windowCharCount.size() == k) {
                // Update maximum length found so far
                maxLen = Math.max(maxLen, windowEnd-windowStart+1);
            }
        }

        return maxLen;
    }


    public static void main(String[] args) {

        LongestSubKDistinct lskd = new LongestSubKDistinct();
        String s = "aabacbebebe";
        int k = 3;
        System.out.println("Longest substring with "+k+" unique characters = "+lskd.findLengthOfLongestSubstringWithKUniqueCharacters(s, k));

    }
}
