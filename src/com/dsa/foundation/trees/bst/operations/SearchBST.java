package com.dsa.foundation.trees.bst.operations;

import com.dsa.foundation.trees.bst.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SearchBST {
    private TreeNode sortedArrayToBST(int[] values, int start, int end){

        if (start > end){
            return null;
        }

        int mid = start + (end - start) / 2;
        TreeNode root = new TreeNode(values[mid]);

        root.left = sortedArrayToBST(values,start,mid-1);
        root.right = sortedArrayToBST(values,mid+1,end);

        return root;
    }

    public TreeNode sortedArrayToBST(int[] values){
        return sortedArrayToBST(values,0,values.length-1);
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

    public TreeNode searchBST(TreeNode root,int val){

        if (root == null){
            return null;
        }

        TreeNode curr = root;
        while (curr != null){
            if (val < curr.val){
                curr = curr.left;
            } else if (val > curr.val){
                curr = curr.right;
            } else {
                return curr;
            }
        }
        return null;
    }

    public TreeNode searchBSTRecursively(TreeNode root,int val){

        if (root == null){
            return null;
        }

        if (val < root.val){
            root = searchBSTRecursively(root.left,val);
        } else if (val > root.val){
            root = searchBSTRecursively(root.right,val);
        }

        return root;
    }

    public static void main(String[] args) {
        SearchBST bst = new SearchBST();
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

        int val = 12;
        TreeNode sNode = bst.searchBST(newNode,val);
        if (sNode!=null){
            System.out.println("Found node "+sNode.val+ " in the BST");
        } else {
            System.out.println("Couldn't find "+val+" in the BST");
        }

        val = 99;
        TreeNode node = bst.searchBSTRecursively(newNode,val);
        if (node!=null){
            System.out.println("Found node "+node.val+ " in the BST");
        } else {
            System.out.println("Couldn't find "+val+" in the BST");
        }
    }
}
