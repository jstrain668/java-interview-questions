package com.dsa.foundation.tries;

// The worst-case runtime for creating a trie is a combination of m, the length of the longest key in the trie, and n,
// the total number of keys in the trie. Thus, the worst case runtime of creating a trie is O(mn).

//The time complexity of searching, inserting, and deleting from a trie depends on the length of the word a that’s
// being searched for, inserted, or deleted, and the number of total words, n, making the runtime of these operations
// O(an). Of course, for the longest word in the trie, inserting, searching, and deleting will take more time and memory
// than for the shortest word in the trie.

import java.util.ArrayList;
import java.util.List;

public class TrieNodeOperations {

    TrieNode root;

    public void insert(String word){
        TrieNode node = root;

        for (char ch : word.toCharArray()){
            if (node.children[ch - 'a'] == null){
                node.children[ch - 'a'] = new TrieNode();
            }
            node = node.children[ch - 'a'];
        }
        node.endOfWord = true;
    }


    public boolean search(String word) {
        int index;
        TrieNode pCrawl = root;

        for (int i = 0; i < word.length(); i++) {
            index = word.charAt(i) - 'a';

            if (pCrawl.children[index] == null)
                return false;

            pCrawl = pCrawl.children[index];
        }

        return (pCrawl.endOfWord);
    }

    public boolean startsWith(String prefix){
        int index;
        TrieNode pCrawl = root;

        for (int i=0; i < prefix.length();i++){
            index = prefix.charAt(i) - 'a';

            if (pCrawl.children[index] == null){
                return false;
            }
            pCrawl = pCrawl.children[index];
        }
        return true;
    }


    ////Reference: https://jojozhuang.github.io/algorithm/data-structure-trie/
    /*public boolean delete(String word) {
        TrieNode current = root;
        TrieNode lastBranchNode = null;
        Character lastBrachChar = null;

        for (int i = 0; i < word.length(); i++){
            char ch = word.charAt(i);
            if (current.children[ch-'a'] != null) {
                if (current.children.length > 1) {
                    lastBranchNode = current;
                    lastBrachChar = ch;
                }
                current = current.children[ch-'a'];
            } else {
                // word not found
                return false;
            }
        }

        if (current.children.length > 0) {
            // case 1: The to-be deleted word is prefix of another long word in trie.
            current.endOfWord = false;
            return true;
        }

        if (lastBranchNode != null) {
            // case 2: The to-be deleted word has other words as prefix
            lastBranchNode.children[lastBrachChar - 'a'] = null;
            return true;
        } else {
            // case 3: The to-be deleted word present as unique word
            root.children[word.charAt(0) - 'a'] = null;
            return true;
        }
    } */

    // Returns true if root has no children, else false
    public boolean isEmpty(TrieNode root)
    {
        for (int i = 0; i < TrieNode.ALPHABET_SIZE; i++)
            if (root.children[i] != null)
                return false;
        return true;
    }

    // Recursive function to delete a word from given Trie
    public TrieNode remove(TrieNode root, String word, int depth)
    {
        // If tree is empty
        if (root == null)
            return null;

        // If last character of key is being processed
        if (depth == word.length()) {

            // This node is no more end of word after
            // removal of given key
            if (root.endOfWord)
                root.endOfWord = false;

            // If given is not prefix of any other word
            if (isEmpty(root)) {
                root = null;
            }

            return root;
        }

        // If not last character, recur for the child
        // obtained using ASCII value
        int index = word.charAt(depth) - 'a';
        root.children[index] =
                remove(root.children[index], word, depth + 1);

        // If root does not have any child (its only child got
        // deleted), and it is not end of another word.
        if (isEmpty(root) && root.endOfWord == false){
            root = null;
        }

        return root;
    }

    //https://www.geeksforgeeks.org/trie-delete/
    public TrieNode delete(String word){
        if (word == null || word.isEmpty()){
            return root;
        }

        return remove(root,word,0);
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

    public List<String> searchWords(String prefix){
        List<String> list = new ArrayList<>();

        if (prefix == null || prefix.isEmpty()){
            return list;
        }

        StringBuilder sb = new StringBuilder();
        TrieNode curr = root;

        for (char ch : prefix.toCharArray()){

            if (curr.children[ch - 'a'] != null){
                sb.append(ch);
                curr = curr.children[ch - 'a'];
            } else{
                return list;
            }
        }

        dfs(curr, sb.toString(), list);
        return list;
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
                "by", "bye", "their","mad"};

        String[] output = {"Not present in trie", "Present in trie"};

        TrieNodeOperations trie = new TrieNodeOperations();

        trie.root = new TrieNode();

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


        trie.delete("mad");

        if(trie.search("mad")){
            System.out.println("mad found in trie dictionary");
        }
        else{
            System.out.println("mad not found in trie dictionary");
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
