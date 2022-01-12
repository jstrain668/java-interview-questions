package com.dsa.foundation.trees.bst.preorder;

import com.dsa.foundation.trees.bst.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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


    //Solution: Pre-Order traversal visits the given node before its children. This traversal builds the path to the
    //leaf node. Add the path to the list and when leaf node encountered, print the current list.
    //After left and right sub trees processed removed the last element added to the list in preparation for next path.
    private void leafPaths(TreeNode root,List<Integer> list){

        //base case
        if (root == null){
            return;
        }

        // include the current node to the path
        list.add(root.val);

        //if leaf node print the path
        if (root.left == null && root.right == null){
            printList(list);
        }

        leafPaths(root.left,list);
        leafPaths(root.right,list);

        /// backtrack: remove the current node after the left, and right subtree are done
        list.remove(list.size()-1);
    }

    public void rootToLeafPaths(TreeNode root){
        List<Integer> list = new ArrayList<>();
        leafPaths(root,list);
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

        System.out.println("Root to Leaf Paths:");
        leafPaths.rootToLeafPaths(root);
    }


}
