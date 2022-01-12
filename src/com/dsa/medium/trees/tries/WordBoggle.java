package com.dsa.medium.trees.tries;

import java.util.*;

//Word game Boggle implemented using Trie and Depth First Search (DFS) algorithm
//This algorithm uses the following
//
//DFS is used to form all possible strings in the Boggle grid.
//Trie is used for searching if the string formed using DFS is present in the list of words inserted into it.
//The idea behind this algorithm is to create a Trie with the list of words that we want to search in the Boggle. If the
// sub-string formed using DFS is not present in the trie, we terminate our search as this sub-string cannot form any
// other word that we want to search. i.e This sub-string cannot be a prefix of any other word that we want to search.

public class WordBoggle {

    // A class to store a Trie node
    class Trie
    {
        // true when the node is a leaf node
        boolean isLeaf;

        Map<Character, Trie> children;

        // Constructor
        public Trie() {
            children = new HashMap<>();
            isLeaf = false;
        }
    }

    Trie root;

    // Below arrays detail all eight possible movements from a cell
    // (top, right, bottom, left, and four diagonal moves)
    private static final int[] row = { -1, -1, -1, 0, 1, 0, 1, 1 };
    private static final int[] col = { -1, 1, 0, -1, -1, 1, 0, 1 };

    public WordBoggle(){
        root = new Trie();
    }

    // Iterative function to insert a string into a Trie
    private void insert(Trie root, String str)
    {
        // start from the root node
        Trie curr = root;

        for (char ch: str.toCharArray())
        {
            // create a new node if the path doesn't exist
            curr.children.putIfAbsent(ch, new Trie());

            // go to the next node
            curr = curr.children.get(ch);
        }

        curr.isLeaf = true;
    }


    private int search(Trie root, String str){
        if (str == null || str.isEmpty()){
            return 0;
        }

        Trie curr = root;
        for (char ch : str.toCharArray()){
            if (curr.children.get(ch) != null){
                curr = curr.children.get(ch);
            } else {
                //No match found
                return 0;
            }
        }
        //Found a word match
        if (curr.isLeaf){
            return 2;
        }

        //Found a prefix match
        return 1;
    }

    // The function returns false if (x, y) is not valid matrix coordinates
    // or cell (x, y) is already processed or doesn't lead to the solution
    public boolean isSafe(int x, int y, boolean[][] processed,
                          char[][] board, char ch)
    {

        return (x >= 0 && x < board.length && y >= 0 && y < board[0].length && !processed[x][y]
        && ch == board[x][y]);
    }

    private void searchBoggle(Trie root,char[][] board,int i, int j,
                              boolean[][] processed,String path,Set<String> result){

        if (root.isLeaf){
            result.add(path);
        }

        processed[i][j] = true;

        // traverse all children of the current Trie node
        for (Map.Entry<Character,Trie> entry : root.children.entrySet()){

            //check all possible movements from current position on the board
            for (int k=0; k < row.length; k++){
                if (isSafe(i+row[k],j+col[k],processed,board,entry.getKey())){
                    searchBoggle(entry.getValue(), board,i+row[k],j+col[k],processed,
                            path+entry.getKey(),result);
                }

            }
        }

        // backtrack: mark the current cell as unprocessed
        processed[i][j] = false;
    }


    //Solution:
    //1. Create an Empty trie and insert all words of given dictionary into trie
    //2. After that pick only those character in board[][] which are child of root of Trie
    //Let for above we pick ‘S’ boggle[0][1], ‘N’ boggle[2][2] (they both are present in boggle board)
    // 3. search a word in a trie which start with character that we pick in step 2

    //Time complexity: O((rows*cols) * (8*(rows*cols)).
    //Even after applying trie the time complexity remains same. For every cell there are 8 possible directions and
    //there are rows*cols cells. So the time complexity is O(8^(N^2)).
    //Auxiliary Space: O(N^2).
    //The maximum length of recursion can be N^2, where N is the side of the matrix. So the space Complexity is O(N^2).

    public Set<String> searchBoggle(Trie root,char[][] board,List<String> words) {

      Set<String> result = new HashSet<>();

      if (board == null || board.length == 0 || board[0].length == 0){
          return result;
      }

      for (String word : words){
          insert(root,word);
      }

      int nRows = board.length;
      int nCols = board.length;

      boolean[][] processed = new boolean[nRows][nCols];

      for (int i=0; i < nRows; i++){
          for (int j=0; j < nCols; j++){
              char ch = board[i][j];

              //Only search if character match in word dictionary
              if (root.children.containsKey(ch)){
                  searchBoggle(root.children.get(ch),board,i,j,processed,Character.toString(ch),result);
              }
          }
      }

      return result;
    }


    public static void main(String[] args)
    {
        WordBoggle trie = new WordBoggle();
        char[][] board =
                {
                        {'M', 'S', 'E', 'F'},
                        {'R', 'A', 'T', 'D'},
                        {'L', 'O', 'N', 'E'},
                        {'K', 'A', 'F', 'B'}
                };

        List<String> words = Arrays.asList("START", "NOTE", "SAND", "STONED");

        Set<String> validWords = trie.searchBoggle(trie.root,board, words);
        System.out.println(validWords);
    }

}
