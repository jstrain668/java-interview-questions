package com.dsa.foundation.stacks;

import java.util.Stack;

public class StringReverse {

    Stack<Character> stack;

    public StringReverse(){
        stack = new Stack<>();
    }

    public String reverseString(String s){

        if (s == null || s.isEmpty()){
            return s;
        }

        for (char ch: s.toCharArray()){
            stack.push(ch);
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()){
            sb.append(stack.pop());
        }

        return sb.toString();
    }
    public static void main(String[] args) {
        StringReverse sr = new StringReverse();
        String s = "Madness";
        System.out.println(s);
        String r = sr.reverseString(s);
        System.out.println(r);
    }
}
