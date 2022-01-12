package com.dsa.medium.strings;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

//Question: https://leetcode.com/problems/word-break/

//Reference: https://www.techiedelight.com/word-break-problem/
//Reference: https://medium.com/@harycane/word-break-4da79fedcf43
//Reference: http://www.algorithmsandme.com/word-break-problem/
//Reference: https://massivealgorithms.blogspot.com/2015/09/leetcode-word-break-java.html

public class WordBreak {


    // We start with the first character of the string, check if the character itself is a word in the dictionary?
    // If yes, then our problem reduces to the smaller problem, that is to check if substring from index 1 to s.length
    // is breakable or not. If not, then we check two characters and then three characters and so on till we can check
    // the whole string. As with every character inclusion, the problem reduces in size but remains the same, so ideal
    // case for recursive implementation.
    // Change wordDict from ArrayList into HashSet as the average time complexity for a search is O(1) as opposed to
    // O(n) for ArrayList
    //Time Complexity : O(2 to the power of n) for the number of recursive calls in conjunction with the number of
    //characters in the string s. In addition there is O(n) for the substrings of the given string. The dominant term
    //is O(2 to the power of n)
    //Space Complexity: O(w) where w is the number of words in the HashSet and further O(r) where r is the number of
    //recursive calls
    public boolean wordBreak(String s, List<String> wordDict) {

        if (s == null || s.length() == 0) {
            return false;
        }

        Set<String> set = new HashSet<>(wordDict);

        return wordBreakUtil(s,set);
    }

    public boolean wordBreakUtil(String s,Set<String> set){

        // Return true if the end of the string is reached,

        if (s.length() == 0){
            return true;
        }

        for (int i=1; i <= s.length(); i++){

            // consider all prefixes of the current string
            String prefix = s.substring(0,i);

            // return true if the prefix is present in the dictionary and the
            // remaining string also forms a space-separated sequence of one or
            // more dictionary words
            if (set.contains(prefix) && wordBreakUtil(s.substring(i),set)){
                return true;
            }
        }

        return false;
    }

    // The problem can be broken down into smaller sub problem, which can further be broken down into yet smaller
    // sub problem, and so on. The word-break problem also exhibits. overlapping sub problems</a>, so we will end up
    // solving the same sub problem over and over again. If we draw the recursion tree, we can see that the same
    // sub problems are getting computed repeatedly. The problems having optimal substructure and overlapping
    // sub problem can be solved by dynamic programming, in which sub problem solutions are memoized rather than
    // computed repeatedly
    //Time Complexity: O(n to the power of 2)
    //Space Complexity:  requires O(n+1) extra space, where n is the length of the input string for the int array,
    // another O(w) for the HashSet where w is the number of words. Also O(r) for the number of recursive calls
    public boolean wordBreakTopDown(String s, List<String> wordDict) {

        if (s == null || s.length() == 0) {
            return false;
        }

        Set<String> set = new HashSet<>(wordDict);
        // lookup array to store solutions to sub problems
        // `lookup[i]` stores if substring `str[n-i…n)` can be segmented or not
        int[] lookup = new int[s.length() + 1];
        Arrays.fill(lookup,-1);

        return wordBreakUtilTopDown(s,set,lookup);
    }

    public boolean wordBreakUtilTopDown(String s, Set<String> dict,int[] lookup)
    {
        // `n` stores length of the current substring
        int n = s.length();

        // return true if the end of the string is reached
        if (n == 0) {
            return true;
        }

        // if the sub problem is seen for the first time
        if (lookup[n] == -1)
        {
            // mark subproblem as seen (0 initially assuming string
            // can't be segmented)
            lookup[n] = 0;

            for (int i = 1; i <= n; i++)
            {
                // consider all prefixes of the current string
                String prefix = s.substring(0, i);

                // if the prefix is found in the dictionary, then recur for the suffix
                if (dict.contains(prefix) && wordBreakUtilTopDown(s.substring(i),dict, lookup))
                {
                    // return true if the string can be segmented
                    lookup[n] = 1;
                    return true;
                }
            }
        }

        // return solution to the current sub problem
        return lookup[n] == 1;
    }


    // How can we do it bottom up? The string before index 0 is alway breakable as empty string. So table[0] can be
    // always true. To check if string till index i is breakable or not, we check from index 0 to index i-1 if there is
    // any index j till which string is breakable. If yes, then we just check if substring from index j to i, that will
    // make table[i] as true.
    //Time Complexity: O(n to the power of 2)
    //Space Complexity:  requires O(n) extra space, where n is the length of the input string for the boolean array
    public boolean wordBreakBottomUp(String s, List<String> wordDict) {

        if (s == null || s.length() == 0) {
            return false;
        }

        Set<String> set = new HashSet<>(wordDict);

        return wordBreakUtilBottomUp(s,set);
    }

    public boolean wordBreakUtilBottomUp(String s,Set<String> set){
        int n = s.length();
        //declare a boolean dp array 'table' with size = n+1
        boolean[] table = new boolean[n+1];

        //initialize table[0] = true coz empty string is included in the dictionary
        table[0] = true;

        for (int i=0; i <= n; i++) {
            //iterate over j from 0 to < i each time checking if
            for (int j=i-1; j >= 0; j--) {
                // Check if table[j] && set.contains(s.substring(j,i)) for all values of j in 0 to < i is true
                if (table[j] && set.contains(s.substring(j,i))){
                    table[i] = true;
                    break;
                }
            }
        }

        return table[n];
    }

    public List<String> addWords(){
        return Arrays.asList("leet","code");
    }

    public List<String> addWords2(){
        return Arrays.asList("cats","dog","sand","and","cat");
    }

    public static void main(String[] args) {
        WordBreak wb = new WordBreak();
        
        List<String> wordDict = wb.addWords2();
        String s = "catsandog";
        System.out.println(s+" can be broken into space-separated sequence of one or more dictionary words: "+wb.wordBreakBottomUp(s,wordDict));

    }
    
}