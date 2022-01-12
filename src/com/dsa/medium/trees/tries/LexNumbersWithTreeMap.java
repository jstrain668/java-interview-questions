package com.dsa.medium.trees.tries;

import java.util.*;

//Question: https://leetcode.com/problems/lexicographical-numbers/

public class LexNumbersWithTreeMap {

    class TrieNode {
        Map<Character,TrieNode> children;
        boolean isLeaf;

        public TrieNode(){
            children = new TreeMap<>();
            isLeaf = false;
        }
    }

    public void insert(TrieNode curr,String num){
        if (num == null || num.isEmpty()){
            return;
        }

        for (char ch : num.toCharArray()){
            curr.children.putIfAbsent(ch,new TrieNode());
            curr = curr.children.get(ch);
        }
        curr.isLeaf = true;
    }

    private void dfs(TrieNode node,String num,List<Integer> result){
        if (node == null){
            return;
        }

        if (node.isLeaf){
            result.add(Integer.valueOf(num));
        }

        for (Map.Entry<Character,TrieNode> entry : node.children.entrySet()){
            dfs(entry.getValue(),num+entry.getKey(),result);
        }
    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> result = new ArrayList<>();

        TrieNode root = new TrieNode();

        TrieNode curr = root;

        for (int i=1; i <= n; i++){
            insert(curr,String.valueOf(i));
        }

        curr = root;
        dfs(curr,"",result);

        return result;
    }

    public static void main(String[] args) {
        LexNumbersWithTreeMap trie = new LexNumbersWithTreeMap();
        int n = 13;
        List<Integer> list = trie.lexicalOrder(n);

        for (int val : list){
            System.out.print(val+" ");
        }
        System.out.println();
    }
}
