package com.dsa.medium.hashmaps;

import com.sun.source.tree.Tree;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

//Question: Check if frequencies can be equal
//Given a string s which contains only lower alphabetic characters, check if it is possible to remove at most one
//character from this string in such a way that frequency of each distinct character becomes same in the string.


public class CanEqualFrequency {


    public boolean sameFreq(String s){

        Map<Character,Integer> charFreqs = new HashMap<>();
        for (Character ch : s.toCharArray()){
            charFreqs.put(ch,charFreqs.getOrDefault(ch,0)+1);
        }

        Map<Integer,Integer> freqCounts = new TreeMap<>();
        for (var entry : charFreqs.values()){
            freqCounts.put(entry,freqCounts.getOrDefault(entry,0)+1);
        }

        if (freqCounts.size() == 1){
            return true;
        }

        if (freqCounts.size() == 2){
            Iterator<Map.Entry<Integer,Integer>> iter = freqCounts.entrySet().iterator();
            Map.Entry<Integer,Integer> minEntry = iter.next();
            int minFreq = minEntry.getKey();
            int minFreqNum = minEntry.getValue();

            if (minFreq == 1 && minFreqNum == 1){
                return true;
            }

            Map.Entry<Integer,Integer> maxEntry = iter.next();
            int maxFreq = maxEntry.getKey();
            int maxFreqNum = maxEntry.getValue();

            if (maxFreq == minFreq + 1 && maxFreqNum == 1){
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
