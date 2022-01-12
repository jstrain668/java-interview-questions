package com.dsa.foundation.strings;

import java.util.Arrays;
import java.util.HashMap;
//Question: Check Permutation: Given two strings, write a method to decide if one is a permutation of the other


public class Anagram {

    //If both strings are equal (same) after sorting them then they are anagrams for each other.
    //Time Complexity: O(nlogn) where n is the length of the string
    //Space Complexity: O(1)
    public boolean isPermutation(String word1, String word2){
        if (word1 == null && word2 == null){
            return true;
        }

        if (word1.isEmpty() && word2.isEmpty()){
            return true;
        }

        if (word1.length() != word2.length()){
            return false;
        }

        return sort(word1).equals(sort(word2));
    }

    private String sort(String s) {
        char[] content = s.toCharArray();
        Arrays.sort(content);

        return new String(content);
    }

    //Reference: https://stackoverflow.com/questions/2131997/checking-if-two-strings-are-permutations-of-each-other/43954079#43954079

    //Linear Time solution in HashMap. Traverse and put first String in HashMap, keep the count of each character.
    //Traverse second String and if it is already in the hashmap decrease the count by 1. At the end if all character
    //were in the string the value in hashmap will be 0 for each character.
    //Time Complexity: O(w1 + w2) + O(k) where w1 is the length of first string and w2 is the length of second string. k
    //is the size of the hashmap. If an anagram then w1 = w2 = k
    //Space Complexity = O(w1)
    public boolean isPermutation2(String word1,String word2)
    {

        if (word1 == null && word2 == null){
            return false;
        }

        if (word1.isEmpty() && word2.isEmpty()){
            return true;
        }

        if (word1.length() != word2.length()){
            return false;
        }

        HashMap<Character, Integer> map = new HashMap<Character, Integer>();

        //Add each character and frequency count
        for(Character c: word1.toCharArray())
        {
            if (map.containsKey(c)){
                map.put(c,map.get(c)+1);
            } else{
                map.put(c,1);
            }
        }

        //Check each character in second word for a match in first word. For each match found reduce freq count and if
        //no match then return false.
        for(Character c: word2.toCharArray())
        {
            if (map.containsKey(c)){
                map.put(c,map.get(c)-1);
            } else{
                return false;
            }
        }

        //If freq count for each character not zero then not a permutation
        for(Character c: map.keySet())
        {
            if(map.get(c)!=0)
                return false;
        }
        return true;

    }


    public static void main(String[] args) {
        Anagram am = new Anagram();

        String[][] pairs = {{"apple", "papel"}, {"carrot", "tarroc"}, {"llloh", "hello"}};
        for (String[] pair : pairs) {
            String word1 = pair[0];
            String word2 = pair[1];
            boolean anagram = am.isPermutation(word1, word2);
            System.out.println(word1 + ", " + word2 + ": " + anagram);
            boolean isPerm = am.isPermutation2(word1,word2);
            System.out.println(word1 + ", " + word2 + ": " + isPerm);
        }
    }
}
