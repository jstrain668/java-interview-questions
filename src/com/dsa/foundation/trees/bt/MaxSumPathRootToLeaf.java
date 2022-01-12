package com.dsa.foundation.trees.bt;

//Question: https://leetcode.com/problems/binary-tree-maximum-path-sum/

//Reference: https://github.com/awangdev/LintCode/blob/master/Java/124.%20Binary%20Tree%20Maximum%20Path%20Sum.java
//Reference: https://www.educative.io/edpresso/the-algorithm-for-the-maximum-sum-in-a-binary-tree

//The maximum sum in a binary tree is the largest sum that can be found in a path which may start and end at any node
// in the tree.

public class MaxSumPathRootToLeaf {

    public TreeNode createBT(){
		/*
		        3
		       / \
		      2   20
		     /   /  \
		    7   5   -8

		*/
        TreeNode root = new TreeNode(3);

        // Creating 2nd level:
        TreeNode one = new TreeNode(2);
        TreeNode two = new TreeNode(20);
        root.left = one;
        root.right = two;

        // Creating 3rd level:
        TreeNode three = new TreeNode(7);
        TreeNode four = new TreeNode(5);
        TreeNode five = new TreeNode(-8);
        one.left = three;
        two.left = four;
        two.right = five;

        return root;
    }

    // Function to print the root-to-leaf path with a given sum in a binary tree
    public boolean printPath (TreeNode root, int sum)
    {
        // base case
        if (sum == 0) {
            return true;
        }

        // base case
        if (root == null) {
            return false;
        }

        // recur for the left and right subtree with reduced sum
        boolean left = printPath(root.left, sum - root.val);
        boolean right = printPath(root.right, sum - root.val);

        // print the current node if it lies on a path with a given sum
        if (left || right) {
            System.out.print(root.val + " ");
        }

        return left || right;
    }

    // Function to calculate the maximum root-to-leaf sum in a binary tree
    public int getRootToLeafSum(TreeNode root)
    {
        // base case: tree is empty
        if (root == null) {
            return 0;
        }

        // calculate the maximum node-to-leaf sum for the left child
        int left = getRootToLeafSum(root.left);

        // calculate the maximum node-to-leaf sum for the right child
        int right = getRootToLeafSum(root.right);

        // consider the maximum sum child
        return (left > right? left : right) + root.val;
    }


    //The time complexity of the above solution is O(n) and requires O(n) extra space (system stack - recursive calls)
    // where n is the number of nodes in the binary tree
    // Function to print maximum sum root-to-leaf path in a given binary tree
    public void findMaxSumPath(TreeNode root)
    {
        int sum = getRootToLeafSum(root);
        System.out.println("The maximum sum is " + sum);
        System.out.print("The maximum sum path is ");

        printPath(root, sum);
    }

    public static void main(String[] args) {

        MaxSumPathRootToLeaf tree = new MaxSumPathRootToLeaf();
        tree.findMaxSumPath(tree.createBT());
    }
}
