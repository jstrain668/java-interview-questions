package com.dsa.foundation.trees.bst.construct;

//Question: https://leetcode.com/problems/construct-binary-search-tree-from-preorder-traversal/

//Reference: https://dev.to/chakrihacker/construct-binary-search-tree-from-preorder-traversal-leetcode-5chn
//So basically return a Binary Search Tree from an array of elements that are in preorder
//
//For a binary search tree at any given descendant of node.left < node.val and any descendant of node.right > node.val
//
//Algorithm:
//
//Loop through each element
//find the leaf node to insert
//repeat


import com.dsa.foundation.trees.bst.TreeNode;

public class ConstructBSTFromPreOrder {


    public void displayTree(TreeNode root){

        if (root != null){
            displayTree(root.left);
            System.out.print(root.val+" ");
            displayTree(root.right);
        }
    }

    private TreeNode constructBST(TreeNode root, int val){
        if (root == null){
            return new TreeNode(val);
        }

        if (val > root.val){
            root.right = constructBST(root.right,val);
        } else{
            root.left = constructBST(root.left,val);
        }
        return root;
    }

    public TreeNode bstFromPreorder(int[] preorder){
        int n = preorder.length;
        if (n <= 0){
            return null;
        }
        TreeNode root = new TreeNode(preorder[0]);

        for (int i = 1; i < preorder.length; i++){
            root = constructBST(root,preorder[i]);
        }
        return root;
    }

    public static void main(String[] args) {
        ConstructBSTFromPreOrder p = new ConstructBSTFromPreOrder();
        int[] preOrder = { 20, 10, 5, 1, 7, 15, 30, 25, 35, 32, 40 };
        TreeNode root = p.bstFromPreorder(preOrder);
        System.out.println("Inorder Traversal of Constructed Tree : ");
        p.displayTree(root);
        System.out.println();

        int[] preOrder2 = {8,5,1,7,10,12};
        root = p.bstFromPreorder(preOrder2);
        System.out.println("Inorder Traversal of Constructed Tree : ");
        p.displayTree(root);
        System.out.println();

        int[] preOrder3 = {4,2};
        root = p.bstFromPreorder(preOrder3);
        System.out.println("Inorder Traversal of Constructed Tree : ");
        p.displayTree(root);
        System.out.println();

    }

}
