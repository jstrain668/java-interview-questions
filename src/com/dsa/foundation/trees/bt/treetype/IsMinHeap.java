package com.dsa.foundation.trees.bt.treetype;

import com.dsa.foundation.trees.bt.TreeNode;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.Queue;

public class IsMinHeap {

    public TreeNode createBT(){
         /* Construct the following tree
                   2
                 /   \
                /     \
               3       4
              / \     / \
             /   \   /   \
            5     6 8    10
        */

        TreeNode root = new TreeNode(2);
        root.left = new TreeNode(3);
        root.right = new TreeNode(4);
        root.left.left = new TreeNode(5);
        root.left.right = new TreeNode(6);
        root.right.left = new TreeNode(8);
        root.right.right = new TreeNode(10);

        return root;
    }

    //The idea is to perform a level order traversal for the given binary tree to check both
    // structural and heap properties of a min-heap.
    //
    //To check for the structural property, simply check that no non-empty child is
    // encountered for any node once an empty child is seen.
    //To check for the heap property, check that both left and right children are greater than the parent node. This can be easily done while inserting the children into the queue.

    //The time complexity of the above solution is O(n), where n is the total number of
    // nodes in the binary tree. The auxiliary space required by the program is O(n) for
    // level order traversal, where n is the total number of nodes in the binary tree.

    // Function to check if a given binary tree is a min-heap or not
    public boolean isHeap(TreeNode root)
    {
        // base case
        if (root == null) {
            return true;
        }

        // create an empty queue and enqueue the root node
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // take a boolean flag, which becomes true when an empty left or right
        // child is seen for a node
        boolean isNullSeen = false;

        // loop till queue is empty
        while (!queue.isEmpty())
        {
            // process front node in the queue
            TreeNode curr = queue.poll();

            // left child is non-empty
            if (curr.left != null)
            {
                // if either heap property is violated
                if (isNullSeen || curr.left.val <= curr.val) {
                    return false;
                }

                // enqueue left child
                queue.add(curr.left);
            }
            // left child is empty
            else {
                isNullSeen = true;
            }

            // right child is non-empty
            if (curr.right != null)
            {
                // if either heap property is violated
                if (isNullSeen || curr.right.val <= curr.val) {
                    return false;
                }

                // enqueue left child
                queue.add(curr.right);
            }
            // right child is empty
            else {
                isNullSeen = true;
            }
        }

        // we reach here only when the given binary tree is a min-heap
        return true;
    }

    public static void main(String[] args)
    {
        IsMinHeap minHeap = new IsMinHeap();

        TreeNode root = minHeap.createBT();

        if (minHeap.isHeap(root)) {
            System.out.print("The given binary tree is a min-heap");
        }
        else {
            System.out.print("The given binary tree is not a min-heap");
        }
    }
}
