package com.dsa.foundation.tries;

import java.util.HashMap;
import java.util.Map;

public class TrieNodeMap {

    Map<Character,TrieNodeMap> children;
    boolean endOfWord;

    public TrieNodeMap(){
        children = new HashMap<>();
        endOfWord = false;
    }

}
