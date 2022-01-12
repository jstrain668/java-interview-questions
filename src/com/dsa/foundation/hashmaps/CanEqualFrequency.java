package com.dsa.foundation.hashmaps;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

//Question: Check if frequencies can be equal
//Given a string s which contains only lower alphabetic characters, check if it is possible to remove at most one
//character from this string in such a way that frequency of each distinct character becomes same in the string.



public class CanEqualFrequency {


    public boolean sameFreq(String s){
        Map<Character, Integer> frequencies = new HashMap<>();
        for (char ch : s.toCharArray()) {
            frequencies.put(ch, frequencies.getOrDefault(ch,0) + 1);
        }

        TreeMap<Integer, Integer> frequencyCounts = new TreeMap<>();
        for (int freq : frequencies.values()) {
            frequencyCounts.put(freq, frequencyCounts.getOrDefault(freq,0)+1);
        }

        if (frequencyCounts.size() == 1) {
            return true;
        }

        if (frequencyCounts.size() == 2) {
            Iterator<Map.Entry<Integer, Integer>> iter = frequencyCounts.entrySet().iterator();
            Map.Entry<Integer, Integer> minEntry = iter.next();
            long minFrequency = minEntry.getKey();
            long numMinFrequency = minEntry.getValue();

            if (minFrequency == 1 && numMinFrequency == 1) {
                return true;
            }

            Map.Entry<Integer, Integer> maxEntry = iter.next();
            long maxFrequency = maxEntry.getKey();
            long numMaxFrequency = maxEntry.getValue();

            if (numMaxFrequency == 1 && maxFrequency == minFrequency + 1) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) {
        CanEqualFrequency cef = new CanEqualFrequency();
        String s = "xyyz";
        System.out.println("Can equalize frequency of characters: "+cef.sameFreq(s));

        String s2 = "xxxxyyzz";
        System.out.println("Can equalize frequency of characters: "+cef.sameFreq(s2));

        String s3 = "xxxyyzz";
        System.out.println("Can equalize frequency of characters: "+cef.sameFreq(s3));
    }
}
