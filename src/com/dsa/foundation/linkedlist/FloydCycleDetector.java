package com.dsa.foundation.linkedlist;

//Reference: https://javabypatel.blogspot.com/2017/05/floyds-cycle-detection-algorithm-in-java.html

public class FloydCycleDetector {

    Node startNode;

    public class Node{

        private int data;
        private Node next;

        public Node(int data) {
            this.data = data;
        }
        public int getData() {
            return data;
        }
        public void setData(int data) {
            this.data = data;
        }
        public Node getNext() {
            return next;
        }
        public void setNext(Node next) {
            this.next = next;
        }
    }

    public Node createNode(int data){
        return new Node(data);
    }

    public boolean isLoopPresent(Node startNode){
        Node slowPointer = startNode; // Initially ptr1 is at starting location.
        Node fastPointer = startNode; // Initially ptr2 is at starting location.

        while(fastPointer!=null && fastPointer.getNext()!=null){ // If ptr2 encounters NULL, it means there is no Loop in Linked list.
            slowPointer = slowPointer.getNext(); // ptr1 moving one node at at time
            fastPointer = fastPointer.getNext().getNext(); // ptr2 moving two nodes at at time

            if(slowPointer==fastPointer) // if ptr1 and ptr2 meets, it means linked list contains loop.
                return true;
        }
        return false;
    }

    public Node getStartNodeOfLoopInLinklist(Node startNode){
        Node tortoisePointer = startNode; // Initially ptr1 is at starting location.
        Node harePointer = startNode; // Initially ptr2 is at starting location.

        // If ptr2 encounters NULL, it means there is no Loop in Linked list.
        while(harePointer!=null && harePointer.getNext()!=null){
            tortoisePointer = tortoisePointer.getNext(); // ptr1 moving one node at at time
            harePointer = harePointer.getNext().getNext(); // ptr2 moving two nodes at at time

            // if ptr1 and ptr2 meets, it means linked list contains loop.
            if(tortoisePointer==harePointer){

                //After meet, moving tortoisePointer to start node of list.
                tortoisePointer = startNode;

                //Moving tortoisePointer and harePointer one node at a time till the time they meet at common point.
                while(tortoisePointer!=harePointer){
                    tortoisePointer = tortoisePointer.getNext();
                    harePointer = harePointer.getNext();
                }

                //returning start node of loop.
                return tortoisePointer;

            }
        }

        // this condition will arise when there is no loop in list.
        return null;
    }

    private Node detectAndRemoveLoopInLinkedList(Node startNode) {
        Node slowPointer=startNode;
        Node fastPointer=startNode;
        Node previousPointer=null;

        while(fastPointer!=null && fastPointer.getNext()!=null){
            slowPointer = slowPointer.getNext();
            previousPointer = fastPointer.getNext(); // For capturing just previous node of loop node for setting it to null for breaking loop.
            fastPointer = fastPointer.getNext().getNext();

            if(slowPointer==fastPointer){ // Loop identified.
                slowPointer = startNode;

                //If loop start node is starting at the root Node, then we slowpointer, fastpointer and head all point at same location.
                //we already capture previous node, just setting it to null will work in this case.
                if(slowPointer == fastPointer){
                    previousPointer.setNext(null);

                }else{
                    // We need to first identify the start of loop node and then by setting just previous node of loop node next to null.
                    while(slowPointer.getNext()!=fastPointer.getNext()){
                        slowPointer = slowPointer.getNext();
                        fastPointer = fastPointer.getNext();
                    }
                    fastPointer.setNext(null);
                }
            }
        }
        return startNode;
    }

    //Print linked list.
    private void printList(Node startNode){
        while(startNode!=null){
            System.out.print(startNode.getData() + " " );
            startNode=startNode.getNext();
        }
    }

    public static void main(String[] args) {
        FloydCycleDetector dLoopInLinkedList = new FloydCycleDetector();

        Node n1 = dLoopInLinkedList.createNode(10);
        Node n2 = dLoopInLinkedList.createNode(20);
        Node n3 = dLoopInLinkedList.createNode(30);
        Node n4 = dLoopInLinkedList.createNode(40);
        Node n5 = dLoopInLinkedList.createNode(50);
        Node n6 = dLoopInLinkedList.createNode(60);
        Node n7 = dLoopInLinkedList.createNode(70);
        Node n8 = dLoopInLinkedList.createNode(80);

        dLoopInLinkedList.startNode = n1;

        n1.setNext(n2);
        n2.setNext(n3);
        n3.setNext(n4);
        n4.setNext(n5);
        n5.setNext(n6);
        n6.setNext(n7);
        n7.setNext(n8);
        n8.setNext(n6);

        if(dLoopInLinkedList.isLoopPresent(dLoopInLinkedList.startNode)){
            System.out.println("Loop is present in list");
        }else{
            System.out.println("No Loop present in list");
        }

        Node loopNode = dLoopInLinkedList.getStartNodeOfLoopInLinklist(dLoopInLinkedList.startNode);

        if(loopNode==null){
            System.out.println("Loop not present");
        }else{
            System.out.println("Start node of Loop is :"+loopNode.getData());
        }

        Node newStart = dLoopInLinkedList.detectAndRemoveLoopInLinkedList(dLoopInLinkedList.startNode);
        dLoopInLinkedList.printList(newStart);
    }

}