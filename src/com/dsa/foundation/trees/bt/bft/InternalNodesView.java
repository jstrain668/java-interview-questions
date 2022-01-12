package com.dsa.foundation.trees.bt.bft;

import com.dsa.foundation.trees.bt.TreeNode;

import javax.swing.text.View;
import java.util.LinkedList;
import java.util.Queue;

public class InternalNodesView {

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

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.left.right.left = new TreeNode(9);
        root.right.left.right.right = new TreeNode(10);

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

    // Function to print all internal nodes
    // in level order from left to right
    public void printInternalNodes(TreeNode root)
    {
        // Using a queue for a level order traversal
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty())
        {

            // Check and pop the element in
            // the front of the queue
            TreeNode curr = q.poll();

            // The variable flag keeps track of
            // whether a node is an internal node
            boolean isInternal = false;

            // The node has a left child
            if (curr.left != null)
            {
                isInternal = true;
                q.add(curr.left);
            }

            // The node has a right child
            if (curr.right != null)
            {
                isInternal = true;
                q.add(curr.right);
            }

            // In case the node has either a left
            // or right child or both print the data
            if (isInternal == true)
                System.out.print(curr.val + " ");
        }
    }

    public static void main(String[] args) {
        InternalNodesView tree = new InternalNodesView();
        TreeNode root = tree.createBTree();
        tree.printLevelOrder(root);

        System.out.println("Internal nodes of a tree by level: ");
        tree.printInternalNodes(root);
        System.out.println();


    }
}
