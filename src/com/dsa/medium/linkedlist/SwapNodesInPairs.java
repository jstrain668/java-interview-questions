package com.dsa.medium.linkedlist;

import java.util.Arrays;

public class SwapNodesInPairs {

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

    public ListNode swapInPairs(ListNode head){

        if (head == null || head.next == null) {
            return head;
        }

        ListNode current = head;

        while (current != null && current.next != null){
            int temp = current.val;
            current.val = current.next.val;
            current.next.val = temp;
            current = current.next.next;
        }

        return head;
    }


    public static void main(String[] args) {
        SwapNodesInPairs snip = new SwapNodesInPairs();
        int[] nums1 = {3,7,9,45,56,87,99};
        Arrays.sort(nums1);
        for (int num : nums1){
            snip.append(num);
        }
        snip.printList(snip.head);

        ListNode swap = snip.swapInPairs(snip.head);
        snip.printList(snip.head);
    }
}
