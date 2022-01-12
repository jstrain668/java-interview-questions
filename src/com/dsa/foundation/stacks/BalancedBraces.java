package com.dsa.foundation.stacks;

import java.util.Stack;

//Reference: https://www.techiedelight.com/check-given-expression-balanced-expression-not/

public class BalancedBraces {

    Stack<Character> stack;

    public BalancedBraces(){
        stack = new Stack<>();
    }

    public boolean isBalanced(String exp){

        if (exp == null || exp.isEmpty()){
            return true;
        }

        for (char ch: exp.toCharArray()){

            switch(ch){
                case '[':
                case '{':
                case '(':
                    stack.push(ch);
                    break;
                default:
            }

            if (ch == ']' || ch == '}' || ch == ')'){
                if (stack.isEmpty()){
                    return false;
                } else {
                    char brace = stack.pop();
                    switch (brace){
                        case '[' :
                            if (ch != ']'){
                                return false;
                            }
                            break;
                        case '{' :
                            if (ch != '}'){
                                return false;
                            }
                            break;
                        case '(' :
                            if (ch != ')'){
                                return false;
                            }
                            break;
                        default:
                    }
                }
            }
        }

        return (stack.isEmpty());
    }

    public static void main(String[] args) {
        BalancedBraces bb = new BalancedBraces();
        String exp = "{(a+b)}[{}]";

        if (bb.isBalanced(exp)) {
            System.out.println("The expression is balanced");
        }
        else {
            System.out.println("The expression is not balanced");
        }
    }
}
