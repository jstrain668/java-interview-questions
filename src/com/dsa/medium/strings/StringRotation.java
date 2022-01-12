package com.dsa.medium.strings;

//Question; String Rotation:Assume you have a method isSubstring which checks if one word is a substring
//of another. Given two strings, s1 and s2, write code to check if s2 is a rotation of s1 using only one
//call to isSubstring (e.g., "waterbottle" is a rotation of"erbottlewat").

//Reference: https://www.geeksforgeeks.org/check-for-string-rotation-in-java/

public class StringRotation {

    private boolean isSubstring(String big, String small) {
        return big.contains(small);
    }


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

    public boolean isRotation(String s1, String s2) {
        if (s1 == null || s2 == null || s1.length() != s2.length() || s1.length() == 0) {
            return false;
        }
        return isSubstring(s1+s1,s2);
        //return isSubstringEff(s2, s1 + s1);
    }


    public static void main(String[] args) {
        StringRotation sr = new StringRotation();
        String[][] pairs = {{"apple", "pleap"}, {"waterbottle", "erbottlewat"}, {"camera", "macera"}};
        for (String[] pair : pairs) {
            String word1 = pair[0];
            String word2 = pair[1];
            boolean is_rotation = sr.isRotation(word1, word2);
            System.out.println(word1 + ", " + word2 + ": " + is_rotation);
        }
    }
}
