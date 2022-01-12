package com.dsa.medium.strings;

//Reference: http://learning.coreref.com/www.programiz.com/java-programming/examples/check-valid-shuffle-of-strings.html

//Note: variant of shuffle string where order of s1 and s2 don't have to be maintained
//https://www.programiz.com/java-programming/examples/check-valid-shuffle-of-strings

public class ShuffleString {

    public boolean isValidShuffleString(String s1,String s2,String result){

        // check length of result is same as
        // sum of result of first and second
        if (s1.length() + s2.length() != result.length()){
            return false;
        }

        // variables to track each character of 3 strings
        int i = 0,j = 0,k = 0;

        // iterate through all characters of result
        while (k < result.length()){
            // check if first character of result matches with first character of first string
            if (i < s1.length() && result.charAt(k) == s1.charAt(i)){
                i++;
            }
            // check if first character of result matches with first character of second string
            else if (j < s2.length() && result.charAt(k) == s2.charAt(j)){
                j++;
            }
            else{ //No matching character
                return false;
            }
            k++;
        }

        // after accessing all characters of result
        // if either first or second has some characters left
        if(i < s1.length() || j < s2.length()) {
            return false;
        }
        return true;
    }


    public static void main(String[] args) {
        ShuffleString ss = new ShuffleString();
        String s1 = "abc";
        String s2 = "def";
        String[] results = {"defabc","deabcf","adecbf"};

        for (String result: results){
            if (ss.isValidShuffleString(s1,s2,result)){
                System.out.println(result+" is a valid shuffle string of "+s1+" and "+s2);
            }
        }

        String s3 = "XY";
        String s4 = "12";
        String[] results2 = {"1XY2", "Y12X"};

        for (String result: results2){
            if (ss.isValidShuffleString(s3,s4,result)){
                System.out.println(result+" is a valid shuffle string of "+s3+" and "+s4);
            }
        }
    }
}
