package com.dsa.foundation.stacks;

//Question: Design a stack that supports push and pop operations using standard enqueue and dequeue operations of the queue

//Solution: To push an item into the stack, first move all elements from the first queue to the second queue, then
// enqueue the new item into the first queue, and finally move all elements back to the first queue.
// This ensures that the new item lies in front of the queue and hence would be the first one to be removed.
// To pop an item from the stack, return the front item from the first queue.

//Reference: https://www.techiedelight.com/implement-stack-using-queue-data-structure/

import java.util.ArrayDeque;
import java.util.Queue;

public class StackUsingQueue<T> {

    Queue<T> q1, q2;

    // Constructor
    public StackUsingQueue()
    {
        q1 = new ArrayDeque<>();
        q2 = new ArrayDeque<>();
    }

    // Insert an item into the stack
    void add(T data)
    {
        // Push the given item into the first queue
        q1.add(data);
    }

    // Remove the top item from the stack
    public T poll()
    {
        // if the first queue is empty
        if (q1.isEmpty())
        {
            System.out.println("Stack Underflow!!");
            System.exit(0);
        }

        // Move all elements except last from the first queue to the second queue
        T front = null;
        while (!q1.isEmpty())
        {
            if (q1.size() == 1) {
                front = q1.poll();
            }
            else {
                q2.add(q1.poll());
            }

            //q1.poll();
        }

        // Return the last element after moving all elements back to
        // the first queue
        while (!q2.isEmpty())
        {
            q1.add(q2.poll());
            //q2.poll();
        }

        return front;
    }

    public static void main(String[] args)
    {
        int[] keys = { 1, 2, 3, 4, 5 };

        // insert the above keys into the stack
        StackUsingQueue<Integer> s = new StackUsingQueue<>();
        for (int key: keys) {
            s.add(key);
        }

        for (int i = 0; i <= keys.length; i++) {
            System.out.println(s.poll());
        }
    }

}
