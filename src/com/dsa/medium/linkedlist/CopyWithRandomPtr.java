package com.dsa.medium.linkedlist;


import java.util.HashMap;
import java.util.Map;

public class CopyWithRandomPtr {

    ListNodeRandom head;
    ListNodeRandom tail;

    public ListNodeRandom append(int val){

        ListNodeRandom newNode = new ListNodeRandom(val);

        if (head == null){
            head = newNode;
            tail = head;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        return newNode;
    }

    public void printList(ListNodeRandom node){

        ListNodeRandom current = node;

        while (current != null){
            System.out.print(current.val+",");
            current = current.next;
        }
        System.out.println();
    }

    public void createListWithRandom(){

        ListNodeRandom node1 = append(7);
        ListNodeRandom node2 = append(13);
        ListNodeRandom node3 = append(11);
        ListNodeRandom node4 = append(10);
        ListNodeRandom node5 = append(1);

        node1.random = null;
        node2.random = node1;
        node3.random = node5;
        node4.random = node3;
        node5.random = node1;

    }

    public ListNodeRandom duplicateWithRandom(ListNodeRandom head){

        if (head == null){
            return null;
        }

        ListNodeRandom node = head;
        Map<ListNodeRandom,ListNodeRandom> nodeMap = new HashMap<>();

        while (node != null){
            ListNodeRandom newNode = new ListNodeRandom(node.val);
            nodeMap.put(node,newNode);
            node = node.next;
        }

        for (Map.Entry<ListNodeRandom,ListNodeRandom> entry: nodeMap.entrySet()){
            entry.getValue().next = entry.getKey().next;
            entry.getValue().random = entry.getKey().random;
        }

        return nodeMap.get(head);
    }

    public ListNodeRandom duplicateWithRandom2(ListNodeRandom head) {

        if (head == null) {
            return null;
        }

        ListNodeRandom current = head;

        //Create the duplicate list with the current list. Every second node is the dup node of the preceding node
        while (current != null){
            ListNodeRandom dupNode = new ListNodeRandom(current.val);
            dupNode.next = current.next;
            current.next = dupNode;
            current = dupNode.next;
        }

        current = head;

        //Set the random ptr
        while (current != null){
            current.next.random = (current.random != null)? current.random.next : null;
            current = current.next.next;
        }

        current = head;
        ListNodeRandom dupHead = head.next;
        ListNodeRandom dupNode = dupHead;

       while (current != null){
           current.next = current.next.next;
           dupNode.next = (dupNode.next != null)? dupNode.next.next : null;
           current = current.next;
           dupNode = dupNode.next;
       }
       return dupHead;
    }

    public static void main(String[] args) {
        CopyWithRandomPtr random = new CopyWithRandomPtr();
        random.createListWithRandom();
        random.printList(random.head);
        ListNodeRandom dupHead = random.duplicateWithRandom(random.head);
        random.printList(dupHead);
        ListNodeRandom secondHead = random.duplicateWithRandom2(random.head);
        random.printList(secondHead);
    }
}
