package com.dsa.foundation.trees.bt.treetype;

import com.dsa.foundation.trees.bt.TreeNode;

import java.util.*;

//Question: Check whether a given Binary Tree is Complete or not

//A complete binary tree is a binary tree in which every level, except possibly the last, is completely filled, and all
// nodes are as far left as possible.

//Reference: https://www.geeksforgeeks.org/check-if-a-given-binary-tree-is-complete-tree-or-not/

public class IsCompleteBinarytree {

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

    public void printLevelOrder(TreeNode root)
    {
        // Base Case
        if(root == null)
            return;

        // Create an empty queue for level order traversal
        Queue<TreeNode> q =new LinkedList<>();

        // Enqueue Root and initialize height
        q.add(root);

        while(!q.isEmpty())
        {
            int size = q.size();
            for (int i =0; i < size; i++)
            {
                TreeNode node = q.poll();
                System.out.print(node.val + " ");

                if(node.left != null)
                    q.add(node.left);
                if(node.right != null)
                    q.add(node.right);
            }
            System.out.println();
        }
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


    //Level order traversal can be modified to check whether a tree is Complete or not. To understand the approach,
    //define the term ‘Full Node’. A node is ‘Full Node’ if both left and right children are not empty (or not NULL).
    //The approach is to do a level order traversal starting from the root. In the traversal, once a node is found which
    //is NOT a Full Node, all the following nodes must be leaf nodes.

    //The idea is for every dequeued node, check if it is a full node (have both left and right children). If a node is
    // found that is not a full node, i.e., either it has no children or only one child, then all the remaining nodes in
    // the queue should not have any children. If anyone has a child, then its not a complete binary tree; otherwise,
    // it is

    public boolean isComplete(TreeNode root){
        //Empty tree is true
        if (root == null){
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Create a flag variable which will be set true
        // when a non full node is seen
        boolean flag = false;

        while(!queue.isEmpty()){
            TreeNode curr = queue.poll();

            // if we have encountered a non-full node before and the current node
            // is not a leaf, a tree cannot be complete
            if (flag && (curr.left != null || curr.right != null)) {
                return false;
            }

            // if the left child is empty and the right child exists,
            // a tree cannot be complete
            if (curr.right != null && curr.left == null){
                return false;
            }

            if (curr.left != null){
                queue.add(curr.left);
            }  // if the current node is a non-full node, set the flag to true
            else {
                flag = true;
            }

            if (curr.right != null){
                queue.add(curr.right);
            }  // if the current node is a non-full node, set the flag to true
            else {
                flag = true;
            }
        }

        return true;
    }

    public boolean isCompleteAlt(TreeNode root) {
        //Empty tree is true
        if (root == null) {
            return true;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        // Create a flag variable which will be set true
        // when a non full node is seen
        boolean flag = false;

        // Do level order traversal using queue.
        //enQueue(queue, &rear, root);
        while(!queue.isEmpty())
        {
            TreeNode curr =queue.poll();

            if(curr == null){
                // If we have seen a NULL node,
                // we set the flag to true
                flag = true ;
            }else{
                // If that NULL node
                // is not the last node
                // then return false
                if(flag == true){
                    return false ;
                }
                // Push both nodes
                // even if there are null
                queue.add(curr.left);
                queue.add(curr.right);
            }
        }

        // If we reach here, then the
        // tree is complete Binary Tree
        return true;
    }

    public static void main(String[] args) {
        IsCompleteBinarytree completeBinaryTree = new IsCompleteBinarytree();

        int[] values = {22,30,10,36,77,33,40};
        Arrays.sort(values);

        TreeNode root = completeBinaryTree.sortedArrayToBST(values,0,values.length-1);

        List<Integer> list = new ArrayList<>();
        System.out.println("Inorder:");
        completeBinaryTree.traverseInOrder(root,list);
        completeBinaryTree.printList(list);
        completeBinaryTree.printLevelOrder(root);

        System.out.println("The binary tree is full : "+completeBinaryTree.isComplete(root));
        System.out.println("The binary tree is full using alt method 2: "+completeBinaryTree.isCompleteAlt(root));
        completeBinaryTree.insertIteratively(root,67);
        System.out.println("The binary tree is full : "+completeBinaryTree.isComplete(root));
        System.out.println("The binary tree is full using alt method 2: "+completeBinaryTree.isCompleteAlt(root));

        completeBinaryTree.insertIteratively(root,0);
        completeBinaryTree.insertIteratively(root,12);
        completeBinaryTree.insertIteratively(root,32);
        completeBinaryTree.insertIteratively(root,27);
        completeBinaryTree.insertIteratively(root,35);
        completeBinaryTree.insertIteratively(root,37);
        System.out.println("level order print out ");
        completeBinaryTree.printLevelOrder(root);
        System.out.println("The binary tree is full : "+completeBinaryTree.isComplete(root));
        System.out.println("The binary tree is full using alt method 2: "+completeBinaryTree.isCompleteAlt(root));

    }
}
