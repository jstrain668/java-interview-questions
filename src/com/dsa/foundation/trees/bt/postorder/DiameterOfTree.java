package com.dsa.foundation.trees.bt.postorder;

//Question: Given a binary tree, write an algorithm to find the diameter of the tree.
//https://leetcode.com/problems/diameter-of-binary-tree/

//What is Diameter Of a Tree: Diameter of tree is defined as the longest path or route between any two nodes in a tree.
//The path may or may not go through the root.

//Diameter of a tree w.r.t root can be defined as
//
//Maximum(Diameter of left subtree, Diameter of right subtree, Longest path between two nodes which passes through the
// root.) The diameter of left and right subtree’s can be solved recursively. And Longest path between two nodes which
// passes through the root can be calculated as 1 + height of left subtree + height of right subtree.

import com.dsa.foundation.trees.bt.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class DiameterOfTree {

    int maxDiameter = 0;

    public TreeNode createBTree(){

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.left.right.left = new TreeNode(6);
        root.left.right.left.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);

        return root;
    }

    public TreeNode createBTree2(){

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        return root;
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

    //Return height of a subtree
    private int getHeight(TreeNode root){
        if (root == null){
            return 0;
        }

        return (1 + Math.max(getHeight(root.left),getHeight(root.right)));
    }

    //Execute pre-order traversal
    //Find the height of left subtree.
    //Find the height of right subtree.
    //Find the left diameter.
    //Find the right diameter.
    //Return the Maximum(Diameter of left subtree, Diameter of right subtree, Longest path between two nodes which passes through the root.)

    public int diameterOfBinaryTree(TreeNode root) {
        maxDiameter = 0;
        maxDepth(root);
        return maxDiameter;
    }

    private int maxDepth(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = maxDepth(root.left);
        int right = maxDepth(root.right);

        // For https://algorithms.tutorialhorizon.com/diameter-of-a-binary-tree/
        //maxDiameter = Math.max(maxDiameter, left + right +1);
        //For Leetcode
        maxDiameter = Math.max(maxDiameter, left + right);

        return Math.max(left, right) + 1;
    }

    public static void main(String[] args) {
        DiameterOfTree tree = new DiameterOfTree();
        TreeNode root = tree.createBTree2();
        tree.printLevelOrder(root);
        System.out.println("Diameter of Tree: " + tree.diameterOfBinaryTree(root));

        TreeNode root1 = tree.createBTree();
        tree.printLevelOrder(root1);
        System.out.println("Diameter of Tree: " + tree.diameterOfBinaryTree(root1));

    }
}
