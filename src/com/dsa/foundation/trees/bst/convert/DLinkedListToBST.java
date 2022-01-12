package com.dsa.foundation.trees.bst.convert;

import com.dsa.foundation.trees.bst.TreeNode;

import java.util.Arrays;

//Reference: https://algorithms.tutorialhorizon.com/convert-a-sorted-doubly-linked-list-to-balanced-bst/
//Reference: https://dev.to/seanpgallivan/solution-convert-sorted-list-to-binary-search-tree-2i0e#idea

public class DLinkedListToBST {

    ListNode head;
    ListNode tail;
    ListNode curr;
    int size;

    class ListNode{
        ListNode next;
        ListNode prev;
        int val;

        public ListNode(int val){
            this.val = val;
            next = null;
            prev = null;
        }
    }

    public ListNode createDLList(int[] nums){

        if (nums == null || nums.length == 0){
            return null;
        }

        //Append to the end of the list
        for (int i : nums){

            ListNode newNode = new ListNode(i);

            if (head == null){
                head = newNode;
            } else{
                ListNode curr = head;

                while (curr.next != null){
                    curr = curr.next;
                }

                //curr is now the the last node which the newNode can be appended to
                curr.next = newNode;
                newNode.prev = curr;
            }
            size++;
        }
        return head;
    }

    public ListNode createDLList2(int[] nums){

        if (nums == null || nums.length == 0){
            return null;
        }

        //Append to the end of the list
        for (int i : nums){

            ListNode newNode = new ListNode(i);

            if (head == null){
                head = newNode;
                tail = newNode;
            } else{
                newNode.prev = tail;
                tail.next = newNode;
                tail = newNode;
            }
            size++;
        }
        return head;
    }

    public void printDLList(){

        curr = head;
        while (curr != null){
            System.out.print(curr.val+" ");
            curr = curr.next;
        }
        curr = head;
        System.out.println();
    }

    public void inOrder(TreeNode root){

        if (root != null){
            inOrder(root.left);
            System.out.print(root.val+" ");
            inOrder(root.right);
        }
    }

    //In order to build a height-balanced binary tree, we need to ensure that roughly half of the total
    //number of nodes are on either side of the root, and the only way to know what half of the total
    //number of nodes is requires finding the total number of nodes first.
    //We can complete this problem in O(N) time with only O(log N) extra space (in excess of the output space).

    //First, we'll have to iterate once through the linked list to count the total number of nodes (count).
    //Then, we can define our recursive method (convertToBST()) using index numbers as our arguments. Even
    //though we won't be able to access the listnodes directly by index number, we can take advantage of
    //an inorder tree traversal to force our access to go in iterative order.

    //We'll need to have our list pointer (curr) have global scope in order to update properly via recursion.
    //In an inorder traversal, we recursively process the left subtree,then process the middle node,
    //then recursively process the right subtree. For this solution, we'll just need to make sure we move
    // curr to curr.next at the end of processing the middle node.
    //
    //We can then return the full tree built by our recursive method.

    // Time Complexity: O(N) where N is the length of the linked list
    // Space Complexity: O(log N) in excess of the space needed for the input/output, due to the recursion
    // stack

    private TreeNode convertToBST(int start,int end){

        if (start > end){
            return null;
        }

        int mid = start + (end - start)/2;

        TreeNode root = new TreeNode();
        root.left = convertToBST(start,mid-1);
        root.val = curr.val;
        curr = curr.next;
        root.right = convertToBST(mid+1,end);

        return root;
    }

    public TreeNode convertToBST(int size){

        return convertToBST(1,size);
    }

    public static void main(String[] args) {
        DLinkedListToBST dllToBST = new DLinkedListToBST();
        int[] nums = {1,2,3,4,5,6,7};
        Arrays.sort(nums);

        ListNode head = dllToBST.createDLList2(nums);
        dllToBST.printDLList();

        TreeNode root = dllToBST.convertToBST(dllToBST.size);
        dllToBST.inOrder(root);

    }
}
