package com.dsa.foundation.trees.bt.convert;

//Given a Binary Tree (BT), convert it to a Doubly Linked List(DLL) In-Place. The left and right pointers in
//nodes are to be used as previous and next pointers respectively in converted DLL. The order of nodes in DLL
//must be same as Inorder of the given Binary Tree. The first node of Inorder traversal (left most node in BT)
//must be head node of the DLL.

//Reference: https://programmerall.com/article/98861307813/

import com.dsa.foundation.trees.bt.TreeNode;

public class BTreeToDLL {

    //The idea is to do in order traversal of the binary tree. While doing inorder traversal, keep track of the
    //previously visited node in a variable, say prev. For every visited node, make it next to the prev and
    // previous of this node as prev.
    TreeNode head, prev = null;

    public void binaryTree2DoubleLinkedList(TreeNode root) {

        if (root == null) {
            return;
        }
        // Recursively convert left subtree
        binaryTree2DoubleLinkedList(root.left);
        if (prev == null) {
            head = root;
        } else {
            root.left = prev;
            prev.right = root;
        }
        prev = root;
        // Finally convert right subtree
        binaryTree2DoubleLinkedList(root.right);
    }

    public void inOrder(TreeNode root){

        if (root != null){
            inOrder(root.left);
            System.out.print(root.val+" ");
            inOrder((root.right));
        }
    }

    public void printList(TreeNode node) {
        while (node != null) {
            System.out.print(node.val + " ");
            node = node.right;
        }
    }

    public TreeNode createBTree(){
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(12);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(25);
        root.left.right = new TreeNode(30);
        root.right.left = new TreeNode(36);

        return root;
    }

    public static void main(String[] args) {
        BTreeToDLL tree = new BTreeToDLL();
        TreeNode root = tree.createBTree();
        tree.inOrder(root);
        System.out.println();

        tree.binaryTree2DoubleLinkedList(root);
        tree.printList(tree.head);

        //tree.head = tree.binaryTreeToDDL(root);
        //tree.printList(tree.head);
    }

    public TreeNode binaryTreeToDDL(TreeNode root) {
        if (root == null)
            return null;

        TreeNode leftHead = binaryTreeToDDL(root.left);
        TreeNode rightHead = binaryTreeToDDL(root.right);

        TreeNode newHead = null;

        if (leftHead == null) {
            newHead = root;
        } else {
            TreeNode leftEnd = leftHead;
            while (leftEnd.right != null) {
                leftEnd = leftEnd.right;
            }

            leftEnd.right = root;
            root.left = leftEnd;
            newHead = leftHead;
        }

        if (rightHead != null) {
            rightHead.left = root;
            root.right = rightHead;
        }
        return newHead;
    }
}
