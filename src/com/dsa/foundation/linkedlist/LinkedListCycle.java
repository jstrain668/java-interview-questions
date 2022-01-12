package com.dsa.foundation.linkedlist;

import java.util.HashSet;

//Reference: https://leetcode.com/problems/linked-list-cycle/

public class LinkedListCycle {

    ListNode head;
    ListNode tail;
    //  Definition for singly-linked list.
    public class ListNode {
        int val;
        ListNode next;
        ListNode(int x) {
            val = x;
            next = null;
        }
    }

    public void prepend(int val){

        ListNode newNode = new ListNode(val);

        if (this.head == null){
            this.head = newNode;
            this.tail = this.head;
        } else{
            this.head.next = head;
            this.head = newNode;
        }
    }

    public void append(int val){

        ListNode newNode = new ListNode(val);

        if (this.head == null){
            this.head = newNode;
            this.tail = this.head;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
    }
    public ListNode lookUpNode(int val){
          ListNode current = this.head;

          while (current != null){
              if (current.val == val){
                  return current;
              }
              current = current.next;
          }
          return null;
    }

    public void insertCycleLink(ListNode fromNode, ListNode toNode){

          fromNode.next = toNode;
    }

    // Description: To detect a cycle a repeated node each node is checked to see if its repeated. The repeat check is
    // enacted with a HashSet, flags if the node object is already in the HashSet. Traverse through the list from the
    // head node until a cycle is found or not use the ListNode next pointer.
    // Time Complexity: O(n)
    // Space Complexity: O(n)
    public boolean hasCycle(ListNode head) {

        if (head == null || head.next == null){
            return false;
        }

        HashSet<ListNode> nodes = new HashSet<>();

        while (head != null){

            if (nodes.contains(head)){
                return true;
            } else {
                nodes.add(head);
            }
            head = head.next;
        }
        return false;
    }

    // Description: To detect a cycle a repeated node each node is checked to see if its repeated.
    // Traverse linked list using two pointers.
    // Move one pointer(slowPtr) by one and another pointer(fastPtr) by two.
    // If these pointers meet at the same node then there is a loop.
    // If pointers do not meet then linked list doesn’t have a loop.
    // Time Complexity: O(n)
    // Space Complexity: O(1)
    public boolean hasCycleFastCheck(ListNode head) {

        if (head == null || head.next == null) {
            return false;
        }

        ListNode slowPtr = head;
        ListNode fastPtr = head;

        while (slowPtr != null && fastPtr != null && fastPtr.next != null){
            slowPtr = slowPtr.next;
            fastPtr = fastPtr.next.next;
            if (slowPtr == fastPtr) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        LinkedListCycle llc = new LinkedListCycle();
        int[] nums = {3,2,0,-4};
        for (int i=0; i < nums.length; i++){
            llc.append(nums[i]);
        }

        ListNode node = llc.lookUpNode(-4);
        if (node != null){
            llc.insertCycleLink(node,llc.head.next);
        }
        System.out.println("Does linked list have cycle? "+llc.hasCycleFastCheck(llc.head));
    }
}
