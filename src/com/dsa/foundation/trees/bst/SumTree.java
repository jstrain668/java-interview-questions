package com.dsa.foundation.trees.bst;

public class SumTree {

    public class TreeNode{
        int val;
        TreeNode left;
        TreeNode right;

        public TreeNode(int val){
            this.val = val;
            left = null;
            right = null;
        }
    }

    //Time Complexity for recursive function is O(branches^depth). Since 2 branches its its O(2^depth)
    //What is depth? Since the tree is a balanced binary search tree. Therefore, if there are N total nodes, then depth
    //is roughly log N. By the equation we get O(2^logN) which equals O(N)
    //Space Complexity: O(N)
    public int sum(TreeNode root){
        if (root == null){
            return 0;
        }

        return sum(root.left) + root.val + sum(root.right);
    }
}
