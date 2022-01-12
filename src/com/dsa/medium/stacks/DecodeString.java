package com.dsa.medium.stacks;

import java.util.Stack;

//Solution: For each element of the given sequence, insert its position index+1  into a stack. If the current character
// is increasing 'I' or all characters of the input sequence are processed, pop all numbers from the stack and append
// them to the output string.

//Reference: https://www.techiedelight.com/decode-the-given-sequence-construct-minimum-number-without-repeated-digits/

public class DecodeString {

    // Function to decode the given sequence to construct a minimum number
    // without repeated digits
    public String decode(String seq)
    {
        // base case
        if (seq == null || seq.length() == 0) {
            return seq;
        }

        // `result` store the output string
        StringBuilder result = new StringBuilder();

        // create an empty stack of integers
        Stack<Integer> stack = new Stack<>();

        // run `n+1` times, where `n` is the length of the input sequence
        for (int i = 0; i <= seq.length(); i++)
        {
            // push number `i+1` into the stack
            stack.push(i + 1);

            // if all characters of the input sequence are processed, or
            // the current character is 'I' (increasing)
             if (i == seq.length() || seq.charAt(i) == 'I')
            {
                // run till stack is empty
                while (!stack.empty())
                {
                    // remove a top element from the stack and add it to the solution
                    result.append(stack.pop());
                }
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {
        DecodeString ds = new DecodeString();

        // input sequence
        String seq = "IDIDII";

        System.out.println("The minimum number is " + ds.decode(seq));
    }
}
