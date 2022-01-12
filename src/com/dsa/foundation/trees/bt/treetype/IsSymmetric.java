package com.dsa.foundation.trees.bt.treetype;

import com.dsa.foundation.trees.bt.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

//https://www.geeksforgeeks.org/symmetric-tree-tree-which-is-mirror-image-of-itself/

public class IsSymmetric {

    public TreeNode createBTree(){

        TreeNode root = new TreeNode(26);
        root.left = new TreeNode(10);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(7);

        return root;
    }

    public TreeNode createBTree2(){

        TreeNode root = new TreeNode(26);
        root.left = new TreeNode(10);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(7);

        return root;
    }

    public TreeNode createBTree3(){

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(4);
        root.right.left = new TreeNode(4);
        root.right.right = new TreeNode(3);

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

    // // For two trees to be mirror images, the following
    //        // three conditions must be true 1 - Their root
    //        // node's key must be same 2 - left subtree of left
    //        // tree and right subtree
    //        //      of right tree have to be mirror images
    //        // 3 - right subtree of left tree and left subtree
    //        //      of right tree have to be mirror images

    public boolean isSymmetric(TreeNode root1,TreeNode root2){

        if (root1 == null && root2 == null){
            return true;
        }

        if (root1 != null && root2 != null){
            return (root1.val == root2.val && isSymmetric(root1.left,root2.left)
                    && isSymmetric(root1.right,root2.right));
        }
        return false;
    }

    public boolean isSymmetric(TreeNode root){

        return isSymmetric(root,root);
    }

    public static void main(String[] args) {
        IsSymmetric tree = new IsSymmetric();
        TreeNode root1 = tree.createBTree();
        TreeNode root2 = tree.createBTree2();

        System.out.println("Tree 1 and Tree 2 are symmetric "+tree.isSymmetric(root1,root2));

        root2.right.right.right = new TreeNode(20);
        System.out.println("Tree 1 and Tree 2 are symmetric "+tree.isSymmetric(root1,root2));

        TreeNode root3 = tree.createBTree3();
        System.out.println("Tree 3 is symmetric "+tree.isSymmetric(root3));
    }

}
