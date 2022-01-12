package com.dsa.medium.trees.tries;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Question: https://www.techiedelight.com/find-maximum-occurring-word-given-set-strings/

//Reference: https://www.geeksforgeeks.org/frequent-word-array-strings/

public class MaxOccurrence {

    class TrieNode {

        Map<Character,TrieNode> children;
        boolean leaf;
        int wordFreq;
        String key;

        public TrieNode(){
            children = new HashMap<>();
            leaf = false;
            wordFreq = 0;
            key = null;
        }
    }

    public void insert(TrieNode root,String word){
        if (word == null || word.isEmpty()){
            return;
        }

        TrieNode curr = root;

        for (char ch : word.toCharArray()){
            curr.children.putIfAbsent(ch,new TrieNode());
            curr = curr.children.get(ch);
        }
        curr.leaf = true;
        curr.wordFreq++;
        curr.key = word;
    }

    // perform dfs traversal on a Trie and find the word
    // with maximum frequency
    public int dfs(TrieNode node,StringBuilder mostFreqWord,int maxCount){
        if (node == null){
            return maxCount;
        }

        for (Map.Entry<Character,TrieNode> entry : node.children.entrySet()){

            if (entry.getValue().leaf && entry.getValue().wordFreq > maxCount){
                maxCount = entry.getValue().wordFreq;
                mostFreqWord.replace(0,mostFreqWord.length(),entry.getValue().key);
            }

            maxCount = dfs(entry.getValue(),mostFreqWord,maxCount);
        }

        return maxCount;
    }

    public int maxOccurrences(List<String> words,StringBuilder mostFreqWord){
        if (words == null || words.isEmpty()){
            return 0;
        }

        TrieNode root = new TrieNode();

        for (String word : words){
            insert(root,word);
        }

        return dfs(root,mostFreqWord,0);
    }

    public static void main(String[] args)
    {
        MaxOccurrence trie = new MaxOccurrence();
        // given set of keys
        List<String> words = Arrays.asList(
                "code", "coder", "coding", "codable", "codec", "codecs", "coded",
                "codeless", "codec", "codecs", "codependence", "codex", "codify",
                "codependents", "codes", "code", "coder", "codesign", "codec",
                "codeveloper", "codrive", "codec", "codecs", "codiscovered"
        );

        StringBuilder mostFreqWord = new StringBuilder();
        int maxCount = trie.maxOccurrences(words,mostFreqWord);

        // perform preorder traversal on a Trie and find the key
        // with maximum frequency

        System.out.println("Word : " + mostFreqWord.toString());
        System.out.println("Count: " + maxCount);

        List<String> dict = Arrays.asList("geeks", "for", "geeks", "a",
                "portal", "to", "learn", "can", "be",
                "computer", "science", "zoom", "yup",
                "fire", "in", "be", "data", "geeks" );

        StringBuilder freqWord = new StringBuilder();
        maxCount = trie.maxOccurrences(dict,freqWord);

        // perform preorder traversal on a Trie and find the key
        // with maximum frequency

        System.out.println("Word : " + freqWord.toString());
        System.out.println("Count: " + maxCount);
    }
}
