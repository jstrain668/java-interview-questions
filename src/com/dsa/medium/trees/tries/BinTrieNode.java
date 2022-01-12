package com.dsa.medium.trees.tries;

public class BinTrieNode {

    public static final int SIZE = 2;

    BinTrieNode[] children;
    boolean leaf;

    public BinTrieNode(){
        children = new BinTrieNode[SIZE];
        leaf = false;
    }

}
