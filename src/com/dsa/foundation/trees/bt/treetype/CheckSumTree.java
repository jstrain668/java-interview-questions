package com.dsa.foundation.trees.bt.treetype;

import com.dsa.foundation.trees.bt.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

//Question: https://www.codespeedy.com/check-if-a-given-binary-tree-is-sumtree-in-java/

public class CheckSumTree {

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

    private int sumValue(TreeNode node){
        // If empty it is 0;
        if (node == null) {
            return 0;
        }

        return sumValue(node.left) + node.val + sumValue(node.right);
    }

    public boolean isCheckSumBTree(TreeNode root)
    {
        if ((root == null) || (root.left == null && root.right == null)){
            return true;
        }

        //Sum up the left and right subtree
        int leftSum = sumValue(root.left);
        int rightSum = sumValue(root.right);

        return root.val == leftSum+rightSum && isCheckSumBTree(root.left) && isCheckSumBTree(root.right);
    }


    public static void main(String[] args) {
        CheckSumTree cst = new CheckSumTree();
        TreeNode root = cst.createBTree();

        cst.printLevelOrder(root);
        System.out.println("Binary tree is a check sum btree: "+cst.isCheckSumBTree(root));

    }
}
