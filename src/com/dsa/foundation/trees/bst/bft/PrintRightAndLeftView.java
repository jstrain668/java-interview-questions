package com.dsa.foundation.trees.bst.bft;

import com.dsa.foundation.trees.bst.TreeNode;

import java.util.*;

//Question: Print view of a binary tree
//Reference: https://www.techiedelight.com/print-right-view-binary-tree/
//Reference: https://www.techiedelight.com/print-left-view-of-binary-tree/

public class PrintRightAndLeftView {
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

    public void printRightView(TreeNode root){

        if (root == null){
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();

            for (int i =0; i < size; i++){

                TreeNode curr = queue.poll();

                if (i == size -1){
                    System.out.print(curr.val+" ");
                }

                if (curr.left != null){
                    queue.add(curr.left);
                }

                if (curr.right != null){
                    queue.add(curr.right);
                }
            }
        }

    }

    public void printLeftView(TreeNode root){

        if (root == null){
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            int size = queue.size();

            for (int i =0; i < size; i++){

                TreeNode curr = queue.poll();

                if (i == 0){
                    System.out.print(curr.val+" ");
                }

                if (curr.left != null){
                    queue.add(curr.left);
                }

                if (curr.right != null){
                    queue.add(curr.right);
                }
            }
        }

    }

    public static void main(String[] args) {
        PrintRightAndLeftView prv = new PrintRightAndLeftView();
        int[] values = {22,34,0,36,77,99,33,35};
        Arrays.sort(values);

        TreeNode root = prv.sortedArrayToBST(values,0,values.length-1);
        List<Integer> list = new ArrayList<>();
        System.out.println("Inorder:");
        prv.traverseInOrder(root,list);
        prv.printList(list);

        System.out.println("Right View of BST: ");
        prv.printRightView(root);
        prv.insertIteratively(root,29);
        System.out.println();
        System.out.println("Left View of BST: ");
        prv.printLeftView(root);
    }
}
