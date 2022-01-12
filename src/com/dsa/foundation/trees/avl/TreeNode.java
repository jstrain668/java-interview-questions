package com.dsa.foundation.trees.avl;

public class TreeNode {

    int data;
    TreeNode left;
    TreeNode right;
    int height;

    public TreeNode(int data) {
        this.data = data;
        height = 1;
    }
}
