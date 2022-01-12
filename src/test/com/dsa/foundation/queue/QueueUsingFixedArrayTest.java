package com.dsa.foundation.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class QueueUsingFixedArrayTest {

    QueueUsingFixedArray queue;
    @BeforeEach
    void setUp() {
        queue = new QueueUsingFixedArray(5);
    }

    @Test
    public void test1(){
        queue.offer(1);
        queue.offer(2);
        int res = queue.poll();
        assertEquals(1, res);
        queue.offer(3);
        queue.offer(4);
        queue.offer(5);
        queue.offer(6);
        queue.offer(7);
        queue.offer(8);
        queue.offer(9);
        res = queue.poll();
        assertEquals(2, res);
        res = queue.poll();
        assertEquals(3, res);
    }
}