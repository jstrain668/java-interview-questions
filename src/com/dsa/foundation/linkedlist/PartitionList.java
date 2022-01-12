package com.dsa.foundation.linkedlist;


import java.util.List;

public class PartitionList {

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

    public ListNode partition(ListNode head,int x){
        if (head == null){
            return null;
        }

        ListNode beforeHead = new ListNode(0);
        ListNode before = beforeHead;
        ListNode afterHead = new ListNode(0);
        ListNode after = afterHead;

        while (head != null){

            if (head.val < x){
                before.next = head;
                before = before.next;
            } else{
                after.next = head;
                after = after.next;
            }

            head = head.next;
        }

        after.next = null;
        before.next = afterHead.next;

        return beforeHead.next;
    }

    public static void main(String[] args) {
        PartitionList pl = new PartitionList();
        int[] nums1 = {3,5,8,5,10,2,1};
        int pNumber = 5;
        for (int num: nums1){
            pl.append(num);
        }
        pl.printList(pl.head);

        ListNode partition = pl.partition(pl.head,pNumber);
        pl.printList(partition);


    }
}
