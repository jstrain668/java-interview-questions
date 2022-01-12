package com.dsa.foundation.trees.bt;

import java.util.LinkedList;
import java.util.Queue;

//Question: Sum all the node values in the tree

public class SumTree {

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

    public int sumBTree(TreeNode root){

        if (root == null){
            return 0;
        }

        return root.val + sumBTree(root.left) + sumBTree(root.right);
    }

    public static void main(String[] args) {
        SumTree st = new SumTree();

        TreeNode root = st.createBTree();

        st.printLevelOrder(root);
        System.out.println("Sum of all node values in btree: "+st.sumBTree(root));
    }
}
