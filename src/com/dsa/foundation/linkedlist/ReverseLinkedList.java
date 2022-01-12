package com.dsa.foundation.linkedlist;

import java.util.Arrays;

public class ReverseLinkedList {

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

    public ListNode reverseList(ListNode head){

        if (head == null){
            return null;
        }

        ListNode current = head;
        ListNode next = null;
        ListNode prev = null;

        while (current != null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    //In recursion Stack is used to store data.
    // Traverse linked list till we find the tail, that would be new head for reversed linked list.
    public ListNode reverseRecursively(ListNode node){

        //base case - tail of original linked list
        if(node.next == null) {
            return node;
        }
        ListNode newHead = reverseRecursively(node.next);

        //reverse the link e.g. C->D->null will be null
        node.next.next = node;
        node.next = null;

        return newHead;
    }


    public static void main(String[] args) {
        ReverseLinkedList rll = new ReverseLinkedList();
        int[] nums = {3,7,9,45,56,87,99};
        Arrays.sort(nums);
        for (int num : nums){
            rll.append(num);
        }

        rll.printList(rll.head);
        ListNode root = rll.reverseList(rll.head);
        rll.printList(root);

        root = rll.reverseRecursively(root);
        rll.printList(root);
    }
}
