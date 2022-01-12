package com.dsa.foundation.linkedlist;

public class DLListOperations {

    DListNode head;
    DListNode tail;

    public DListNode append(int val){

        DListNode newNode = new DListNode(val);

        if (head == null){
            head = newNode;
            tail = head;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        return newNode;
    }

    public DListNode prepend(int val){
        DListNode newNode = new DListNode(val);

        if (head == null){
            head = newNode;
            tail = head;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }

        return newNode;
    }

    public DListNode insertAfter(DListNode prev,int val){

        if (prev == null){
            System.out.println("Cannot insert after null node");
            return null;
        }

        DListNode newNode = new DListNode(val);
        newNode.next = prev.next;
        newNode.prev = prev;
        prev.next = newNode;

        return newNode;
    }

    public void printList(DListNode node){

        DListNode current = node;

        while (current != null){
            System.out.print(current.val+",");
            current = current.next;
        }
        System.out.println();
    }

    public DListNode findNode(int val){
        DListNode current = this.head;

        if (current == null){
            return null;
        }

        while (current != null){

            if (current.val == val){
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public void deleteNode(int val){

        DListNode findNode = findNode(val);

        if (findNode == null){
            return;
        }

        DListNode pTemp;
        DListNode nTemp;
        //Check if head node
        if (findNode.prev == null){
            this.head = findNode.next;
            this.head.prev = null;
            this.head.next = findNode.next.next;
        } else if (findNode.next == null){ // Check if tail node
            this.tail = findNode.prev;
            this.tail.next = null;
            this.tail.prev = findNode.prev.prev;
        } else{
            pTemp = findNode.prev;
            nTemp = findNode.next;
            pTemp.next = nTemp;
            nTemp.prev = pTemp;
        }
    }

    public static void main(String[] args) {
        DLListOperations dList = new DLListOperations();
        int[] nums1 = {3,7,9,45,50,56,99,100};
        for (int num : nums1){
            dList.prepend(num);
        }
        dList.printList(dList.head);

        dList.deleteNode(3);
        dList.printList(dList.head);

        dList.deleteNode(100);
        dList.printList(dList.head);

        dList.deleteNode(50);
        dList.printList(dList.head);

        dList.deleteNode(50);
        dList.printList(dList.head);
    }
}
