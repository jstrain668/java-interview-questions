package com.dsa.foundation.strings;


//Question: https://leetcode.com/problems/reverse-words-in-a-string/
//Reference: https://github.com/awangdev/LintCode/blob/master/Java/151.%20Reverse%20Words%20in%20a%20String.java


public class ReverseWordsInString {

    //Solution: Split the string into words using whitespace as the separator. Reverse the string by adding each
    //word from string array to the start of the string builder with single or multiple white space characters
    //separating each word. After array is processed removing trailing white space by use of trim.
    //While "\\s+" pattern matches multiple white space characters, its slower than matching on the single white
    //space character because of the multiples match. This is the slowest solution.
    //Time Complexity: Overall O(n) where O(n+k) for the split function finding all the words in the string separated by
    // one or k white space chars plus another O(n) for traversing all the words in the string array plus another O(n)
    // for StringBuilder insert function.
    // Space Complexity: O(n) for the array of strings
    public String reverseWords(String s) {

        if (s == null || s.isEmpty()){
            return "";
        }

        String[] temp = s.split("\\s+");
        StringBuilder sb = new StringBuilder();

        for (int i=0; i < temp.length; i++){
            sb.insert(0,temp[i]+ " ");
        }

        return sb.toString().trim();
    }


    // Solution: Split the string into words using single whitespace as the separator in the split function. Reverse
    // the position of each word in the string array. Now append each word into a StringBuffer ensuring word is not
    // empty string and appending white space character after each word. Remove the the last whitespace character by
    // trimming the reverse string
    // Time Complexity: O(n) where O(n) for the split function finding all the words in the string separated by white
    // space. Another O(n) for reversing the position of each word in the string array. Another O(n) for traversing all
    // the words in the string array to create reversed string via StringBuffer.
    // Space Complexity: O(n) for the array of strings
    public String reverseWords2(String s) {

        String[] strs = s.split(" ");
        reverse(strs);
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < strs.length; i++){
            if (strs[i].length() > 0) {
                sb.append(strs[i]);
                sb.append(" ");
            }
        }
        return sb.toString().trim();
    }

    private void reverse(String[] strs) {
        int i = 0, j = strs.length - 1;
        while (i < j) {
            String temp = strs[i];
            strs[i++] = strs[j];
            strs[j--] = temp;
        }
    }

    // Solution: Split the string into words using single whitespace as the separator in the split function. Process
    // the string array from end to start where each word is appended into a StringBuffer ensuring word is not
    // empty string and appending white space character after each word. Remove the the last whitespace character by
    // trimming the reverse string. Fastest solution
    // Time Complexity: O(n) where O(n) for the split function finding all the words in the string separated by white
    // space. Another O(n) for traversing all the words in the string array to create reversed string via StringBuffer.
    // Space Complexity: O(n) for the array of strings
    public String reverseWords3(String s) {

        if (s == null || s.isEmpty()) {
            return s;
        }

        String[] words = s.split(" ");
        StringBuilder sb = new StringBuilder();

        for (int i= words.length -1; i >= 0; i--) {
            if (words[i].length() != 0) {
                sb.append(words[i] + " ");
            }
        }
        return sb.toString().trim();
    }

    public static void main(String[] args) {

        ReverseWordsInString rw = new ReverseWordsInString();

        String s1 = "Welcome to geeksforgeeks";
        System.out.println(s1);
        System.out.println(rw.reverseWords(s1));
        System.out.println(rw.reverseWords2(s1));
        System.out.println(rw.reverseWords3(s1));

        String s2 = "I love Java Programming";
        System.out.println(s2);
        System.out.println(rw.reverseWords(s2));
        System.out.println(rw.reverseWords2(s2));
        System.out.println(rw.reverseWords3(s2));
    }
}
