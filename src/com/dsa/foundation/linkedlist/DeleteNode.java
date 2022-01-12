package com.dsa.foundation.linkedlist;

//Given a ‘key’, delete the first occurrence of this key in the linked list.

//Reference: https://makeinjava.com/delete-nodes-single-linked-list-java-examplenon-recursive/

import java.util.Arrays;

public class DeleteNode {

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

    public ListNode prepend(int val){
        ListNode newNode = new ListNode(val);

        if (head == null){
            head = newNode;
            tail = head;
        } else {
            newNode.next = head;
            head = newNode;
        }

        return newNode;
    }

    public ListNode insertAfter(ListNode prev,int val){

        if (prev == null){
            System.out.println("Cannot insert after null node");
            return null;
        }

        ListNode newNode = new ListNode(val);
        newNode.next = prev.next;
        prev.next = newNode;

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

    public int findLengthRecursive(ListNode head){

        if (head == null){
            return 0;
        }

        return 1 + findLengthRecursive(head.next);
    }

    public void deleteNode(int val){

        if (head == null){
            return;
        }

        //Is the head to be deleted??
        if (head.val == val){
            head = head.next;
            return;
        }

        ListNode current = head;
        ListNode prev = null;

        //Find the node to be deleted
        while (current != null && current.val != val){
            prev = current;
            current = current.next;
        }

        //If current is null then there is no node in the list which has a value of 'val
        if (current == null){
            System.out.println("Cannot find "+val+ " in the list");
            return;
        }

        //found the node now point the prev node next ptr to current next ptr, to remove current node from list
        prev.next = current.next;
    }

    public void deleteAllNodes() {

        if (head == null) {
            return;
        }

        ListNode next = null;
        while (head != null) {
            next = head.next;
            head = null;
            head = next;
        }

    }
    public static void main(String[] args) {
        DeleteNode dn = new DeleteNode();
        int[] nums = {3,7,9,45,56,87,99};
        Arrays.sort(nums);
        for (int num : nums){
            dn.append(num);
        }

        dn.printList(dn.head);
        System.out.println("Number of nodes in linked list "+dn.findLengthRecursive(dn.head));
        dn.deleteNode(44);
        System.out.println("Number of nodes in linked list "+dn.findLengthRecursive(dn.head));
        dn.deleteNode(3);
        dn.printList(dn.head);
        System.out.println("Number of nodes in linked list "+dn.findLengthRecursive(dn.head));
        dn.deleteNode(99);
        dn.printList(dn.head);
        System.out.println("Number of nodes in linked list "+dn.findLengthRecursive(dn.head));
        dn.deleteNode(45);
        dn.printList(dn.head);
        System.out.println("Number of nodes in linked list "+dn.findLengthRecursive(dn.head));
        dn.deleteAllNodes();
        dn.printList(dn.head);
        System.out.println("Number of nodes in linked list "+dn.findLengthRecursive(dn.head));

    }
}
