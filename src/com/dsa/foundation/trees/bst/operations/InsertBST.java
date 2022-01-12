package com.dsa.foundation.trees.bst.operations;

import com.dsa.foundation.trees.bst.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Reference: https://www.techiedelight.com/insertion-in-bst/

public class InsertBST {

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

    public TreeNode insertNodeRecursively(TreeNode root,int val){
        if (root == null){
            return new TreeNode(val);
        }

        if (val < root.val){
            root.left = insertNodeRecursively(root.left,val);
        } else {
            root.right = insertNodeRecursively(root.right,val);
        }
        return root;
    }

    public TreeNode insertNodeIteratively(TreeNode root,int val){

        if (root == null){
            return new TreeNode(val);
        }

        TreeNode curr = root;
        TreeNode parent = null;

        while (curr != null){
            parent = curr;
            if (val < curr.val){
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        if (val < parent.val){
            parent.left = new TreeNode(val);
        } else {
            parent.right = new TreeNode(val);
        }
        return root;
    }

    public static void main(String[] args) {
        InsertBST bst = new InsertBST();
        int[] values = {22,34,0,36,77,99,33,35};
        Arrays.sort(values);

        TreeNode root = bst.sortedArrayToBST(values,0,values.length-1);
        List<Integer> list = new ArrayList<>();

        bst.traversePreOrder(root,list);
        System.out.println("Preorder:");
        bst.printList(list);
        System.out.println("Inorder:");
        list.clear();
        bst.traverseInOrder(root,list);
        bst.printList(list);
        System.out.println("Postorder:");
        list.clear();
        bst.traversePostOrder(root,list);
        bst.printList(list);

        TreeNode newNode = bst.insertNodeRecursively(root,13);
        System.out.println("Inorder:");
        list.clear();
        bst.traverseInOrder(newNode,list);
        bst.printList(list);

        newNode = bst.insertNodeRecursively(root,60);
        System.out.println("Inorder:");
        list.clear();
        bst.traverseInOrder(newNode,list);
        bst.printList(list);
    }
}
