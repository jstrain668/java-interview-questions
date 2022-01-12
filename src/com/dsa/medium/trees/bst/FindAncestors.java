package com.dsa.medium.trees.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Question: https://www.educative.io/courses/data-structures-coding-interviews-java/xV6ErQrBBDB

//Reference: https://thecodingbot.com/print-all-the-ancestors-of-a-node-in-a-binary-tree/

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

    public TreeNode insertNodeRecursively(TreeNode root, int val){
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

    public TreeNode insertNodeIteratively(TreeNode root, int val){

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

    public TreeNode searchBST(TreeNode root, int val){

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

    public TreeNode searchBSTRecursively(TreeNode root, int val){

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
    //  Let’s say we want to print all the ancestors of the node “X”. What we will do is, we will perform the postorder
    //  traversal. At any given node in the tree, the postorder will check the left subtree of the node(say “Y”) for the
    //  value “X” and then right subtree for the value “X” in it, if any of those subtrees have the node “X”, then it
    //  will finally print the value of node “Y”. The node “Y” has the node “X” in either of its subtrees which makes it
    //  an ancestor of node “X”. Since the process is recursive, it will print all the ancestor of node “X” from most
    //  recent to the former.

    //Step 1: Base Case 1: Check if the root is null, i.e the tree is empty or not. If root is null, return false.
    //Step 2: Base Case 2: Check if the node value equals to the node’s value we want to find the ancestor of. If it is
    // equal, return true, which means we found the value.
    //Step 3: Else, Recursively Search for the node’s value in the left subtree of the tree.
    //Step 4: Check if the left subtree has node, if yes, then no need to search the right subtree. Else, search for
    // node value in right subtree too.
    //Step 5: After searching both subtrees for the node value, add the root node value to the list if any of the two
    // subtrees have the node in them.
    private boolean findAncestors(TreeNode root,int val, List<Integer> list){

        if (root == null){
            return false;
        }

        if (root.val == val){
            return true;
        }

        boolean left = findAncestors(root.left,val,list);

        boolean right = false;
        if (!left){
            right = findAncestors(root.right,val,list);
        }

        if (left || right){
            list.add(root.val);
            return true;
        }else {
            return false;
        }

    }

    public List<Integer> findAncestors(TreeNode root, int val){

        List<Integer> list = new ArrayList<>();
        if (root == null){
            return list;
        }

        findAncestors(root,val,list);
        return list;
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

        int val = 99;
        list = fa.findAncestors(root,val);
        System.out.println("Ancestor list for node value : "+val);
        fa.printList(list);
    }
}
