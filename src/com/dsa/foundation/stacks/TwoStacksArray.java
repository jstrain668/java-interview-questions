package com.dsa.foundation.stacks;

//Reference: https://www.geeksforgeeks.org/implement-two-stacks-in-an-array/

//A simple solution would be to divide the array into two halves and allocate each half to implement two stacks. In
// other words, for an array 'arr' of size n, the solution would allocate arr[0, n/2] memory for the first stack and
// arr[n/2+1, n-1] memory for the second stack.

public class TwoStacksArray {

    int[] arr;
    int size;
    int top1, top2;

    // Constructor
    public TwoStacksArray(int n)
    {
        size = n;
        arr = new int[n];
        top1 = n / 2 + 1;
        top2 = n / 2;
    }

    // Method to push an element x to stack1
    public void push1(int x)
    {

        // There is at least one empty
        // space for new element
        if (top1 > 0)
        {
            top1--;
            arr[top1] = x;
        }
        else
        {
            System.out.print("Stack Overflow"
                    + " By element :" +  x +"\n");
            return;
        }
    }

    // Method to push an element
    // x to stack2
    public void push2(int x)
    {

        // There is at least one empty
        // space for new element
        if (top2 < size - 1)
        {
            top2++;
            arr[top2] = x;
        }
        else
        {
            System.out.print("Stack Overflow"
                    + " By element :" +  x +"\n");
            return;
        }
    }

    // Method to pop an element from first stack
    public int pop1()
    {
        if (top1 <= size / 2)
        {
            int x = arr[top1];
            top1++;
            return x;
        }
        else
        {
            System.out.print("Stack UnderFlow");
            System.exit(1);
        }
        return 0;
    }

    // Method to pop an element
    // from second stack
    public int pop2()
    {
        if (top2 >= size / 2 + 1)
        {
            int x = arr[top2];
            top2--;
            return x;
        }
        else
        {
            System.out.print("Stack UnderFlow");
            System.exit(1);
        }
        return 1;
    }



    public static void main(String[] args) {
        TwoStacksArray ts = new TwoStacksArray(5);
        ts.push1(5);
        ts.push2(10);
        ts.push2(15);
        ts.push1(11);
        ts.push2(7);
        System.out.print("Popped element from stack1 is "
                + " : " +  ts.pop1() +"\n");
        ts.push2(40);
        System.out.print("Popped element from stack2 is "
                + ": " +  ts.pop2()
                +"\n");
    }
}
