package com.dsa.foundation.trees.bt;

import java.util.LinkedList;
import java.util.Queue;

//Question: Find the distance between two nodes in a binary tree, no parent pointers are given. The distance
// between two nodes is the minimum number of edges to be traversed to reach one node from another.

//The distance between two nodes can be obtained in terms of lowest common ancestor.
//Dist(n1, n2) = Dist(root, n1) + Dist(root, n2) - 2*Dist(root, lca)
//'n1' and 'n2' are the two given keys
//'root' is root of given Binary Tree.
//'lca' is lowest common ancestor of n1 and n2
//Dist(n1, n2) is the distance between n1 and n2.

//Reference:



public class ShortestDistance {

    public TreeNode createBT(){
		/*
		        1
		       / \
		      /   \
		     2     3
		    / \   / \
		   4   5 6   7
		          \
		           8

		*/
        TreeNode root = new TreeNode(1);

        // Creating 2nd level:
        TreeNode one = new TreeNode(2);
        TreeNode two = new TreeNode(3);
        root.left = one;
        root.right = two;

        // Creating 3rd level:
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        // Creating 4th level:
        root.right.left.right = new TreeNode(8);

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

    public TreeNode findLCA(TreeNode root,TreeNode a,TreeNode b){
        if (root == null){
            return null;
        }

        if (root == a || root == b){
            return root;
        }

        TreeNode left = findLCA(root.left,a,b);
        TreeNode right = findLCA(root.right,a,b);

        if (left != null && right != null){
            return root;
        }

        return (left != null) ? left : right;
    }

    public int getHeight(TreeNode root,int level,TreeNode node){
        if (root == null){
            return 0;
        }

        if (root == node){
            return level;
        }

        int leftH = getHeight(root.left,level+1,node);
        if (leftH != 0){
            return leftH;
        }

        return getHeight(root.right,level+1,node);
    }

    public int shortestDistance(TreeNode root,TreeNode a,TreeNode b){
        if(root == null){
            return 0;
        }

        TreeNode lca = findLCA(root, a, b);
        return getHeight(root, 1, a) + getHeight(root, 1, b) - 2*getHeight(root, 1, lca);
    }

    public TreeNode findNode(TreeNode root,int val){
        if (root == null){
            return null;
        }

        if (root.val == val){
            return root;
        }

        TreeNode left = findNode(root.left,val);
        if (left != null){
            return left;
        }
        return findNode(root.right,val);
    }

    public static void main(String[] args) {
        ShortestDistance tree = new ShortestDistance();
        TreeNode root = tree.createBT();
        tree.printLevelOrder(root);

        TreeNode a = tree.findNode(root,4);
        TreeNode b = tree.findNode(root,5);
        System.out.println("Dist(4, 5) = " +tree.shortestDistance(root,a,b));

        b = tree.findNode(root,6);
        System.out.println("Dist(4, 6) = " +tree.shortestDistance(root, a, b));

        a = tree.findNode(root,3);
        b = tree.findNode(root,4);
        System.out.println("Dist(3, 4) = " +tree.shortestDistance(root, a, b));

        a = tree.findNode(root,2);
        b = tree.findNode(root,4);
        System.out.println("Dist(2, 4) = " +tree.shortestDistance(root, a, b));

        a = tree.findNode(root,8);
        b = tree.findNode(root,5);
        System.out.println("Dist(8, 5) = " +tree.shortestDistance(root, a, b));

        //Dist(4, 5) = 2
        //Dist(4, 6) = 4
        //Dist(3, 4) = 3
        //Dist(2, 4) = 1
        //Dist(8, 5) = 5
    }
}
