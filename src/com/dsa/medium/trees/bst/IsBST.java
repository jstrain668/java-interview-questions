package com.dsa.medium.trees.bst;

import java.util.LinkedList;
import java.util.Queue;

//Reference: https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/

//A binary search tree (BST) is a node based binary tree data structure which has the following properties.
//
//The left subtree of a node contains only nodes with keys less than the node’s key.
//The right subtree of a node contains only nodes with keys greater than the node’s key.
//Both the left and right subtrees must also be binary search trees.

public class IsBST {

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
        TreeNode root = insert(null,20);
        root = insert(root,10);
        root = insert(root,15);
        root = insert(root,5);
        root = insert(root,30);
        root = insert(root,25);
        root = insert(root,35);

        return root;
    }

    public void inOrder(TreeNode root){

        if (root != null){
            inOrder(root.left);
            System.out.print(root.val+" ");
            inOrder(root.right);
        }
    }

    public void printLevelOrder(TreeNode root)
    {
        // Base Case
        if(root == null)
            return;

        // Create an empty queue for level order traversal
        Queue<TreeNode> q =new LinkedList<>();

        // Enqueue Root and initialize height
        q.add(root);

        while(!q.isEmpty())
        {
            int size = q.size();
            for (int i =0; i < size; i++)
            {
                TreeNode node = q.poll();
                System.out.print(node.val + " ");

                if(node.left != null)
                    q.add(node.left);
                if(node.right != null)
                    q.add(node.right);
            }
            System.out.println();
        }
    }

    private boolean isBST(TreeNode root, TreeNode left, TreeNode right){
        if (root == null){
            return true;
        }

        // if left node exist then check it has
        // correct data or not i.e. left node's data
        // should be less than root's data
        if (left != null && root.val < left.val){
            return false;
        }

        // if right node exist then check it has
        // correct data or not i.e. right node's data
        // should be greater than root's data
        if (right != null && root.val > right.val){
            return false;
        }

        /* otherwise check the subtrees recursively
        tightening the min/max constraints */
        return (isBST(root.left,left,root) && isBST(root.right, root,right));
    }

     /* can give min and max value according to your code or
    can write a function to find min and max value of tree. */

    /* returns true if given search tree is binary
     search tree (efficient version) */
    boolean isBST(TreeNode root)  {
        return isBST(root, null, null);
    }

    public static void main(String[] args) {
        IsBST tree = new IsBST();
        TreeNode root = tree.createBST();
        tree.printLevelOrder(root);
        System.out.println("Tree is balanced: "+tree.isBST(root));
        root.left.left.left = new TreeNode(50);
        System.out.println("Tree is balanced: "+tree.isBST(root));

    }
}
