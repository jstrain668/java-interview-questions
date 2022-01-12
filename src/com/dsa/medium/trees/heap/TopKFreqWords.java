package com.dsa.medium.trees.heap;

import java.util.*;

public class TopKFreqWords {

    public List<String> topKFrequent(String[] words, int k){
        List<String> result = new LinkedList<>();
        Map<String,Integer> topWords = new HashMap<>();

        for (String word : words){
            topWords.put(word,topWords.getOrDefault(word,0)+1);
        }

        PriorityQueue<Map.Entry<String,Integer>> maxHeap = new PriorityQueue<>(new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                if (o1.getValue() == o2.getValue()){
                    return o1.getKey().compareTo(o2.getKey());
                }
                return o2.getValue() - o1.getValue();
            }
        });

        for (Map.Entry<String,Integer> entry : topWords.entrySet()){
            maxHeap.offer(entry);
        }

        while (!maxHeap.isEmpty() &&  k-- > 0){
            result.add(maxHeap.poll().getKey());
        }

        return result;
    }

    public static void main(String[] args) {

        TopKFreqWords heap = new TopKFreqWords();
        // given set of keys
        String[] dict =
                {
                        "code", "coder", "coding", "codable", "codec", "codecs", "coded",
                        "codeless", "codec", "codecs", "codependence", "codex", "codify",
                        "codependents", "codes", "code", "coder", "codesign", "codec",
                        "codeveloper", "codrive", "codec", "codecs", "codiscovered"
                };

        int k = 4;

        List<String> topKWords = heap.topKFrequent(dict, k);
        for (String word : topKWords){
            System.out.println(word);
        }

        TopKFreqWords heap2 = new TopKFreqWords();
        String[] dict2 =
                {"i","love","leetcode","i","love","coding"};
        k = 3;
        List<String> topWords = heap2.topKFrequent(dict2, k);
        for (String word : topWords){
            System.out.println(word);
        }

    }
}
