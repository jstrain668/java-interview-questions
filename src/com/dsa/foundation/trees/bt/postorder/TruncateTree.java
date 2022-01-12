package com.dsa.foundation.trees.bt.postorder;

//Question: Remove all nodes which lie on path having sum less than k

//A node can be part of multiple paths. So, we have to delete it only if all paths from it have a sum less than k.

//Reference: https://www.techiedelight.com/truncate-given-binary-tree-remove-nodes-lie-path-sum-less-k/


import com.dsa.foundation.trees.bt.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class TruncateTree {

    public TreeNode createBTree(){

        TreeNode root = new TreeNode(6);
        root.left = new TreeNode(3);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(2);
        root.right.left.left = new TreeNode(1);
        root.right.left.right = new TreeNode(7);
        root.right.right.right = new TreeNode(3);

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

    public void inOrder(TreeNode root){
        if (root != null){
            inOrder(root.left);
            System.out.print(root.val+" ");
            inOrder(root.right);
        }
    }

    // The main function to truncate a given binary tree to remove nodes
    // that lie on a path having a sum less than `k`
    private TreeNode trunc(TreeNode root, int k, int target)
    {
        // base case: empty tree
        if (root == null) {
            return null;
        }

        // update sum of nodes in the path from the root node to the current node
        target += root.val;

        // Recursively truncate left and right subtrees
        root.left = trunc(root.left, k, target);
        root.right = trunc(root.right, k, target);

        // Since we are doing postorder traversal, the subtree rooted at the current
        // node may be already truncated, and the current node is a leaf

        // if the current node is a leaf node and its path from the root node has a sum
        // less than the required sum, remove it
        if (target < k && root.left == null && root.right == null)
        {
            // set the current node as null
            return null;
        }

        return root;
    }

    //The idea is to traverse the tree in a bottom-up fashion and truncate the left and right subtree before processing
    // its parent. Since we are doing a postorder traversal, the subtree rooted at the current node may be truncated,
    // and the current node becomes a leaf node now. So, for each node, check
    // If the sum of nodes in the path from the root node to the current node is more than or equal to k nothing needs
    // to be done.
    // If it is a leaf node and its path from the root node has a sum less than <code>k</code>, remove it.

    // Method to truncate a given binary tree to remove nodes which lie on
    // a path having sum less than `k`
    public TreeNode truncate(TreeNode root,int k){

        int target = 0;
        return trunc(root, k, target);
    }

    public static void main(String[] args) {
        TruncateTree tt = new TruncateTree();
        TreeNode root = tt.createBTree();
        tt.printLevelOrder(root);
        System.out.println();
        tt.inOrder(root);
        int k = 20;
        root = tt.truncate(root, k);
        System.out.println();
        tt.inOrder(root);

    }
}
