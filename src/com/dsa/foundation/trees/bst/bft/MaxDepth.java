package com.dsa.foundation.trees.bst.bft;

import com.dsa.foundation.trees.bst.TreeNode;

import java.util.*;

public class MaxDepth {

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

    public void traverseInOrder(TreeNode node,List<Integer> list){

        if (node != null){
            traverseInOrder(node.left,list);
            list.add(node.val);
            traverseInOrder(node.right,list);
        }
    }

    public void printList(List<Integer> list){

        for (int val : list){
            System.out.print(val+" ");
        }
        System.out.println();
    }

    // Description: Using BFS (iterative) to perform level order traversal of the tree. At the end of the
    // traversal both left and right pointers are null return depth value
    // Use Queue, each level node(s) are added and previous removed. With each iteration the depth level
    // is incremented until no more nodes, the queue is empty.
    // Time Complexity: O(n) Visit each node to reach the bottom (leaf nodes)
    // Space Complexity: O(n) for the queue
    // Reference: https://medium.com/@harycane/maximum-depth-of-a-binary-tree-609d129fa571
    public int maxDepthBFS(TreeNode root){

        if (root == null){
            return 0;
        }

        int depthLevel = 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){

            int size = queue.size();
            depthLevel++;
            for (int i=0; i < size; i++){

                TreeNode curr = queue.poll();

                if (curr.left != null){
                    queue.add(curr.left);
                }
                if (curr.right != null){
                    queue.add(curr.right);
                }
            }
        }
        return depthLevel;
    }

    public static void main(String[] args) {
        MaxDepth bst = new MaxDepth();

        int[] values = {22,34,0,36,77,99,33,35};
        Arrays.sort(values);

        TreeNode root = bst.sortedArrayToBST(values,0,values.length-1);
        List<Integer> list = new ArrayList<>();


        System.out.println("Inorder:");
        bst.traverseInOrder(root,list);
        bst.printList(list);

        System.out.println("Max depth of BST: "+bst.maxDepthBFS(root));
        bst.insertNodeIteratively(root,67);
        bst.insertNodeIteratively(root,75);
        System.out.println("Max depth of BST: "+bst.maxDepthBFS(root));
        System.out.println("Height of BST (null tree): "+bst.maxDepthBFS(null));
        TreeNode temp = new TreeNode(0);
        System.out.println("Height of single node BST: "+bst.maxDepthBFS(temp));
    }
}
