package com.dsa.foundation.queue;

import java.util.ArrayDeque;
import java.util.Queue;

//Reference: https://www.techiedelight.com/generate-binary-numbers-1-n/

public class GenBinaryNumbers {

    public void generate(int n){
        if (n <=0){
            return;
        }

        Queue<String> queue = new ArrayDeque<>();
        int i = 1;
        queue.add("1");

        while (i <= n){
            queue.add(queue.peek()+'0');
            queue.add(queue.peek()+'1');

            // remove the front element and print it
            System.out.print(queue.poll() + ' ');
            i++;
        }
    }
    public static void main(String[] args) {
        GenBinaryNumbers gbn = new GenBinaryNumbers();
        int n = 10;
        gbn.generate(n);

    }
}
