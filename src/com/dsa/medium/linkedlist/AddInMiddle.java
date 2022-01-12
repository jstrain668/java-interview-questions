package com.dsa.medium.linkedlist;

//Reference: https://www.geeksforgeeks.org/insert-node-middle-linked-list/

public class AddInMiddle {

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

    public ListNode getMiddle(ListNode head){

        if (head == null){
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null){
            fast = fast.next;
            if (fast!=null && fast.next != null){
                slow = slow.next;
                fast = fast.next;
            }
        }

        return slow;
    }

    public void addInMiddle(ListNode head,int val){

        if (head == null){
            this.head = new ListNode(val);
            this.tail = this.head;
            return;
        }

        ListNode mid = getMiddle(head);
        ListNode newNode = new ListNode(val);

        if (mid.next != null) {
            newNode.next = mid.next;
            mid.next = newNode;
        }else{
            tail.next = newNode;
            tail = newNode;
        }
    }

    public static void main(String[] args) {
        AddInMiddle aim = new AddInMiddle();
        int[] nums1 = {1,2,3,4};
        for (int num : nums1){
            aim.append(num);
        }
        aim.printList(aim.head);

        aim.addInMiddle(aim.head,50);
        aim.printList(aim.head);
    }
}
