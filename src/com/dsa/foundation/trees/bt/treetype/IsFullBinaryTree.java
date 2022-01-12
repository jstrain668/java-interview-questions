package com.dsa.foundation.trees.bt.treetype;

import com.dsa.foundation.trees.bt.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Question: Check whether a binary tree is a full binary tree or not

//A full binary tree is defined as a binary tree in which all nodes have either zero or two child nodes. Conversely,
//there is no node in a full binary tree, which has one child node

//Reference: https://www.geeksforgeeks.org/check-whether-binary-tree-full-binary-tree-not/

public class IsFullBinaryTree {

    public TreeNode sortedArrayToBST(int[] values, int start, int end){
        //Base exit condition
        if (start > end){
            return null;
        }

        //Find and create the mid node
        int mid = start + (end - start)/2;
        TreeNode root = new TreeNode(values[mid]);

        //Left subtree is less in value than its parent
        root.left = sortedArrayToBST(values,start,mid-1);
        root.right = sortedArrayToBST(values,mid+1,end);

        return root;
    }

    public void printList(List<Integer> list){

        for (int val : list){
            System.out.print(val+" ");
        }
        System.out.println();
    }

    public void traversePreOrder(TreeNode node, List<Integer> list){

        if (node != null){
            list.add(node.val);
            traversePreOrder(node.left,list);
            traversePreOrder(node.right,list);
        }
    }

    public void traverseInOrder(TreeNode node, List<Integer> list){

        if (node != null){
            traverseInOrder(node.left,list);
            list.add(node.val);
            traverseInOrder(node.right,list);
        }
    }

    public void traversePostOrder(TreeNode node, List<Integer> list){

        if (node != null){
            traversePostOrder(node.left,list);
            traversePostOrder(node.right,list);
            list.add(node.val);
        }
    }

    public TreeNode insertRecursively(TreeNode root, int val){
        if (root == null){
            return new TreeNode(val);
        }

        if (val < root.val){
            root.left = insertRecursively(root.left,val);
        } else {
            root.right = insertRecursively(root.right,val);
        }

        return root;
    }

    public TreeNode insertIteratively(TreeNode root, int val){
        if (root == null){
            return new TreeNode(val);
        }

        TreeNode curr = root;
        TreeNode parent = null;

        while (curr != null){
            parent = curr;
            if (val < curr.val){
                curr = curr.left;
            } else{
                curr = curr.right;
            }
        }

        if (val < parent.val){
            parent.left = new TreeNode(val);
        } else{
            parent.right = new TreeNode(val);
        }
        return root;
    }

    //1) If a binary tree node is NULL then it is a full binary tree.
    //2) If a binary tree node does have empty left and right sub-trees, then it is a full binary tree by definition.
    //3) If a binary tree node has left and right sub-trees, then it is a part of a full binary tree by definition.
    //   In this case recursively check if the left and right sub-trees are also binary trees themselves.
    //4) In all other combinations of right and left sub-trees, the binary tree is not a full binary tree.

    public boolean isFull(TreeNode root){
        //Empty tree is true
        if (root == null){
            return true;
        }

        //Leaf node is true
        if (root.left == null && root.right == null){
            return true;
        }

        // if both left and right subtrees are not null
        // they are full
        if (root.left != null && root.right != null){
            return (isFull(root.left) && isFull(root.right));
        }
        return  false;
    }

    public static void main(String[] args) {
        IsFullBinaryTree fullBinaryTree = new IsFullBinaryTree();

        int[] values = {22,34,0,36,77,99,33,35};
        Arrays.sort(values);

        TreeNode root = fullBinaryTree.sortedArrayToBST(values,0,values.length-1);

        List<Integer> list = new ArrayList<>();
        System.out.println("Inorder:");
        fullBinaryTree.traverseInOrder(root,list);
        fullBinaryTree.printList(list);

        System.out.println("The binary tree is full : "+fullBinaryTree.isFull(root));
        fullBinaryTree.insertIteratively(root,67);
        System.out.println("The binary tree is full : "+fullBinaryTree.isFull(root));
    }
}
