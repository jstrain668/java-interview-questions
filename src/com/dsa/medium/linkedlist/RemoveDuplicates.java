package com.dsa.medium.linkedlist;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

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

    public ListNode removeDuplicatesFromSortedList(ListNode head){

        if (head == null){
            return head;
        }

        ListNode current = head;

        while (current.next != null){

            if (current.val == current.next.val){
                current.next = current.next.next;
            } else{
                current = current.next;
            }
        }

        return head;
    }

    public ListNode removeDuplicatesWithHashSet(ListNode head){

        if (head == null){
            return null;
        }

        ListNode current = head;
        ListNode prev = null;
        Set<Integer> set = new HashSet<>();

        while (current != null){

            if (set.contains(current.val)){
                prev.next = current.next;
            } else{
                set.add(current.val);
                prev = current;
            }
            current = prev.next;

        }
        return head;
    }

    public ListNode removeDuplicates(ListNode head) {

        if (head == null) {
            return null;
        }

        ListNode current = head;
        while (current != null){
            ListNode runner = current;
            while (runner.next != null){
                if (current.val == runner.next.val){
                    runner.next = runner.next.next;
                } else{
                    runner = runner.next;
                }
            }
            current = current.next;
        }
        return head;

    }
    public static void main(String[] args) {
        RemoveDuplicates rd = new RemoveDuplicates();
        int[] nums = {3, 7, 9, 3,45, 56, 87, 99,87};
        Arrays.sort(nums);
        for (int num : nums) {
            rd.append(num);
        }
        rd.printList(rd.head);
        ListNode node = rd.removeDuplicatesFromSortedList(rd.head);
        rd.printList(node);

        int[] nums2 = {3, 7,3,9,45,56,87,9,99};
        Arrays.sort(nums2);
        for (int num : nums2) {
            rd.append(num);
        }
        rd.printList(rd.head);
        rd.removeDuplicatesFromSortedList(rd.head);
        rd.printList(rd.head);

        RemoveDuplicates rd3 = new RemoveDuplicates();
        int[] nums3 = {3,7,3,9,45,56,99,9,99};
        for (int num : nums3) {
            rd3.append(num);
        }
        rd3.printList(rd3.head);
        rd3.removeDuplicatesWithHashSet(rd3.head);
        rd3.printList(rd3.head);

        RemoveDuplicates rd4 = new RemoveDuplicates();
        int[] nums4 = {3,7,3,9,45,56,99,9,99};
        for (int num : nums4) {
            rd4.append(num);
        }
        rd4.printList(rd4.head);
        rd4.removeDuplicates(rd4.head);
        rd4.printList(rd4.head);
    }
}
