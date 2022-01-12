package com.dsa.foundation.trees.bst.treetype;

import com.dsa.foundation.trees.bst.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

//Given a Binary Tree, the task is to check whether the given binary tree is Binary Search Tree or not.
//A binary search tree (BST) is a node-based binary tree data structure which has the following properties.
//
//The left subtree of a node contains only nodes with keys less than the node’s key.
//The right subtree of a node contains only nodes with keys greater than the node’s key.
//Both the left and right subtrees must also be binary search trees.

//Reference: https://www.geeksforgeeks.org/a-program-to-check-if-a-binary-tree-is-bst-or-not/

public class IsBST {

    TreeNode prev;
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

    private boolean isBST(TreeNode root,int min, int max){
        if (root == null){
            return true;
        }

        if (root.val < min || root.val > max){
            return false;
        }

        /* otherwise check the subtrees recursively
        tightening the min/max constraints */
        return (isBST(root.left,min,root.val-1) && isBST(root.right, root.val+1,max ));
    }

     /* can give min and max value according to your code or
    can write a function to find min and max value of tree. */

    /* returns true if given search tree is binary
     search tree (efficient version) */
    boolean isBST(TreeNode root)  {
        return isBST(root, Integer.MIN_VALUE,
                Integer.MAX_VALUE);
    }

    //Description: In order traversal using a prev pointer to track parent node to compare current value is less than
    // or equal to parent node value (prev)
    //Time Complexity: O(n)
    // Space Complexity: O(n) for the recursive calls
    /* Returns true if given search tree is binary
      search tree (efficient version) */
    public boolean isBSTAlt(TreeNode node)
    {
        // traverse the tree in inorder fashion and
        // keep a track of previous node
        if (node != null)
        {
            if (!isBST(node.left))
                return false;

            // allows only distinct values node
            if (prev != null && node.val <= prev.val ){
                return false;
            }

            prev = node;
            return isBST(node.right);
        }

        return true;
    }


    public static void main(String[] args) {
        IsBST tree = new IsBST();
        TreeNode root = tree.createBST();
        tree.printLevelOrder(root);
        System.out.println("Tree is balanced: "+tree.isBST(root));
        System.out.println("Tree is balanced (alt): "+tree.isBSTAlt(root));
        root.left.left.left = new TreeNode(50);
        System.out.println("Tree is balanced: "+tree.isBST(root));
        System.out.println("Tree is balanced (alt): "+tree.isBSTAlt(root));
    }
}
