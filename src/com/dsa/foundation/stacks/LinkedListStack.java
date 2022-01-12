package com.dsa.foundation.stacks;

//Reference: https://www.techiedelight.com/stack-implementation-using-linked-list/

public class LinkedListStack {

    ListNode head;

    public void push(int val){

        ListNode newNode = new ListNode(val);

        if (isEmpty()){
            head = newNode;

        } else{
            newNode.next = head;
            head = newNode;
        }
    }

    public ListNode pop(){

        if (isEmpty()){
            return null;
        } else {
            ListNode top = head;
            head = head.next;
            return top;
        }
    }

    public boolean isEmpty(){
        return (head == null);
    }

    public ListNode peek(){

        if (isEmpty()){
            return null;
        } else{
            return head;
        }
    }

    public void printStack(){
        ListNode current = head;
        if(!isEmpty()){
            while (current != null){
                System.out.print(current.val+",");
                current = current.next;
            }
            System.out.println();
        }else{
            System.out.println("Stack is empty");
        }
    }

    public static void main(String[] args) {
        LinkedListStack lls = new LinkedListStack();
        for (int i = 1; i< 5; i++){
            lls.push(i);
        }
        lls.printStack();
        System.out.println("Top of stack "+lls.peek().val);
        lls.pop();
        lls.pop();
        lls.push(20);
        lls.printStack();
        lls.pop();
        lls.pop();
        lls.pop();
        lls.pop();
        lls.push(25);
        lls.printStack();

    }
}
