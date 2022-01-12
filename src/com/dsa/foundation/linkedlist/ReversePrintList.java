package com.dsa.foundation.linkedlist;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

public class ReversePrintList {

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

    public void reversePrint(ListNode head){
        if (head == null){
            return;
        }

        Stack<Integer> stack = new Stack<>();
        ListNode current = head;

        while (current != null){
            stack.push(current.val);
            current = current.next;
        }

        while (!stack.isEmpty()){
            System.out.println(stack.pop());
        }

    }

    public static void main(String[] args) {
        ReversePrintList rpl = new ReversePrintList();
        int[] nums = {3,7,9,45,56,87,99};
        Arrays.sort(nums);
        for (int num : nums){
            rpl.append(num);
        }
        rpl.printList(rpl.head);

        rpl.reversePrint(rpl.head);

    }
}
