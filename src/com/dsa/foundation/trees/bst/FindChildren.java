package com.dsa.foundation.trees.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindChildren {

    public TreeNode sortedArrayToBST(int[] values,int start,int end){
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

    private void traverse(TreeNode node,List<TreeNode> nodeList){

        //In order traversal of children list
        if (node != null){
            traverse(node.left,nodeList);
            nodeList.add(node);
            traverse(node.right,nodeList);
        }
    }

    public void findChildren(TreeNode root, int val,List<TreeNode> nodeList){

        if (root == null){
            return;
        }

        TreeNode node = searchBSTRecursively(root,val);
        traverse(node.left,nodeList);
        traverse(node.right,nodeList);
    }

    public void printNodeList(List<TreeNode> list){
        for (TreeNode node : list){
            System.out.print(node.val+" ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        FindChildren fa = new FindChildren();

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
        int val = 36;
        fa.findChildren(root,val,nodeList);
        System.out.println("Inorder list of children for node value : "+val);
        fa.printNodeList(nodeList);
    }
}
