package com.dsa.foundation.strings;

//Question
/*
Given a string, you need to reverse the order of characters
in each word within a sentence while still preserving whitespace and initial word order.
Example 1:
Input: "Let's take LeetCode contest"
Output: "s'teL ekat edoCteeL tsetnoc"
Note: In the string, each word is separated by single space and there will not be any extra space in the string.
*/

//Reference: https://github.com/awangdev/LintCode/blob/master/Java/Reverse%20Words%20in%20a%20String%20II.java

public class ReverseWordsInStringTwo {

    //Solution: Traverse the string character by character, where start and end character are tracked for each word in
    //the string.Once the white space char is encountered pass the subarray from start to current char -1 to reverse
    //method. Each character is swapped with its reverse position. When the last char in the array is reached, the last
    //word to be reversed is the start index and last char index
    //Time Complexity: O(n) for processing each character in the string and O(w) for each word in the string and O(c)
    //for each character in the word.
    //Space Complexity: O(1)
    public String reverseWords(String s) {

        if (s == null || s.length() <= 1) {
            return s;
        }

        char[] str = s.toCharArray();
        int start = 0;
        for (int i = 0; i < str.length; i++) {
            if (str[i] == ' ') {
                reverse(str, start, i - 1);
                start = i + 1;
            } else if (i == str.length - 1) {
                reverse(str, start, i);
            }
        }//end for

        return String.valueOf(str);
    }

    public void reverse(char[] s, int start, int end) {
        while (start < end) {
            char temp = s[start];
            s[start] = s[end];
            s[end] = temp;
            start++;
            end--;
        }
    }


    public static void main(String[] args) {

        ReverseWordsInStringTwo rw = new ReverseWordsInStringTwo();
        String s = "Let's take LeetCode contest";

        System.out.println(s);
        System.out.println(rw.reverseWords(s));

    }
}
