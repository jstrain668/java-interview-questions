package com.dsa.foundation.trees.bt.preorder;

import com.dsa.foundation.trees.bt.TreeNode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

//Reference: https://www.techiedelight.com/find-diagonal-sum-given-binary-tree/

public class DiagonalSum {

    public TreeNode createBTree(){

         /* Construct the following tree
                   1
                 /   \
                /     \
               2       3
              /      /  \
             /      /    \
            4      5      6
                  / \
                 /   \
                7     8
        */
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
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

    //Solve this problem with the help of a TreeMap. The idea is to create an empty map where each key in the map
    //represents a diagonal in the binary tree, and its value maintains the sum of all nodes present in the diagonal.
    //Then perform a preorder traversal on the tree and update the map. For each node, recur for its left subtree by
    //increasing the diagonal by one and recur for the right subtree with the same diagonal


    // Recursive function to perform preorder traversal on the tree and
    // fill the map with the diagonal sum of elements
    public void diagonalSum(TreeNode root, int diagonal, Map<Integer, Integer> map)
    {
        // base case: empty tree
        if (root == null) {
            return;
        }

        // update the current diagonal with the node's value
        map.put(diagonal, map.getOrDefault(diagonal, 0) + root.val);

        // recur for the left subtree by increasing diagonal by 1
        diagonalSum(root.left, diagonal + 1, map);

        // recur for the right subtree with the same diagonal
        diagonalSum(root.right, diagonal, map);
    }

    public void diagonalSum(TreeNode root){

        // create an empty map to store the diagonal sum for every slope
        Map<Integer, Integer> map = new HashMap<>();

        // traverse the tree in a preorder fashion and fill the map
        diagonalSum(root, 0, map);

         // traverse the map and print the diagonal sum
        System.out.println(map.values());
    }

    public static void main(String[] args) {
        DiagonalSum ds = new DiagonalSum();
        TreeNode root = ds.createBTree();
        ds.printLevelOrder(root);
        ds.diagonalSum(root);



    }
}
