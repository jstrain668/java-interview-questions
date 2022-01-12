package com.dsa.medium.linkedlist;

//Reference: https://algorithms.tutorialhorizon.com/reverse-alternative-k-nodes-in-a-linked-list/

public class ReverseKAltNodes {

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

    //Method 2: Taking ‘2k’ nodes for a iteration.
    //Take 2’k’ nodes at a time for one iteration and call rest of the recursively.
    //In every iteration-
    //Reverse the first ‘k’ nodes.
    //Skip the next ‘k’ nodes.

    public ListNode reverseAlter2KNodes(ListNode head, int k) {
        //process 2K nodes at a time
        //reverse till k nodes and set the the pointer to k+1
        int x = k;
        ListNode moving = head;
        ListNode head_prev = null;
        ListNode head_next = null;

        while (x > 0 && moving != null) {
            head_next = moving.next;
            moving.next = head_prev;
            head_prev = moving;
            moving = head_next;
            x--;
        }

        if (head != null)
            head.next = moving;

        x = k;
        while (x > 1 && moving != null) {
            moving = moving.next;
            x--;
        }

        if (moving != null) {
            moving.next = reverseAlter2KNodes(moving.next, k);
        }

        return head_prev;
    }

    public static void main(String[] args) {
        ReverseKAltNodes rkn = new ReverseKAltNodes();
        //int[] nums1 = {1, 2, 3, 4, 5, 6, 7, 8, 9};
        int[] nums1 = {1, 2};
        for (int num : nums1) {
            rkn.append(num);
        }
        rkn.printList(rkn.head);
        int k = 3;

        ListNode kNode = rkn.reverseAlter2KNodes(rkn.head,k);
        rkn.printList(kNode);
    }
}
