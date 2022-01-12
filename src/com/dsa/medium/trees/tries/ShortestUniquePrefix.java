package com.dsa.medium.trees.tries;

import java.util.Map;

//Question and Reference: https://www.techiedelight.com/shortest-unique-prefix/

public class ShortestUniquePrefix {

    TrieNodeMap root;

    public ShortestUniquePrefix(){
        root = new TrieNodeMap();
    }

    public void insert(String word){
        if (word == null || word.isEmpty()){
            return;
        }

        TrieNodeMap curr = root;

        for (char ch : word.toCharArray()){

            //Add node if key (ch) doesn't exist
            curr.children.putIfAbsent(ch,new TrieNodeMap());
            //increment freq of ckey (ch)
            curr.children.get(ch).freq++;
            // Go to the next node
            curr = curr.children.get(ch);
        }
        curr.leaf = true;
    }

    private void printShortestPrefix(TrieNodeMap node,String prefix){
        if (node == null) {
            return;
        }

        // print `word_so_far` if the current Trie node is visited only once
        if (node.freq == 1)
        {
            System.out.println(prefix);
            return;
        }

        // recur for all child nodes
        for (Map.Entry<Character, TrieNodeMap> child: node.children.entrySet()) {
            printShortestPrefix(child.getValue(), prefix + child.getKey());
        }
    }

    public void findShortestPrefix(String[] words){
        if (words == null || words.length == 0){
            return;
        }

        for (String word : words){
            insert(word);
        }

        TrieNodeMap curr = root;
        printShortestPrefix(curr,"");
    }

    public static void main(String[] args)
    {
        String[] words = { "AND", "BONFIRE", "BOOL", "CASE", "CATCH", "CHAR" };
        ShortestUniquePrefix trie = new ShortestUniquePrefix();
        trie.findShortestPrefix(words);
    }
}
