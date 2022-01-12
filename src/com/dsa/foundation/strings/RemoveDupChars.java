package com.dsa.foundation.strings;

import java.util.*;

public class RemoveDupChars {

    //Given a string s, remove duplicate letters so that every letter appears once and only once. You must make sure
    // your result is the smallest in lexicographical order among all possible results.
    public String removeDuplicateLetters(String s) {

        if (s == null || s.isEmpty() || s.length() == 1){
            return s;
        }

        int[] cnt = new int[128];
        for (int i = 0; i < s.length(); ++i) ++cnt[s.charAt(i)];

        LinkedList<Character> stack = new LinkedList<>();
        boolean[] visited = new boolean[128];
        for (int i = 0; i < s.length(); ++i) {
            char ch = s.charAt(i);
            --cnt[ch];
            if (visited[ch]) continue;
            while (!stack.isEmpty() && ch < stack.getLast() && cnt[stack.getLast()] > 0) {
                visited[stack.removeLast()] = false;
            }
            stack.addLast(ch);
            visited[ch] = true;
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) sb.append(stack.removeFirst());
        return sb.toString();

    }

    public String removeDuplicateChars(String s){
        if (s == null || s.isEmpty() || s.length() == 1){
            return s;
        }

        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder();
        Set<Character> set = new HashSet<>();

        for (char ch: s.toCharArray()){
            if(set.add(ch)){
                sb.append(ch);
            }
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        RemoveDupChars rdc = new RemoveDupChars();
        String s1 = "bananas";
        String s2 = "made";
        String s3 = "geeksforgeeks";

        System.out.println(s1+" with dup chars removed "+rdc.removeDuplicateChars(s1));
        System.out.println(s2+" with dup chars removed "+rdc.removeDuplicateChars(s2));
        System.out.println(s3+" with dup chars removed "+rdc.removeDuplicateChars(s3));

        String s4 = "bcabc";
        String s5 = "cbacdcbc";
        String s6 = "";

        System.out.println(s4+" with dup chars removed "+rdc.removeDuplicateLetters(s4));
        System.out.println(s5+" with dup chars removed "+rdc.removeDuplicateLetters(s5));
        System.out.println(s6+" with dup chars removed "+rdc.removeDuplicateLetters(s6));
    }
}
