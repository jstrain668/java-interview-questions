package com.dsa.foundation.queue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//Reference: https://www.geeksforgeeks.org/reversing-first-k-elements-queue/

//Solution: The idea is to use an auxiliary stack.
//
//Create an empty stack.
//One by one dequeue first K items from given queue and push the dequeued items to stack.
//Enqueue the contents of stack at the back of the queue
//Dequeue (size-k) elements from the front and enque them one by one to the same queue.

public class ReverseKElements {

    Queue<Integer> q = new LinkedList<>();

    public Queue<Integer> reverseKElements(Queue<Integer> q,int k){

        if (q.isEmpty() || k > q.size()){
            return new LinkedList<>();
        }

        if (k <= 0){
            return new LinkedList<>();
        }

        // Push the first K elements into a Stack
        Stack<Integer> s = new Stack<>();
        int count =0;
        while (count < k){
            s.push(q.remove());
            count++;
        }

        // Enqueue the contents of stack at the back
        // of the queue
        while (!s.isEmpty()){
            q.add(s.pop());
        }

        // Remove the remaining elements and enqueue
        // them at the end of the Queue
        for (int i = 0; i < q.size() - k; i++) {
            q.add(q.remove());
        }
        return q;
    }

    public static void main(String[] args) {
        ReverseKElements rke = new ReverseKElements();
        int[] nums = {1,2,3,4,5,6,7,8,9,10};
        for (int num: nums){
            rke.q.add(num);
        }

        int k = 5;
        Queue<Integer> queue = rke.reverseKElements(rke.q,k);
        while (!queue.isEmpty()){
            System.out.print(queue.remove()+",");
        }
        System.out.println();
    }
}
