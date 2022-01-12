package com.dsa.foundation.trees.bst;


import java.util.LinkedList;
import java.util.Queue;

//In Binary Tree, Inorder successor of a node is the next node in Inorder traversal of the Binary Tree. Inorder
// Successor is NULL for the last node in Inorder traversal.
// In Binary Search Tree, Inorder Successor of an input node can also be defined as the node with the smallest key
// greater than the key of the input node.

//Question: Given a binary search tree and a node in it, find the in-order successor of that node in the BST.
//https://www.lintcode.com/problem/inorder-successor-in-bst/description

//Reference: https://algorithms.tutorialhorizon.com/inorder-successor-in-binary-search-tree-without-using-parent-link/

public class InOrderSuccessor {

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

        TreeNode root = insert(null,30);
        root = insert(root,15);
        root = insert(root,43);
        root = insert(root,7);
        root = insert(root,20);
        root = insert(root,35);
        root = insert(root,50);
        root = insert(root,18);
        root = insert(root,45);
        root = insert(root,17);
        root = insert(root,19);

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

    public TreeNode leftMostTreeNode(TreeNode node){

        while(node.left != null){
            node = node.left;
        }
        return node;
    }

    //There will be 3 cases to solve this problem.Say the node for which inorder successor needs to be found is x.
    //Case 1 : If the x has a right child then its inorder successor will the left most element in the right sub tree
    // of x.
    //Case 2: If the x doesn’t have a right child then its inorder successor will the one of its ancestors, use the
    // binary search technique to find the node x, start from the root, if root is bigger than the x then go left, if
    // root is less than x, go right. while traveling whenever you go left , store the node and call it successor.
    //Case 3: if x is the right most node in the tree then its inorder successor will be NULL.

    public TreeNode findInOrderSuccessor(TreeNode root,TreeNode node){

        // Case 1: if the right child of node is not null then in-Order successor will the left most node in
        // the right sub tree.
        if (node.right != null){
            return leftMostTreeNode(node.right);
        }

        //Case 2: No right child then its inorder successor will be one of its ancestors, use binary search to find the
        // node, start from the root, if root is bigger than the x then go left, if root is less than x, go right.
        // While traveling whenever you go left , store the node and call it successor.
        TreeNode successor = null;
        while (root != null){
            if (root.val > node.val){
                successor = root;
                root = root.left;
            }else if (root.val < node.val){
                root = root.right;
            }else{
                return successor;
            }
        }

        //Case 3: The 'node' is the rightmost node then it doesn't have a successor
        return null;
    }

    public static void main(String[] args) {
        InOrderSuccessor ios = new InOrderSuccessor();
        TreeNode root = ios.createBSTree();
        ios.printLevelOrder(root);
        ios.traverseInOrder(root);
        System.out.println();

        TreeNode a = ios.findNode(root,7);
        TreeNode x = ios.findInOrderSuccessor(root, a);
        if(x!=null){
            System.out.println("InOrder Successor of " + a.val+ " is " + x.val);
        }else{
            System.out.println("InOrder Successor of " + a.val + " is NULL");
        }

        x = ios.findInOrderSuccessor(root, root);
        if(x!=null){
            System.out.println("InOrder Successor of " + root.val + " is " + x.val);
        }else{
            System.out.println("InOrder Successor of " + root.val + " is NULL");
        }

    }

}
