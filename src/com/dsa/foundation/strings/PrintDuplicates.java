package com.dsa.foundation.strings;

import java.util.*;

//Reference: https://stackoverflow.com/questions/27679137/what-does-256-means-for-128-unique-characters-in-ascii-table

public class PrintDuplicates {

    public void printDuplicates(String s){

        if (s==null || s.isEmpty() || s.length() ==1){
            return;
        }

        s = s.toLowerCase();
        Set<Character> set = new HashSet<>();

        for (char ch: s.toCharArray()){
            if (!set.add(ch)){
                System.out.print(ch+",");
            } else{
                set.add(ch);
            }
        }
        System.out.println();
    }

    public void printDuplicates2(String s){

        if (s==null || s.isEmpty() || s.length() ==1){
            return;
        }

        s = s.toLowerCase();
        char[] charArray = s.toCharArray();
        Arrays.sort(charArray);

        for (int i=0; i < charArray.length -1; i++){
            if (charArray[i] == charArray[i+1]){
                System.out.print(charArray[i]);
            }
        }
        System.out.println();

    }

    public void printDuplicatesOnce(String s){

        if (s==null || s.isEmpty() || s.length() ==1){
            return;
        }

        s = s.toLowerCase();
        Map<Character,Integer> map = new HashMap<>();

        for (char ch: s.toCharArray()){
            if (map.containsKey(ch)){
                int count = map.get(ch);
                if (count == 1){
                    System.out.print(ch);
                }
                map.put(ch,count+1);
            } else{
                map.put(ch,1);
            }
        }
        System.out.println();

    }

    public void printDuplicates3(String s){

        if (s==null || s.isEmpty() || s.length() ==1){
            return;
        }

        s = s.toLowerCase();
        boolean[] charMap = new boolean[128];
        for(char ch: s.toCharArray()) {
            if (charMap[ch]) {
                System.out.print(ch+",");
            }
            charMap[ch] = true;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        PrintDuplicates pd = new PrintDuplicates();
        String s = "abcde";
        String s1 = "hellooo";
        pd.printDuplicates(s);
        pd.printDuplicates(s1);
        pd.printDuplicatesOnce(s);
        pd.printDuplicatesOnce(s1);
        pd.printDuplicates3(s);
        pd.printDuplicates3(s1);
    }
}
