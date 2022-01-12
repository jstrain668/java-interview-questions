package com.dsa.foundation.strings;

//Reference: https://www.studytonight.com/java-programs/java-program-to-check-palindrome-string-using-recursion

public class Palindrome {

    public boolean isPalindrome(String s){

        if (s == null || s.isEmpty() || s.length() == 1){
            return true;
        }

        s = s.toLowerCase();
        char[] chars = s.toCharArray();

        for(int i=0; i < chars.length/2; i++){
            int endIndex = chars.length - i -1;
            if (chars[i] != chars[endIndex]){
                return false;
            }
        }
        return true;
    }

    public boolean checkPalindrome(String s,int start, int end){

        if (s.charAt(start) != s.charAt(end)){
            return false;
        }

        if (start < end){
            return checkPalindrome(s,start+1,end-1);
        }
        return true;
    }

    public boolean checkPalindrome(String s){

        if (s == null || s.isEmpty() || s.length() == 1){
            return true;
        }

        s = s.toLowerCase();

        return checkPalindrome(s,0,s.length()-1);
    }

    public static void main(String[] args) {
        Palindrome p = new Palindrome();
        String s1 = "deified";
        String s2 = "madam";
        String s3 = "abba";

        System.out.println(s1+" is a palindrome "+p.isPalindrome(s1));
        System.out.println(s2+" is a palindrome "+p.isPalindrome(s2));
        System.out.println(s3+" is a palindrome "+p.isPalindrome(s3));

        System.out.println(s1+" is a palindrome "+p.checkPalindrome(s1));
        System.out.println(s2+" is a palindrome "+p.checkPalindrome(s2));
        System.out.println(s3+" is a palindrome "+p.checkPalindrome(s3));
    }
}
