package com.dsa.foundation.linkedlist;


//Amazon Question: Find kth node from the end of a linked list. Write a function/method to find the kth node from the
// end of a singly linked list.

//Reference: https://www.techiedelight.com/find-kth-node-from-the-end-linked-list/

public class KNodeInLinkedList {

    ListNode head;
    ListNode tail;

    public class ListNode{
        ListNode next;
        int val;
        public ListNode(int val){
            this.val = val;
        }
    }

    public void append(int val){

        ListNode newNode = new ListNode(val);

        if (this.head == null){
            this.head = newNode;
            this.tail = this.head;
        } else {
            this.tail.next = newNode;
            this.tail = newNode;
        }
    }

    // Find the size of the list and check if k is within the size of the list. If yes return node otherwise null.
    // Time Complexity: O(n) - two traversals of the linked list, one to get its size and the other to move to the kth
    // node in the list O(n) + O(n-k+1)
    // Space Complexity: O(1)
    public ListNode findKNode(ListNode head,int k){

        if (head == null){
            return null;
        }

        ListNode current = head;
        int len = 0;

        while (current != null){
            current = current.next;
            len++;
        }

        if (len < k){
            return null;
        }

        int KIndex = len - k + 1;
        current = head;

        for (int i=1; i < KIndex; i++){
            current = current.next;
        }

        return current;
    }

    // Solve this problem in a single list traversal only. The idea is to start from the head node and move a pointer
    // k nodes ahead in the given list. Then, take another pointer starting from the head node and run both pointers in
    // parallel till the first pointer reaches the end of the list. Now, the second pointer will point to the k'th
    // node from the end
    // Time Complexity: O(n) - one traversal, comprised of n moves
    // Space Complexity: O(1)
    public ListNode findKNodeTwoPtr(ListNode head,int k) {

        if (head == null){
            return null;
        }

        ListNode curr = head;

        // move `k` nodes ahead in the linked list
        for (int i = 0; curr != null && i < k; i++) {
            curr = curr.next;
        }

        // return if `k` is more than the total number of nodes in the list
        if (curr == null) {
            return null;
        }

        // move the `head` and `curr` parallely till `curr` reaches the list's end
        while (curr != null)
        {
            head = head.next;
            curr = curr.next;
        }

        // `head` will now contain the k'th node from the end
        return head;

    }

    public static void main(String[] args) {
        KNodeInLinkedList linkedList = new KNodeInLinkedList();
        linkedList.append(5);
        linkedList.append(10);
        linkedList.append(15);
        linkedList.append(20);
        linkedList.append(25);
        linkedList.append(30);

        int k = 4;
        ListNode kNode = linkedList.findKNode(linkedList.head,4);
        if (kNode == null){
            System.out.println(k+"th node not found in list");
        } else {
            System.out.println(k+"th node found in linked list with a value of "+kNode.val);
        }

        kNode = linkedList.findKNodeTwoPtr(linkedList.head,4);
        if (kNode == null){
            System.out.println(k+"th node not found in list");
        } else {
            System.out.println(k+"th node found in linked list with a value of "+kNode.val);
        }

    }
}
