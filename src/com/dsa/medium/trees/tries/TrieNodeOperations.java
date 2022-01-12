package com.dsa.medium.trees.tries;

import com.dsa.foundation.tries.TrieNodeMap;
import com.dsa.foundation.tries.TrieNodeOperationsMap;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;

public class TrieNodeOperations {

    TrieNode root;

    public TrieNodeOperations(){
        root = new TrieNode();
    }

    public void insert(String word){

        if (word == null || word.isEmpty()){
            return;
        }

        TrieNode curr = root;
        for (char ch : word.toCharArray()){
            curr.children.putIfAbsent(ch,new TrieNode());
            curr = curr.children.get(ch);
        }
        curr.leaf = true;
    }

    public boolean search(String word){
        if (word == null){
            return false;
        }

        TrieNode curr = root;
        for (char ch : word.toCharArray()) {
            if (!curr.children.containsKey(ch)) {
                return false;
            }
            curr = curr.children.get(ch);
        }
        return curr.leaf;
    }

    public boolean startsWith(String prefix){
        if (prefix == null){
            return false;
        }

        TrieNode curr = root;
        for (char ch : prefix.toCharArray()) {
            if (!curr.children.containsKey(ch)) {
                return false;
            }
            curr = curr.children.get(ch);
        }
        return true;
    }


    // https://github.com/jojozhuang/dsa-java/blob/master/ds-trie/src/main/java/johnny/dsa/ds/Trie.java
    // https://leetcode.com/problems/implement-trie-prefix-tree/discuss/156159/Java-solution-with-delete()-method
    public boolean delete(String word){
        if (word == null || word.isEmpty()){
            return false;
        }

        TrieNode curr = root;
        TrieNode lastBranchNode = null;
        Character lastBranchChar = null;

        for (char ch : word.toCharArray()){

            if (curr.children.containsKey(ch)){
                if (curr.children.size() > 1 || curr.leaf){
                    lastBranchChar = ch;
                    lastBranchNode = curr;
                }
                curr = curr.children.get(ch);
            }  else { // word not found
                return false;
            }
        }

        if (!curr.leaf) { // word isn't in trie
            return false;
        }

        if (curr.children.isEmpty()) {
            lastBranchNode.children.remove(lastBranchChar);
        } else {
            curr.leaf = false; // Delete word by mark it as not the end of a word
        }

        return true;

    }

    // Return all words which start with the given prefix
    public List<String> searchWords(String prefix) {

        List<String> list = new ArrayList<>();

        if (prefix == null || prefix.isEmpty()){
            return list;
        }

        TrieNode curr = root;
        StringBuilder sb = new StringBuilder();

        for (char ch : prefix.toCharArray()){

            if (!curr.children.containsKey(ch)){
                return list;
            }else{
                sb.append(ch);
                curr = curr.children.get(ch);
            }
        }

        dfs(curr,sb.toString(),list);
        return list;
    }

    private void dfs(TrieNode node,String prefix,List<String> list){

        if (node.leaf){
            list.add(prefix);
        }

        for (Map.Entry<Character,TrieNode> entry : node.children.entrySet()){
            dfs(entry.getValue(),prefix+entry.getKey(),list);
        }
    }

    public List<String> findAllWords(){
        List<String> list = new ArrayList<>();
        TrieNode curr = root;

        dfs(curr,"",list);
        return list;
    }

    public static void main(String[] args) {
        // Input keys (use only 'a' through 'z' and lower case)
        String[] keys = {"the", "a", "there", "answer", "any",
                "by", "bye", "their","mad","fire","fart"};

        String[] output = {"Not present in trie", "Present in trie"};

        TrieNodeOperations trie = new TrieNodeOperations();

        for (String key : keys) {
            trie.insert(key);
        }

        System.out.println("List of all words in dictionary");
        List<String> dict = trie.findAllWords();
        for (String str : dict){
            System.out.print(str+" ");
        }
        System.out.println();

        // Search for different keys
        if(trie.search("the"))
            System.out.println("the --- " + output[1]);
        else
            System.out.println("the --- " + output[0]);

        if(trie.search("these"))
            System.out.println("these --- " + output[1]);
        else
            System.out.println("these --- " + output[0]);

        if(trie.search("their"))
            System.out.println("their --- " + output[1]);
        else
            System.out.println("their --- " + output[0]);

        if(trie.search("thaw"))
            System.out.println("thaw --- " + output[1]);
        else
            System.out.println("thaw --- " + output[0]);

        if(trie.startsWith("ther"))
            System.out.println("ther is a prefix in the dictionary");
        else
            System.out.println("ther is not a prefix in the dictionary");

        if(trie.startsWith("ant"))
            System.out.println("ant is a prefix in the dictionary");
        else
            System.out.println("ant is not a prefix in the dictionary");

        if(trie.delete("mad")) {
            System.out.println("mad deleted from trie dictionary");
        }
        else{
            System.out.println("mad not found in trie dictionary");
        }

        if(trie.delete("by")) {
            System.out.println("by deleted from trie dictionary");
        }
        else{
            System.out.println("by not found in trie dictionary");
        }

        if(trie.delete("fire")) {
            System.out.println("fire deleted from trie dictionary");
        }
        else{
            System.out.println("fire not found in trie dictionary");
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

        Collections.sort(words);
        System.out.println("List of all words in dictionary : ");
        for (String word : words){
            System.out.print(word+" ");
        }
        System.out.println();

    }
}
