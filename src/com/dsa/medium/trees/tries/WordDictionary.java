package com.dsa.medium.trees.tries;

import java.util.HashMap;
import java.util.Map;

public class WordDictionary {

    class TrieNode {
        Map<Character,TrieNode>  children;
        boolean ends;

        public TrieNode() {
            children = new HashMap<>();
            ends = false;
        }
    }

    TrieNode head;

    public WordDictionary() {
        head = new TrieNode();
    }

    public void addWord(String word) {
        TrieNode trav = head;
        for (char c: word.toCharArray()) {
            trav.children.putIfAbsent(c,new TrieNode());
            trav = trav.children.get(c);
        }
        trav.ends = true;
    }

    public boolean search(String word) {
        return dfs(word, 0, head);
    }

    public boolean dfs(String word, int index, TrieNode curr) {
        if (curr == null) {
            return false;
        }

        if (index == word.length()) {
            return curr.ends;
        }

        if (word.charAt(index) == '.') {
            for (Map.Entry<Character,TrieNode> entry : curr.children.entrySet()){
                if (dfs(word, index + 1, entry.getValue()))
                    return true;
            }
        } else {
            TrieNode next = curr.children.get(word.charAt(index));
            if (dfs(word, index + 1, next))
                return true;
        }
        return false;
    }

    public static void main(String[] args) {
        WordDictionary obj = new WordDictionary();
        obj.addWord("bad");
        obj.addWord("dad");
        obj.addWord("mad");
        System.out.println("FOUND :"+obj.search("pad")); // return False
        System.out.println("FOUND :"+obj.search("bad")); // return True
        System.out.println("FOUND :"+obj.search(".ad")); // return True
        System.out.println("FOUND :"+obj.search("b..")); // return True
    }
}
