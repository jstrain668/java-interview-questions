package com.dsa.medium.trees.tries;

import java.util.*;

//Question and reference: https://www.techiedelight.com/find-all-words-matching-pattern-dictionary/

//CamelCase Notation is the practice of writing compound words or phrases joined without spaces, where each word’s first
//letter is capitalized. For example, PowerPoint, LibreOffice, CinemaScope, etc., are in CamelCase.
//
// dict = [Hi, HiTech, HiTechCity, Hello, HelloWorld, HiTechLab]
//
//If the pattern is HT, the output is [HiTech, HiTechCity, HiTechLab].
//If the pattern is HTC, the output is [HiTechCity].
//If the pattern is H, the output is the same as the input.

public class CamelCaseSearch {

    class Trie{
        Map<Character,Trie> children;
        boolean leaf;
        Set<String> word;

        public Trie(){
            children = new HashMap<>();
            leaf = false;
            word = new HashSet<>();
        }

    }

    Trie root;

    public CamelCaseSearch(){
        root = new Trie();
    }

    public void insert(Trie root,String word){
        if (word == null || word.isEmpty()){
            return;
        }

        Trie curr = root;
        for (char ch : word.toCharArray()){
            // insert only uppercase characters
            if (Character.isUpperCase(ch)) {
                curr.children.putIfAbsent(ch, new Trie());
                curr = curr.children.get(ch);
            }
        }

        curr.leaf = true;
        curr.word.add(word);
    }

    public Trie searchPattern(Trie root,String pattern){
        if (pattern == null || pattern.isEmpty()){
            return null;
        }

        Trie curr = root;
        for (char ch : pattern.toCharArray()){
            if (!curr.children.containsKey(ch)){
                return null;
            }
            curr = curr.children.get(ch);
        }
        return curr;
    }

    public void dfs(Trie node,Set<String> result){

        if (node == null){
            return;
        }

        if (node.leaf){
            result.addAll(node.word);
        }

        for (Map.Entry<Character,Trie> entry : node.children.entrySet()){
            dfs(entry.getValue(),result);
        }
    }

    public Set<String> findAllWords(List<String> dictionary,String pattern){
        Set<String> result = new HashSet<>();
        if (dictionary == null || dictionary.isEmpty()){
            return result;
        }

        for (String word : dictionary){
            insert(root,word);
        }

        Trie patternTrie = searchPattern(root,pattern);

        if (patternTrie != null){
            dfs(patternTrie,result);
        }

        return result;
    }


    public static void main(String[] args)
    {
        CamelCaseSearch trie = new CamelCaseSearch();
        List<String> dictionary = Arrays.asList("Hi", "HiTech", "HiTechCity", "Techie",
                "TechieDelight", "Hello", "HelloWorld", "HiTechLab");

        String pattern = "HT";
        Set<String> result = trie.findAllWords(dictionary, pattern);

        if (result != null) {
            for (String match : result) {
                System.out.print(match + " ");
            }
        }
        else{
            System.out.println("No match found in with pattern "+pattern);
        }
    }
}
