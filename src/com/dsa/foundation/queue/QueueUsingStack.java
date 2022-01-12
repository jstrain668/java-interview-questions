package com.dsa.foundation.queue;

//Design a queue that supports enqueue and dequeue operations using standard push and pop operations of the stack

//We know that queue is a (FIFO) data structure in which elements are removed in the same order in which they were added
// to the queue. In an enqueue operation, items are added to the rear of the queue, while in dequeue operation, items
// are removed from the front of the queue

//Solution: To enqueue an item into the queue, push the item into the first stack.
//To dequeue an item from the queue, move elements from the first stack to the second stack if it is empty, and return
// the top item from the second stack.

//Reference: https://www.techiedelight.com/implement-a-queue-using-stack-data-structure/

import java.util.Stack;

public class QueueUsingStack<T> {

    private Stack<T> s1, s2;

    // Constructor
    QueueUsingStack()
    {
        s1 = new Stack<>();
        s2 = new Stack<>();
    }

    // Add an item to the queue
    public void enqueue(T data)
    {
        // push item into the first stack
        s1.push(data);
    }

    // Remove an item from the queue
    public T dequeue()
    {
        // if both stacks are empty
        if (s1.isEmpty() && s2.isEmpty())
        {
            System.out.println("Underflow!!");
            System.exit(0);
        }

        // if the second stack is empty, move elements from the first stack to it
        if (s2.isEmpty())
        {
            while (!s1.isEmpty()) {
                s2.push(s1.pop());
            }
        }

        // return the top item from the second stack
        return s2.pop();
    }

    public static void main(String[] args) {
        int[] keys = { 1, 2, 3, 4, 5 };
        QueueUsingStack<Integer> q = new QueueUsingStack<>();

        // insert above keys
        for (int key: keys) {
            q.enqueue(key);
        }

        System.out.println(q.dequeue());    // print 1
        System.out.println(q.dequeue());    // print 2
    }
}
