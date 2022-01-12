package com.dsa.medium.trees.tries;

import java.util.ArrayList;
import java.util.List;

//Question: https://leetcode.com/problems/lexicographical-numbers/

public class LexNumbersWithTrieArray {

    public static final int NUMBERS = 10;
    class TrieNode{
        public TrieNode[] children;
        boolean isLeaf;

        public TrieNode(){
            children = new TrieNode[NUMBERS];
        }
    }

    public void insert(TrieNode curr,String num){
        if (num == null || num.isEmpty()){
            return;
        }

        for (char ch : num.toCharArray()){
            if (curr.children[ch - '0'] == null){
                curr.children[ch - '0'] = new TrieNode();
            }
            curr = curr.children[ch - '0'];
        }
        curr.isLeaf = true;
    }

    public void dfs(TrieNode node,String num,List<Integer> result){
        if (node == null){
            return;
        }

        if (node.isLeaf){
            result.add(Integer.parseInt(num));
        }

        for (int i=0; i < NUMBERS; i++){
            if (node.children[i] != null){
                dfs(node.children[i],num+i,result);
            }
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
        LexNumbersWithTrieArray trie = new LexNumbersWithTrieArray();
        int n = 13;
        List<Integer> list = trie.lexicalOrder(n);

        for (int val : list){
            System.out.print(val+" ");
        }
        System.out.println();
    }
}
