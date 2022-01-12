package com.dsa.foundation.strings;

//Question Reference: https://leetcode.com/problems/valid-palindrome/

public class ValidPalindrome {

    public boolean alphaNumeric(char c){
        return (c >= 'a' && c <= 'z') || (c >= '0' && c <= '9');
    }

    //Description: Run two indexes from start and end, which checks that the char at start index and end
    //index are alphanumeric characters. The case of string is changed to lower case as case insensitive
    //requirement. If character is not alphanumeric skip to the next character to be processed otherwise
    //check character at respective indexes match. If not a match return false otherwise continue processing
    //incrementing and decrementing respective indexes. If we reach end of while loop then string is a
    //palindrome as characters match.
    //Time Complexity: O(n) as each character is only visited once.
    //Space Complexity: O(1)
    public boolean isPalindrome(String s) {

        if (s == null || s.length() == 0){
            throw new IllegalArgumentException("Null or empty string passed");
        }

        if (s.length() == 1){
            return true;
        }

        s = s.toLowerCase();
        int startIndex = 0;
        int endIndex = s.length()-1;

        while (startIndex <= endIndex){

            if (startIndex <= endIndex && !alphaNumeric(s.charAt(startIndex))){
                startIndex++;
                continue;
            }

            if (startIndex <= endIndex && !alphaNumeric(s.charAt(endIndex))){
                endIndex--;
                continue;
            }

            if (s.charAt(startIndex) != s.charAt(endIndex)){
                return false;
            }

            startIndex++;
            endIndex--;
        }
        return true;
    }

    //Description: Create a new StringBuffer where all non-alphanumeric characters are removed and
    //case of source string is lowered as its a case insensitive check for the string to be a valid
    //palindrome. After creating the StringBuffer with only alphanumerics check string is a valid
    //palindrome by comparing startIndex (0) and endIndex (length -1) and if not a match return false
    //otherwise continue processing incrementing startIndex and decrementing endIndex. If we reach end
    //of while loop then string is a valid palindrome.
    //Time Complexity: O(n) to create alphanumeric Stringbuffer and add O(m) for char/digit comparison.
    // Add O(n/2) for the while loop for each char. Overall its O(n)
    //Space Complexity: O(n) - Appending a character each time to the StringBuffer
    public boolean isPalindrome2(String s) {

        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException("Null or empty string passed");
        }

        if (s.length() == 1) {
            return true;
        }

        s = s.toLowerCase();

        StringBuffer sb = new StringBuffer();

        for (int i=0; i < s.length(); i++){
            char c = s.charAt(i);
            if (alphaNumeric(c)){
                sb.append(c);
            }
        }

        int startIndex = 0;
        int endIndex= sb.length() - 1;

        while (startIndex <= endIndex) {
            if (!(sb.charAt(startIndex) == sb.charAt(endIndex))){
                return false;
            }
            startIndex++;
            endIndex--;
        }

        return true;
    }

    public boolean isPalindrome3(String s) {

        if (s == null || s.length() == 0) {
            throw new IllegalArgumentException("Null or empty string passed");
        }

        if (s.length() == 1) {
            return true;
        }

        s = s.toLowerCase();
        int startInd = 0;
        int endInd = s.length() -1;

        while (startInd <= endInd){

            if (!alphaNumeric(s.charAt(startInd))){
                startInd++;
                continue;
            }

            if (!alphaNumeric(s.charAt(endInd))){
                endInd--;
                continue;
            }

            if ((s.charAt(startInd)) != s.charAt(endInd)){
                return false;
            }
            startInd++;
            endInd--;
        }
        return true;
    }
    public static void main(String[] args) {
        ValidPalindrome vp = new ValidPalindrome();
        String str = "A man, a plan, a canal: Panama";
        String sss = "A man, a plan, a canal: Panama";
        System.out.println("Source string: "+str);
        System.out.println("String is a valid palindrome: "+vp.isPalindrome(str));
        System.out.println("String is a valid palindrome: "+vp.isPalindrome2(str));
        System.out.println("String is a valid palindrome: "+vp.isPalindrome3(str));
    }
}
