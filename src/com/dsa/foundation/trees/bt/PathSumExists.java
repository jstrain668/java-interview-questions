package com.dsa.foundation.trees.bt;

import java.util.LinkedList;
import java.util.Queue;

public class PathSumExists {
    public TreeNode createBTree(){

        TreeNode root = new TreeNode(26);
        root.left = new TreeNode(10);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
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

    /*
     Given a tree and a sum, return true if there is a path from the root
     down to a leaf, such that adding up all the values along the path
     equals the given sum.

     Strategy: subtract the node value from the sum when recurring down,
     and check to see if the sum is 0 when you run out of tree.
     */
    public boolean pathSumExists(TreeNode root,int sum){

        if (root == null) {
            return false;
        }

        if (root.val == sum && (root.left == null && root.right == null)) {
            return true;
        }

        return pathSumExists(root.left, sum - root.val)
                || pathSumExists(root.right, sum - root.val);
    }
    public static void main(String[] args) {
        PathSumExists pse = new PathSumExists();

        TreeNode root = pse.createBTree();

        pse.printLevelOrder(root);
        int sum = 32;
        System.out.println("There is a path of nodes that sum to "+sum+ ": "+pse.pathSumExists(root,sum));
    }
}
