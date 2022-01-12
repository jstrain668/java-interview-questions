package com.dsa.foundation.trees.bt.postorder;

import com.dsa.foundation.trees.bt.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

//Question: Given A binary Tree, how do you remove all the half nodes (which has only one child)? Note leaves should not
// be touched as they have both children as NULL.

//Reference: https://www.geeksforgeeks.org/given-a-binary-tree-how-do-you-remove-all-the-half-nodes/

public class RemoveHalfNodes {

    public TreeNode createBTree(){

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(7);
        root.right = new TreeNode(5);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(1);
        root.left.right.right = new TreeNode(11);
        root.right.right = new TreeNode(9);
        root.right.right.left = new TreeNode(4);

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

    //The idea is to use post-order traversal to solve this problem efficiently. We first process the left children,
    //then right children, and finally the node itself. So we form the new tree bottom up, starting from the leaves
    //towards the root. By the time we process the current node, both its left and right subtrees were already processed.
    public TreeNode removeHalfNodes(TreeNode root){

        if (root == null){
            return null;
        }

        root.left = removeHalfNodes(root.left);
        root.right = removeHalfNodes(root.right);

        //leaf node - no change
        if (root.left == null && root.right == null) {
            return root;
        }

        /* if current nodes is a half node with left
         child NULL left, then it's right child is
         returned and replaces it in the given tree */
        if (root.left == null)
        {
            //TreeNode new_root = root.right;
            //return new_root;
            return root.right;
        }

        /* if current nodes is a half node with right
           child NULL right, then it's right child is
           returned and replaces it in the given tree  */
        if (root.right == null)
        {
            //TreeNode new_root = root.left;
            //return new_root;
            return root.left;
        }

        //full node - no change
        return root;
    }

    public static void main(String[] args) {
        RemoveHalfNodes rhn = new RemoveHalfNodes();
        TreeNode root = rhn.createBTree();
        System.out.println("Inorder: ");
        rhn.inOrder(root);
        System.out.println();

        root = rhn.removeHalfNodes(root);
        System.out.println("Inorder: ");
        rhn.inOrder(root); // 1 6 11 2 4
        System.out.println();


    }
}
