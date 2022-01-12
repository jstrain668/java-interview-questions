package com.dsa.foundation.trees.bst.treetype;

//Question: Given a binary tree, Find whether if a Given Binary Tree is Balanced?

//What is balanced Tree: A balanced tree is a tree in which difference between heights of sub-trees of any node in the
// tree is not greater than one.

//Reference: https://takeuforward.org/data-structure/check-if-the-binary-tree-is-balanced-binary-tree/


import com.dsa.foundation.trees.bst.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class IsBalanced {

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

    //So, the idea is to use post-order traversal. Since, in postorder traversal, we first traverse the left and
    //right subtrees and then visit the parent node, similarly instead of calculating the height of the left
    //subtree and right subtree every time at the root node, use post-order traversal, and keep calculating the
    //heights of the left and right subtrees and perform the validation.

    public boolean isBalanced(TreeNode root) {
        return getHeight(root) != -1;
    }

    //Start traversing the tree recursively and do work in Post Order.
    //For each call, calculate the height of the root node, and return it to previous calls.
    //Simultaneously, in the Post Order of every node , Check for condition of balance as information of left
    //and right subtree height is available.
    //If it is balanced , simply return height of current node and if not then return -1.
    //Whenever the subtree result is -1 , simply keep on returning -1.

    private int getHeight (TreeNode root) {
        if (root == null) return 0;

        int leftHeight = getHeight (root.left);
        if (leftHeight == -1) {
            return -1;
        }

        int rightHeight = getHeight (root.right);
        if (rightHeight == -1) {
            return -1;
        }

        if (Math.abs(leftHeight - rightHeight) > 1){
            return -1;
        }

        return Math.max(leftHeight, rightHeight) + 1;
    }


    public static void main(String[] args) {
        IsBalanced tree = new IsBalanced();
        TreeNode root = tree.createBST();
        tree.printLevelOrder(root);
        System.out.println("Tree is balanced: "+tree.isBalanced(root));
        root = tree.insert(root,40);
        root = tree.insert(root,45);
        System.out.println("Tree is balanced: "+tree.isBalanced(root));

    }
}
