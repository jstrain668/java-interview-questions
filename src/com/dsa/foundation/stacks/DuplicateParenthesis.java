package com.dsa.foundation.stacks;

import java.util.Stack;

//Reference: https://www.techiedelight.com/find-duplicate-parenthesis-expression/

public class DuplicateParenthesis {

    // Function to find duplicate parenthesis in an expression
    public boolean hasDuplicateParenthesis(String exp)
    {
        if (exp == null || exp.length() <= 3) {
            return false;
        }

        // take an empty stack of characters
        Stack<Character> stack = new Stack<>();

        // traverse the input expression
        for (char c: exp.toCharArray())
        {
            // if the current char in the expression is not a closing parenthesis
            if (c != ')') {
                stack.push(c);
            }
            // if the current char in the expression is a closing parenthesis
            else {
                // if the stack's top element is an opening parenthesis,
                // the subexpression of the form ((exp)) is found
                if (stack.peek() == '(') {
                    return true;
                }

                // pop till '(' is found for current ')'
                while (stack.peek() != '(') {
                    stack.pop();
                }

                // pop '('
                stack.pop();
            }
        }

        // if we reach here, then the expression does not have any
        // duplicate parenthesis
        return false;
    }

    public static void main(String[] args) {
        DuplicateParenthesis dp = new DuplicateParenthesis();
        String exp = "((x+y))";        // assumes valid expression

        if (dp.hasDuplicateParenthesis(exp)) {
            System.out.println("The expression has duplicate parenthesis.");
        }
        else {
            System.out.println("The expression does not have duplicate parenthesis");
        }
    }
}
