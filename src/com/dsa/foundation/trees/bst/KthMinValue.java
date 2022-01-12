package com.dsa.foundation.trees.bst;

//Question: https://leetcode.com/problems/kth-smallest-element-in-a-bst/

//Reference: https://helloacm.com/how-to-find-the-kth-smallest-element-in-a-bst-tree-using-java-c/
//Reference: https://javabypatel.blogspot.com/2015/11/find-kth-largest-element-in-binary-search-tree.html

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class KthMinValue {
    class Result {
        int visitedCount = 0;
        TreeNode result = null;
    }

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

    private void inOrder(TreeNode root,List<TreeNode> list){

        if (root != null){
            inOrder(root.left,list);
            list.add(root);
            inOrder(root.right,list);
        }
    }

    public TreeNode findKthMinValue(TreeNode root,int k){
        if (root == null){
            return null;
        }

        List<TreeNode> list = new ArrayList<>();
        inOrder(root,list);

        if (k < 1 || k > list.size()){
            return null;
        }

        return list.get(k-1);
    }

    private void helper(TreeNode root, int k, Result result) {
        if(root == null) {
            return;
        } else if (result.result == null) {
            helper(root.left, k, result);
            result.visitedCount++;
            if(result.visitedCount == k) {
                result.result = root;
                return;
            }
            //If here kth min is not in left subtree search
            helper(root.right, k, result);
        }
    }

    public TreeNode findKMinValue(TreeNode root,int k){

        Result result = new Result();
        helper(root, k, result);
        return result.result;

    }

    public static void main(String[] args) {
        KthMinValue kMinValue = new KthMinValue();
        int[] values = {22, 34, 0, 36, 77, 99, 33, 35};
        Arrays.sort(values);

        TreeNode root = kMinValue.sortedArrayToBST(values, 0, values.length - 1);
        List<Integer> list = new ArrayList<>();
        System.out.println("Inorder:");
        kMinValue.traverseInOrder(root, list);
        kMinValue.printList(list);

        int k = 1;
        TreeNode kMinNode = kMinValue.findKthMinValue(root,k);
        //TreeNode kMinNode = kthMaxValue.findKMaxValue(root,k);
        System.out.println(k+"th min value: "+kMinNode.val);

        k = 1;
        kMinNode = kMinValue.findKMinValue(root,k);
        System.out.println(k+"th min value: "+kMinNode.val);

        KthMinValue kMinValue1 = new KthMinValue();
        TreeNode root1 = kMinValue1.insertRecursively(null,5);
        root1 = kMinValue1.insertRecursively(root1,3);
        root1 = kMinValue1.insertRecursively(root1,6);
        root1 = kMinValue1.insertRecursively(root1,2);
        root1 = kMinValue1.insertRecursively(root1,4);
        root1 = kMinValue1.insertRecursively(root1,1);

        k=3;
        kMinNode = kMinValue1.findKthMinValue(root1,k);
        System.out.println(k+"th min value: "+kMinNode.val);

        kMinNode = kMinValue1.findKMinValue(root1,k);
        System.out.println(k+"th min value: "+kMinNode.val);


    }
}
