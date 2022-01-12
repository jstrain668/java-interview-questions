package com.dsa.medium.trees.heap;

import com.dsa.foundation.trees.bt.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

//https://www.techiedelight.com/convert-binary-search-tree-into-min-heap/


public class ConvertBSTToMinHeap {

    // Recursive function to insert a key into a BST
    public TreeNode insert(TreeNode root, int key)
    {
        // if the root is null, create a new node and return it
        if (root == null) {
            return new TreeNode(key);
        }

        // if the given key is less than the root node, recur for the left subtree
        if (key < root.val) {
            root.left = insert(root.left, key);
        }
        // if the given key is more than the root node, recur for the right subtree
        else {
            root.right = insert(root.right, key);
        }

        return root;
    }

    // Helper function to perform level order traversal on a binary tree
    public static void printLevelOrderTraversal(TreeNode root)
    {
        // base case: empty tree
        if (root == null) {
            return;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty())
        {
            int size = q.size();
            for (int i=0; i < size; i++) {

                TreeNode front = q.poll();
                System.out.print(front.val + " ");

                if (front.left != null) {
                    q.add(front.left);
                }

                if (front.right != null) {
                    q.add(front.right);
                }
            }

            System.out.println();
        }
    }

    // Function to perform inorder traversal on a given binary tree and
    // enqueue all nodes (in encountered order)
    public static void inorder(TreeNode root, Queue<Integer> keys)
    {
        if (root == null) {
            return;
        }

        inorder(root.left, keys);
        keys.add(root.val);
        inorder(root.right, keys);
    }

    // Function to perform preorder traversal on a given binary tree.
    // Assign each encountered node with the next key from the queue
    public void preorder(TreeNode root, Queue<Integer> keys)
    {
        // base case: empty tree
        if (root == null) {
            return;
        }

        // replace the root's key value with the next key from the queue
        root.val = keys.poll();

        // process left subtree
        preorder(root.left, keys);

        // process right subtree
        preorder(root.right, keys);
    }

    // Function to construct a complete binary tree from sorted keys in a queue
    public TreeNode construct(Queue<Integer> keys)
    {
        // construct a queue to store the parent nodes
        Queue<TreeNode> q = new LinkedList<>();

        // initialize the root node of the complete binary tree
        TreeNode root = new TreeNode(keys.poll());

        // enqueue root node
        q.add(root);

        // loop till all keys are processed
        while (!keys.isEmpty())
        {
            // dequeue front node
            TreeNode parent = q.poll();

            // allocate the left child of the parent node with the next key
            parent.left = new TreeNode(keys.poll());

            // enqueue left child node
            q.add(parent.left);

            // if the next key exists
            if (!keys.isEmpty())
            {
                // allocate the right child of the parent node with the next key
                parent.right = new TreeNode(keys.poll());

                // enqueue right child node
                q.add(parent.right);
            }
        }

        // return the root node of the complete binary tree
        return root;
    }

    // Function to convert a BST into a min-heap without using
    // any auxiliary space
    public TreeNode convertAlt(TreeNode root)
    {
        // base case
        if (root == null) {
            return null;
        }

        // maintain a queue to store inorder traversal on the tree
        Queue<Integer> keys = new ArrayDeque<>();

        // fill the queue in an inorder fashion
        inorder(root, keys);

        // construct a complete binary tree from sorted keys in the queue
        root = construct(keys);
        return root;
    }


    // Function to convert a BST into a min-heap
    //The time complexity of the above solution is O(n), where n is the size of the BST. The program also requires O(n)
    // extra space for the queue.

    //If the given BST is already a complete binary tree, the min-heap’s structural property is already satisfied, and
    //we need to take care of the only heap-ordering property of the min-heap. Basically, we need to ensure that each
    //node’s value is greater than its parent’s value, with the minimum element present at the root.
    //
    //The idea is to traverse the binary search tree in an inorder fashion and enqueue all encountered keys. Then
    //traverse the tree in a preorder fashion and for each encountered node, dequeue a key and assign it to the node.
    public void convert(TreeNode root)
    {
        // base case
        if (root == null) {
            return;
        }

        // maintain a queue to store inorder traversal on the tree
        Queue<Integer> keys = new LinkedList<>();

        // fill the queue in an inorder fashion
        inorder(root, keys);

        // traverse tree in preorder fashion, and for each encountered node,
        // dequeue a key and assign it to the node
        preorder(root, keys);
    }

    public TreeNode createBST(){
        int[] keys = { 5, 3, 2, 4, 8, 6, 10 };

        /* Construct the following BST
                   5
                 /   \
                /     \
               3       8
              / \     / \
             /   \   /   \
            2     4 6    10
        */

        TreeNode root = null;
        for (int key: keys) {
            root = insert(root, key);
        }
        return root;
    }

    public static void main(String[] args)
    {
        ConvertBSTToMinHeap bst = new ConvertBSTToMinHeap();

        int[] keys = { 5, 3, 2, 4, 8, 6, 10 };

        TreeNode root = bst.createBST();

        bst.convert(root);
        //bst.convertAlt(root) - when teh tree is not a complete binary tree
        bst.printLevelOrderTraversal(root);
    }
}
