package com.dsa.foundation.trees.bst.bft;

//Question: https://thecodingbot.com/given-a-binary-tree-find-the-node-at-the-deepest-level/
// Given a binary tree, find the node at the deepest level. If there are more than one nodes at the last level, return
// any of them.

import com.dsa.foundation.trees.bst.TreeNode;

import java.util.*;

public class FindNodeDeepestLevel {

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

    //Use BFS to find the deepest node. BFS traverses the tree level by level going downwards into the tree thus the
    //last node to be processed will be at the deepest level. if there are more than one nodes in the last level, the
    // rightmost node of the last level is returned.

    public TreeNode findNodeDeepestLevel(TreeNode root){

        if (root == null){
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        TreeNode deepest = null;

        while (!queue.isEmpty()){
            deepest = queue.poll();

            if (deepest.left != null){
                queue.add(deepest.left);
            }

            if (deepest.right != null){
                queue.add(deepest.right);
            }
        }
        return deepest;
    }

    public static void main(String[] args) {
        FindNodeDeepestLevel deepestLevel = new FindNodeDeepestLevel();
        int[] values = {22,34,0,36,77,99,33,35};
        Arrays.sort(values);

        TreeNode root = deepestLevel.sortedArrayToBST(values,0,values.length-1);
        List<Integer> list = new ArrayList<>();
        System.out.println("Inorder:");
        deepestLevel.traverseInOrder(root,list);
        deepestLevel.printList(list);

        TreeNode deepest = deepestLevel.findNodeDeepestLevel(root);
        System.out.println("Deepest node in BST: "+deepest.val);
    }
}
