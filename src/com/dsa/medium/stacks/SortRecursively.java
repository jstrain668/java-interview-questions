package com.dsa.medium.stacks;

import java.util.Arrays;
import java.util.List;
import java.util.Stack;

//Reference: https://www.techiedelight.com/recursive-solution-sort-stack/

//Solution: The idea is simple – recursively remove values from the stack until the stack becomes
// empty and then insert those values back into the stack in a sorted position
//Time Complexity : O(n^2)
//Space Complexity : O(n)

public class SortRecursively {

    public void  sortInsert(Stack<Integer> stack, int key){

        if (stack.isEmpty() || key > stack.peek()){
            stack.push(key);
            return;
        }

        /* We reach here when the key is smaller than the top element */

        // remove the top element
        int top = stack.pop();

        sortInsert(stack,key);

        stack.push(top);
    }

    public void sortStack(Stack<Integer> stack){

        //Base condition empty stack
        if (stack.isEmpty()){
            return;
        }

        //remove top element
        int top = stack.pop();

        //recursively pop the stack until stack is empty
        sortStack(stack);

        //insert the popped element back into the stack
        sortInsert(stack,top);
    }

    public static void main(String[] args) {
        SortRecursively sr = new SortRecursively();
        List<Integer> list = Arrays.asList(5, -2, 9, -7, 3);

        Stack<Integer> stack = new Stack<>();
        stack.addAll(list);

        System.out.println("Stack before sorting: " + stack);
        sr.sortStack(stack);
        System.out.println("Stack after sorting: " + stack);
    }
}
