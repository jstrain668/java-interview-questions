package com.dsa.foundation.linkedlist;

public class  GetNthNodeFromEnd {

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

    public ListNode prepend(int val){
        ListNode newNode = new ListNode(val);

        if (head == null){
            head = newNode;
            tail = head;
        } else {
            newNode.next = head;
            head = newNode;
        }

        return newNode;
    }

    public ListNode insertAfter(ListNode prev,int val){

        if (prev == null){
            System.out.println("Cannot insert after null node");
            return null;
        }

        ListNode newNode = new ListNode(val);
        newNode.next = prev.next;
        prev.next = newNode;

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

    private int getLength(ListNode head){

        if (head == null){
            return 0;
        }

        return 1 + getLength(head.next);
    }

    public ListNode returnNthFromEnd(ListNode head,int n){

        if (head == null){
            return null;
        }

        int len = getLength(head);

        if (n > len){
            System.out.println(n+" is greater than size of list "+len);
            return null;
        }

        int nodePos = len - n + 1;
        ListNode current = head;

        for (int i=1; i < nodePos; i++){
            current = current.next;
        }

        return current;
    }


    // Solve this problem in a single list traversal only. The idea is to start from the head node and move a pointer
    // n nodes ahead in the given list. Then, take another pointer starting from the head node and run both pointers in
    // parallel till the first pointer reaches the end of the list. Now, the second pointer will point to the k'th
    // node from the end
    // Time Complexity: O(n) - one traversal, comprised of n moves
    // Space Complexity: O(1)
    public ListNode returnNthFromEnd2(ListNode head,int n) {

        if (head == null) {
            return null;
        }

        ListNode current = head;
        int count = 0;

        //Move n nodes ahead in list
        while (current != null && count < n){
            current = current.next;
            count++;
        }

        // return if `n` is more than the total number of nodes in the list
        if (current == null) {
            return null;
        }


        // move the `ref` and `curr` parallely till `curr` reaches the list's end
        ListNode ref = head;
        while (current != null){
            ref = ref.next;
            current = current.next;
        }

        return ref;
    }

    public static void main(String[] args) {
        GetNthNodeFromEnd go1 = new GetNthNodeFromEnd();
        int[] nums = {3, 7, 9,45, 56, 87, 99,87};
        for (int num : nums) {
            go1.append(num);
        }
        go1.printList(go1.head);

        int n = 1;
        ListNode node = go1.returnNthFromEnd(go1.head,n);
        System.out.println(n+" node from end of list "+node.val);

        n= 2;
        node = go1.returnNthFromEnd2(go1.head,n);
        System.out.println(n+" node from end of list "+node.val);
    }
}
