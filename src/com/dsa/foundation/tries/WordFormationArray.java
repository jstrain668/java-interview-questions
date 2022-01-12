package com.dsa.foundation.tries;

import java.util.ArrayList;
import java.util.List;

//Question and Reference: https://www.geeksforgeeks.org/word-formation-using-concatenation-of-two-dictionary-words/

public class WordFormationArray {

    TrieNode root;

    public WordFormationArray(){
        root = new TrieNode();
    }

    public void insert(String word){

        if (word == null || word.isEmpty()){
            return;
        }

        TrieNode curr = root;
        for (char ch: word.toCharArray()){
            if (curr.children[ch - 'a'] == null){
                curr.children[ch - 'a'] = new TrieNode();
            }
            curr = curr.children[ch - 'a'];
        }
        curr.endOfWord = true;
    }

    // Searches a prefix of key. If prefix is present,
    // returns its ending position in string. Else
    // returns -1.
    public List<Integer> findPrefix(String prefix)
    {
        List<Integer> prefixPositions = new ArrayList<>();

        if (prefix == null || prefix.isEmpty()){
            return prefixPositions;
        }

        TrieNode curr = root;
        int index = 0;

        for (index = 0; index < prefix.length(); index++){
            int letterIndex = prefix.charAt(index) - 'a';

            if (curr.children[letterIndex] == null){
                return prefixPositions;
            }

            if (curr.endOfWord){
                prefixPositions.add(index);
            }
            curr = curr.children[letterIndex];
        }

        if (curr != null && curr.endOfWord){
            prefixPositions.add(index);
        }
        return prefixPositions;
    }

    // Function to check if word formation is possible
    // or not
    public boolean isPossible(String word)
    {
        // Search for the word in the trie and get its prefix positions
        // upto which there is matched
        List<Integer> prefixPositions1 = findPrefix(word);

        // Word formation is not possible if the word did not have
        // at least one prefix match
        if (prefixPositions1.isEmpty())
            return false;

        // Search for rest of substring for each prefix match
        for (Integer len1 : prefixPositions1) {
            String restOfSubstring = word.substring(len1, word.length());
            List<Integer> prefixPositions2 = findPrefix(restOfSubstring);
            for (Integer len2 : prefixPositions2) {
                // check if word formation is possible
                if (len1 + len2 == word.length())
                    return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        // Let the given dictionary be following
        String[] dictionary = {"news", "newspa", "paper", "geek"};

        String word = "newspaper"; //word to be formed

        // root Node of trie
        WordFormationArray trie = new WordFormationArray();

        // insert all words of dictionary into trie
        for (String w : dictionary) {
            trie.insert(w);
        }

        if(trie.isPossible(word))
            System.out.println( "Yes");
        else
            System.out.println("No");
    }
}
