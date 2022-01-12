package com.dsa.medium.trees.tries;

import org.assertj.core.data.MapEntry;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

//Question and Reference: https://www.techiedelight.com/longest-common-prefix-given-set-strings-using-trie/

public class LongestCommonPrefix {

    TrieNode root;

    public LongestCommonPrefix(){
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

    public String findLCP(List<String> dict){
        if (dict == null || dict.isEmpty()){
            return null;
        }

        for (String word : dict){
            insert(word);
        }

        StringBuilder sb = new StringBuilder();
        TrieNode curr = root;

        while (curr != null && curr.children.size() == 1 && !curr.leaf){
            for (Map.Entry<Character,TrieNode> entry : curr.children.entrySet()){
                sb.append(entry.getKey());
                curr = entry.getValue();
            }
        }
        return sb.toString();
    }

    public static void main (String[] args)
    {
        // given set of keys
        List<String> dict = Arrays.asList(
                "code", "coder", "coding", "codable", "codec", "codecs", "coded",
                "codeless", "codependence", "codependency", "codependent",
                "codependents", "codes", "codesign", "codesigned", "codeveloped",
                "codeveloper", "codex", "codify", "codiscovered", "codrive"
        );

        LongestCommonPrefix trie = new LongestCommonPrefix();

        System.out.println("The longest common prefix is " + trie.findLCP(dict));
    }
}
