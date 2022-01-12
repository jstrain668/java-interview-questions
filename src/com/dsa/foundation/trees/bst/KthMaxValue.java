package com.dsa.foundation.trees.bst;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Reference: https://helloacm.com/how-to-find-the-kth-smallest-element-in-a-bst-tree-using-java-c/
//Reference: https://javabypatel.blogspot.com/2015/11/find-kth-largest-element-in-binary-search-tree.html

public class KthMaxValue {

    class Result {
        TreeNode result = null;
        int visitedCount = 0;
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

    public TreeNode findNode(TreeNode root,int val){
        if (root == null){
            return null;
        }

        if (val < root.val){
            root = findNode(root.left,val);
        } else if (val > root.val){
            root = findNode(root.right,val);
        }
        return root;
    }

    private void inOrder(TreeNode root, List<TreeNode> list){

        if (root != null){
            inOrder(root.left,list);
            list.add(root);
            inOrder(root.right,list);
        }
    }

    public TreeNode findKthMaxValue(TreeNode root,int k){
        if (root == null){
            return null;
        }

        List<TreeNode> list = new ArrayList<>();
        inOrder(root,list);

        if (k < 0 || k > list.size()){
            return null;
        }

        return list.get(list.size()-k);
    }

    private void helper(TreeNode root,int k,Result result){
        if (root == null){
            return;
        } else if (result.result == null){
            helper(root.right,k,result);
            result.visitedCount++;
            if (k == result.visitedCount){
                result.result = root;
                return;
            }
            //If here kth max is not in right subtree
            helper(root.left,k,result);
        }
    }

    public TreeNode findKMaxValue(TreeNode root,int k) {
       Result result = new Result();
       helper(root,k,result);
       return result.result;

    }

    public static void main(String[] args) {
        KthMaxValue kthMaxValue = new KthMaxValue();
        int[] values = {22, 34, 0, 36, 77, 99, 33, 35};
        Arrays.sort(values);

        TreeNode root = kthMaxValue.sortedArrayToBST(values, 0, values.length - 1);
        List<Integer> list = new ArrayList<>();
        System.out.println("Inorder:");
        kthMaxValue.traverseInOrder(root, list);
        kthMaxValue.printList(list);

        int k = 2;
        TreeNode kMaxNode = kthMaxValue.findKthMaxValue(root,k);
        //TreeNode kMaxNode = kthMaxValue.findKMaxValue(root,k);
        System.out.println(k+"th max value: "+kMaxNode.val);

        k = 7;
        kMaxNode = kthMaxValue.findKMaxValue(root,k);
        if (kMaxNode != null) {
            System.out.println(k + "th max value: " + kMaxNode.val);
        } else {
            System.out.println("Cannot find "+k+"th max value in BST");
        }

    }
}
