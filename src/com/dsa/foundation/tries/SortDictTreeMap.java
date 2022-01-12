package com.dsa.foundation.tries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class SortDictTreeMap {

    TrieNodeTreeMap root;

    public SortDictTreeMap(){
        root = new TrieNodeTreeMap();
    }

    public void insert(String word){

        if (word == null || word.length() == 0){
            return;
        }

        TrieNodeTreeMap node = root;

        for (Character ch : word.toCharArray()){
            if (!node.children.containsKey(ch)){
                node.children.put(ch,new TrieNodeTreeMap());
            }
            node = node.children.get(ch);
        }
        node.leaf = true;

    }

    // Return all words which start with the given prefix
    public List<String> searchWords(String prefix) {
        TrieNodeTreeMap current = root;
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < prefix.length(); i++) {
            char ch = prefix.charAt(i);
            if (!current.children.containsKey(ch)) {
                return null;
            } else {
                sb.append(ch);
                current = current.children.get(ch);
            }
        }

        List<String> list = new ArrayList<>();
        dfs(current, sb.toString(), list);

        return list;
    }

    private void dfs(TrieNodeTreeMap node, String prefix, List<String> list) {
        if (node.leaf) {
            list.add(prefix);
        }
        for (Map.Entry<Character, TrieNodeTreeMap> entry : node.children.entrySet()) {
            dfs(entry.getValue(), prefix + entry.getKey(), list);
        }

    }

    public List<String> findAllWords(){
        List<String> list = new ArrayList<>();
        TrieNodeTreeMap curr = root;

        dfs(curr,"",list);

        return list;
    }

    private void lexicographlySort(TrieNodeTreeMap node,String prefix,List<String> list){
        if (node == null) {
            return;
        }

        for (Map.Entry<Character, TrieNodeTreeMap> entry : node.children.entrySet()){

            if (entry.getValue().leaf){
                list.add(prefix+entry.getKey());
            }
            lexicographlySort(entry.getValue(),prefix+entry.getKey(),list);
        }
    }

    public List<String> sortAllWords(){
        List<String> list = new ArrayList<>();
        TrieNodeTreeMap curr = root;

        //Use either dfs or lexicographlySort
        dfs(curr,"",list);
        //lexicographlySort(curr,"",list);

        return list;
    }

    public static void main(String[] args) {
        // Input keys (use only 'a' through 'z' and lower case)
        String[] keys = {"the", "a", "there", "answer", "any",
                "by", "bye", "their","mad"};

        String[] output = {"Not present in trie", "Present in trie"};

        SortDictTreeMap trie = new SortDictTreeMap();

        for (String key : keys) {
            trie.insert(key);
        }


        String prefix = "the";
        List<String> words = trie.searchWords(prefix);
        System.out.println("List of words starting with the given prefix "+prefix);
        for (String word : words){
            System.out.print(word+" ");
        }
        System.out.println();

        words = trie.findAllWords();
        System.out.println("List of all words in dictionary : ");
        for (String word : words){
            System.out.print(word+" ");
        }
        System.out.println();

        words = trie.sortAllWords();
        System.out.println("Sort all words in dictionary : ");
        for (String word : words){
            System.out.print(word+" ");
        }
        System.out.println();
    }

}
