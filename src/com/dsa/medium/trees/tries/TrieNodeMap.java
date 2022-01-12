package com.dsa.medium.trees.tries;

import java.util.HashMap;
import java.util.Map;

public class TrieNodeMap {

    Map<Character,TrieNodeMap> children;
    boolean leaf;
    int freq;

    public TrieNodeMap(){
        children = new HashMap<>();
        leaf = false;
        freq = 0;
    }
}
