package com.dsa.medium.trees.tries;

//Question and reference: https://www.techiedelight.com/find-duplicate-rows-binary-matrix/

public class DuplicatesInBinaryMatrix {

    BinTrieNode root;

    public DuplicatesInBinaryMatrix()
    {
        root = new BinTrieNode();
    }


    public boolean insert(int[] row){
        BinTrieNode curr = root;

        for (int i=0; i < row.length; i++){

            if (curr.children[row[i]] == null){
                curr.children[row[i]] = new BinTrieNode();
            }
            curr = curr.children[row[i]];
        }

        // if the row is inserted before, return false
        if (curr.leaf){
            return false;  // duplicate row
        }

        return curr.leaf = true;
    }

    public static void main (String[] args)
    {
        DuplicatesInBinaryMatrix trie = new DuplicatesInBinaryMatrix();

        int[][] mat =
                {
                        {1, 0, 0, 1, 0},
                        {0, 1, 1, 0, 0},
                        {1, 0, 0, 1, 0},
                        {0, 0, 1, 1, 0},
                        {0, 1, 1, 0, 0}
                };

        // insert all rows of the matrix into a Trie
        for (int i = 0; i < mat.length; i++)
        {
            if (!trie.insert(mat[i])) {
                System.out.println("Duplicate row found: Row #" + (i + 1));
            }
        }
    }
}
