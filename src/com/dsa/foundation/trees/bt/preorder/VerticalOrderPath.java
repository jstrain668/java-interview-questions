package com.dsa.foundation.trees.bt.preorder;

import com.dsa.foundation.trees.bt.TreeNode;

import java.util.*;

//Reference: https://www.techiedelight.com/vertical-traversal-binary-tree/
//Reference: https://algorithms.tutorialhorizon.com/print-the-binary-tree-in-vertical-order-path/

public class VerticalOrderPath {

    public TreeNode createBTree(){

         /* Construct the following tree
                1
              /   \
             /     \
            2       3
                  /   \
                 /     \
                5       6
              /   \
             /     \
            7       8
                  /   \
                 /     \
                9      10
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.left.right.left = new TreeNode(9);
        root.right.left.right.right = new TreeNode(10);

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

    private void printVertical(TreeNode root,int hDist,Map<Integer,List<Integer>> map){

        // base case: empty tree
        if (root == null) {
            return;
        }

        // insert nodes present at a current horizontal distance into the map
        if (map.containsKey(hDist)){
            List<Integer> list = map.get(hDist);
            list.add(root.val);
            map.put(hDist, list);
        } else {
            List<Integer> list = new ArrayList<>();
            list.add(root.val);
            map.put(hDist,list);
        }

        // recur for the left subtree by decreasing horizontal distance by 1
        printVertical(root.left,hDist-1,map);
        // recur for the right subtree by increasing horizontal distance by 1
        printVertical(root.right,hDist+1,map);
    }

    //Do the preorder traversal.
    //Take a variable called hDist, when ever you go left, do hDist-- AND when ever you go right do hDist++.
    //With step above we have separated out the levels vertically.
    //Now you need to store the elements of each level, so create a TreeMap and the (key,value) pair will be (level,elements at that level).
    //At the end iterate through the TreeMap and print the results.

    // Function to perform vertical traversal on a given binary tree
    public void printVertical(TreeNode root)
    {
        // create an empty `TreeMap` where
        // key —> relative horizontal distance of the node from the root node, and
        // value —> nodes present at the same horizontal distance
        Map<Integer, List<Integer>> map = new TreeMap<>();

        // perform preorder traversal on the tree and fill the map
        printVertical(root, 0, map);

        // traverse the `TreeMap` and print vertical nodes
        //for (Collection<Integer> collection: map.values()) {
        //    System.out.println(collection);
        //}

        for (Map.Entry<Integer,List<Integer>> entry: map.entrySet()){
            List<Integer> list = entry.getValue();
            for (int i : list){
                System.out.print(i+" ");
            }
        }
        System.out.println();
    }

    public static void main(String[] args) {
        VerticalOrderPath vop = new VerticalOrderPath();

        TreeNode root = vop.createBTree();
        vop.printLevelOrder(root);
        vop.printVertical(root);
    }
}
