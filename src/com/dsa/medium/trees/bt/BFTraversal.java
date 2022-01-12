package com.dsa.medium.trees.bt;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Question: https://leetcode.com/problems/binary-tree-level-order-traversal/

public class BFTraversal {

    public TreeNode createBT()
    {

        //                     20
        //                  /      \
        //                8         22
        //               /  \
        //              4    12
        //                  /   \
        //                 10    14

        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(8);
        root.right = new TreeNode(22);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(12);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(14);

        return root;
    }

    // A simple inorder traversal used for testing the
    // constructed tree
    public void inorder(TreeNode root)
    {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }
    }

    public List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> listOfLists = new ArrayList<List<Integer>>();
        if (root == null){
            return listOfLists;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()){
            int size = q.size();
            List<Integer> list = new ArrayList<>();

            for (int i=0; i < size; i++){
                TreeNode curr = q.poll();

                list.add(curr.val);

                if (curr.left != null){
                    q.add(curr.left);
                }

                if (curr.right != null){
                    q.add(curr.right);
                }
            }

            listOfLists.add(list);
        }
        return listOfLists;
    }

    public static void main(String[] args) {
        BFTraversal tree = new BFTraversal();
        TreeNode root = tree.createBT();
        System.out.println("Inorder traversal:");
        tree.inorder(root);
        System.out.println();

        List<List<Integer>> lists = tree.levelOrder(root);
        System.out.println("Level order");
        for (List<Integer> list : lists){
            System.out.println(list.toString());
        }
    }
}
