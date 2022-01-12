package com.dsa.medium.stacks;

import java.util.Stack;

public class StackWithMin extends Stack<ListNodeWithMin> {

    public void push(int val){

        if (this.isEmpty()){
            this.push(new ListNodeWithMin(val,val));
        } else{
            int min = this.min();
            this.push(new ListNodeWithMin(val,Math.min(min,val)));
        }
    }

    public int min(){
        if (this.isEmpty()){
            return Integer.MAX_VALUE;
        } else{
            return this.peek().min;
        }
    }

    public static void main(String[] args) {
        StackWithMin stack = new StackWithMin();

        int[] array = {2, 1, 3, 1};
        for (int value : array) {
            stack.push(value);
            System.out.print(value + ", ");
        }
        System.out.println('\n');
        for (int i = 0; i < array.length; i++) {
            System.out.println("Popped " + stack.pop().val);
            System.out.println("New min is " + stack.min());
        }
    }
}
