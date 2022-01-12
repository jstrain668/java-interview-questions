package com.dsa.foundation.trees.bt;

import java.util.LinkedList;
import java.util.Queue;

public class CheckValueExists {

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

    public boolean checkValueExists(TreeNode root,int val){

        if (root == null){
            return false;
        }

        if (root.val == val){
            return true;
        }

        return checkValueExists(root.left, val) || checkValueExists(root.right, val);
    }

    public static void main(String[] args) {
        CheckValueExists cve = new CheckValueExists();
        TreeNode root = cve.createBTree();

        cve.printLevelOrder(root);
        int val = 25;
        System.out.println("Does value "+val+" exist in btree: "+cve.checkValueExists(root,val));
    }
}
