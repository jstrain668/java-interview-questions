package com.dsa.medium.trees.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Question: https://leetcode.com/problems/delete-node-in-a-bst/

//Reference: https://www.techiedelight.com/deletion-from-bst/

public class DeleteBST {

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

    private TreeNode getMinNode(TreeNode node){
        while (node.left != null){
            node = node.left;
        }
        return node;
    }

    public TreeNode deleteNode(TreeNode root,int val){
        if (root == null){
            return null;
        }

        TreeNode curr = root;
        TreeNode parent = null;

        while (curr != null && curr.val != val){
            parent = curr;
            if (val < curr.val){
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        //'val' not found in list return null
        if (curr == null){
            return null;
        }


        //Case 1 'curr' is a leaf node
        if (curr.left == null && curr.right == null){
            if (parent != null){
                if (parent.left == curr){
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else // deleting root node
            {
                root = null;
            }
        }
        //Case 2 'curr' has two child nodes
        else if (curr.left != null && curr.right != null){
            TreeNode successor = getMinNode(curr.right);
            int successorVal = successor.val;
            deleteNode(root,successor.val);
            curr.val = successorVal;
        }
        // Case 3 'curr' has one child
        {
            TreeNode child = (curr.left != null) ? curr.left : curr.right;

            if (parent != null){
                if (parent.left == curr){
                    parent.left = child;
                } else {
                    parent.right = child;
                }
            } // if parent is null then child becomes root
            else{
                root = child;
            }
        }
        return root;
    }

    private TreeNode getMaxNode(TreeNode node){
        while (node.right != null){
            node = node.right;
        }
        return node;
    }

    // Function to delete a node from a BST
    public TreeNode deleteNodeRecursively(TreeNode root, int key)
    {
        // base case: the key is not found in the tree
        if (root == null) {
            return null;
        }

        // if the given key is less than the root node, recur for the left subtree
        if (key < root.val) {
            root.left = deleteNodeRecursively(root.left, key);
        }

        // if the given key is more than the root node, recur for the right subtree
        else if (key > root.val) {
            root.right = deleteNodeRecursively(root.right, key);
        }

        // key found
        else {
            // Case 1: node to be deleted has no children (it is a leaf node)
            if (root.left == null && root.right == null)
            {
                // update root to null
                return null;
            }

            // Case 2: node to be deleted has two children
            else if (root.left != null && root.right != null)
            {
                // find its inorder predecessor node
                TreeNode predecessor = getMaxNode(root.left);

                // copy value of the predecessor to the current node
                root.val = predecessor.val;

                // recursively delete the predecessor. Note that the
                // predecessor will have at most one child (left child)
                root.left = deleteNodeRecursively(root.left, predecessor.val);
            }

            // Case 3: node to be deleted has only one child
            else {
                // choose a child node
                TreeNode child = (root.left != null) ? root.left: root.right;
                root = child;
            }
        }

        return root;
    }


    public static void main(String[] args) {
        DeleteBST bst = new DeleteBST();
        int[] values = {22,34,0,36,77,99,33,35};
        Arrays.sort(values);

        TreeNode root = bst.sortedArrayToBST(values);
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

        int val = 77;
        TreeNode node = bst.deleteNode(root,val);
        System.out.println("Inorder:");
        list.clear();
        bst.traverseInOrder(node,list);
        bst.printList(list);

        int[] values2 = {5,3,6,2,4,7};
        Arrays.sort(values2);

        TreeNode root2 = bst.sortedArrayToBST(values2);
        System.out.println("Inorder:");
        list.clear();
        bst.traverseInOrder(root2,list);
        bst.printList(list);

        val = 3;
        TreeNode node2 = bst.deleteNodeRecursively(root2,val);
        System.out.println("Inorder:");
        list.clear();
        bst.traverseInOrder(node2,list);
        bst.printList(list);
    }
}
