package com.dsa.foundation.stacks;

//Design a stack to support an additional operation that returns the minimum element from the stack in constant time.
// The stack should continue supporting all other operations like push, pop, top, size, empty, etc., without degradation
// in these operations performance."

//Suppose we will push a number value into a stack with a minimum number, min. If the value is greater than or equal to
//the min, it is pushed directly into the stack. If it is less than min, push 2×value-min, and update min as a value
//since a new minimum number is pushed. How about to pop? We pop it directly if the top of the stack (it is denoted as
// top) is greater than or equal to min. Otherwise, the number top is not the real pushed number. The real pushed number
// is stored as min. After the current minimum number is popped, we need to restore the previous minimum number,
// 2×min-top

//Reference: https://www.techiedelight.com/design-a-stack-which-returns-minimum-element-without-using-auxiliary-stack/

import java.util.Stack;

public class MinStack2 {

    // main stack to store elements
    private Stack<Integer> s = new Stack<>();

    // variable to store the minimum element
    private int min;

    // Inserts a given element on top of the stack
    public void push(int x)
    {
        if (s.empty())
        {
            s.push(x);
            min = x;
        }
        else if (x > min) {
            s.push(x);
        }
        else {
            s.push(2*x - min);
            min = x;
        }
    }

    // Removes the top element from the stack and returns it
    public void pop()
    {
        if (s.empty()) {
            System.out.println("Stack underflow!!");
        }

        int top = s.peek();
        if (top < min) {
            min = 2*min - top;
        }
        s.pop();
    }

    // Returns the minimum element from the stack in constant time
    public int min() {
        return min;
    }

    public static void main (String[] args) {
        MinStack2 s = new MinStack2();

        s.push(6);
        System.out.println(s.min());

        s.push(7);
        System.out.println(s.min());

        s.push(5);
        System.out.println(s.min());

        s.push(3);
        System.out.println(s.min());

        s.pop();
        System.out.println(s.min());

        s.pop();
        System.out.println(s.min());
    }
}
