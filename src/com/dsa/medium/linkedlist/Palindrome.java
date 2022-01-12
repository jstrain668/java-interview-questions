package com.dsa.medium.linkedlist;

//Reference: https://java2blog.com/how-to-check-if-linked-list-is/

public class Palindrome {

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

    private ListNode middleNode(ListNode head){

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

    private ListNode reverse(ListNode node){

        ListNode current = node;
        ListNode prev = null;
        ListNode next = null;

        while (current!=null){
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        }
        return prev;
    }

    public int isPalindrome(ListNode head){

        //Empty list
        if (head == null) {
            return 1;
        }

        ListNode midNode = middleNode(head);
        ListNode secondHead = midNode.next;

        midNode.next = null;

        ListNode revSecondHead = reverse(secondHead);

        while (head !=null && revSecondHead != null){
            if (head.val == revSecondHead.val){
                head = head.next;
                revSecondHead = revSecondHead.next;
            } else{
                return 0;
            }
        }

        return 1;
    }

    public static void main(String[] args) {
        Palindrome p = new Palindrome();
        int[] nums = {1,2,3,2,1};
        for (int num : nums) {
            p.append(num);
        }
        p.printList(p.head);
        System.out.println(p.isPalindrome(p.head));

        Palindrome p1 = new Palindrome();
        int[] nums1 = {1,2,23,1};
        for (int num : nums1) {
            p1.append(num);
        }
        p1.printList(p1.head);
        System.out.println(p1.isPalindrome(p1.head));
    }
}
