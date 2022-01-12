package com.dsa.foundation.trees.bt.treetype;

//Given two binary trees, check if the first tree is subtree of the second one. A subtree of a tree T is a tree S
//consisting of a node in T and all of its descendants in T. The subtree corresponding to the root node is the entire
//tree; the subtree corresponding to any other node is called a proper subtree.

//Reference: https://www.geeksforgeeks.org/check-if-a-binary-tree-is-subtree-of-another-binary-tree/

import com.dsa.foundation.trees.bt.TreeNode;

public class IsSubTreeOfAnotherBT {

    public TreeNode createBTree1(){

        // TREE 1
        /* Construct the following tree
              26
             /   \
            10     3
           /    \     \
          4      6      3
           \
            30  */

        TreeNode root = new TreeNode(26);
        root.right = new TreeNode(3);
        root.right.right = new TreeNode(3);
        root.left = new TreeNode(10);
        root.left.left = new TreeNode(4);
        root.left.left.right = new TreeNode(30);
        root.left.right = new TreeNode(6);

        return root;
    }

    public TreeNode createBTree2(){
        // TREE 2
        /* Construct the following tree
           10
         /    \
         4      6
          \
          30  */

        TreeNode root = new TreeNode(10);
        root.right = new TreeNode(6);
        root.left = new TreeNode(4);
        root.left.right = new TreeNode(30);

        return root;
    }

    private boolean areIdentical(TreeNode root1,TreeNode root2){

        if (root1 == null && root2 == null){
            return true;
        }

        if (root1 != null && root2 != null){
            return (root1.val == root2.val && areIdentical(root1.left,root2.left) &&
                    areIdentical(root1.right,root2.right));
        }
        return false;
    }

    //Traverse the tree T in preorder fashion. For every visited node in the traversal, see if the subtree rooted with
    //this node is identical to S.

    /* This function returns true if S is a subtree of T, otherwise false */
    //Time Complexity: Time worst-case complexity of above solution is O(mn) where m and n are number of nodes in given
    // two trees.
    //Auxiliary space: O(n)
    public boolean isSubtree(TreeNode T, TreeNode S)
    {
        /* base cases */
        if (S == null)
            return true;

        if (T == null)
            return false;

        /* Check the tree with root as current node */
        if (areIdentical(T, S))
            return true;

        /* If the tree with root as current node doesn't match then
           try left and right subtrees one by one */
        return isSubtree(T.left, S)
                || isSubtree(T.right, S);
    }

    public static void main(String[] args) {

        IsSubTreeOfAnotherBT tree = new IsSubTreeOfAnotherBT();
        TreeNode root1 = tree.createBTree1();
        TreeNode root2 = tree.createBTree2();

        if (tree.isSubtree(root1,root2))
            System.out.println("Tree 2 is subtree of Tree 1 ");
        else
            System.out.println("Tree 2 is not a subtree of Tree 1");


    }
}
