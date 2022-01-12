package com.dsa.foundation.hashmaps;

import java.util.HashSet;
import java.util.Set;

//Question: Remove Duplicate from a Linked List using Hashing
//Reference: https://leetcode.com/problems/remove-duplicates-from-sorted-list/



public class RemoveDuplicate {

    class ListNode {
        int val;
        ListNode next;
        public ListNode(int val){
            this.val = val;
            next = null;
        }
    }


    public ListNode createLinkedList(){

        ListNode root = new ListNode(2);

        root.next = new ListNode(2);
        root.next.next = new ListNode(7);
        root.next.next.next = new ListNode(9);
        root.next.next.next.next = new ListNode(13);
        root.next.next.next.next.next = new ListNode(7);

        return root;
    }

    public void printList(ListNode head){
        ListNode curr = head;

        while (curr != null){
            System.out.print(curr.val+" ");
            curr = curr.next;
        }
        System.out.println();
    }

    public ListNode removeDuplicate(ListNode head){
        ListNode curr = head;
        Set<Integer> set = new HashSet<>();

        ListNode prev = null;
        while (curr != null){
            if (!set.contains(curr.val)){
                set.add(curr.val);
                prev = curr;
            } else{
               prev.next = curr.next;
            }
            curr = curr.next;
        }
        return head;
    }

    public static void main(String[] args) {
        RemoveDuplicate rd = new RemoveDuplicate();
        ListNode head = rd.createLinkedList();
        rd.printList(head);

        head = rd.removeDuplicate(head);
        rd.printList(head);
    }
}
