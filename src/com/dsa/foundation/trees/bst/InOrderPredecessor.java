package com.dsa.foundation.trees.bst;

import java.util.LinkedList;
import java.util.Queue;

public class InOrderPredecessor {

    public TreeNode createBT(){
        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(10);
        root.right = new TreeNode(30);
        root.left.left = new TreeNode(5);
        root.left.left.right = new TreeNode(7);
        root.left.right = new TreeNode(15);
        root.right.left = new TreeNode(25);
        root.right.right = new TreeNode(35);
        root.left.right.left = new TreeNode(13);
        root.left.right.right = new TreeNode(18);

        return root;
    }

    public void printLevelOrder(TreeNode root)
    {
        // Base Case
        if(root == null)
            return;

        // Create an empty queue for level order traversal
        Queue<TreeNode> q = new LinkedList<>();

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

    public void traverseInOrder(TreeNode node){

        if (node != null){
            traverseInOrder(node.left);
            System.out.print(node.val+" ");
            traverseInOrder(node.right);
        }
    }

    public TreeNode findNode(TreeNode root,int val){

        if (root == null){
            return null;
        }

        if (val < root.val){
            root = findNode(root.left,val);
        } else if (val > root.val ){
            root = findNode(root.right,val);
        }

        return root;
    }

    public TreeNode rightMostElement(TreeNode node){

        while (node.right != null){
            node = node.right;
        }
        return node;
    }

    public TreeNode findPredecessor(TreeNode root,TreeNode node){
        if (root == null){
            return null;
        }

        //Check for left child - if found find right most element of left child
        if (node.left != null){
            return rightMostElement(node.left);
        }

        TreeNode predecessor = null;
        while (root != null){
            if (root.val < node.val){
                predecessor = root;
                root = root.right;
            } else if (root.val > node.val){
                root = root.left;
            } else{
                return predecessor;
            }
        }
        return null;
    }

    public static void main(String[] args) {
        InOrderPredecessor tree = new InOrderPredecessor();
        TreeNode root = tree.createBT();
        TreeNode node = tree.findNode(root,5);
        tree.traverseInOrder(root);
        System.out.println();
        tree.printLevelOrder(root);

        TreeNode predecessor = tree.findPredecessor(root,node);
        if (predecessor != null){
            System.out.println("Predecessor of "+node.val+ " is "+predecessor.val);
        } else{
            System.out.println("Predecessor of "+node.val+ " not found");
        }
    }
}
