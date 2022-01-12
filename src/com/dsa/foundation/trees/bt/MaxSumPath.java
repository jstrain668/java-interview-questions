package com.dsa.foundation.trees.bt;

//Question: https://leetcode.com/problems/binary-tree-maximum-path-sum/

//Reference: https://github.com/awangdev/LintCode/blob/master/Java/124.%20Binary%20Tree%20Maximum%20Path%20Sum.java
//Reference: https://www.educative.io/edpresso/the-algorithm-for-the-maximum-sum-in-a-binary-tree

//The maximum sum in a binary tree is the largest sum that can be found in a path which may start and end at any node
// in the tree.


public class MaxSumPath {

    int max = Integer.MIN_VALUE;

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

      //1. Use recursion to find the maximum sum in the left and right sub-trees.
      //2. Compute the maximum of the following four cases:
      //   - Value of root
      //   - Value of root + left sub-tree
      //   - Value of root + right sub-tree
      //   - Value of root + left + right sub-trees
      //3. If the maximum value (found in step 2) is greater than the global max variable, update the global maximum.
      //4. Return the maximum of the first three cases.
      //The value returned in the last step of the algorithm is the maximum value of only the first three cases
      // (mentioned in step 2). If the fourth case has the maximum value, then the root and its sub-trees are the top of
      // our max sum path (i.e., the root cannot be connected to its parent caller; otherwise, the path would start or end
      // at more than one node).


    public int maxPathSum(TreeNode root) {
        helper(root);
        return max;
    }

    // helper returns the max branch
    // plus current node's value
    int helper(TreeNode root) {
        if (root == null) {
            return 0;
        }

        int left = Math.max(helper(root.left), 0);
        int right = Math.max(helper(root.right), 0);

        max = Math.max(max, root.val + left + right);

        return root.val + Math.max(left, right);
    }

    public static void main(String[] args) {
        MaxSumPath tree = new MaxSumPath();

        System.out.println("Maximum possible sum in a path: " + tree.maxPathSum(tree.createBT()));
    }
}
