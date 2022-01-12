package com.dsa.foundation.trees.bst.postorder;

import com.dsa.foundation.trees.bst.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

// The ancestor of a node: A node that is connected to all lower-level nodes is called an “ancestor“. The connected
// lower-level nodes are “descendants” of the ancestor node.
// The root node will be the ancestor to all the nodes in its left and right subtree.

public class FindAncestors {

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

    //Given a binary tree, find all ancestors of a given node in it. The idea is to traverse the tree in a postorder
    // fashion and search for a given node in the tree.

    private boolean findAncestors(TreeNode root,TreeNode node,List<TreeNode> nodeList){

        //base case
        if (root == null){
            return false;
        }

        //return true if given node found
        if (root == node){
            return true;
        }

        boolean left = findAncestors(root.left,node,nodeList);

        boolean right = false;
        if (!left){
            right = findAncestors(root.right,node,nodeList);
        }

        if (left || right){
            nodeList.add(root);
            return true;
        } else {
            return false;
        }
    }

    public void findAncestors(TreeNode root, int val, List<TreeNode> nodeList){

        if (root == null){
            return;
        }

        TreeNode node = searchBSTRecursively(root,val);
        findAncestors(root,node,nodeList);
    }

    public void printNodeList(List<TreeNode> list){
        for (TreeNode node : list){
            System.out.print(node.val+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        FindAncestors fa = new FindAncestors();

        int[] values = {22,34,0,36,77,99,33,35};
        Arrays.sort(values);

        TreeNode root = fa.sortedArrayToBST(values,0,values.length-1);
        List<Integer> list = new ArrayList<>();

        fa.traversePreOrder(root,list);
        System.out.println("Preorder:");
        fa.printList(list);
        System.out.println("Inorder:");
        list.clear();
        fa.traverseInOrder(root,list);
        fa.printList(list);
        System.out.println("Postorder:");
        list.clear();
        fa.traversePostOrder(root,list);
        fa.printList(list);

        List<TreeNode> nodeList = new ArrayList<>();
        int val = 99;
        fa.findAncestors(root,val,nodeList);
        System.out.println("Ancestor list for node value : "+val);
        fa.printNodeList(nodeList);
    }
}
