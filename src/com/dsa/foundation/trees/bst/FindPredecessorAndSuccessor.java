package com.dsa.foundation.trees.bst;

//Question: Given a Binary Search Tree, Find predecessor and Successor of a given node.

//When you do the inorder traversal of a binary tree, the neighbors of given node are called Predecessor(the node lies
// behind of given node) and Successor (the node lies ahead of given node).

//Reference: https://algorithms.tutorialhorizon.com/inorder-predecessor-and-successor-in-binary-search-tree/

public class FindPredecessorAndSuccessor {

    TreeNode predecessor = null;
    TreeNode successor = null;

    // See reference for picture data
    //Say you have to find the inorder predecessor and successor node 15.
    //
    //First compare the 15 with root (25 here).
    //25>15 => successor = 25, make recursive call to root.left.(Why do we do it , is explained at step 3).
    //New root which is = 15, now stop making recursive calls.
    //Now successor will be the left most node in right subtree of which has the root 18 here. So the left most element and successor will be 19. (What if 15 doesn’t have a right subtree, then successor of 15 will be the parent of it, and that is why we set parent as successor in step1).
    //Now predecessor will be the right most node in left subtree which has the root 10 here. but 10 doesn’t have right child.
    //For the same reason when node<root then make predecessor = root and make a recursive call to the root.right.

    public void successorPredecessor(TreeNode root, int val) {
        if (root != null) {
            if (root.val == val) {
                // go to the right most element in the left subtree, it will be the predecessor.
                if (root.left != null) {
                    TreeNode t = root.left;
                    while (t.right != null) {
                        t = t.right;
                    }
                    predecessor = t;
                }
                if (root.right != null) {
                    // go to the left most element in the right subtree, it will be the successor.
                    TreeNode t = root.right;
                    while (t.left != null) {
                        t = t.left;
                    }
                    successor = t;
                }
            } else if (root.val > val) {
                // we make the root as successor because we might have a
                // situation when value matches with the root, it wont have
                // right subtree to find the successor, in that case we need
                // parent to be the successor
                successor = root;
                successorPredecessor(root.left, val);
            } else if (root.val < val) {
                // we make the root as predecessor because we might have a
                // situation when value matches with the root, it wont have
                // left subtree to find the predecessor, in that case we need
                // parent to be the predecessor.
                predecessor = root;
                successorPredecessor(root.right, val);
            }
        }
    }

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


    public static void main(String[] args) {

        FindPredecessorAndSuccessor tree = new FindPredecessorAndSuccessor();
        TreeNode root = tree.createBT();
        tree.successorPredecessor(root, 10);

        if (tree.successor != null){
            System.out.println("Inorder Successor of 10 is : " + tree.successor.val);
        } else {
            System.out.println("Successor doesn't exist");
        }

        if (tree.predecessor != null) {
            System.out.println("Predecessor of 10 is : " + tree.predecessor.val);
        }

        tree.successor = null;
        tree.predecessor = null;

        tree.successorPredecessor(root, 30);

        if (tree.successor != null){
            System.out.println("Inorder Successor of  is 30 : " + tree.successor.val);
        } else {
            System.out.println("Successor doesn't exist");
        }
        System.out.println("Predecessor of 30 is : " + tree.predecessor.val);
    }

}
