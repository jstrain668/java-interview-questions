package com.dsa.foundation.trees.heap;

import java.util.*;

//Question and Reference: https://github.com/cherryljr/LeetCode/blob/master/Top%20K%20Frequent%20Words.java

public class TopKFreqWords {

    class CustomComparator implements Comparator<String> {
        HashMap<String, Integer> topK = new HashMap<>();

        public CustomComparator(HashMap<String, Integer> topK){
            this.topK = topK;
        }

        public int compare(String a, String b){
            if(topK.get(b) - topK.get(a) == 0){ //if number of occurrences are the same
                return a.compareTo(b); // then use the lexicographical order
            }
            return topK.get(b) - topK.get(a);
        }
    }

    class Node {
        String word;
        Integer count;
        public Node(String word,Integer count){
            this.word = word;
            this.count = count;
        }
    }

    ///**
    // * Approach 2: HashMap and PriorityQueue (maxHeap)
    // * This Approach is the same as -- Top K Frequent Elements' Approach 1.
    // * Count the frequency of each word, then add it to heap that stores the best k candidates.
    // * At last, we poll the results and add them to the rst list.
    // *
    // * Complexity Analysis
    // * Time Complexity: O(Nlogk), where N is the length of words.
    // * We count the frequency of each word in O(N) time,
    // * then we add N words to the heap, each in O(logk) time.
    // * Finally, we pop from the heap up to k times. As k≤N, this is O(Nlogk) in total.
    // * Space Complexity: O(N), the space used to store our count.
    // */

    public List<String> topKFrequent(String[] words, int k) {
        List<String> result = new LinkedList<>();
        if (words == null || words.length == 0){
            return result;
        }

        Map<String,Integer> topWords = new HashMap<>();
        Queue<Node> pq = new PriorityQueue<>(new Comparator<Node>() {
            @Override
            public int compare(Node o1, Node o2) {
                if (o1.count == o2.count){
                    return o2.word.compareTo(o1.word);
                }
                return o1.count - o2.count;
            }
        });

        for (String word : words){
            topWords.put(word,topWords.getOrDefault(word,0)+1);
        }

        for (Map.Entry<String,Integer> entry : topWords.entrySet()){
            pq.add(new Node(entry.getKey(),entry.getValue()));
            while (pq.size() > k){
                pq.poll();
            }
        }

        while (!pq.isEmpty()){
            result.add(0,pq.poll().word);
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
