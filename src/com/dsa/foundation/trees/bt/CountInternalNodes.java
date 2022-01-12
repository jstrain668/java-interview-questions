package com.dsa.foundation.trees.bt;

//Question: Calculate the number of internal nodes in the btree

//Nodes which are not leaves are called internal nodes

public class CountInternalNodes {

    public TreeNode createBTree(){
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

        return root;
    }

    public int countInternalNodes(TreeNode root){

        if (root == null || (root.left == null && root.right == null)){
            return 0;
        }

        return countInternalNodes(root.left) + countInternalNodes(root.right) + 1;
    }

    public static void main(String[] args) {
        CountInternalNodes tree = new CountInternalNodes();
        TreeNode root = tree.createBTree();

        System.out.println("Number of internal nodes "+tree.countInternalNodes(root));
    }
}
