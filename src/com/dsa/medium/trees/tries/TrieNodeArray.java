package com.dsa.medium.trees.tries;

public class TrieNodeArray {

    public static final int ALPHABET_SIZE = 26;

    TrieNodeArray[] children;
    boolean leaf;

    public TrieNodeArray(){
        children = new TrieNodeArray[ALPHABET_SIZE];
        leaf = false;
    }
}
