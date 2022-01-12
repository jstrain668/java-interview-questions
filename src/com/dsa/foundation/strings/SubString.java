package com.dsa.foundation.strings;

//Question: Given two strings s1 and s2, find if s1 is a substring of s2.

//Reference; https://www.geeksforgeeks.org/check-string-substring-another/

public class SubString {

    //Time Complexity: O(m*n) where m is length of s1 and n length of s2
    //Space Complexity: O(1)
    public boolean isSubstring(String s1, String s2){
        if (s1 == null || s1.isEmpty() || s2 == null || s2.isEmpty()){
            return false;
        }

        if (s1.length() > s2.length()){
            return false;
        }

        return s2.contains(s1);
    }

    //Single pass solution where you traverse string s2. Match each char from s1 against s2, if match increment count
    //and if count reaches length of string 1 then s1 is a substring of s2 otherwise not. When a char match is not found
    //reset the char index of s2 to 'i' minus current count, then reset count for s1 to zero.
    //Time complexity: O(n) where n is the length of s2
    //Space Complexity: O(1)
    public boolean isSubstringEff(String s1, String s2){
        if (s1 == null || s1.isEmpty() || s2 == null || s2.isEmpty()){
            return false;
        }

        if (s1.length() > s2.length()){
            return false;
        }

        int count = 0;

        for (int i=0; i < s2.length(); i++){
            if (count == s1.length()){
                return true;
            }

            if (s1.charAt(count) == s2.charAt(i)){
                count++;
            } else{
                if (count > 0){
                    i -= count;
                }
                count = 0;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        SubString ss = new SubString();

        String s1 = "for";
        String s2 = "geeksforgeeks";

        if (ss.isSubstring(s1, s2))
            System.out.println(s1+" is a substring of "+s2);
        else
            System.out.println(s1+" is not a substring of "+s2);


        String s3 = "forg";
        String s4 = "geeksfffffoorrfoorforgeeks";

        if (ss.isSubstringEff(s3, s4))
            System.out.println(s3+" is a substring of "+s4);
        else
            System.out.println(s3+" is not a substring of "+s4);
    }
}
