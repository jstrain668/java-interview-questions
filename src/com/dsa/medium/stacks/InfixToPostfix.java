package com.dsa.medium.stacks;

//Reference: https://java2blog.com/infix-to-postfix-java/

//use two Stacks : One for Operators (Character), One for Operands (String). The Operand stack will contain the
//resultant Postfix expression after traversing string. The Infix expression will be given as a String input. We will
//take decisions when we encounter Parentheses, Operators and Operands. So the steps are:
//
//Step 1: We will iterate throughout the String length. For each character, there are three cases to consider :
//
//  1. If the Current character is an Operand.
//  2. If the character is an Open or Close Parentheses.
//  3. If the character is an Operator.
//Step 2: If the Character at each iteration is an Operand. We simply push it into the operand or Postfix stack.
//
//Step 3: If the character encountered is : '(' , i.e. Opening Parentheses, we push it into Operator Stack.
//
//Step 4: Now, if we encounter ')' i.e. Closing Parenthesis, we are going to pop the elements out of Operator Stack
// until we get the opening '('. For each operator we pop its two operands and process them.
//
//Step 5: The process for each operator is : We pop two elements from Postfix or operand Stack we concatenate them in
// reverse order with its operator and add the result again to Postfix stack for future evaluation until we get the
// total Postfix expression.
//
//Step 6: Now if we get an Operator as the current character, we check whether the precedence of current operator is
// lower than the operator present at top of the stack. If the condition is true, we pop the operator present at the
// top and process its operands following Step 5. Then we push the current operator into the stack.
//
//Step 7: After traversing the whole string if we are still left with any operators we pop them and continue Step 5
// until the Operator Stack is empty. The Postfix stack will have only one element which will be our resultant
// Postfix Expression.

import java.util.Stack;

public class  InfixToPostfix {

    public int precedence(char ch)
    {
        if(ch=='+' || ch=='-')
            return 1;

        else if(ch=='*' || ch=='/')
            return 2;                       // * and / divide have higher precedence.

        return 0;
    }

    public String convertToPostfix(String exp)
    {
        Stack<Character> operators = new Stack<>();
        Stack<String> postFix = new Stack<>();

        for(int i=0;i<exp.length();i++)
        {
            char ch=exp.charAt(i);         // current character.

            if(ch=='(')
                operators.push(ch);

            else if((ch>='a' && ch<='z') || (ch>='A' && ch<='Z'))
                postFix.push(ch+"");

            else if(ch==')')
            {
                while(operators.peek()!= '(')
                {
                    // STEP 5 of Algorithm.
                    char op = operators.pop();

                    String first = postFix.pop();          // get the two operands.
                    String second = postFix.pop();

                    String new_postFix = second+first+op;  // add them in reverse order.

                    postFix.push(new_postFix);     // push the result to postFix stack again.
                }

                operators.pop();     // pop '(' from stack.
            }

            // We do the same thing if we get an operator as the current character.

            else if(ch=='+' || ch=='-' || ch== '*' || ch== '/')
            {
                // check precedence of each operator with top of the stack and process them.
                while(operators.size()>0 && operators.peek()!='(' && precedence(ch) <= precedence(operators.peek()))
                {
                    char op = operators.pop();

                    String first = postFix.pop();
                    String second = postFix.pop();

                    String new_postFix = second+first+op;

                    postFix.push(new_postFix);
                }

                operators.push(ch);
            }
        }

        // If the operator stack still has some elements in it process them too Repeat Step 5.
        while(operators.size()>0)
        {
            char op = operators.pop();

            String first = postFix.pop();
            String second = postFix.pop();

            String new_postFix = second+first+op;

            postFix.push(new_postFix);
        }

        return postFix.pop();         // return resultant Postfix.
    }


    public static void main(String args[])
    {
        InfixToPostfix itp = new InfixToPostfix();
        // We pass Uppercase Infix
        String infixExpression = "A*(B-C)/D+E";
        System.out.println("The Infix Expression is: "+infixExpression);
        String result = itp.convertToPostfix(infixExpression);
        System.out.println("The Postfix of the given Infix Expression is: "+result);

        System.out.println();

        //We also check for Lowercase Infix
        infixExpression = "a*(b-c+d)/e";
        System.out.println("The Infix Expression is: "+infixExpression);
        result = itp.convertToPostfix(infixExpression);
        System.out.println("The Postfix of the given Infix Expression is: "+result);

    }
}
