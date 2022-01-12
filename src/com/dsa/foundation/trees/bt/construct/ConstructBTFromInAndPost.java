package com.dsa.foundation.trees.bt.construct;

//Question: Given a inorder and postorder traversal, write an algorithm to construct a binary tree from that
//https://leetcode.com/problems/construct-binary-tree-from-inorder-and-postorder-traversal/

//Ref: https://algorithms.tutorialhorizon.com/construct-a-binary-tree-from-given-inorder-and-postorder-traversal/
//Reference: https://medium.com/@huilin1618/constructing-binary-tree-from-inorder-and-postorder-traversal-3f92c4183d65

//Similar Problems: Make a Binary Tree from Given Inorder and Preorder Traversal.
//
//Approach:
//int[] inOrder = { 4, 2, 5, 1, 6, 3, 7 };
//int[] postOrder = { 4, 5, 2, 6, 7, 3, 1 };.
//
//1. Find the last node in the postorder array, which is the root of the current tree.
//2. Find the position of root node in the inorder array. Divide the inorder array into 2 sub tree inorder arrays. Left
// part is the left sub-tree, right part is the right sub-tree.
//3. Do 1 and 2 for the right and left sub-tree, respectively.

///For example, given
//inorder = [9,3,15,20,7]
//postorder = [9,15,7,20,3]
//Return the following binary tree:
//    3
//   / \
//  9  20
//    /  \
//   15   7

//Notice that the root is visited last in an postorder traversal. Hence, we know that the root of the tree must be the
// last element of the postorder array.
//A. The last element of the postorder traversal is the root of the tree.
//B. For a given element x in the inorder array, all elements to the left of x must be in x’s left subtree, and all
// elements of the right of x must be in x’s right subtree.
//From observations A and B, we can deduce that the binary tree looks something like this:
//    3
//   / \
// [9] [15, 20, 7]
//The inorder traversal of the left subtree is the portion of the inorder array to the left of 3, and the inorder
// traversal of the right subtree is the portion of the array to the right of 3.
//We know that in the postorder traversal, all elements int the left subtree of the root are visited before the elements
//in the right subtree of the root. Hence, if we know the number of elements in the left subtree of the root, we can
// find the portion of the postorder array that corresponds to the left subtree. Analogous reasoning can be applied to
// find the portions of the inorder and postorder array that correspond to the right subtree.

//Last element in the postorder [] will be the root of the tree, here it is 1.
//Now the search element 1 in inorder[], say you find it at position i, once you find it, make note of elements which
//are left to i (this will construct the left subtree) and elements which are right to i ( this will construct the right
//Subtree).
//Suppose in previous step, there are X number of elements which are left of ‘i’ (which will construct the leftsubtree), take first X elements from the postorder[] traversal, this will be the post order traversal for elements which are left to i. similarly if there are Y number of elements which are right of ‘i’ (which will construct the rightsubtree), take next Y elements, after X elements from the postorder[] traversal, this will be the post order traversal for elements which are right to i
//From previous two steps construct the left and right subtree and link it to root.left and root.right respectively.


import com.dsa.foundation.trees.bt.TreeNode;

public class ConstructBTFromInAndPost {

    private TreeNode constructBT(int[] inOrder, int[] postOrder, int iStart, int iEnd,
    int postStart, int postEnd) {
        if (iStart > iEnd) {
            return null;
        }

        TreeNode root = new TreeNode(postOrder[postEnd]);

        if (iStart == iEnd) {
            return root;
        }

        int index = getInorderIndex(inOrder, iStart, iEnd, root.val);
        root.left = constructBT(inOrder, postOrder, iStart, index - 1, postStart,
                postStart + index - (iStart + 1));
        root.right = constructBT(inOrder, postOrder, index + 1, iEnd, postStart
                + index - iStart, postEnd - 1);

        return root;
    }

    public int getInorderIndex(int[] inOrder, int start, int end, int val) {
        for (int i = start; i <= end; i++) {
            if (inOrder[i] == val) {
                return i;
            }
        }
        return -1;
    }

    public TreeNode makeBTree(int[] inOrder,int[] postOrder){

        if (postOrder.length <= 0 || inOrder.length <= 0){
            return null;
        }

        return constructBT(inOrder,postOrder,0,inOrder.length-1,0,postOrder.length-1);
    }

    public void printINORDER(TreeNode root) {
        if (root != null) {
            printINORDER(root.left);
            System.out.print("  " + root.val);
            printINORDER(root.right);
        }
    }

    public static void main(String[] args) throws java.lang.Exception {
        int[] inOrder = { 4, 2, 5, 1, 6, 3, 7 };
        int[] postOrder = { 4, 5, 2, 6, 7, 3, 1 };
        ConstructBTFromInAndPost tree = new ConstructBTFromInAndPost();
        TreeNode x = tree.makeBTree(inOrder, postOrder);
        System.out.println("inorder traversal of constructed tree : ");
        tree.printINORDER(x);

        TreeNode root = tree.makeBTree(inOrder, postOrder);
    }

}
