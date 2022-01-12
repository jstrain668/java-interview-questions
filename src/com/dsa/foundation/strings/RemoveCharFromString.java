package com.dsa.foundation.strings;

//Question and Reference: https://javarevisited.blogspot.com/2015/04/how-to-remove-given-character-from.html#axzz7539kFaaL

public class RemoveCharFromString {

    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public String removeCharFromString(String s,char ch){
        if (s == null || s.isEmpty()){
            return s;
        }

        StringBuilder sb = new StringBuilder();

        for (int i=0; i < s.length(); i++){
            char c = s.charAt(i);
            if (c != ch){
                sb.append(c);
            }
        }

        return sb.toString();
    }


    //Time Complexity: O(n) - dependent on the number of occurrences of the character in the string to be removed plus
    // the number of characters in indexof before reaching the desired character
    //Space Complexity: O(n) - system calls for the number of occurrences (stack)
    public String removeCharFromStringRecursive(String s,char ch){

        int index = s.indexOf(ch);
        if (index == -1){
            return s;
        }

        return removeCharFromStringRecursive(s.substring(0,index) +s.substring(index+1,s.length()),ch);
    }

    public static void main(String[] args) {
        RemoveCharFromString rcfs = new RemoveCharFromString();
        String s = "abac";
        char ch ='b';
        System.out.println("Remove character: "+ch +" from string: "+s+" = "+rcfs.removeCharFromString(s,ch));
        System.out.println("Remove character: "+ch +" from string: "+s+" = "+rcfs.removeCharFromStringRecursive(s,ch));

    }
}
