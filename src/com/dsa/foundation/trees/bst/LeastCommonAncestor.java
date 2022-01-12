package com.dsa.foundation.trees.bst;

import java.util.LinkedList;
import java.util.Queue;

//Question: Given values of two values n1 and n2 in a Binary Search Tree, find the Lowest Common Ancestor (LCA). You may
// assume that both the values exist in the tree.
//https://leetcode.com/problems/lowest-common-ancestor-of-a-binary-search-tree/


//Reference: https://www.geeksforgeeks.org/lowest-common-ancestor-in-a-binary-search-tree/
//Let T be a rooted tree. The lowest common ancestor between two nodes n1 and n2 is defined as the lowest node in T that
// has both n1 and n2 as descendants (where we allow a node to be a descendant of itself).
// The LCA of n1 and n2 in T is the shared ancestor of n1 and n2 that is located farthest from the root. Computation of
// lowest common ancestors may be useful, for instance, as part of a procedure for determining the distance between pairs
// of nodes in a tree: the distance from n1 to n2 can be computed as the distance from the root to n1, plus the distance
// from the root to n2, minus twice the distance from the root to their lowest common ancestor.

public class LeastCommonAncestor {

    public TreeNode insert(TreeNode root,int val){

        if (root == null){
            return new TreeNode(val);
        }

        if (val < root.val){
            root.left = insert(root.left,val);
        } else {
            root.right = insert(root.right,val);
        }

        return root;
    }

    public TreeNode createBST(){
        TreeNode root = null;
        root = new TreeNode(20);
        root.left = new TreeNode(8);
        root.right = new TreeNode(22);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(12);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(14);

        return root;
    }

    public void inOrder(TreeNode root){
        if (root != null){
            inOrder(root.left);
            System.out.print(root.val+" ");
            inOrder(root.right);
        }
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

    // For Binary search tree, while traversing the tree from top to bottom the first node which lies in between the two
    // numbers n1 and n2 is the LCA of the nodes, i.e. the first node n with the lowest depth which lies in between n1
    // and n2 (n1<=n<=n2) n1 < n2. So just recursively traverse the BST in, if node’s value is greater than both n1 and
    // n2 then our LCA lies in the left side of the node, if it’s is smaller than both n1 and n2, then LCA lies on the
    // right side. Otherwise, the root is LCA (assuming that both n1 and n2 are present in BST).

    // Create a recursive function that takes a node and the two values n1 and n2.
    // If the value of the current node is less than both n1 and n2, then LCA lies in the right subtree. Call the recursive function for the right subtree.
    // If the value of the current node is greater than both n1 and n2, then LCA lies in the left subtree. Call the recursive function for the left subtree.
    //  If both the above cases are false then return the current node as LCA.
    public TreeNode getLCA(TreeNode root,int n1,int n2){

        if (root == null){
            return null;
        }

        if (root.val < n1 && root.val < n2){
            return getLCA(root.right,n1,n2);
        }

        if (root.val > n1 && root.val > n2){
            return getLCA(root.left,n1,n2);
        }

        return root;

    }

    public static void main(String[] args) {
        LeastCommonAncestor lca = new LeastCommonAncestor();

        TreeNode root = lca.createBST();
        lca.inOrder(root);
        System.out.println();

        int n1 = 10, n2 = 14;
        TreeNode t = lca.getLCA(root, n1, n2);
        System.out.println("LCA of " + n1 + " and " + n2 + " is " + t.val);

        n1 = 14;
        n2 = 8;
        t = lca.getLCA(root, n1, n2);
        System.out.println("LCA of " + n1 + " and " + n2 + " is " + t.val);

        n1 = 10;
        n2 = 22;
        t = lca.getLCA(root, n1, n2);
        System.out.println("LCA of " + n1 + " and " + n2 + " is " + t.val);


    }

}
