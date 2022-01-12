package com.dsa.foundation.strings;

public class ReverseString {

    public String reverseIterative(String s){
        if (s == null || s.isEmpty()){
            return "";
        }

        if (s.length() == 1){
            return s;
        }

        char[] charArray = s.toCharArray();

        for (int i=0; i < charArray.length/2; i++){
            int other = charArray.length -i -1;
            char temp = charArray[i];
            charArray[i] = charArray[other];
            charArray[other] = temp;
        }

        return new String(charArray);
    }

    public String reverseRecursively(String s){

        if (s.isEmpty()){
            return s;
        }

        return reverseRecursively(s.substring(1))+s.charAt(0);
    }

    public static void main(String[] args) {
        ReverseString rs = new ReverseString();
        String s = "hello";
        System.out.println(rs.reverseIterative(s));
        String ss = "gotcha";
        System.out.println(rs.reverseIterative(ss));

        String s1 = "JAVATPOINT";
        System.out.println(rs.reverseRecursively(s1));
        String s2 = "COMPUTER";
        System.out.println(rs.reverseRecursively(s2));
    }
}
