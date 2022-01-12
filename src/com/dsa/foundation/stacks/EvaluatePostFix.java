package com.dsa.foundation.stacks;

import java.util.Stack;

//Reference: https://www.techiedelight.com/evaluate-given-postfix-expression/

public class EvaluatePostFix {

    Stack<Integer> stack = new Stack<>();


    //Can assume a valid postfix expression
    public int evalPostfix(String expr){

        if (expr == null || expr.isEmpty()){
            return -1;
        }

        for (char ch: expr.toCharArray()){

            // if the current character is an operand, push it into the stack
            if (Character.isDigit(ch)){
                stack.push(ch - '0');
            } else{ //current character is an operator

                int x = stack.pop();
                int y = stack.pop();
                switch (ch){
                    case '+' :
                        stack.push(y+x);
                        break;
                    case '-' :
                        stack.push(y-x);
                        break;
                    case '*' :
                        stack.push(y*x);
                        break;
                    case '/' :
                        stack.push(y/x);
                        break;
                    default:
                }

            }
        }

        return stack.pop();
    }

    public static void main(String[] args) {

        EvaluatePostFix epf = new EvaluatePostFix();
        String exp = "138*+";
        System.out.println(epf.evalPostfix(exp));
    }
}
