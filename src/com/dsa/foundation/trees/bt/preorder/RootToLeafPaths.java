package com.dsa.foundation.trees.bt.preorder;

import com.dsa.foundation.trees.bt.TreeNode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

//Reference: https://www.techiedelight.com/print-all-paths-from-root-to-leaf-nodes-binary-tree/

public class RootToLeafPaths {

    public TreeNode createBTree(){

        /* Construct the following tree
                  10
                /   \
               /     \
              8       2
             / \     /
            3   5   7
        */

        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(8);
        root.right = new TreeNode(2);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(7);

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

    private void printList(List<Integer> list){
        for (int i : list){
            System.out.print(i+" ");
        }
        System.out.println();
    }

    public void printRootToLeafPaths(TreeNode root,List<Integer> list){
        if (root == null){
            return;
        }

        list.add(root.val);

        if (root.left == null && root.right == null){
            printList(list);
        }

        printRootToLeafPaths(root.left,list);
        printRootToLeafPaths(root.right,list);
        list.remove(list.size()-1);
    }

    public static void main(String[] args) {
        RootToLeafPaths tree = new RootToLeafPaths();
        TreeNode root = tree.createBTree();
        tree.printLevelOrder(root);

        System.out.println("Root to Leaf Paths: ");
        List<Integer> list = new ArrayList<>();
        tree.printRootToLeafPaths(root,list);

    }
}
