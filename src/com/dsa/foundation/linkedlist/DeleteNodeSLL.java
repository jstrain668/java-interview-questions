package com.dsa.foundation.linkedlist;

//Reference: https://leetcode.com/problems/delete-node-in-a-linked-list/

public class DeleteNodeSLL {

    ListNode head, tail;

    public class ListNode {
      int val;
      ListNode next;
      ListNode(int x) { val = x; }
    }

    public void insert(int val){

        ListNode newNode = new ListNode(val);

        if (this.head == null){
            this.head = newNode;
            this.tail = this.head;
        } else{ //append the node
            this.tail.next = newNode;
            tail = newNode;
        }
    }

    public ListNode retrieveNode(int val){
        ListNode current = this.head;

        while (current != null){
            if (current.val == val){
                return current;
            } else{
                current = current.next;
            }
        }
        return null;
    }

    // Description: Given the node to delete and no access to head. The means of deleting is to replace
    // given node with the next node. Assign the value of next node to given node and point given node to
    // next and next.
    // Assumed that the leaf node is not passed
    // Time Complexity: O(1)
    // Space Complexity: O(1)
    public void deleteNode(ListNode node){

        ListNode current = node;
        ListNode next = current.next;

        current.val = next.val;
        current.next = next.next;
    }

    public void pintSLL(){
        ListNode current = this.head;

        while (current != null){
            System.out.print(current.val+" ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        DeleteNodeSLL dn = new DeleteNodeSLL();
        dn.insert(4);
        dn.insert(5);
        dn.insert(1);
        dn.insert(9);
        ListNode node = dn.retrieveNode(5);
        dn.deleteNode(node);
        dn.pintSLL();
    }
}
