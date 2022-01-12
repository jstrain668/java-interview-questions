package com.dsa.foundation.linkedlist;

//import java.util.Stack;
//Reference: https://leetcode.com/problems/add-two-numbers/

public class AddTwoNumbers {

    ListNode head;
    ListNode tail;
    /**
     * Definition for singly-linked list */
    public class ListNode {
          int val;
          ListNode next;
          ListNode() {}
          ListNode(int val) { this.val = val; }
          ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }

    public void append(int val){

        ListNode newNode = new ListNode(val);

        if (this.head == null){
            this.head = newNode;
            this.tail = this.head;
        } else{
            this.tail.next = newNode;
            this.tail = newNode;
        }

    }

    /*
    private int convertToInteger(Stack stack){

        int value =0;
        int powerValue = stack.size()-1;

        while (!stack.empty()){
            int digit = (int) stack.pop();
            value += (digit * (int) Math.pow(10d,powerValue));
            powerValue--;
        }

        return value;
    } */

    //Description: Approach: Traverse both lists and One by one pick nodes of both lists and add the
    // values. If the sum is more than 10 then make carry as 1 and reduce sum. If one list has more
    // elements than the other then consider the remaining values of this list as 0.
    //1.Traverse the two linked lists from start to end
    //2.Add the two digits each from respective linked lists.
    //3.If one of the lists has reached the end then take 0 as its digit.
    //4.Continue it until both the end of the lists.
    //5.If the sum of two digits is greater than 9 then set carry as 1 and the current digit as sum % 10
    //Time Complexity: O(m + n), where m and n are numbers of nodes in first and second lists respectively.
    //The lists need to be traversed only once.
    //Space Complexity: O(m + n).
    //A temporary linked list is needed to store the output number
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {

        ListNode dummyHead = new ListNode(0);
        ListNode current = dummyHead;
        ListNode p = l1;
        ListNode q = l2;
        int carry = 0;

        while (p!=null || q!=null){
            int x = (p!=null)? p.val : 0;
            int y = (q!=null)? q.val : 0;
            int sum = carry + x + y;
            carry = sum/10;
            current.next = new ListNode(sum%10);
            current = current.next;
            if (p!=null){
                p = p.next;
            }
            if (q!=null){
                q = q.next;
            }
        }

        if (carry > 0){
            current.next = new ListNode(carry);
        }
        return  dummyHead.next;

    }

    public static void main(String[] args) {
        AddTwoNumbers atn1 = new AddTwoNumbers();
        AddTwoNumbers atn2 = new AddTwoNumbers();
        AddTwoNumbers atn3 = new AddTwoNumbers();
        int[] nums1 = {2,4,3};
        int[] nums2 = {5,6,4};

        for (int i=0; i < nums1.length; i++){
            atn1.append(nums1[i]);
        }

        for (int i=0; i < nums2.length; i++){
            atn2.append(nums2[i]);
        }

        atn3.head = atn1.addTwoNumbers(atn1.head, atn2.head);
    }
}
