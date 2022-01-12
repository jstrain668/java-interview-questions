package com.dsa.foundation.strings;

//Reference: https://leetcode.com/problems/implement-strstr/

public class StrStr {

    //Description: Use String indexOf method to find first occurrence of need in haystack.
    //Time complexity: O(n)
    //Space Complexity: O(1)
    public int strStrIndexOf(String haystack, String needle) {

        if(needle.length()==0) {
            return 0;
        }

        return haystack.indexOf(needle);
    }

    //Description: Starting at index 0 of the haystack string, create a substring equal in length to
    //the string to be matched (needle) and check for equality. If not matched index is incremented
    // creating a new substring and tested for matching against the needle string. Process is repeated
    // until match found or index exceeds the length of haystack string minus length of needle.
    // The index of the haystack where the match was found is returned.
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public int strStr(String haystack, String needle) {

        if (haystack == null || needle == null || haystack.length() == 0){
            return -1;
        }

        if (needle.isEmpty()){
            return 0;
        }

        int hsLen = haystack.length();
        int needleLen = needle.length();

        if (needleLen > hsLen){
            return -1;
        }

        int threshold = hsLen - needleLen;

        for (int i=0; i <= threshold; i++){
            if (haystack.substring(i,i+needleLen).equals(needle)){
                return i;
            }
        }

        return -1;
    }

    public static void main(String[] args) {
        StrStr ss = new StrStr();
        String input = "hello";
        String needle = "ll";
        System.out.println("The string: "+needle+" is found at index: "+ss.strStrIndexOf(input,needle)+" in string: "+input);
        System.out.println("The string: "+needle+" is found at index: "+ss.strStr(input,needle)+" in string: "+input);
    }
}
