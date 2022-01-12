package com.dsa.medium.trees.bt;

//Question: https://leetcode.com/problems/path-sum/


import java.util.LinkedList;
import java.util.Queue;

public class PathSum {

    public TreeNode createBTree(){

        TreeNode root = new TreeNode(5);
        root.left = new TreeNode(4);
        root.right = new TreeNode(8);
        root.right.left = new TreeNode(13);
        root.right.right = new TreeNode(4);
        root.right.right.right = new TreeNode(1);
        root.left.left = new TreeNode(11);
        root.left.left.left = new TreeNode(7);
        root.left.left.right = new TreeNode(2);

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

    public boolean pathSumExists(TreeNode root,int sum){
        if (root == null){
            return false;
        }

        if (root.val == sum && root.left == null && root.right == null){
            return true;
        }

        return pathSumExists(root.left,sum - root.val) || pathSumExists(root.right, sum - root.val);
    }

    public static void main(String[] args) {
        PathSum tree = new PathSum();
        TreeNode root = tree.createBTree();
        tree.printLevelOrder(root);
        int sum = 22;

        System.out.println("Path sum = "+sum+ " exists: "+tree.pathSumExists(root,sum));
    }
}
