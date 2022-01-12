package com.dsa.medium.linkedlist;

//reference: https://www.techiedelight.com/remove-loop-linked-list/

import java.util.Arrays;


public class DetectAndRemoveLoop {

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

    public ListNode findNodeRecursive(ListNode head, int val){
        if (head == null){
            return null;
        }

        if (head.val == val){
            return head;
        }

        return findNodeRecursive(head.next,val);
    }

    public ListNode createCircular(int val1, int val2){

        ListNode circular = findNodeRecursive(this.head,val1);
        ListNode second = findNodeRecursive(this.head,val2);

        circular.next = second;

        return circular;

    }

    public ListNode identifyCircle(ListNode head){
        if (head == null){
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while (fast!= null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
            if (fast == slow){
                return slow;
            }
        }
        return null;
    }

    public ListNode detectAndRemove(ListNode head){
        if (head == null){
            return null;
        }

        ListNode circular = identifyCircle(head);
        if (circular != null){
            System.out.println("Circular loop detected at "+circular.val);
            circular.next = null;
        } else{
            System.out.println("Circular loop not detected");
        }

        return head;
    }

    public static void main(String[] args) {
        DetectAndRemoveLoop drl = new DetectAndRemoveLoop();
        int[] nums = {3, 7, 9, 45, 56, 87, 99};
        Arrays.sort(nums);
        for (int num : nums) {
            drl.append(num);
        }
        drl.printList(drl.head);

       // ListNode circular = drl.createCircular(87,7);
        ListNode node = drl.detectAndRemove(drl.head);
        drl.printList(node);
    }
}
