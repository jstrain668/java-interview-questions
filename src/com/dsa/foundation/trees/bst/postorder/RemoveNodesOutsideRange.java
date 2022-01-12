package com.dsa.foundation.trees.bst.postorder;

//Question: Remove the nodes of binary search tree which are outside the given range

//Reference: https://www.geeksforgeeks.org/remove-bst-keys-outside-the-given-range/

import com.dsa.foundation.trees.bst.TreeNode;

public class RemoveNodesOutsideRange {

    public TreeNode insert(TreeNode root, int val){

        if (root == null){
            return new TreeNode(val);
        }

        if (val < root.val){
            root.left = insert(root.left,val);
        } else {
            root.right = insert(root.right,val);
        }

        return root;
    }

    public TreeNode createBST(){
        TreeNode root = null;
        root = insert(root, 6);
        root = insert(root, -13);
        root = insert(root, 14);
        root = insert(root, -8);
        root = insert(root, 15);
        root = insert(root, 13);
        root = insert(root, 7);

        return root;
    }

    public void inOrder(TreeNode root){
        if (root != null){
            inOrder(root.left);
            System.out.print(root.val+" ");
            inOrder(root.right);
        }
    }

    //There are two possible cases for every node.
    //        1) Node’s key is outside the given range. This case has two sub-cases.
    //          a) Node’s key is smaller than the min value.
    //          b) Node’s key is greater than the max value.
    //        2) Node’s key is in range.
    //We don’t need to do anything for case 2. In case 1, we need to remove the node and change the root of the subtree
    //rooted with this node.The idea is to fix the tree in a Post-order fashion. When we visit a node, we make sure
    //that its left and right sub-trees are already fixed.
    // In case 1.a), we remove the root and return the right sub-tree as a new root.
    // In case 1.b), we remove the root and return the left sub-tree as a new root.

    // Removes all nodes having value outside the given range and return the root of modified tree
    private TreeNode removeOutsideRange(TreeNode root,int min,int max)
    {
        // BASE CASE
        if(root == null)
        {
            return null;
        }

        // FIRST FIX THE LEFT AND
        // RIGHT SUBTREE OF ROOT
        root.left = removeOutsideRange(root.left, min, max);
        root.right = removeOutsideRange(root.right, min, max);

        // NOW FIX THE ROOT. THERE ARE
        // TWO POSSIBLE CASES FOR THE ROOT
        // 1. a) Root's key is smaller than
        // min value(root is not in range)
        if(root.val < min)
        {
            TreeNode rChild = root.right;
            root = null;
            return rChild;
        }

        // 1. b) Root's key is greater than
        // max value (Root is not in range)
        if(root.val > max)
        {
            TreeNode lChild = root.left;
            root = null;
            return lChild;
        }

        // 2. Root in range
        return root;
    }

    public static void main(String[] args) {
        RemoveNodesOutsideRange rnor  = new RemoveNodesOutsideRange();
        TreeNode root = rnor.createBST();
        rnor.inOrder(root);
        System.out.println();
        rnor.removeOutsideRange(root,-10,13);
        rnor.inOrder(root);
        System.out.println();


    }

}
