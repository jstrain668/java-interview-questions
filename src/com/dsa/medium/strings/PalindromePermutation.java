package com.dsa.medium.strings;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
        *
        * Problem: Palindrome Permutation: Given a string, write a function to check if
        * it is a permutation of a palindrome. A palindrome is a word or phrase that is
        * the same forwards and backwards. A permutation is a rearrangement of letters.
        * The palindrome does not need to be limited to just dictionary words.
        *
        * EXAMPLE Input: tact coa Output: True (permutations: "taco cat'; "atco eta·;
        * etc.)
        *
        */

public class PalindromePermutation {


    //Solution: Since we know some basic qualities of a palindrome string, we can count characters to determine if the
    // string is a permutation of a palindrome. Create an array from string and loop through the array, either adding
    // the character to the hash table as a key or incrementing the count (value) if it already is in the table.
    // Characters = key and count = value. Iterate through the hash table to make sure character counts are all even or
    // at most one value is odd.
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public boolean isPalindromePermutation(String input){

        if (input == null){
            throw new IllegalArgumentException("Null parameter not allowed");
        }

        if (input.isEmpty()){
            return true;
        }

        input = input.toLowerCase();
        Map<Character,Integer> charMap = new HashMap<>();

        for (Character ch: input.toCharArray()){

            if (ch == ' '){
                continue;
            }

            if (charMap.containsKey(ch)){
                charMap.put(ch,charMap.get(ch)+1);
            } else {
                charMap.put(ch,1);
            }
        }

        int oddCount = 0;

        for (Map.Entry<Character,Integer> entry: charMap.entrySet()){

            if (entry.getValue() % 2 == 0){
                continue;
            } else {
                oddCount++;
                if (oddCount > 1){
                    return false;
                }
            }
        }

        return true;
    }

    //For a palindrome since there can only be one odd character in the string then if you remove all even counts of
    //characters in the HashSet then the size of the hashset can only be zero or one to be a valid palindrome and
    //permutation
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public boolean isPalindromePermutation2(String input) {

        if (input == null) {
            throw new IllegalArgumentException("Null parameter not allowed");
        }

        if (input.isEmpty()) {
            return true;
        }

        input = input.toLowerCase();
        Set<Character> charSet = new HashSet<>();

        for(Character ch: input.toCharArray()){
            if (ch == ' '){
                continue;
            }

            if (charSet.contains(ch)){
                charSet.remove(ch);
            } else {
                charSet.add(ch);
            }
        }

        return charSet.size() <= 1;
    }

    public static void main(String[] args) {
        PalindromePermutation pp = new PalindromePermutation();
        String palindromePerm = "Tact Coa";
        System.out.println(palindromePerm+" is a palindrome permutation: "+pp.isPalindromePermutation(palindromePerm));
        String palindromePerm2 = "Rats live on no evil star";
        System.out.println(palindromePerm2+" is a palindrome permutation: "+pp.isPalindromePermutation(palindromePerm2));
        String palindromePerm3 = "Tact Coa";
        System.out.println(palindromePerm3+" is a palindrome permutation: "+pp.isPalindromePermutation2(palindromePerm3));
        String palindromePerm4 = "Rats live on no evil star";
        System.out.println(palindromePerm4+" is a palindrome permutation: "+pp.isPalindromePermutation2(palindromePerm4));
    }
}
