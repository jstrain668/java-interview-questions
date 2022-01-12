package com.dsa.foundation.linkedlist;

import java.util.Arrays;

//Reference: https://iq.opengenus.org/check-if-linked-list-is-sorted/

public class SortedList {

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

    public boolean isSortedDescIterative(ListNode head){

        if (head == null){
            return true;
        }

        ListNode current = head;

        while (current.next != null){
            if (current.val < current.next.val){
                return false;
            }
            current = current.next;
        }
        return true;
    }

    public boolean isSortedAscIterative(ListNode head){

        if (head == null){
            return true;
        }

        ListNode current = head;

        while (current.next != null){
            if (current.val > current.next.val){
                return false;
            }
            current = current.next;
        }
        return true;
    }

    public boolean isSortedDescRecursive(ListNode head) {

        //This is the base condition of the recursive code. If the head becomes NULL or the next element to the head
        // points to NULL, we return true which means the linked list is sorted and the condition to check for sorted
        // element has not returned false yet.
        if (head == null || head.next == null) {
            return true;
        }

        return ((head.val > head.next.val) && isSortedDescRecursive(head.next));
    }

    public boolean isSortedAscRecursive(ListNode head) {

        //This is the base condition of the recursive code. If the head becomes NULL or the next element to the head
        // points to NULL, we return true which means the linked list is sorted and the condition to check for sorted
        // element has not returned false yet.
        if (head == null || head.next == null) {
            return true;
        }

        return ((head.val < head.next.val) && isSortedAscRecursive(head.next));
    }

    public static void main(String[] args) {
        SortedList sl = new SortedList();
        int[] nums1 = {3, 7, 9, 45, 56, 87, 99};
        Arrays.sort(nums1);
        for (int num : nums1) {
            sl.append(num);
        }
        sl.printList(sl.head);

        if (sl.isSortedDescIterative(sl.head)){
            System.out.println("Linked list is sorted in descending order");
        } else{
            System.out.println("Linked list is not sorted in descending order");
        }

        if (sl.isSortedAscIterative(sl.head)){
            System.out.println("Linked list is sorted in ascending order");
        } else{
            System.out.println("Linked list is not sorted in ascending order");
        }

        if (sl.isSortedDescRecursive(sl.head)){
            System.out.println("Linked list is sorted in descending order");
        } else{
            System.out.println("Linked list is not sorted in descending order");
        }

        if (sl.isSortedAscRecursive(sl.head)){
            System.out.println("Linked list is sorted in ascending order");
        } else{
            System.out.println("Linked list is not sorted in ascending order");
        }


        SortedList sl1 = new SortedList();
        int[] nums2 = {99, 87, 56, 45, 9, 7, 3};
        for (int num : nums2) {
            sl1.append(num);
        }
        sl1.printList(sl1.head);

        if (sl1.isSortedDescIterative(sl1.head)){
            System.out.println("Linked list is sorted in descending order");
        } else{
            System.out.println("Linked list is not sorted in descending order");
        }

        if (sl1.isSortedAscIterative(sl1.head)){
            System.out.println("Linked list is sorted in ascending order");
        } else{
            System.out.println("Linked list is not sorted in ascending order");
        }

        if (sl1.isSortedDescRecursive(sl1.head)){
            System.out.println("Linked list is sorted in descending order");
        } else{
            System.out.println("Linked list is not sorted in descending order");
        }

        if (sl1.isSortedAscRecursive(sl1.head)){
            System.out.println("Linked list is sorted in ascending order");
        } else{
            System.out.println("Linked list is not sorted in ascending order");
        }
    }
}
