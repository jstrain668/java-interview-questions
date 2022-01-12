package com.dsa.foundation.stacks;

import java.util.Stack;

//Reference: https://leetcode.com/problems/valid-parentheses/

public class ValidParentheses {

    public boolean isValid2(String s) {

        if (s == null || s.length() == 0 || s.length() == 1) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (int i=0; i < s.length(); i++){
            char c = s.charAt(i);

            if (c == '(' || c == '{' || c == '['){
                stack.push(c);
            } else{

                if (stack.empty()){
                    return false;
                }

                switch (c) {
                    case ')':
                        if (stack.pop() != '('){
                            return false;
                        }
                        break;
                    case '}':
                        if (stack.pop() != '{'){
                            return false;
                        }
                        break;
                    case ']':
                        if (stack.pop() != '['){
                            return false;
                        }
                        break;
                    default:
                        return false;
                }
            }
        }
        return stack.empty();
    }

    //Description: For the string of parentheses {([])} to be valid each bracket requires a corresponding
    //bracket. Use a Stack structure to resolve this challenge - LIFO. Each opening bracket is pushed onto
    //stack. Every closing bracket encountered is checked for its corresponding opening bracket by popping
    //top value of the stack. Last opening bracket pushed onto stack is first to be popped. If they match
    //continue processing otherwise invalid string.
    //Time Complexity: O(n)
    //Space Complexity: O(n)
    public boolean isValid(String s) {

        if (s == null || s.length() == 0 || s.length() == 1) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);

            if (c == '(' || c == '[' || c == '{') {
                stack.push(c);
                continue;
            }

            // If stack is empty then we didn't have an opening bracket so string is invalid
            if (stack.empty()) {
                return false;
            }

            switch (c) {
                case ')':
                    if (stack.pop() != '(') {
                        return false;
                    }
                    break;
                case ']':
                    if (stack.pop() != '[') {
                        return false;
                    }
                    break;
                case '}':
                    if (stack.pop() != '{') {
                        return false;
                    }
                    break;
                default:
                    return false;
            }
        }
        //If the stack is not empty then we have an invalid string
        return stack.empty();
    }


    public static void main(String[] args) {
        ValidParentheses vp = new ValidParentheses();
        String str1= "([)]";
        String str2 = "[";
        String str3 = "((";
        System.out.println("String "+str1+" is valid: "+vp.isValid(str1));
        System.out.println("String "+str1+" is valid: "+vp.isValid2(str1));
    }
}
