package com.dsa.foundation.trees.bst.convert;

import java.util.ArrayList;
import java.util.Arrays;

//Reference: https://leetcode.com/problems/convert-sorted-array-to-binary-search-tree/

public class ConvertArrayToBST {

    SingleLinkNode head, tail;
    SingleLinkNode current;

    //Definition for singly linked list node
    public class SingleLinkNode{
        int val;
        SingleLinkNode next;
        SingleLinkNode(int val){
            this.val = val;
            next = null;
        }
    }

    //Definition for a binary tree node.
    public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    public void insertAtFront(int value){

        if (this.head == null){
            this.head = new SingleLinkNode(value);
            this.tail = this.head;
        } else {
            //Create new node with specified value
            SingleLinkNode newNode = new SingleLinkNode(value);

            //New node points to old head via next ptr
            newNode.next = head;

            //New node becomes head
            this.head = newNode;
        }
    }

    public void insertAtEnd(int value){

        if (this.head == null){
            this.head = new SingleLinkNode(value);
            this.tail = this.head;
        } else {
            //Create new node with specified value
            SingleLinkNode newNode = new SingleLinkNode(value);

            //Old tail next points to newNode
            this.tail.next = newNode;

            //New node becomes tail
            this.tail = newNode;
        }
    }

    private TreeNode convert(int[] nums,int low, int high,TreeNode root){

        // Base exit condition.
        if (low > high){
            return root;
        }

        //Find the middle element of the current range
        int mid = (low+high)/2;

        //Construct a new node for middle element and assign it to root
        root = new TreeNode(nums[mid]);

        //left subtree of root contains elements less than middle element
        root.left = convert(nums, low,mid-1,root.left);

        //right subtree of root contains elements greater than middle element
        root.right = convert(nums,mid+1,high,root.right);

        return root;
    }

    public ArrayList<Integer> inOrder(TreeNode root, ArrayList<Integer> output){

        //Traverse left subtree until reach null
        if (root.left != null){
            inOrder(root.left,output);
        }

        //Add value to ArrayList in ascending order
        output.add(root.val);

        //Traverse right subtree until reach null
        if (root.right != null){
            inOrder(root.right,output);
        }
        return output;
    }

    public boolean isSorted(int[] nums){
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] > nums[i + 1])
                return false;
        }
        return true;
    }

    //Description: Use Binary search algorithm to create BST
    //1) Get the Middle of the array and make it root.
    //2) Recursively do same for left half and right half.
    //      a) Get the middle of left half and make it left child of the root
    //          created in step 1.
    //      b) Get the middle of right half and make it right child of the
    //          root created in step 1.
    // Time Complexity: O(n) = 2T(n/2) + c
    // Space Complexity: O(n) - system stack

    public TreeNode convertToBST(int[] nums, int left, int right){

        //Base condition to exit recursive loop
        if (left > right){
            return null;
        }

        int mid = left + (right - left) / 2;

        TreeNode node = new TreeNode(nums[mid]);
        node.left = convertToBST(nums, left,mid-1);
        node.right = convertToBST(nums,mid+1,right);

        return node;
    }

    public TreeNode sortedArrayToBST(int[] nums){
          if (!isSorted(nums)){
              Arrays.sort(nums);
          }
          //return convert(nums,0,nums.length-1,null);
          return convertToBST(nums,0,nums.length-1);
    }

    public void constructFromArray(){
        int[] nums = new int[] {-3,-10,0,5,9};
        System.out.println("Source array for height balanced BST: "+Arrays.toString(nums));

        TreeNode root = sortedArrayToBST(nums);
        ArrayList<Integer> output = new ArrayList<>();
        output = inOrder(root,output);
        System.out.println("Output array for height balanced BST: "+output);
    }

    public TreeNode treeify(int low, int high){

        if (high < low){
            return null;
        }

        int mid = (low + high) / 2;

        TreeNode node = new TreeNode();
        node.left = treeify(low,mid-1);
        node.val = current.val;
        current = current.next;
        node.right = treeify(mid+1,high);

        return node;
    }

    // https://dev.to/seanpgallivan/solution-convert-sorted-list-to-binary-search-tree-2i0e#idea
    public TreeNode sortedSingledLinkedListToBST(SingleLinkNode head){

        int nodeCount = 0;
        current = head;

        while (current != null){
            current = current.next;
            nodeCount++;
        }

        current = head;
        return treeify(1,nodeCount);
    }

    public void constructFromSingleLinkedList(){
        insertAtFront(-10);
        insertAtEnd(-3);
        insertAtEnd(0);
        insertAtEnd(5);
        insertAtEnd(9);

        TreeNode root = sortedSingledLinkedListToBST(this.head);
        ArrayList<Integer> output = new ArrayList<>();
        output = inOrder(root,output);
        System.out.println("Output array for height balanced BST: "+output);

    }

    public static void main(String[] args) {
        ConvertArrayToBST hbBST = new ConvertArrayToBST();
        hbBST.constructFromArray();
        hbBST.constructFromSingleLinkedList();
    }

}
