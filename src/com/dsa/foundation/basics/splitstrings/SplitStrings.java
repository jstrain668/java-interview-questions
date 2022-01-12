package com.dsa.foundation.basics.splitstrings;

//Reference: https://javarevisited.blogspot.com/2015/12/how-to-split-comma-separated-string-in-java-example.html#axzz75s6UAO6a

import java.util.Arrays;
import java.util.List;

public class SplitStrings {

    public List<String> splitString(String source,String splitPattern){

        if (source == null || source.isEmpty() || splitPattern == null || splitPattern.isEmpty()){
            return null;
        }

        return Arrays.asList(source.split(splitPattern));
    }

    public static void main(String[] args) {
        SplitStrings ss = new SplitStrings();
        String s = "War of the worlds    has begun";
        System.out.println("Source string:" +s);

        //Split by one or more whitespace characters including tab,cr.
        String splitPattern = "\\s+";
        List<String> words = ss.splitString(s,splitPattern);

        System.out.println("Source String split by: "+splitPattern);
        for(String word: words){
            System.out.println(word);
        }

        s = "Madness,Madness is all   in the mind , Bad Manners, Lip up Fatty";
        //Split by comma and removes leading whitespace before comma and after the comma
        splitPattern = "\\s*,\\s*";
        System.out.println("Source string:" +s);
        words = ss.splitString(s,splitPattern);

        System.out.println("Source String split by: "+splitPattern);
        for(String word: words){
            System.out.println(word);
        }
    }

}
