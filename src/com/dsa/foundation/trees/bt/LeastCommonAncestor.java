package com.dsa.foundation.trees.bt;

import java.util.LinkedList;
import java.util.Queue;

//Question: Given a binary tree (not a binary search tree) and two values say n1 and n2, write a program to find the
// least common ancestor.

//The lowest common ancestor between two nodes n1 and n2 is defined as the lowest node in T that has both n1 and n2 as
// descendants (where we allow a node to be a descendant of itself).
//The LCA of n1 and n2 in T is the shared ancestor of n1 and n2 that is located farthest from the root.

//Reference: https://www.geeksforgeeks.org/lowest-common-ancestor-binary-tree-set-1/

//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-tree/


public class LeastCommonAncestor {

    //Flags to indicate
    boolean v1 = false;
    boolean v2 = false;

    public TreeNode createBTree(){

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

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

    //Assume that the keys n1 and n2 are present in Binary Tree, we can find LCA using a single traversal of BT and
    //without extra storage for path arrays. Traverse the tree starting from the root. If any of the given keys (n1 or
    // n2) matches with the root, then the root is LCA (assuming that both keys are present). If the root doesn’t match
    // with any of the keys, we recur for the left and right subtree. The node which has one key present in its left
    // subtree and the other key present in the right subtree is the LCA. If both keys lie in the left subtree, then the
    // left subtree has LCA also, otherwise, LCA lies in the right subtree.

    //The time complexity of the above solution is O(n) as the method does a simple tree traversal in a bottom-up fashion.
    //
    //Note that the above method assumes that keys are present in Binary Tree. If one key is present and the other is
    // absent, then it returns the present key as LCA (Ideally should have returned NULL).
    public TreeNode findLCA(TreeNode root,int n1, int n2){
        if (root == null){
            return null;
        }

        if (root.val == n1 || root.val == n2){
            return root;
        }

        TreeNode left = findLCA(root.left,n1,n2);
        TreeNode right = findLCA(root.right,n1,n2);

        // If both of the above calls return Non-NULL, then one key
        // is present in once subtree and other is present in other,
        // So this node is the LCA
        if (left != null && right != null){
            return root;
        }

        // Otherwise check if left subtree or right subtree is LCA
        return (left != null)? left : right;

    }

    private TreeNode findLCAUtil(TreeNode root,int n1,int n2){
        if (root == null){
            return null;
        }

        //Store result in temp, in case of key match so that we can search for other key also.
        TreeNode temp = null;

        //// If either n1 or n2 matches with root's key, report the presence
        //        // by setting v1 or v2 as true and return root (Note that if a key
        //        // is ancestor of other, then the ancestor key becomes LCA)
        if (root.val == n1){
            v1 = true;
            temp = root;
        }

        if (root.val == n2){
            v2 = true;
            temp = root;
        }

        // Look for keys in left and right subtrees
        TreeNode left = findLCA(root.left,n1,n2);
        TreeNode right = findLCA(root.right,n1,n2);

        if (temp != null){
            return temp;
        }

        // If both of the above calls return Non-NULL, then one key
        // is present in once subtree and other is present in other,
        // So this node is the LCA
        if (left != null && right != null){
            return root;
        }

        // Otherwise check if left subtree or right subtree is LCA
        return (left != null)? left : right;
    }

    public TreeNode findLCAAlt(TreeNode root,int n1, int n2){

        // Initialize n1 and n2 as not visited
        v1 = false;
        v2 = false;

        // Find lca of n1 and n2 using the technique discussed above
        TreeNode lca = findLCAUtil(root, n1, n2);

        // Return LCA only if both n1 and n2 are present in tree
        if (v1 && v2)
            return lca;

        // Else return NULL
        return null;
    }

    public static void main(String[] args) {

        LeastCommonAncestor tree = new LeastCommonAncestor();
        TreeNode root = tree.createBTree();

        System.out.println("LCA(4, 5) = " + tree.findLCA(root,4, 5).val);
        System.out.println("LCA(4, 6) = " + tree.findLCA(root,4, 6).val);
        System.out.println("LCA(3, 4) = " + tree.findLCA(root,3, 4).val);
        System.out.println("LCA(2, 4) = " + tree.findLCA(root,2, 4).val);

    }
}
