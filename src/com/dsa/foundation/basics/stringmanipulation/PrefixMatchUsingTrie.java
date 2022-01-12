package com.dsa.foundation.basics.stringmanipulation;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//Reference: https://www.techiedelight.com/longest-common-prefix-given-set-strings-using-trie/

//Find the longest common prefix (LCP) in a given set of strings using Trie data structure. Since all descendants of a
//Trie node have a common prefix of the string associated with that node, Trie is the best data structure for this problem
//We start by inserting all keys (strings) into the Trie. Then traverse the Trie until we find a leaf node or node with
// more than one child. All characters in the Trie path form the longest common prefix.

public class PrefixMatchUsingTrie {

    public class TrieNode{
        Map<Character,TrieNode> character = new HashMap<>();
        boolean leaf;

        public TrieNode(){
            leaf = false;
        }
    }

    public void insert(TrieNode root,String s){

        TrieNode curr = root;

        for (char ch: s.toCharArray()){
            curr.character.putIfAbsent(ch,new TrieNode());
            curr = curr.character.get(ch);
        }
        curr.leaf = true;
    }

    public TrieNode addStrings(String[] strings){

        TrieNode root = new TrieNode();

        for (String s: strings){
            insert(root,s);
        }
        return root;
    }

    public String findLCP(TrieNode root){

        // traverse the Trie and find the longest common prefix

        StringBuilder lcp = new StringBuilder();
        TrieNode curr = root;

       //Continue to traverse prefix tree until reach leaf node or number of child nodes exceed 1
       while (curr != null && !curr.leaf && (curr.character.size() == 1)){
           for (Map.Entry<Character,TrieNode> entry : curr.character.entrySet()){
               lcp.append(entry.getKey());
               curr = entry.getValue();
           }
       }
       return lcp.toString();

    }

    public static void main(String[] args) {

        PrefixMatchUsingTrie pmut = new PrefixMatchUsingTrie();
        String[] strings = {"MadNotMad",
                "Madness",
                "Madden",
                "MadHatter"};

        System.out.println(Arrays.toString(strings));
        TrieNode root = pmut.addStrings(strings);
        String lcp = pmut.findLCP(root);
        System.out.println("longest common prefix: "+lcp);
    }
}
