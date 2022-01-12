package com.dsa.foundation.basics.stringmanipulation;

import java.util.Arrays;

public class PrefixMatch {

    public String getShortestString(String[] strings){

        int minStrLength = Integer.MAX_VALUE;
        int strIndex = 0;

        for (int i=0; i < strings.length; i++){

            int strLen = strings[i].length();
            if (strLen < minStrLength){
                minStrLength = strLen;
                strIndex = i;
            }
        }

        return strings[strIndex];
    }

    public String longestCommonPrefix(String[] strings){

        if (strings == null || strings.length == 0){
            return "";
        }

        if (strings.length == 1){
            return strings[0];
        }

        String prefix = getShortestString(strings);

        for(String s: strings){
            while (s.indexOf(prefix) != 0){
                prefix = prefix.substring(0,prefix.length()-1);
                if (prefix.isEmpty()){
                    return "";
                }
            }
        }
        return prefix;
    }

    public static void main(String[] args) {
        PrefixMatch pm = new PrefixMatch();
        String[] strings = {"MadNotMad",
                            "Madness",
                            "Madden"};
        System.out.println(Arrays.toString(strings));
        System.out.println("Longest common prefix: "+pm.longestCommonPrefix(strings));
    }
}
