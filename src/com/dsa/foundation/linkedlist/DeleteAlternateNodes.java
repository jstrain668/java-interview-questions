package com.dsa.foundation.linkedlist;

//Reference: https://www.geeksforgeeks.org/delete-alternate-nodes-of-a-linked-list/

import java.util.Arrays;

public class DeleteAlternateNodes {
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

    public ListNode delAlternateNode(ListNode head){

        if (head == null){
            return null;
        }
        ListNode prev = head;
        ListNode now = head.next;

        while (prev != null && now != null)
        {
            /* Change next link of previous node */
            prev.next = now.next;

            /* Free node */
            now = null;

            /*Update prev and now */
            prev = prev.next;
            if (prev != null)
                now = prev.next;
        }

        return head;
    }

    public static void main(String[] args) {
        DeleteAlternateNodes dan = new DeleteAlternateNodes();
        int[] nums1 = {3,7,9,56,87,99,100,101};
        Arrays.sort(nums1);
        for (int num : nums1){
            dan.append(num);
        }
        dan.printList(dan.head);

        ListNode delAlt = dan.delAlternateNode(dan.head);
        dan.printList(delAlt);

    }
}
