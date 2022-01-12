package com.dsa.medium.trees.bt;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Question: https://leetcode.com/problems/binary-tree-paths/

//Reference: https://massivealgorithms.blogspot.com/search?q=binary+tree+paths


public class RootToLeafPaths {

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

    private void printStringList(List<String> list){

        for (String str: list){
            System.out.println(str);
        }
    }

    private void findPaths(TreeNode root,String path,List<String> result){

        if (root.left == null && root.right == null){
            result.add(path);
            return;
        }

        if (root.left != null){
            findPaths(root.left,path+"->"+root.left.val,result);
        }

        if (root.right != null){
            findPaths(root.right,path+"->"+root.right.val,result);
        }
    }

    public List<String> binaryTreePaths(TreeNode root) {

        List<String> result = new ArrayList<>();

        if (root != null){
            findPaths(root,String.valueOf(root.val),result);
        }
        return result;
    }

    public static void main(String[] args) {
        RootToLeafPaths leafPaths = new RootToLeafPaths();
        int[] values = {22,34,0,36,77,99,33,35};
        Arrays.sort(values);

        TreeNode root = leafPaths.sortedArrayToBST(values,0,values.length-1);
        List<Integer> list = new ArrayList<>();
        System.out.println("Inorder:");
        leafPaths.traverseInOrder(root,list);
        leafPaths.printList(list);

        List<String> paths = leafPaths.binaryTreePaths(root);
        leafPaths.printStringList(paths);

    }

}
