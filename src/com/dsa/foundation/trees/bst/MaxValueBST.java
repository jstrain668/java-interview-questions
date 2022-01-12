package com.dsa.foundation.trees.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MaxValueBST {
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

    public TreeNode findMaxValue(TreeNode root){
        if (root == null){
            return null;
        }

        TreeNode curr = root;
        while (curr.right != null){
            curr = curr.right;
        }
        return curr;
    }

    public TreeNode findMaxValueRecursive(TreeNode root){
        if (root.right == null){
            return root;
        }

        return findMaxValueRecursive(root.right);
    }

    public static void main(String[] args) {
        MaxValueBST maxValueBST = new MaxValueBST();
        int[] values = {22,34,0,36,77,99,33,35};
        Arrays.sort(values);

        TreeNode root = maxValueBST.sortedArrayToBST(values,0,values.length-1);
        List<Integer> list = new ArrayList<>();
        System.out.println("Inorder:");
        list.clear();
        maxValueBST.traverseInOrder(root,list);
        maxValueBST.printList(list);

        System.out.println("Max Value in BST: "+maxValueBST.findMaxValue(root).val);
        System.out.println("Max Value in BST: "+maxValueBST.findMaxValueRecursive(root).val);

        root = maxValueBST.insertIteratively(root,120);
        System.out.println("Max Value in BST: "+maxValueBST.findMaxValueRecursive(root).val);
        root = maxValueBST.insertRecursively(root,125);
        System.out.println("Max Value in BST: "+maxValueBST.findMaxValue(root).val);
    }
}
