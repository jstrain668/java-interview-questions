package com.dsa.medium.trees.tries;

import java.util.HashMap;
import java.util.Map;


public class Trie {

    Map<Character,Trie> children;
    int freq;
    String key;
    boolean leaf;

    public Trie (){
        children = new HashMap<>();
        freq = 0;
        key = null;
        leaf = false;
    }
}
