package com.dsa.foundation.trees.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MinValueBST {

    public TreeNode sortedArrayToBST(int[] values,int start,int end){
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

    public void traversePreOrder(TreeNode node,List<Integer> list){

        if (node != null){
            list.add(node.val);
            traversePreOrder(node.left,list);
            traversePreOrder(node.right,list);
        }
    }

    public void traverseInOrder(TreeNode node,List<Integer> list){

        if (node != null){
            traverseInOrder(node.left,list);
            list.add(node.val);
            traverseInOrder(node.right,list);
        }
    }

    public void traversePostOrder(TreeNode node,List<Integer> list){

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

    public TreeNode insertIteratively(TreeNode root,int val){
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
    public TreeNode findMinValue(TreeNode root){
        if (root == null){
            return null;
        }

        TreeNode curr = root;
        while (curr.left != null){
            curr = curr.left;
        }
        return curr;
    }

    public TreeNode findMinValueRecursive(TreeNode root){

        if (root.left == null){
            return root;
        }

        return findMinValueRecursive(root.left);
    }

    public static void main(String[] args) {
        MinValueBST minValueBST = new MinValueBST();
        int[] values = {22,34,0,36,77,99,33,35};
        Arrays.sort(values);

        TreeNode root = minValueBST.sortedArrayToBST(values,0,values.length-1);
        List<Integer> list = new ArrayList<>();
        System.out.println("Inorder:");
        list.clear();
        minValueBST.traverseInOrder(root,list);
        minValueBST.printList(list);

        TreeNode minNode = minValueBST.findMinValue(root);
        System.out.println("Min value in BST: "+minNode.val);
        minNode = minValueBST.findMinValueRecursive(root);
        System.out.println("Min value in BST: "+minNode.val);

        root = minValueBST.insertIteratively(root,-1);
        System.out.println("Min value in BST: "+minValueBST.findMinValue(root).val);

        root = minValueBST.insertRecursively(root,-10);
        System.out.println("Min value in BST: "+minValueBST.findMinValueRecursive(root).val);
    }
}
