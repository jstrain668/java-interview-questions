package com.dsa.foundation.strings;

import java.util.*;

public class UniqueString {

    //Use the add method of Set data structure to determine uniqueness. True its unique false its not.
    //Time Complexity: O(n) where n is the length of the string
    //Space Complexity: O(n) for the has the HashSet
    public boolean isUnique(String s){

        if (s == null){
            throw new IllegalArgumentException("Cannot pass null parameter");
        }

        if ( s.isEmpty() || s.length() == 1){
            return true;
        }

        Set<Character> set = new HashSet<>();

        for(Character ch: s.toCharArray()){
            if(!set.add(ch)){
                return false;
            }
        }

        return true;
    }

    //Solution not using additional data structure. Change the string into char array and sort it. Traverse array to
    //check if before and current char are the same, if yes then false otherwise true if no match found.
    //Time Complexity: O(nlogn) where n is the length of the string
    //Space Complexity: O(1)
    public boolean isUniqueString(String s){

        if (s == null){
            throw new IllegalArgumentException("Cannot pass null parameter");
        }

        if ( s.isEmpty() || s.length() == 1){
            return true;
        }

        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);

        for (int i=1; i < charArray.length; i++){
            if (charArray[i-1] == charArray[i]){
                return false;
            }
        }
        return true;
    }

    /* Assumes only letters a through z. */
    public boolean isUniqueChars(String str) {

        if (str == null){
            throw new IllegalArgumentException("Cannot pass null parameter");
        }

        if (str.isEmpty() || str.length() == 1){
            return true;
        }

        if (str.length() > 26) { // Only 26 characters
            return false;
        }

        int checker = 0;

        for (int i = 0; i < str.length(); i++) {
            int val = str.charAt(i) - 'a';
            if ((checker & (1 << val)) > 0){
                return false;
            }
            checker |= (1 << val);
        }
        return true;
    }

    //Solution: We are certain that we don't traverse more than 128 characters in string as we would return false, once
    // we see it repeating. Worst case O(128)
    //Time Complexity: O(1) as O(128) is a constant
    //Space Complexity: O(128) for the boolean array but becomes O(1) as you drop constant
    public boolean uniqueChars(String str){
		/*
		We assume that characters are ASCII so we have max of 128 different-types
		*/

        if (str == null){
            throw new IllegalArgumentException("Cannot pass null parameter");
        }

        if (str.isEmpty() || str.length() == 1){
            return true;
        }

        if(str.length() > 128) {
            return false;
        }

        boolean[] charMap = new boolean[128];
        for(char ch: str.toCharArray())
        {
            if(charMap[ch]){
                return false;
            }
            charMap[ch] = true;
        }

        return true;
    }


    public static void main(String[] args) {
        UniqueString us = new UniqueString();
        String[] strings = {"abcde", "abdidf","zxy","a","bbcc",""};

        for (String s: strings){
            System.out.println(s+" is unique "+us.isUnique(s));
        }

        for (String s: strings){
            System.out.println(s+" is unique using sort "+us.isUniqueString(s));
            System.out.println(s+" is unique using 128 char check "+us.uniqueChars(s));
        }

    }
}
