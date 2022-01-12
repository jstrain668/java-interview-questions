package com.dsa.medium.linkedlist;

//Reference: https://leetcode.com/problems/merge-k-sorted-lists/
//https://www.geeksforgeeks.org/merge-k-sorted-linked-lists/
//https://www.educative.io/m/merge-two-sorted-linked-lists
//https://afteracademy.com/blog/merge-k-sorted-lists

import java.util.ArrayList;
import java.util.List;

public class MergeNSortedLists {

    ListNode head;
    ListNode tail;

    public class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public void insert(int value){

        ListNode newNode = new ListNode(value);
        if (this.head == null){
            this.head = newNode;
            this.tail = this.head;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
    }

    public ListNode mergeTwoLists(ListNode slh1, ListNode slh2){

        // if both lists are empty then merged list is also empty
        if (slh1 == null && slh2 == null){
            return null;
        }

        // if one of the lists is empty then other is the merged list
        if (slh1 == null) {
            return slh2;
        } else if (slh2 == null) {
            return slh1;
        }

        // Resolve the head of to be merged and sorted list
        ListNode mergedHead = null;
        if (slh1.val <= slh2.val) {
            mergedHead = slh1;
            slh1 = slh1.next;
        } else {
            mergedHead = slh2;
            slh2 = slh2.next;
        }

        //Tail also points to head at the beginning of a new linked list
        ListNode mergedTail = mergedHead;

        //Resolve the rest of the merged list. Both lists are processed until there are no more
        //nodes to be processed in of the lists.

        while (slh1 != null && slh2 != null){

            ListNode temp = null;
            if(slh1.val <= slh2.val){
                temp = slh1;
                slh1 = slh1.next;
            } else {
                temp = slh2;
                slh2 = slh2.next;
            }

            mergedTail.next = temp;
            mergedTail = temp;
        }

        //Append the remaining nodes of the list to the merged list
        if (slh1 != null) {
            mergedTail.next = slh1;
        } else if (slh2 != null) {
            mergedTail.next = slh2;
        }

        return mergedHead;
    }

    //Description: Convert merge n lists problem to merge 2 lists (n-1) times.
    //Following the concept of merging two lists, we can merge all the k lists taking two lists at a time.
    //Time Complexity: O(nN)(How?) where N is the number of linked lists.
    //We can merge two sorted linked lists in O(n) time where n is the total number of nodes in two lists.
    //Time Complexity: merge two sorted linked list in O(1) space

    public ListNode mergeListsOneByOne(ListNode[] lists){

        if (lists == null || lists.length == 0){
            return null;
        }

        if (lists.length == 1){
            return lists[0];
        }

        ListNode mergedHead = mergeTwoLists(lists[0],lists[1]);

        for (int i=2; i < lists.length; i++){
            mergedHead = mergeTwoLists(mergedHead,lists[i]);
        }

        return mergedHead;

    }

    //Description: The idea is to insert all the node values from all the k lists into an array. Sorting the array and
    // creating a new linked list from the sorted array will give the required output.
    //Traverse all the linked lists and collect the values of the nodes into an array.
    //Sort and iterate over this array to get the proper value of nodes.
    //Create a new sorted linked list and extend it with the new nodes.
    // Time complexity: O(Nlogn) where n is the total number of nodes and N number of lists. Collecting all the values
    // costs O(N) time.
    // Space Complexity: O(n). Creating a new linked list costs O(n) space. Also creating arraylist to store all elements
    // from n sorted linked lists into one array list. O(2n) but drop the constant to give O(n)

    public ListNode bfMergeNLists(ListNode[] lists) {

        if (lists == null || lists.length == 0){
            return null;
        }

        List<Integer> mergedArray = new ArrayList<>();

        for (int i=0; i < lists.length; i++){
            ListNode head = lists[i];

            while (head != null){
                mergedArray.add(head.val);
                head = head.next;
            }
        }

        mergedArray.sort(null);

        ListNode head = new ListNode(-1);
        ListNode temp = head;

        for(int i = 0; i < mergedArray.size(); i++) {
            ListNode newNode = new ListNode(mergedArray.get(i));
            temp.next = newNode;
            temp = temp.next;
        }
        return head.next;

    }

    public void printList(ListNode head){

        while (head != null){
            System.out.println(head.val);
            head = head.next;
        }
    }

    //Divide and Conquer: We don’t need to traverse most nodes many times repeatedly
    //Pair up n lists and merge each pair.
    //After the first pairing, n lists are merged into n/2 lists with average 2N/n length, then n/4, n/8 and so on.
    //Repeat this procedure until we get the final sorted linked list.
    //Thus, we’ll traverse almost N nodes per pairing and merging, and repeat this procedure about logN times.
    public ListNode mergeNLists(ListNode[] lists) {

        if(lists == null || lists.length == 0){
            return null;
        }

        int interval = 1;
        while(interval < lists.length){
            int i = 0;
            while(i + interval < lists.length) {
                lists[i]= mergeTwoLists(lists[i], lists[i+interval]);
                i = i + interval * 2;
            }
            interval *= 2;
        }
        return lists[0];
    }

    public static void main(String[] args) {
        MergeNSortedLists mnsl1 = new MergeNSortedLists();
        MergeNSortedLists mnsl2 = new MergeNSortedLists();
        MergeNSortedLists mnsl3 = new MergeNSortedLists();
        MergeNSortedLists mnsl4 = new MergeNSortedLists();

        mnsl1.insert(1);
        mnsl1.insert(4);
        mnsl1.insert(5);

        mnsl2.insert(1);
        mnsl2.insert(3);
        mnsl2.insert(4);

        mnsl3.insert(2);
        mnsl3.insert(6);

        mnsl4.insert(0);
        mnsl4.insert(5);
        mnsl4.insert(7);
        mnsl4.insert(9);

        ListNode[] lists = new ListNode[4];

        lists[0] = mnsl1.head;
        lists[1] = mnsl2.head;
        lists[2] = mnsl3.head;
        lists[3] = mnsl4.head;

        //ListNode mergedList = mnsl1.bfMergeNLists(lists);
        //ListNode mergedList = mnsl1.mergeListsOneByOne(lists);
        ListNode mergedList = mnsl1.mergeNLists(lists);
        mnsl1.printList(mergedList);


    }
}
