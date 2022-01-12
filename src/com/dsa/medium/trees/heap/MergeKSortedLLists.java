package com.dsa.medium.trees.heap;

//Question: https://leetcode.com/problems/merge-k-sorted-lists/

//Reference: https://www.techiedelight.com/efficiently-merge-k-sorted-linked-lists/

import java.util.Comparator;
import java.util.PriorityQueue;

public class MergeKSortedLLists {

    public class ListNode {
      int val;
      ListNode next;
      ListNode() {}
      ListNode(int val) { this.val = val; }
      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public ListNode createList1(){

        ListNode node = new ListNode(1);
        node.next = new ListNode(5);
        node.next.next = new ListNode(7);

        return node;
    }

    public ListNode createList2(){

        ListNode node = new ListNode(2);
        node.next = new ListNode(3);
        node.next.next = new ListNode(6);
        node.next.next.next = new ListNode(9);

        return node;
    }

    public ListNode createList3(){

        ListNode node = new ListNode(4);
        node.next = new ListNode(8);
        node.next.next = new ListNode(10);

        return node;
    }

    // The main function to merge given `k` sorted linked lists.
    // It takes array `lists` of size `k` and generates the sorted output

    //The heap has size k at any point, and we pop and push exactly n times, where n is the total number of nodes.
    // Since each pop/push operation takes O(log(k)) time, the overall time complexity of this solution is O(n.log(k)).
    public ListNode mergeKLists(ListNode[] lists)
    {
        // create an empty min-heap using a comparison object for ordering the min-heap
        PriorityQueue<ListNode> pq = new PriorityQueue<>(new Comparator<ListNode>() {
            @Override
            public int compare(ListNode o1, ListNode o2) {
                return Integer.compare(o1.val, o2.val);
            }
        });

        for (ListNode node : lists){
            pq.add(node);
        }

        // take two pointers, head and tail, where the head points to the first node
        // of the output list and tail points to its last node
        ListNode head = null, last = null;

        // run till min-heap is empty
        while (!pq.isEmpty())
        {
            // extract the minimum node from the min-heap
            ListNode min = pq.poll();

            // add the minimum node to the output list
            if (head == null) {
                head = last = min;
            }
            else {
                last.next = min;
                last = min;
            }

            // take the next node from the "same" list and insert it into the min-heap
            if (min.next != null) {
                pq.add(min.next);
            }
        }

        // return head node of the merged list
        return head;
    }

    public void printList(ListNode head){

        while (head != null){
            System.out.print(head.val+" ");
            head = head.next;
        }
        System.out.println();
    }
    public static void main(String[] s)
    {

        MergeKSortedLLists lLists = new MergeKSortedLLists();
        int k = 3;    // total number of linked lists

        // an array to store the head nodes of the linked lists
        ListNode[] lists = new ListNode[k];

        lists[0] = lLists.createList1();
        lists[1] = lLists.createList2();
        lists[2] = lLists.createList3();

        // Merge all lists into one
        ListNode head = lLists.mergeKLists(lists);
        lLists.printList(head);
    }
}
