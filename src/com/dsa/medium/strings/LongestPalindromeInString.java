package com.dsa.medium.strings;

//https://www.journaldev.com/530/longest-palindrome-substring-string-java
//https://leetcode.com/problems/longest-palindromic-substring/

public class LongestPalindromeInString {

    public static void main(String[] args) {

        LongestPalindromeInString lpis = new LongestPalindromeInString();
        System.out.println(lpis.longestPalindromeString("1234"));
        System.out.println(lpis.longestPalindromeString("12321"));
        System.out.println(lpis.longestPalindromeString("9912321456"));
        System.out.println(lpis.longestPalindromeString("9912333321456"));
        System.out.println(lpis.longestPalindromeString("12145445499"));
        System.out.println(lpis.longestPalindromeString("1223213"));
        System.out.println(lpis.longestPalindromeString("abb"));
    }


    public String intermediatePalindrome(String s,int left,int right){

        if (left > right){
            return null;
        }

        while (left >=0 && right < s.length() && s.charAt(left) == s.charAt(right)){
            left--;
            right++;
        }

        return s.substring(left+1,right);
    }

    // O(n^2)
    public String longestPalindromeString(String s) {

        if (s == null || s.isEmpty()){
            return s;
        }

        String longest = s.substring(0,1);
        for (int i=0; i < s.length()-1; i++){

            //Handle odd number of characters in 's'
            String palindrome = intermediatePalindrome(s,i,i);
            if (palindrome.length() > longest.length()){
                longest = palindrome;
            }

            //Handle even number of characters in 's'
            palindrome = intermediatePalindrome(s,i,i+1);
            if (palindrome.length() > longest.length()){
                longest = palindrome;
            }
        }
        return longest;
    }
}
