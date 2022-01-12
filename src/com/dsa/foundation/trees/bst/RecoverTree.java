package com.dsa.foundation.trees.bst;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//A binary search tree is a binary tree data structure where each node contains a key and it maintains some special properties:
//
//All the nodes in the left subtree of a node have keys less than the node's key
//All the nodes in the right subtree of a node have keys greater than the node's key.
//The left and right subtrees are also binary search trees

//Question: https://leetcode.com/problems/recover-binary-search-tree/submissions/

//Reference: https://www.tutorialcup.com/interview/tree/recover-binary-search-tree.htm#Algorithm-3

public class RecoverTree {
    private TreeNode insert(TreeNode root,int val){
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

    public TreeNode createBSTree(){

        /* create the incorrect BST*/
        TreeNode root = new TreeNode(4);
        root.left = new TreeNode(7);
        root.right = new TreeNode(6);
        root.left.left = new TreeNode(1);
        root.left.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(2);

        return root;
    }

    public void printLevelOrder(TreeNode root)
    {
        // Base Case
        if(root == null)
            return;

        // Create an empty queue for level order traversal
        Queue<TreeNode> q = new LinkedList<>();

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

    public void traverseInOrder(TreeNode node){

        if (node != null){
            traverseInOrder(node.left);
            System.out.print(node.val+" ");
            traverseInOrder(node.right);
        }
    }

    public TreeNode findNode(TreeNode root,int val){

        if (root == null){
            return null;
        }

        if (val < root.val){
            root = findNode(root.left,val);
        } else if (val > root.val ){
            root = findNode(root.right,val);
        }

        return root;
    }


    //Define prev(stores previous node in traversal), first(stores first node out of order), second(stores second node
    //out of order) variables to store various tree node addresses.
    //Perform simple inorder traversal.
    //If during inorder traversal, we find a node that violates the BST order. i.e. prev.val > root->val we store prev
    //into first and root into second.
    //During further traversal, if we find another node that violates the BST criteria,i.e. prev.val > root->data, but
    //this time the first node had been already assigned a value. so we assign the current node to second.
    //After execution of inorder traversal. swap the data of the first and second nodes. The tree is fixed.
    public TreeNode recoverTree(TreeNode root) {

        TreeNode prev,first,second;
        prev = first = second = null;

        TreeNode curr = root;

        Stack<TreeNode> st = new Stack<>();

        while(!st.isEmpty() ||  root != null)
        {
            while (root != null)
            {
                // visit current node's (root) left subtree
                st.push(root);
                root = root.left;
            }

            // done left subtree of current node
            root = st.pop();

            // compare root.val with prev.val if we have one
            if(prev != null && root.val <= prev.val)
            {
                //incorrect larger node is always found as current root node
                second = root;

                // incorrect smaller node is always found as prev node
                if(first == null) {
                    first = prev;
                } else break;

            }

            // visit root's right subtree
            prev = root;
            root = root.right;
        }

        // fix the BST by swapping values
        int temp = first.val;
        first.val = second.val;
        second.val = temp;

        return curr;
    }

    public static void main(String[] args) {
        RecoverTree rt = new RecoverTree();
        TreeNode root = rt.createBSTree();
        rt.traverseInOrder(root);
        System.out.println();

        root = rt.recoverTree(root);
        rt.traverseInOrder(root);
        System.out.println();
    }
}
