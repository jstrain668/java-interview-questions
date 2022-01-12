package com.dsa.foundation.trees.bst;

//Question: Given a binary search tree and a target node K. The task is to find the node with minimum absolute
// difference with given target value K.

//Reference: http://lixinchengdu.github.io/algorithmbook/leetcode/closest-binary-search-tree-value.html

import java.util.LinkedList;
import java.util.Queue;

public class ClosestValue {

    public TreeNode createBST(){
        TreeNode root = new TreeNode(9);
        root.left = new TreeNode(4);
        root.right   = new TreeNode(17);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(6);
        root.left.right.left = new TreeNode(5);
        root.left.right.right = new TreeNode(7);
        root.right.right = new TreeNode(22);
        root.right.right.left = new TreeNode(20);

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

    public int closestValue(TreeNode root, double target){
        int ret = root.val;
        if (root.left != null) {
            int leftClose = closestValue(root.left, target);
            if (Math.abs(ret - target) > Math.abs(leftClose - target)) {
                ret = leftClose;
            }
        }

        if (root.right != null) {
            int rightClose = closestValue(root.right, target);
            if (Math.abs(ret - target) > Math.abs(rightClose - target)) {
                ret = rightClose;
            }
        }

        return ret;
    }

    public int findClosestValue(TreeNode node,double target) {
        TreeNode currentNode = node;
        int closestValue = currentNode.val;
        double minDiff = Double.MAX_VALUE;

        while(currentNode != null) {
            double currentDiff = Math.abs(target - currentNode.val);
            if(currentDiff < minDiff) {
                minDiff = currentDiff;
                closestValue = currentNode.val;
            }
            if(target < currentNode.val) {
                currentNode = currentNode.left;
            } else if (target > currentNode.val) {
                currentNode = currentNode.right;
            } else {
                break;
            }
        }
        return closestValue;
    }

    public static void main(String[] args) {
        ClosestValue bst = new ClosestValue();
        TreeNode root = bst.createBST();

        bst.printLevelOrder(root);
        double value = 13.7123d;
        System.out.println("Closest value to "+value+ " is: "+bst.closestValue(root,value));
        System.out.println("Closest value to "+value+ " is: "+bst.findClosestValue(root,value));
    }
}
