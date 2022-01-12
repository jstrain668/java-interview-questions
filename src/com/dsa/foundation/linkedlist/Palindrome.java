package com.dsa.foundation.linkedlist;

import java.util.Arrays;

public class Palindrome {

    DListNode head;
    DListNode tail;

    public DListNode append(int val){

        DListNode newNode = new DListNode(val);

        if (head == null){
            head = newNode;
            tail = head;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        return newNode;
    }

    public DListNode prepend(int val){
        DListNode newNode = new DListNode(val);

        if (head == null){
            head = newNode;
            tail = head;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        return newNode;
    }

    public DListNode insertAfter(DListNode prev,int val){

        if (prev == null){
            System.out.println("Cannot insert after null node");
            return null;
        }

        DListNode newNode = new DListNode(val);
        newNode.next = prev.next;
        newNode.prev = prev;
        prev.next = newNode;

        return newNode;
    }

    public void printList(DListNode node){

        DListNode current = node;

        while (current != null){
            System.out.print(current.val+",");
            current = current.next;
        }
        System.out.println();
    }

    public boolean isPalindrome(DListNode left){

        if (left == null){
            return true;
        }

        DListNode right = left;

        //Set right to point to last node in list
        while (right.next != null){
            right = right.next;
        }

        while (left != right){

            if (left.val != right.val){
                return false;
            }

            left = left.next;
            right = right.prev;
        }
        return true;

    }

    public static void main(String[] args) {
        Palindrome p1 = new Palindrome();
        int[] nums1 = {3,7,9,45,56,99};
        for (int num : nums1){
            p1.prepend(num);
        }
        p1.printList(p1.head);

        Palindrome p2 = new Palindrome();
        int[] nums2 = {3,7,9,45,56,99};
        for (int num : nums2){
            p2.append(num);
        }
        p2.printList(p2.head);

        Palindrome p3 = new Palindrome();
        int[] nums3 = {4,7,5,7,3};
        for (int num : nums3){
            p3.append(num);
        }
        p3.printList(p3.head);
        if (p3.isPalindrome(p3.head)){
            System.out.println(Arrays.toString(nums3)+ " is a palindrome");
        } else {
            System.out.println(Arrays.toString(nums3)+ " is not a palindrome");
        }
    }
}
