package com.dsa.foundation.stacks;

import java.util.Stack;

// Description: Use one stack and a Node class which stores the min value of the stack along with each
// node value. When pushing to the Stack, push the value as min value when empty stack, otherwise
// get the current peek node to retrieve current min value and compare against supplied value to
// determine which one is smaller. Which ever is smaller is added as the min value to the node to be
// pushed onto the stack. top retrieves peek val and getMin retrieves peek min value.
// Time Complexity : O(1) for each of the stack operations
// Space Complexity: O(1) -

public class MinStack {

    Stack<Node> stack;

    public class Node{
        Node next;
        int val;
        int min;
        public Node(int val, int min){
            this.val = val;
            this.min = min;
        }
    }

    public MinStack(){
        stack = new Stack<>();
    }

    public void push (int val){

        if (stack.isEmpty()){
            stack.push(new Node(val,val));
        } else{
            int currentMin = stack.peek().min;
            stack.push(new Node(val,Math.min(val,currentMin)));
        }
    }

    public Node pop(){

        if (!stack.isEmpty()){
            return stack.pop();
        }

        return null;
    }

    public Node top(){

        if (!stack.isEmpty()){
            return stack.peek();
        }

        return null;
    }

    public int getMin(){

        if (!stack.isEmpty()){
            return stack.peek().min;
        }

        return -1;
    }

    public static void main(String[] args) {

        MinStack minStack = new MinStack();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        System.out.println("Current min stack: "+minStack.getMin()); // return -3
        minStack.pop();
        System.out.println("Current top of stack: "+minStack.top().val); // return 0
        System.out.println("Current min stack: "+minStack.getMin()); // return -2
    }
}
