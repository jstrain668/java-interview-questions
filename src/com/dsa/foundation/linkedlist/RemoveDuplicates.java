package com.dsa.foundation.linkedlist;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

//Reference; https://www.techiedelight.com/remove-duplicates-sorted-linked-list/

public class RemoveDuplicates {

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

    //Time Complexity: O(n)
    //Space complexity: O(n)
    public ListNode removeDuplicates(ListNode head){

        if (head == null){
            return null;
        }

        ListNode current = head;
        ListNode prev = null;
        Set<Integer> set = new HashSet<>();

        while (current != null){

            // if the current node is seen before, ignore it
            if (set.contains(current.val)){
                prev.next = current.next;
            } else { // insert the current node into the set and proceed to the next node
                set.add(current.val);
                prev = current;
            }
            current = prev.next;
        }

        return head;
    }

    //Time Complexity: O(n)
    //Space complexity: O(1)
    public ListNode removeDuplicatesFromSortedList(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode current = head;

        while (current.next != null){

            if (current.val == current.next.val){
                current.next = current.next.next;
            } else{
                current = current.next; //only advance if no deletion (no duplicate)
            }
        }
        return head;
    }

    public void deleteAll(){

        if (head == null){
            return;
        }

        ListNode next = null;
        while (head != null){
            next = head.next;
            head = null;
            head = next;
        }
    }

    public static void main(String[] args) {
        RemoveDuplicates rd = new RemoveDuplicates();
        int[] nums = {3, 7, 9, 3,45, 56, 87, 99,87};
        for (int num : nums) {
            rd.append(num);
        }
        rd.printList(rd.head);
        rd.removeDuplicates(rd.head);
        rd.printList(rd.head);
        rd.deleteAll();
        rd.printList(rd.head);
        int[] nums2 = {3, 7,3,9,45,56,87,9,99};
        Arrays.sort(nums2);
        for (int num : nums2) {
            rd.append(num);
        }
        rd.printList(rd.head);
        rd.removeDuplicatesFromSortedList(rd.head);
        rd.printList(rd.head);
    }
}
