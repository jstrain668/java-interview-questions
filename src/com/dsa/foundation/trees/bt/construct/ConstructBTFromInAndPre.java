package com.dsa.foundation.trees.bt.construct;

//Question: Given a inorder and preorder traversal, construct a binary tree from that.

//Reference: https://algorithms.tutorialhorizon.com/construct-a-binary-tree-from-given-inorder-and-depth-first-search/
//Reference: https://algorithms.tutorialhorizon.com/make-a-binary-tree-from-given-inorder-and-preorder-traveral/

import com.dsa.foundation.trees.bt.TreeNode;

public class ConstructBTFromInAndPre {

    int pIndex = 0;

    public TreeNode makeBTree(int[] inorder, int[] preorder, int iStart, int iEnd) {
        if (iStart > iEnd) {
            return null;
        }

        int rootVal = preorder[pIndex];
        TreeNode root = new TreeNode(rootVal);
        pIndex++;
        if (iStart == iEnd) {
            return root;
        }

        int index = findIndex(inorder, rootVal, iStart, iEnd);
        root.left = makeBTree(inorder, preorder, iStart, index - 1);
        root.right = makeBTree(inorder, preorder, index + 1, iEnd);

        return root;
    }

    public int findIndex(int[] inorder, int value, int iStart, int iEnd) {
        int x = -1;
        for (int i = iStart; i <= iEnd; i++) {
            if (value == inorder[i]) {
                x = i;
            }
        }
        return x;
    }

    public void printINORDER(TreeNode root) {
        if (root != null) {
            printINORDER(root.left);
            System.out.print("  " + root.val);
            printINORDER(root.right);
        }
    }

    public static void main(String args[]) {
        int [] inOrder = {2,5,6,10,12,14,15};
        int [] preOrder = {10,5,2,6,14,12,15};
        ConstructBTFromInAndPre i = new ConstructBTFromInAndPre();
        TreeNode x = i.makeBTree(inOrder, preOrder, 0, inOrder.length - 1);
        System.out.println("inorder traversal of constructed tree : ");
        i.printINORDER(x);
    }
}
