package com.dsa.medium.strings;

//Reference: https://www.techiedelight.com/determine-string-transformed-into-another-string-single-edit/

public class OneEditAway {

    //Solution: The standard solution is to find the Levenshtein Distance (Edit Distance) between the given strings. If
    // the edit distance is 1, transform the first string into the second string with a single edit operation.
    // Time Complexity: O(m +n) where m length of first string and n length of second string
    //Space Complexity: O(n)

    public boolean isOneEditAway(String s1,String s2){

        // base case
        if (s1 == null || s2 == null) {
            return false;
        }

        // store length of both strings
        int m = s1.length();
        int n = s2.length();

        // difference between the length of both strings is more than one
        if (Math.abs(m - n) > 1) {
            return false;
        }

        // to keep track of the total number of edits
        int edits = 0;

        // `i` and `j` keep track of the index of current characters in the first and
        // second strings, respectively
        int i = 0, j = 0;

        // loop till either string runs out
        while (i < m && j < n)
        {
            // if the current character of both strings doesn't match
            if (s1.charAt(i) != s2.charAt(j))
            {
                // when the length of the first string is more than the length
                // of the second string, remove the current character at
                // index `i` in the first string

                if (m > n) {
                    i++;
                }

                // when the length of the first string is less than the length
                // of the second string, add the current character at index `j`
                // in the second string to the first string

                else if (m < n) {
                    j++;
                }

                // when the length of both strings is the same, replace the character
                // present at index `i` in the first string with the character present
                // at index `j` in the second string.

                else {
                    i++; j++;
                }

                // increment the number of edits
                edits++;
            }

            // if the current character of both strings matches
            else {
                i++; j++;
            }
        }

        // remove any extra characters left in the first string
        if (i < m) {
            edits++;
        }

        // add any extra characters left in the second string at the end of
        // the first string
        if (j < n) {
            edits++;
        }

        // return true if the number of edits is exactly one; return false otherwise
        return (edits == 1);
    }

    public static void main(String[] args) {
        OneEditAway oea = new OneEditAway();
        System.out.println(oea.isOneEditAway("ac", "abc"));     // true
        System.out.println(oea.isOneEditAway("xyz", "xz"));     // true
        System.out.println(oea.isOneEditAway("xyz", "xyyz"));   // true
        System.out.println(oea.isOneEditAway("xyz", "xyx"));    // true
        System.out.println(oea.isOneEditAway("xyz", "xxx"));    // false

    }
}
