package com.dsa.foundation.tries;

import java.util.Map;
import java.util.TreeMap;

class TrieNodeTreeMap{
    Map<Character, TrieNodeTreeMap> children;
    boolean leaf;

    public TrieNodeTreeMap(){
        leaf = false;
        children = new TreeMap<>();
    }
}
