package com.dsa.foundation.strings;

import java.util.Arrays;
import java.util.Comparator;

//Question: Write a Program to sort String on their length in Java? Your method should accept an array of String and
// return a sorted array based upon the length of String.



public class SortStringsByLength {

    public String[] sortStringsByLength(String[] strings){

        if (strings == null || strings.length == 0){
            return strings;
        }

        Arrays.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return Integer.compare(o1.length(),o2.length());
            }
        });

        return strings;
    }

    public static void main(String[] args) {
        SortStringsByLength ssbl = new SortStringsByLength();
        String[] strings = {"the beat","the specials","the bodysnatchers","madness","bad manners","the selecter"};

        String[] sortedStrings = ssbl.sortStringsByLength(strings);

        for (String s: sortedStrings){
            System.out.println(s);
        }
    }
}
