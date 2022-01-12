package com.dsa.foundation.trees.bst.convert;

//Question: Given a binary search tree (BST), convert it into greater sum tree.

//What is greater sum tree: Greater sum tree is a tree in which every node contains the sum of all the nodes which are
// greater than the node.

//Reference: https://algorithms.tutorialhorizon.com/convert-bst-to-greater-sum-tree/

import com.dsa.foundation.trees.bst.TreeNode;

public class GreaterSumTree {

    public int sum = 0;

    public TreeNode insert(TreeNode root, int val){
        if (root == null){
            return new TreeNode(val);
        }

        if (val < root.val){
            root.left = insert(root.left,val);
        } else{
            root.right = insert(root.right,val);
        }
        return root;
    }

    public TreeNode createBST(){
        TreeNode root = insert(null,10);
        root = insert(root,5);
        root = insert(root,15);
        root = insert(root,2);
        root = insert(root,7);
        root = insert(root,12);
        root = insert(root,20 );

        return root;
    }

    public void inOrder(TreeNode root){

        if (root != null){
            inOrder(root.left);
            System.out.print(root.val+" ");
            inOrder(root.right);
        }
    }


    //By leveraging the fact that the tree is a BST, we can find an O(n) solution. The idea is to traverse BST in
    // reverse inorder. Reverse inorder traversal of a BST gives us keys in decreasing order. Before visiting a node, we
    // visit all greater nodes of that node.
    //Since we are visiting the nodes in the decreasing order so all we need to care about maintaining the sum of the
    //nodes visited.
    //Keep a global variable which maintains the sum of the nodes visited till now.
    //Before updating the node value, store it in temp, we need it later
    //Updated the node value as sum ( since each node contains sum of nodes greater than that node which means we need to exclude the node value itself).
    //Update the sum as sum = sum + temp (for the next node in iteration).

    //This function traverses the tree in reverse inorder so that we have visited all greater key nodes of the currently
    //visited node
    public void convertToGreaterSumTree(TreeNode root){

        if (root == null){
            return;
        }

        //Visit right - descending order
        convertToGreaterSumTree(root.right);
        //store the node value in temp
        int temp = root.val;

        //update the sum, sum till previous visited node
        root.val = sum;

        //update the sum for the next node
        sum = sum + temp;

        //visit left
        convertToGreaterSumTree(root.left);

    }


    //Variation: https://leetcode.com/problems/binary-search-tree-to-greater-sum-tree/
    public TreeNode greaterSumTree(TreeNode root){

        if (root == null) {
            return null;
        }

        greaterSumTree(root.right);
        sum += root.val;
        root.val = sum;
        greaterSumTree(root.left);

        return root;
    }

    public static void main(String[] args) {
        GreaterSumTree tree = new GreaterSumTree();
        TreeNode root = tree.createBST();
        tree.inOrder(root);
        System.out.println();

        //tree.convertToGreaterSumTree(root);
        root = tree.greaterSumTree(root);
        tree.inOrder(root);
        System.out.println();
    }
}
