package com.dsa.foundation.queue;

import java.util.ArrayList;
import java.util.List;

public class QueueUsingFixedArray {
    private int fixedSize;

    private int count;
    private int head;
    private int tail;
    private List<Object> headList;
    private List<Object> tailList;

    public QueueUsingFixedArray(int fixedSize) {
        this.fixedSize = fixedSize;
        this.count = 0;
        this.head = 0;
        this.tail = 0;
        this.headList = new ArrayList<>();
        this.tailList = this.headList;
    }

    public void offer(int num) {
        if (tail == fixedSize - 1) {
            List<Object> newList = new ArrayList<>();
            newList.add(num);
            tailList.add(newList);
            tailList = (List<Object>) tailList.get(tail);
            tail = 0;
        } else {
            tailList.add(num);
        }
        count++;
        tail++;
    }

    public Integer poll() {
        if (count == 0) {
            return null;
        }

        int num = (int) headList.get(head);
        head++;
        count--;

        if (head == fixedSize - 1) {
            List<Object> newList = (List<Object>) headList.get(head);
            headList.clear();
            headList = newList;
            head = 0;
        }

        return num;
    }

    public int size() {
        return count;
    }

    public Integer peek() {
        if (count == 0) {
            return null;
        }

        return (int) headList.get(head);
    }

    public boolean isEmpty(){
        return count == 0;
    }

    public static void main(String[] args) {
        // create a queue of capacity 5
        QueueUsingFixedArray q = new QueueUsingFixedArray(5);

        q.offer(1);
        q.offer(2);
        q.offer(3);
        q.offer(4);
        q.offer(5);
        q.offer(6);
        q.offer(7);
        q.offer(8);
        q.offer(9);
        q.offer(10);
        q.offer(11);

        System.out.println("The queue size is " + q.size());

        System.out.println("The front element is " + q.peek());
        q.poll();
        System.out.println("The front element is " + q.peek());

        System.out.println("The queue size is " + q.size());

        q.poll();
        q.poll();

        System.out.println("The queue size is " + q.size());
        System.out.println("The front element is " + q.peek());
        q.poll();
        q.poll();

        if (q.isEmpty()) {
            System.out.println("The queue is empty");
        }
        else {
            System.out.println("The queue is not empty");
        }
    }
}
