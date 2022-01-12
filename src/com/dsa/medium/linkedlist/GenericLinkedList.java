package com.dsa.medium.linkedlist;

//Reference: https://www.geeksforgeeks.org/how-to-implement-generic-linkedlist-in-java/
//Reference: https://www.netsurfingzone.com/datastructures/singly-linked-list-implementation-using-generics-in-java/

public class GenericLinkedList<T> {

    ListNode<T> head;
    ListNode<T> tail;

    public class ListNode<T> {
        ListNode<T> next;
        T val;

        public ListNode(T val) {
            this.val = val;
        }

        @Override
        public String toString() {
            return val.toString();
        }

    }

    public ListNode<T> append(T val){

        ListNode<T> newNode = new ListNode<>(val);

        if (head == null){
            head = newNode;
            tail = head;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        return newNode;
    }

    public ListNode<T> prepend(T val){
        ListNode<T> newNode = new ListNode<>(val);

        if (head == null){
            head = newNode;
            tail = head;
        } else {
            newNode.next = head;
            head = newNode;
        }

        return newNode;
    }

    public void printList(ListNode<T> node){

        ListNode<T> current = node;

        while (current != null){
            System.out.print(current.val+",");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {

        GenericLinkedList<Integer> gll1 = new GenericLinkedList<>();
        int[] nums1 = {3,7,9,45,56,99};
        for (int num : nums1){
            gll1.prepend(num);
        }
        gll1.printList(gll1.head);

        GenericLinkedList<Integer> gll2 = new GenericLinkedList<>();
        int[] nums2 = {0,5,10,44,58,98};
        for (int num : nums2){
            gll2.append(num);
        }
        gll2.printList(gll2.head);
    }
}
