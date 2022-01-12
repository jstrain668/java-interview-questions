package com.dsa.foundation.linkedlist;

import java.util.HashSet;
import java.util.Set;

public class SetOperations {

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

    private void addToSet(ListNode head,Set<Integer> union){

        ListNode current = head;
        while (current != null){
            if (!union.contains(current.val)){
                union.add(current.val);
            }
            current = current.next;
        }
    }

    public ListNode union(ListNode head1,ListNode head2){
        if (head1 == null && head2 == null){
            return null;
        }
        if (head1 == null){
            return head2;
        }
        if (head2 == null){
            return head1;
        }

        Set<Integer> union = new HashSet<>();
        addToSet(head1,union);
        addToSet(head2,union);

        for (Integer val: union){
            append(val);
        }

        return this.head;
    }

    public ListNode difference(ListNode head1,ListNode head2){
        if (head1 == null && head2 == null){
            return null;
        }
        if (head1 == null){
            return head2;
        }
        if (head2 == null){
            return head1;
        }

        Set<Integer> set1 = new HashSet<>();
        addToSet(head1,set1);

        Set<Integer> set2 = new HashSet<>();
        addToSet(head2,set2);

        //Set difference
        set1.removeAll(set2);

        for (Integer val: set1){
            append(val);
        }

        return this.head;
    }

    public ListNode intersect(ListNode head1,ListNode head2){
        if (head1 == null || head2 == null){
            return null;
        }

        Set<Integer> set1 = new HashSet<>();
        addToSet(head1,set1);

        Set<Integer> set2 = new HashSet<>();
        addToSet(head2,set2);

        set1.retainAll(set2);

        for (Integer val: set1){
            append(val);
        }

        return this.head;
    }

    public static void main(String[] args) {
        SetOperations so1 = new SetOperations();
        int[] nums = {3, 7, 9,45, 56, 87, 99,87};
        for (int num : nums) {
            so1.append(num);
        }
        so1.printList(so1.head);

        SetOperations so2 = new SetOperations();
        int[] nums2 = {0, 3, 10,40, 51, 67, 89,99};
        for (int num : nums2) {
            so2.append(num);
        }
        so2.printList(so2.head);

        //Union
        SetOperations so3 = new SetOperations();
        so3.head = so3.union(so1.head,so2.head);
        so3.printList(so3.head);

        //Intersection
        SetOperations so4 = new SetOperations();
        so4.head = so4.intersect(so1.head,so2.head);
        so4.printList(so4.head);

        //Difference (so1/so2)
        SetOperations so5 = new SetOperations();
        so5.head = so5.difference(so1.head,so2.head);
        so5.printList(so5.head);

        //Difference (so2/so1)
        SetOperations so6 = new SetOperations();
        so6.head = so6.difference(so2.head,so1.head);
        so6.printList(so6.head);
    }
}
