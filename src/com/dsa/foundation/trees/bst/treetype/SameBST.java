package com.dsa.foundation.trees.bst.treetype;

import java.util.ArrayList;
import java.util.List;

//Question: Given two arrays which represent a sequence of keys. Imagine we make a Binary Search Tree (BST) from each
// array. We need to tell whether two BSTs will be identical or not without actually constructing the tree.

//Reference: https://www.geeksforgeeks.org/check-for-identical-bsts-without-building-the-trees/

//Reference: https://massivealgorithms.blogspot.com/2014/07/check-for-identical-bsts-without.html


public class SameBST {

    public boolean isSameBST(int[] a,int b[]){

        List<Integer> listForTree1 = new ArrayList<>();
        List<Integer> listForTree2 = new ArrayList<>();

        for (int i : a){
            listForTree1.add(i);
        }

        for (int i : b){
            listForTree2.add(i);
        }

        return checkIfSameBSTs(listForTree1,listForTree2);

    }

    //According to BST property, elements of the left subtree must be smaller and elements of right subtree must be
    //greater than root. Two arrays represent the same BST if, for every element x, the elements in left and right
    //subtrees of x appear after it in both arrays. And same is true for roots of left and right subtrees.
    //The idea is to check of if next smaller and greater elements are same in both arrays. Same properties are
    // recursively checked for left and right subtrees.

    private boolean checkIfSameBSTs(List<Integer> listForTree1,List<Integer> listForTree2) {

        // both trees should have same size
        if (listForTree1.size() != listForTree2.size())
        {
            return false;
        }

        // if both trees are null trees
        if (listForTree1.size() == 0)
        {
            return true;
        }


        // root node has to be the same
        if (listForTree1.get(0) == listForTree2.get(0))
        {
            // segregate nodes for left and right sub-trees for both trees
            ArrayList<Integer> listForLeftTree1 = new ArrayList<>();
            ArrayList<Integer> listForRightTree1 = new ArrayList<>();

            ArrayList<Integer> listForLeftTree2 = new ArrayList<>();
            ArrayList<Integer> listForRightTree2 = new ArrayList<>();

            for (int i = 1; i < listForTree1.size(); i++)
            {
                if (listForTree1.get(i) < listForTree1.get(0))
                {
                    listForLeftTree1.add(listForTree1.get(i));
                }
                else
                {
                    listForRightTree1.add(listForTree1.get(i));
                }

                if (listForTree2.get(i) < listForTree2.get(0))
                {
                    listForLeftTree2.add(listForTree2.get(i));
                }
                else
                {
                    listForRightTree2.add(listForTree2.get(i));
                }
            }

            // and check that left and right sub-tree are also same
            return checkIfSameBSTs(listForLeftTree1, listForLeftTree2) &&
                    checkIfSameBSTs(listForRightTree1, listForRightTree2);
        }
        return false;
    }

    // Driver code
    public static void main(String[] args)
    {
        SameBST same = new SameBST();
        int[] a = {8, 3, 6, 1, 4, 7, 10, 14, 13};
        int[] b = {8, 10, 14, 3, 6, 4, 1, 7, 13};

        System.out.printf("%s\n", same.isSameBST(a, b)?
                "BSTs are same":"BSTs not same");

        int[] c = {2, 4, 1, 3};
        int[] d = {2, 3, 4, 1};

        System.out.printf("%s\n", same.isSameBST(c, d)?
                "BSTs are same":"BSTs not same");


    }
}
