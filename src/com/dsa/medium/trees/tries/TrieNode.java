package com.dsa.medium.trees.tries;

import java.util.HashMap;
import java.util.Map;

public class TrieNode {

    Map<Character,TrieNode> children;
    boolean leaf;

    public TrieNode(){
        children = new HashMap<>();
        leaf = false;
    }
}
