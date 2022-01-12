package com.dsa.foundation.queue;

//Reference: https://www.techiedelight.com/queue-implementation-using-linked-list/

public class LinkedListQueue {
    ListNode front;
    ListNode rear;

    //add to the end/rear of the queue
    public void enqueue(int val){

        ListNode newNode = new ListNode(val);

        if (isEmpty()){
            front = newNode;
            rear = newNode;
        } else{
            rear.next = newNode;
            rear = newNode;
        }
    }

    //remove from the front/start of the queue
    public ListNode dequeue(){

        if (isEmpty()){
            return null;
        }
        ListNode node = front;

        System.out.println("Removing "+node.val);

        // advance front to the next node
        front = front.next;

        // if the list becomes empty
        if (front == null) {
            rear = null;
        }

        // optionally return the removed item
        return node;
    }

    public ListNode peek(){
        if (isEmpty()){
            return null;
        } else{
            return front;
        }
    }

    public boolean isEmpty(){

        return front == null && rear == null;
    }

    public void printQueue(){
        if (!isEmpty()){
            ListNode current = front;
            while (current != null){
                System.out.print(current.val+",");
                current = current.next;
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        LinkedListQueue llq = new LinkedListQueue();
        llq.enqueue(10);
        llq.enqueue(20);
        llq.dequeue();
        llq.dequeue();
        llq.printQueue();
        llq.enqueue(30);
        llq.enqueue(40);
        llq.enqueue(50);
        llq.printQueue();

        llq.dequeue();
        System.out.println("Queue Front : " + llq.front.val);
        System.out.println("Queue Rear : " + llq.rear.val);
        llq.printQueue();
    }

}
