package com.dsa.medium.linkedlist;

public class KToLastNode {

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

    public ListNode kToLastNode(ListNode head,int k){
        if (head == null){
            return null;
        }

        ListNode p1 = head;
        ListNode p2 = head;

        for (int i=0; i < k; i++){
            if (p1 == null){
                return null;
            }
            p1 = p1.next;
        }

        while (p1 != null){
            p1 = p1.next;
            p2 = p2.next;
        }
        return p2;
    }

    public static void main(String[] args) {
        KToLastNode kln = new KToLastNode();
        int[] nums1 = {1,2,3,4,6,9,10,12,20,25};
        for (int num : nums1) {
            kln.append(num);
        }
        kln.printList(kln.head);

        int k = 111;
        ListNode kHead = kln.kToLastNode(kln.head,k);
        kln.printList(kHead);
    }
}
