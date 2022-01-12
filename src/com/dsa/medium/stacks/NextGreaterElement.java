package com.dsa.medium.stacks;

import java.util.Arrays;
import java.util.Stack;

//Reference: https://www.techiedelight.com/next-greater-element/

public class NextGreaterElement {

    public int[] findNextGreaterElements(int[] input){
        if (input == null || input.length == 0){
            return new int[0];
        }

        int[] result = new int[input.length];
        Arrays.fill(result,-1);
        Stack<Integer> s = new Stack<>();

        for (int i=0; i < input.length; i++){

            while (!s.isEmpty() && input[s.peek()] < input[i]){
                result[s.pop()] = input[i];
            }

            s.push(i);
        }

        return result;
    }

    public static void main(String[] args) {
        NextGreaterElement nge = new NextGreaterElement();
        int[] input = { 2, 7, 3, 5, 4, 6, 8 };

        int[] result = nge.findNextGreaterElements(input);
        System.out.println(Arrays.toString(result));
    }
}
