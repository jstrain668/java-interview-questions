package com.dsa.medium.linkedlist;

//https://medium.com/nerd-for-tech/leetcode-remove-nth-node-from-end-of-list-3e7902c4c2af

public class DelNNodeFromEnd {

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

    public ListNode removeNthNodeFromEnd(ListNode head,int n){

        if (head == null){
            return null;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (n>0){
            fast = fast.next;
            n--;
        }

        // if fast is null it means the first node is supposed to be removed
        if (fast == null){
            head = head.next;
            return head;
        }

        while (fast.next != null){
            fast = fast.next;
            slow = slow.next;
        }

        if (slow.next != null && slow.next.next != null){
            slow.next = slow.next.next;
        } else{
            slow.next = null;
        }
        return head;
    }

    public static void main(String[] args) {
        DelNNodeFromEnd dnfe = new DelNNodeFromEnd();
        int[] nums1 = {1,2,3,4,5,6,7,8};
        for (int num : nums1){
            dnfe.append(num);
        }
        dnfe.printList(dnfe.head);

        int n = 2;
        ListNode node = dnfe.removeNthNodeFromEnd(dnfe.head,n);
        dnfe.printList(node);


    }
}
