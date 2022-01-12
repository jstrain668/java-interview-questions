package com.dsa.foundation.hashmaps;

import java.util.HashSet;

//https://www.geeksforgeeks.org/find-the-first-repeated-character-in-a-string/

public class FirstRepeatedCharacter {

    // This function prints the first repeated
    // character in str[]
    public char firstRepeating(char str[])
    {
        // Creates an empty hashset
        HashSet<Character> h = new HashSet<>();

        // Traverse the input array from left to right
        for (int i=0; i<=str.length-1; i++)
        {
            char c = str[i];

            // If element is already in hash set, update x
            // and then break
            if (h.contains(c))
                return c;

            else // Else add element to hash set
                h.add(c);
        }

        return '\0';
    }

    public static void main(String[] args) {
        FirstRepeatedCharacter frc = new FirstRepeatedCharacter();

        String str = "geeksforgeeks";
        char[] arr = str.toCharArray();
        System.out.println(frc.firstRepeating(arr));
    }
}
