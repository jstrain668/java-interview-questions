package com.dsa.foundation.tries;

import java.util.ArrayList;
import java.util.List;

public class SortDictArray {

    TrieNode root;

    public SortDictArray(){
        root = new TrieNode();
    }


    public void insert(String word){

        if (word == null || word.isEmpty()){
            return;
        }

        TrieNode curr = root;
        for (char ch: word.toCharArray()){
            if (curr.children[ch - 'a'] == null){
                curr.children[ch - 'a'] = new TrieNode();
            }
            curr = curr.children[ch - 'a'];
        }
        curr.endOfWord = true;
    }


    private void dfs(TrieNode node,String prefix,List<String> list){
        if (node == null){
            return;
        }

        if (node.endOfWord){
            list.add(prefix);
        }

        for (int i=0; i < TrieNode.ALPHABET_SIZE; i++){
            TrieNode child = node.children[i];
            if (child != null){
                int letter = i + 'a';
                dfs(child,prefix + (char) letter,list);
            }

        }
    }

    // Return all words which start with the given prefix
    public List<String> searchWords(String prefix) {
        List<String> list = new ArrayList<>();
        if (prefix == null || prefix.isEmpty()){
            return list;
        }

        TrieNode current = root;
        StringBuilder sb = new StringBuilder();

        for (char ch: prefix.toCharArray()){
            int index = ch - 'a';

            if (current.children[index] == null){
                return list;
            }
            current = current.children[index];
            sb.append(ch);
        }

        dfs(current, sb.toString(), list);

        return list;
    }

    public List<String> sortAllWords(){
        TrieNode current = root;
        List<String> list = new ArrayList<>();

        dfs(current, "", list);

        return list;
    }

    public static void main(String[] args) {
        // Input keys (use only 'a' through 'z' and lower case)
        String[] keys = {"the", "a", "there", "answer", "any",
                "by", "bye", "their","mad"};

        String[] output = {"Not present in trie", "Present in trie"};

        SortDictArray trie = new SortDictArray();

        for (String key : keys){
            trie.insert(key);
        }

        String prefix = "the";
        System.out.println("Words with prefix: "+prefix);
        List<String> words = trie.searchWords(prefix);
        for (String word : words){
            System.out.print(word+" ");
        }
        System.out.println();

        System.out.println("Sort All words in dictionary: ");
        words = trie.sortAllWords();
        for (String word : words){
            System.out.print(word+" ");
        }
        System.out.println();
    }

}
