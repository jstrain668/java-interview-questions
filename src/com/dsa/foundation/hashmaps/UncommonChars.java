package com.dsa.foundation.hashmaps;

import java.util.Map;
import java.util.TreeMap;

//Question: Given two strings A and B. Find the characters that are not common in the two strings.

//reference: https://kalkicode.com/find-uncommon-characters-two-strings

public class UncommonChars {

    public String UncommonChars(String A, String B)
    {
        // code here
        Map<Character,Integer> tMap = new TreeMap<>();
        for (Character ch : A.toCharArray()){
            //Add first string character
            if (!tMap.containsKey(ch)){
                tMap.put(ch,1);
            }
        }

        for (Character ch : B.toCharArray()){
            if (tMap.containsKey(ch)){
                if (tMap.get(ch) == 1){
                    tMap.put(ch,3);
                }
            }
            else {
                tMap.put(ch,2);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (var entry : tMap.entrySet()){
            if (entry.getValue() !=3){
                sb.append(entry.getKey());
            }
        }

        if (sb.length() == 0){
            return "-1";
        }

        return sb.toString();
    }
    public static void main(String[] args) {
        UncommonChars uc = new UncommonChars();

        String str1 = "geeksforgeeks";
        String str2 = "geeksquiz";

        System.out.println("Uncommon characters: "+uc.UncommonChars(str1,str2));

    }
}
