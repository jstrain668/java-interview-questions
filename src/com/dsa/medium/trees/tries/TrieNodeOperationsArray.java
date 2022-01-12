package com.dsa.medium.trees.tries;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class TrieNodeOperationsArray {

    TrieNodeArray root;

    public TrieNodeOperationsArray(){
        root = new TrieNodeArray();
    }

    public void insert(String word){
        if (word == null || word.isEmpty()){
            return;
        }

        TrieNodeArray curr = root;

        for (char ch : word.toCharArray()){
            if (curr.children[ch - 'a'] == null){
                curr.children[ch - 'a'] = new TrieNodeArray();
            }
            curr = curr.children[ch - 'a'];
        }
        curr.leaf = true;
    }

    public boolean search(String word){
        if (word == null || word.isEmpty()){
            return false;
        }

        TrieNodeArray curr = root;
        for (char ch : word.toCharArray()){
            if (curr.children[ch - 'a'] == null){
                return false;
            } else{
                curr = curr.children[ch - 'a'];
            }
        }
        return curr.leaf;
    }

    public boolean startsWith(String prefix){
        if (prefix == null || prefix.isEmpty()){
            return false;
        }

        TrieNodeArray curr = root;
        for (char ch : prefix.toCharArray()){
            if (curr.children[ch - 'a'] == null){
                return false;
            } else{
                curr = curr.children[ch - 'a'];
            }
        }
        return true;
    }

    // Returns true if root has no children, else false
    public boolean isEmpty(TrieNodeArray root)
    {
        for (int i = 0; i < TrieNodeArray.ALPHABET_SIZE; i++)
            if (root.children[i] != null)
                return false;
        return true;
    }

    // Recursive function to delete a word from given Trie
    public TrieNodeArray remove(TrieNodeArray root, String word, int depth)
    {
        // If tree is empty
        if (root == null)
            return null;

        // If last character of key is being processed
        if (depth == word.length()) {

            // This node is no more end of word after
            // removal of given key
            if (root.leaf)
                root.leaf = false;

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
        if (isEmpty(root) && root.leaf == false){
            root = null;
        }

        return root;
    }


    // https://www.geeksforgeeks.org/trie-delete/
    public TrieNodeArray delete(String word){
        if (word == null || word.isEmpty()){
            return root;
        }

        return remove(root,word,0);
    }

    private void dfs(TrieNodeArray node,String prefix,List<String> list){

        if (node == null){
            return;
        }

        if (node.leaf){
            list.add(prefix);
        }

        for (int i=0; i < TrieNodeArray.ALPHABET_SIZE; i++){
            TrieNodeArray child = node.children[i];
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

        TrieNodeArray curr = root;
        StringBuilder sb = new StringBuilder();
        for (char ch: prefix.toCharArray()){
            if (curr.children[ch - 'a'] == null){
                return null;
            } else {
                sb.append(ch);
                curr = curr.children[ch - 'a'];
            }
        }

        dfs(curr,sb.toString(),list);

        return list;
    }

    public List<String> getAllWords(){
        List<String> list = new ArrayList<>();
        dfs(root,"",list);
        return list;
    }

    public static void main(String[] args) {
        TrieNodeOperationsArray trie = new TrieNodeOperationsArray();

        // Input keys (use only 'a' through 'z' and lower case)
        String[] keys = {"the", "a", "there", "answer", "any",
                "by", "bye", "their","mad","made","madness", "mire","eagle"};

        String[] output = {"Not present in trie", "Present in trie"};

        for (String word : keys){
            trie.insert(word);
        }

        String word = "theres";
        if (trie.search(word)){
            System.out.println("Word found "+word+ " in dictionary");
        } else {
            System.out.println("Word "+word+ " not found in dictionary");
        }

        word = "made";
        if (trie.search(word)){
            System.out.println("Word found "+word+ " in dictionary");
        } else {
            System.out.println("Word "+word+ " not found in dictionary");
        }

        String prefix = "ma";
        if (trie.startsWith(prefix)){
            System.out.println("Prefix found "+prefix+ " in dictionary");
        } else {
            System.out.println("Prefix "+prefix+ " not found in dictionary");
        }

        prefix = "ba";
        if (trie.startsWith(prefix)){
            System.out.println("Prefix found "+prefix+ " in dictionary");
        } else {
            System.out.println("Prefix "+prefix+ " not found in dictionary");
        }

        List<String> words = trie.getAllWords();
        System.out.println("List of all words in dict");
        for (String str : words){
            System.out.print(str+" ");
        }
        System.out.println();

        String delWord = "mad";
        trie.delete(delWord);


        word = "mad";
        if (trie.search(word)){
            System.out.println("Word found "+word+ " in dictionary");
        } else {
            System.out.println("Word "+word+ " not found in dictionary");
        }

        delWord = "mire";
        trie.delete(delWord);
        word = "mire";
        if (trie.search(word)){
            System.out.println("Word found "+word+ " in dictionary");
        } else {
            System.out.println("Word "+word+ " not found in dictionary");
        }

        delWord = "eagle";
        trie.delete(delWord);
        word = "eagle";
        if (trie.search(word)){
            System.out.println("Word found "+word+ " in dictionary");
        } else {
            System.out.println("Word "+word+ " not found in dictionary");
        }

        words = trie.getAllWords();
        System.out.println("List of all words in dict");
        for (String str : words){
            System.out.print(str+" ");
        }
        System.out.println();

        prefix = "the";
        words = trie.searchWords(prefix);
        System.out.println("List of words with prefix "+prefix+" in dict");
        for (String str : words){
            System.out.print(str+" ");
        }
        System.out.println();
    }
}
