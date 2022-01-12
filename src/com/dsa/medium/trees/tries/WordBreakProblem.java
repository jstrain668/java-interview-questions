package com.dsa.medium.trees.tries;

//Question: https://leetcode.com/problems/word-break/

//Given a dictionary of words, determine if a given string can be segmented into a space-separated sequence of one or
// more dictionary words.

//Reference: https://www.techiedelight.com/word-break-problem-using-trie/

//Consider the problem of breaking a string into component words. Call this string s. Let x be a prefix of s, and y be
//the remaining characters forming a suffix,so x concatenated with y is s. Then if we can break x and y into words
//recursively, we can break xy = s by merging the two sets of words. We can simplify things for ourselves by assuming
//that x will be a dictionary word; the problem is then to construct such x. We can do this with a Trie. Since x is
//known to be a prefix of s,any candidate word in the dictionary must be found on the Tries path corresponding to the
//first few letters of s. To do this use dynamic-programming any time we find an appropriate x, set our array to have a
// solution at |x|+1, where |x| is the size of prefix x. Then we can check the last entry to find if the entire string
// can be broken up.

public class WordBreakProblem {

    TrieNode root;

    public WordBreakProblem(){
        root = new TrieNode();
    }


    public void insert(String word){
        if (word == null || word.isEmpty()){
            return;
        }

        TrieNode curr = root;

        for (char ch : word.toCharArray()){

            curr.children.putIfAbsent(ch,new TrieNode());
            curr = curr.children.get(ch);
        }
        curr.leaf = true;
    }

    public boolean isTargetSegmented(String s) {
        boolean[] good = new boolean[s.length() + 1];

        good[0] = true;
        for (int i = 0; i < s.length(); i++) {
            TrieNode current = root;
            if (good[i]) {
                for (int j = i; j < s.length(); j++) {
                    if (current.children.get(s.charAt(j)) == null) {
                        break;
                    }
                    current = current.children.get(s.charAt(j));

                    if (current != null && current.leaf) {
                        good[j + 1] = true;
                    }
                }
            }
        }
        return good[s.length()];
    }

    //A naive analysis suggests O(nxn) runtime, but notice that the second loop will break when the node is null. This
    //must occur after k steps, where k is the deepest vertex in the Trie (though it could, of course, occur earlier).
    //It is not too difficult to see that with a dictionary containing a word of maximum length w, we would have
    //k = w+1. So, the time complexity of the loop is actually O(w), and thus the whole function has a time complexity
    //of O(n.w).
    //The additional space used is the space necessary to hold a trie and the good array, i.e.,O(n + sum of word lengths)

    public boolean wordBreak(String s) {
        if(s == null) {
            return false;
        }

        return isTargetSegmented(s);
    }

    public static void main(String[] args)
    {
        String[] dictionary = { "mobile", "samsung",
                "sam", "sung", "ma",
                "mango", "icecream",
                "and", "go", "i", "like",
                "ice", "cream" };

       WordBreakProblem trie = new WordBreakProblem();

        // Construct trie
        for (String word : dictionary) {
            trie.insert(word);
        }

        System.out.print(trie.wordBreak("ilikesamsung") ?
                "Yes\n" : "No\n");
        System.out.print(trie.wordBreak("iiiiiiii") ?
                "Yes\n" : "No\n");
        System.out.print(trie.wordBreak("") ?
                "Yes\n" : "No\n");
        System.out.print(trie.wordBreak("ilikelikeimangoiii") ?
                "Yes\n" : "No\n");
        System.out.print(trie.wordBreak("samsungandmango") ?
                "Yes\n" : "No\n");
        System.out.print(trie.wordBreak("samsungandmangok") ?
                "Yes\n" : "No\n");
        System.out.print(trie.wordBreak("samsung") ?
                "Yes\n" : "No\n");
    }
}
