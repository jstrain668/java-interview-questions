package com.dsa.foundation.hashmaps;

import java.util.HashSet;
import java.util.Set;

//https://www.geeksforgeeks.org/union-and-intersection-of-two-linked-lists/

public class UnionAndIntersection {

    Node head; // head of list

    /* Linked list Node*/
    class Node {
        int data;
        Node next;
        Node(int d)
        {
            data = d;
            next = null;
        }
    }

    public Node push(int val){

        Node newNode = new Node(val);

        if (head == null){
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }

        return head;
    }


    public Node push(Node head,int val){

        Node newNode = new Node(val);

        if (head == null){
            head = newNode;
        } else {
            newNode.next = head;
            head = newNode;
        }

        return head;
    }

    public Node getIntersection(Node head1,Node head2){
        if (head1 == null || head2 == null){
            return null;
        }

        Node intersection = null;
        Set<Integer> set = new HashSet<>();

        Node curr1 = head1;
        while (curr1 != null){
            set.add(curr1.data);
            curr1 = curr1.next;
        }

        Node curr2 = head2;
        while (curr2 != null){
            if (set.contains(curr2.data)){
                Node newNode = new Node(curr2.data);
                if (intersection == null){
                    intersection = newNode;
                } else{
                    newNode.next = intersection;
                    intersection = newNode;
                }
            }
            curr2 = curr2.next;
        }

        return intersection;
    }

    public Node getUnion(Node head1,Node head2){
        if (head1 == null && head2 == null){
            return null;
        }

        if (head1 == null){
            return head2;
        }

        if (head2 == null){
            return head1;
        }

        Node union = null;
        Set<Integer> set = new HashSet<>();

        Node curr1 = head1;
        while (curr1 != null){
            set.add(curr1.data);
            curr1 = curr1.next;
        }

        Node curr2 = head2;
        while (curr2 != null){
            set.add(curr2.data);
            curr2 = curr2.next;
        }

        for (int val : set){
            union = push(union,val);
        }

        return union;
    }

    public void printList(Node head){
        Node curr = head;

        while (curr != null){
            System.out.print(curr.data+" ");
            curr = curr.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        UnionAndIntersection unit1 = new UnionAndIntersection();
        UnionAndIntersection unit2 = new UnionAndIntersection ();

        /*create a linked lists 10->15->5->20 */
        unit1.push(20);
        unit1.push(4);
        unit1.push(15);
        unit1.push(10);

        /*create a linked lists 8->4->2->10 */
        unit2.push(10);
        unit2.push(2);
        unit2.push(4);
        unit2.push(8);

        Node intersect = unit1.getIntersection(unit1.head, unit2.head);
        Node union = unit1.getUnion(unit1.head, unit2.head);

        System.out.println("First List is");
        unit1.printList(unit1.head);

        System.out.println("Second List is");
        unit2.printList(unit2.head);

        System.out.println("Intersection List is");
        unit1.printList(intersect);

        System.out.println("Union List is");
        unit1.printList(union);
    }
}
