package com.dsa.medium.linkedlist;

import java.util.Arrays;

//Reference: https://just4once.gitbooks.io/leetcode-notes/content/leetcode/linked-list/109-convert-sorted-list-to-binary-search-tree.html

public class ConvertLListToBST {
    ListNode head;
    ListNode tail;

    public ListNode append(int val){

        ListNode newNode = new ListNode(val);

        if (head == null){
            head = newNode;
            tail = head;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        return newNode;
    }

    public void printList(ListNode node){

        ListNode current = node;

        while (current != null){
            System.out.print(current.val+",");
            current = current.next;
        }
        System.out.println();
    }

    public void printInOrder(TreeNode root){

        if (root != null){
            printInOrder(root.left);
            System.out.println(root.val);
            printInOrder(root.right);
        }
    }

    public TreeNode sortedListToBST(ListNode head,ListNode tail){

       //Base condition, exit when head equals tail
        if (head == tail){
            return null;
        }

        //Use Fast and Slow ptrs to find mid point of head and tail
        ListNode slow = head;
        ListNode fast = head;

        while (fast != tail && fast.next != tail){
            slow = slow.next;
            fast = fast.next.next;
        }

        TreeNode root = new TreeNode(slow.val);
        root.left = sortedListToBST(head,slow);
        root.right = sortedListToBST(slow.next,tail);

        return root;
    }

    public TreeNode sortedListToBST(ListNode head){

        if (head == null){
            return null;
        }
        return sortedListToBST(head,null);
    }

    public static void main(String[] args) {
        ConvertLListToBST ll = new ConvertLListToBST();
        int[] nums1 = {5,7,3,4,20,0,33};
        Arrays.sort(nums1);
        for (int num : nums1){
            ll.append(num);
        }
        ll.printList(ll.head);

        TreeNode root = ll.sortedListToBST(ll.head);
        ll.printInOrder(root);
    }
}
