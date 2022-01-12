package com.dsa.foundation.trees.bt;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//Question: Given a Binary Tree, write an iterative function to print the Preorder traversal of the given binary tree.

//Reference: https://www.geeksforgeeks.org/iterative-preorder-traversal/

//To convert an inherently recursive procedure to iterative, we need an explicit stack. Following is a simple stack
//based iterative process to print Preorder traversal.
//1) Create an empty stack nodeStack and push root node to stack.
//2) Do the following while nodeStack is not empty.
//….a) Pop an item from the stack and print it.
//….b) Push right child of a popped item to stack
//….c) Push left child of a popped item to stack
//The right child is pushed before the left child to make sure that the left subtree is processed first.

public class IterativePreOrder {

    public TreeNode createBTree(){

         /* Construct the following tree
                1
              /   \
             /     \
            2       3
                  /   \
                 /     \
                5       6
              /   \
             /     \
            7       8
                  /   \
                 /     \
                9      10
        */

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(8);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(2);


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

    public void preOrder(TreeNode root){

        if (root != null){
            System.out.print(root.val+" ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    //1) Create an empty stack nodeStack and push root node to stack.
    //2) Do the following while nodeStack is not empty.
    //….a) Pop an item from the stack and print it.
    //….b) Push right child of a popped item to stack
    //….c) Push left child of a popped item to stack
    //The right child is pushed before the left child to make sure that the left subtree is processed first.
    public void iterativePreOrder(TreeNode root){

        if (root == null){
            return;
        }

        Stack<TreeNode> tNodeStack = new Stack<>();
        tNodeStack.add(root);

        while (!tNodeStack.isEmpty()){
            TreeNode tNode = tNodeStack.pop();
            System.out.print(tNode.val+" ");

            // Push right and left children of the popped node to stack
            if (tNode.right != null){
                tNodeStack.push(tNode.right);
            }

            if (tNode.left != null){
                tNodeStack.push(tNode.left);
            }
        }



    }

    public static void main(String[] args) {
        IterativePreOrder ipo = new IterativePreOrder();
        TreeNode root = ipo.createBTree();
        ipo.preOrder(root);
        System.out.println();
        ipo.iterativePreOrder(root);
    }
}
