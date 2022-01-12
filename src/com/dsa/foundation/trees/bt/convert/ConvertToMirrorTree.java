package com.dsa.foundation.trees.bt.convert;

import com.dsa.foundation.trees.bt.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class ConvertToMirrorTree {

    public TreeNode createBTree(){

        /* Construct the following tree
                  1
                /   \
               /     \
              2       3
             / \     / \
            4   5   6   7
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

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

    // Function to perform preorder traversal on a given binary tree
    public void preorder(TreeNode root)
    {
        if (root != null) {
            System.out.print(root.val+ " ");
            preorder(root.left);
            preorder(root.right);
        }
    }

    private void swap(TreeNode root){

        TreeNode temp = root.left;
        root.left = root.right;
        root.right = temp;

    }

    //The idea is to traverse the tree in postorder fashion and for every node we swap its left and right child pointer
    // after recursively converting its left subtree and right subtree first to mirror
    public void convertToMirror(TreeNode root){

        if (root == null){
            return;
        }

        convertToMirror(root.left);
        convertToMirror(root.right);
        swap(root);
    }

    public static void main(String[] args) {
        ConvertToMirrorTree mt = new ConvertToMirrorTree();

        TreeNode root = mt.createBTree();
        mt.printLevelOrder(root);
        System.out.println("Preorder: ");
        mt.preorder(root);
        System.out.println();
        System.out.println("Convert to mirror image: ");
        mt.convertToMirror(root);
        mt.printLevelOrder(root);
        System.out.println("Preorder: ");
        mt.preorder(root);

    }
}
