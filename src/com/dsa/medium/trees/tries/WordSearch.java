package com.dsa.medium.trees.tries;

import java.util.*;

//Question: https://leetcode.com/problems/word-search-ii/

public class WordSearch {

    class Trie {
        Map<Character,Trie> children;
        boolean isLeaf;

        public Trie(){
            children = new HashMap<>();
            isLeaf = false;
        }
    }

    static final private int[] rows = {1,0,-1,0};
    static final private int[] cols = {0,1,0,-1};

    public void insert(Trie root,String word){
        if (word == null || word.isEmpty()){
            return;
        }

        Trie curr = root;
        for (char ch : word.toCharArray()){
            curr.children.putIfAbsent(ch,new Trie());
            curr = curr.children.get(ch);
        }
        curr.isLeaf = true;
    }

    private boolean isSafe(int x,int y,char[][] board,char ch,boolean[][] processed){
        return (x >=0 && x < board.length && y >= 0 && y < board[0].length &&
                board[x][y] == ch && !processed[x][y]);
    }

    public void findWords(Trie root,char[][] board,int i, int j,
                          boolean[][] processed,String path,Set<String> result){

        if (root.isLeaf){
            result.add(path);
        }

        processed[i][j] = true;

        for (Map.Entry<Character,Trie> entry : root.children.entrySet()){
            for (int k=0; k < rows.length; k++){
                if (isSafe(i+rows[k],j+cols[k],board,entry.getKey(),processed)){
                    findWords(entry.getValue(),board,i+rows[k],j+cols[k],processed,path+entry.getKey(),result);
                }
            }
        }

        processed[i][j] = false;
    }

    public List<String> findWords(char[][] board, String[] words) {
        Set<String> result = new HashSet<>();
        if (words == null || words.length == 0){
            return new ArrayList<>(result);
        }

        Trie root = new Trie();
        for (String word : words){
            insert(root,word);
        }

        int nRows = board.length;
        int nCols = board[0].length;

        boolean[][] processed = new boolean[nRows][nCols];

        for (int i=0; i < nRows; i++){
            for (int j=0; j < nCols; j++){
                char ch = board[i][j];

                if(root.children.containsKey(ch)){
                    findWords(root.children.get(ch),board,i,j,
                            processed,Character.toString(ch),result);
                }
            }
        }

        return new ArrayList<>(result);
    }


    public static void main(String[] args) {
        WordSearch trie = new WordSearch();

        char[][] board = {{'o','a','a','n'},
                        {'e','t','a','e'},
                        {'i','h','k','r'},
                        {'i','f','l','v'}};

        String[] words = {"oath","eat"};

        List<String> result = trie.findWords(board,words);

        for (String word : result){
            System.out.print(word+" ");
        }
        System.out.println();

        char[][] board2 = {{'a','b'},
                           {'c','d'}};
        String[] words2 = {"abcd"};

        result = trie.findWords(board2,words2);

        for (String word : result){
            System.out.print(word+" ");
        }
    }
}
