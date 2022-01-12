package com.dsa.foundation.trees.bst;

import java.util.LinkedList;
import java.util.Queue;

public class RangeSum {

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

    public int rangeSumBST(TreeNode root, int low, int high) {
        if (root == null){
            return 0;
        }

        int sum = 0;
        if (root.val >= low && root.val <= high){
            sum = root.val;
        }

        if (root.val >= low) {
            sum += rangeSumBST(root.left, low, high);
        }

        if (root.val <= high){
            sum += rangeSumBST(root.right,low,high);
        }

        return sum;
    }

    public static void main(String[] args) {
        RangeSum bst = new RangeSum();
        TreeNode root = bst.createBSTree();
        bst.printLevelOrder(root);
        bst.traverseInOrder(root);
        System.out.println();
        System.out.println("Sum range for 18 to 30 inclusive: "+bst.rangeSumBST(root,18,30));

    }
}
