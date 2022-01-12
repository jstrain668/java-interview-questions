package com.dsa.foundation.linkedlist;

import java.util.Arrays;

public class MergeSortedLists {

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

    //https://cheonhyangzhang.gitbooks.io/leetcode-solutions/content/21_merge_two_sorted_lists__easy.html
    public ListNode mergeSortedLists2(ListNode head1,ListNode head2){

        ListNode fakeNode = new ListNode(-1);
        ListNode node = fakeNode;
        while (head1 != null && head2 != null){
            if (head1.val < head2.val){
                node.next = head1;
                head1 = head1.next;
                node = node.next;
            }//if
            else{
                node.next = head2;
                head2 = head2.next;
                node = node.next;
            }//else
        }//while l1 & l2

        if (head1 != null){
            node.next = head1;
        }
        else if (head2 != null){
            node.next = head2;
        }
        return fakeNode.next;
    }

    public ListNode mergeSortedLists(ListNode head1,ListNode head2){

        if (head1 == null && head2 == null){
            return null;
        }

        if (head1 == null){
            return head2;
        }

        if (head2 == null){
            return head1;
        }

        ListNode mergedHead = null;

        if (head1.val < head2.val){
            mergedHead = head1;
            head1 = head1.next;
        } else {
            mergedHead = head2;
            head2 = head2.next;
        }

        ListNode mergedTail = mergedHead;

        while (head1 != null && head2 != null){

            ListNode temp;
            if (head1.val < head2.val){
                temp = head1;
                head1 = head1.next;
            } else {
                temp = head2;
                head2 = head2.next;
            }
            mergedTail.next = temp;
            mergedTail = temp;
        }

        if (head1 != null){
            mergedTail.next = head1;
        }

        if (head2 != null){
            mergedTail.next = head2;
        }

        return mergedHead;
    }


    public static void main(String[] args) {

        MergeSortedLists msl1 = new MergeSortedLists();
        int[] nums1 = {3,7,9,45,56,87,99};
        Arrays.sort(nums1);
        for (int num : nums1){
            msl1.append(num);
        }

        MergeSortedLists msl2 = new MergeSortedLists();
        int[] nums2 = {0,5,10,40,50,88,100};
        Arrays.sort(nums2);
        for (int num : nums2){
            msl2.append(num);
        }

        ListNode s3 = msl1.mergeSortedLists(msl1.head, msl2.head);
        msl1.printList(s3);

        //ListNode s3 = msl1.mergeSortedLists2(msl1.head,msl2.head);
        //msl1.printList(s3);

    }
}
