package com.dsa.medium.trees.tries;

//Question and Reference: https://www.techiedelight.com/find-first-k-maximum-occurring-words-given-set-strings/

import java.util.*;

public class TopKFreqWords {

    Trie root;

    // A class to store a heap node
    class Node implements Comparable
    {
        String key;
        int count;

        // constructor
        Node(String key, int count)
        {
            this.key = key;
            this.count = count;
        }

        @Override
        public int compareTo(Object o)
        {
            Node node = (Node)o;
            if (count == node.count){
                return node.key.compareTo(key);
            }
            return count - node.count;
        }
    }

    public TopKFreqWords(){
        root = new Trie();
    }

    public void insert(String word){
        if (word == null || word.isEmpty()){
            return;
        }

        Trie curr = root;

        for (char ch : word.toCharArray()){
            curr.children.putIfAbsent(ch,new Trie());
            curr = curr.children.get(ch);
        }
        curr.leaf = true;
        curr.freq++;
        curr.key = word;

    }

    public void preorder(Trie node,PriorityQueue<Node> pq){
        // base condition
        if (node == null) {
            return;
        }

        for (Map.Entry<Character,Trie> entry: node.children.entrySet())
        {
            // if a leaf node is reached (leaf nodes have a non-zero count),
            // push the key with its frequency in max-heap
            if (entry.getValue().leaf) {
                pq.add(new Node(entry.getValue().key, entry.getValue().freq));
            }

            // recur for current node's children
            preorder(entry.getValue(), pq);
        }
    }

    //The time complexity of the above solution isO(N.M), where N is the total number of given words and M is the max
    //word length.
    //The auxiliary space required by the program is O(N × M)

    public List<String> topKFrequent(String[] words, int k) {
        List<String> list = new ArrayList<>();
        if (words == null || words.length == 0){
            return list;
        }

        for (String word : words){
            insert(word);
        }

        // create an empty max-heap
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.reverseOrder());

        // perform preorder traversal on given Trie and push each
        // unique key with its frequency in max-heap
        preorder(root, pq);

        while (k > 0 && !pq.isEmpty()){
            Node max = pq.poll();
            list.add(max.key);
            System.out.println(max.key+" occurs "+max.count+ " times");
            k--;
        }
        return list;
    }

    public static void main(String[] args) {

        TopKFreqWords trie = new TopKFreqWords();
        // given set of keys
        String[] dict =
                {
                        "code", "coder", "coding", "codable", "codec", "codecs", "coded",
                        "codeless", "codec", "codecs", "codependence", "codex", "codify",
                        "codependents", "codes", "code", "coder", "codesign", "codec",
                        "codeveloper", "codrive", "codec", "codecs", "codiscovered"
                };

        int k = 4;

        List<String> topKWords = trie.topKFrequent(dict, k);
        for (String word : topKWords){
            System.out.println(word);
        }

        TopKFreqWords trie2 = new TopKFreqWords();
        String[] dict2 =
                {"i","love","leetcode","i","love","coding"};
        k = 3;
        List<String> topWords = trie2.topKFrequent(dict2, k);
        for (String word : topWords){
            System.out.println(word);
        }

    }
}
