package com.dsa.foundation.tries;

public class TrieNode {

    public static final int ALPHABET_SIZE = 26;

    TrieNode[] children;
    boolean endOfWord;

    public TrieNode(){
        children = new TrieNode[ALPHABET_SIZE];
        endOfWord = false;
    }
}
