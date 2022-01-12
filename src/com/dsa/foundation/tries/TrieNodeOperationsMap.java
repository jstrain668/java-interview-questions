package com.dsa.foundation.tries;

//Reference: https://jojozhuang.github.io/algorithm/data-structure-trie/

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrieNodeOperationsMap {
    TrieNodeMap root;

    public void insert(String word){

        if (word == null || word.length() == 0){
            return;
        }

        TrieNodeMap node = root;

        for (Character ch : word.toCharArray()){
            if (!node.children.containsKey(ch)){
                node.children.put(ch,new TrieNodeMap());
            }
            node = node.children.get(ch);
        }
        node.endOfWord = true;

    }

    public boolean search(String word){

        //Allow for empty string to be in the trie
        if (word == null){
            return false;
        }
        TrieNodeMap pCrawl = root;

        for (int i = 0; i < word.length(); i++){

            char ch = word.charAt(i);
            if (!pCrawl.children.containsKey(ch)){
                return false;
            } else {
                pCrawl = pCrawl.children.get(ch);
            }
        }

        return pCrawl.endOfWord;
    }

    /** Returns if there is any word in the trie that starts with the given prefix. */
    public boolean startsWith(String prefix) {
        TrieNodeMap current = root;
        for(int i=0; i<prefix.length(); i++)
        {
            char c = prefix.charAt(i);
            if(current.children.containsKey(c)){
                current = current.children.get(c);
            }
            else return false;
        }
        return true;
    }


    /** delete - three cases to handle **/
    //Reference: https://jojozhuang.github.io/algorithm/data-structure-trie/

    //There are three cases when deleting a word from Trie.
    //
    //1. Word is prefix of other words : Just un mark the leaf node (set it to false)
    //2. Word has prefix of other words : If word is prefix of other words, then delete nodes from prefix to end of word.
    //3. Word Is Unique, neither it is prefix of other words, nor it has prefix of other words : If word neither is prefix
    //of other words, nor has prefix of other words, then just delete all the nodes.

    public boolean delete(String word) {
        if (word == null || word.length() == 0) {
            return false;
        }

        TrieNodeMap current = root;
        TrieNodeMap lastBranchNode = null;
        Character lastBranchChar = null;

        // Search to ensure word is present
        for (int i = 0; i < word.length(); i++) {
            char ch = word.charAt(i);
            if (current.children.containsKey(ch)) {
                if (current.children.size() > 1) {
                    lastBranchNode = current;
                    lastBranchChar = ch;
                }
                current = current.children.get(ch);
            } else {
                // word not found
                return false;
            }
        }

        if (current.children.size() > 0) {
            // case 1: The to-be deleted word is prefix of another long word in trie.
            current.endOfWord = false;
            return true;
        }

        if (lastBranchNode != null) {
            // case 2: The to-be deleted word has other words as prefix
            lastBranchNode.children.remove(lastBranchChar);
            return true;
        } else {
            // case 3: The to-be deleted word present as unique word
            root.children.remove(word.charAt(0));
            return true;
        }

    }

    // Return all words which start with the given prefix
    public List<String> searchWords(String prefix) {
        TrieNodeMap current = root;
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

    private void dfs(TrieNodeMap node, String prefix, List<String> list) {
        if (node.endOfWord) {
            list.add(prefix);
        }
        for (Map.Entry<Character, TrieNodeMap> entry : node.children.entrySet()) {
            dfs(entry.getValue(), prefix + entry.getKey(), list);
        }

    }

    public List<String> findAllWords(){
        List<String> list = new ArrayList<>();
        TrieNodeMap curr = root;

        dfs(curr,"",list);
        return list;
    }

    public static void main(String[] args) {
        // Input keys (use only 'a' through 'z' and lower case)
        String[] keys = {"the", "a", "there", "answer", "any",
                "by", "bye", "their","mad"};

        String[] output = {"Not present in trie", "Present in trie"};

        TrieNodeOperationsMap trie = new TrieNodeOperationsMap();

        trie.root = new TrieNodeMap();

        for (String key : keys) {
            trie.insert(key);
        }

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
            System.out.println("the deleted from trie dictionary");
        }
        else{
            System.out.println("the not found in trie dictionary");
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
    }

}
