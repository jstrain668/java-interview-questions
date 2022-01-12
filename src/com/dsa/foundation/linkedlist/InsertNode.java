package com.dsa.foundation.linkedlist;

//Reference; https://www.geeksforgeeks.org/linked-list-set-2-inserting-a-node/

public class InsertNode {

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

    public ListNode getNode(int val){

        ListNode current = head;

        while (current!=null){
            if (val == current.val){
                return current;
            }
            current = current.next;
        }

        return null;
    }

    public static void main(String[] args) {
        InsertNode in = new InsertNode();
        int[] nums = {3,7,9,45,56,99};
        for (int num : nums){
            in.prepend(num);
        }
        ListNode prev = in.getNode(56);
        in.insertAfter(prev,66);

        in.printList(in.head);

    }
}
