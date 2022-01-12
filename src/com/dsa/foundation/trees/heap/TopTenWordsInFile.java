package com.dsa.foundation.trees.heap;


import java.io.*;
import java.util.*;

//Question: Determine the 10 most frequent words or least frequent words given a file of strings

//Reference: https://stackoverflow.com/questions/35992891/java-how-to-find-top-10-most-common-string-frequency-in-arraylist
//Reference: https://www.java67.com/2015/01/how-to-sort-hashmap-in-java-based-on.html
//Reference: https://www.geeksforgeeks.org/sort-arraylist-in-descending-order-using-comparator-in-java/
//Reference: https://stackoverflow.com/questions/6176019/comparing-long-values-using-collections-sortobject/26460724
//Reference: https://massivealgorithms.blogspot.com/2014/06/find-k-most-frequent-words-from-file.html
//Reference: http://www.algorithmsandme.com/most-frequent-words-in-file/
//Reference: https://stackoverflow.com/questions/46738870/java-putting-words-from-txt-file-into-hashmap

public class TopTenWordsInFile {


    //Solution: Use HasMap to store words and respective word counts. To get top 10 most frequent words sort the word
    //counts in a Linked list in descending order and cut it to the first 10.
    //Time Complexity: O(n) for reading all the words in the file +O(n) for converting the HashMap into a LinkedList plus
    //O(nlogn) for sorting the Linked list in descending order plus O(10) for the top 10 linked list. The dominant term
    //is O(nlogn)
    //Space Complexity: O(n) for the HashMap + O(n) for the linked list + O(10) for top 10 linked list. The dominant
    //term is O(n)

    public List<Map.Entry<String, Long>> getNWordOccurrences(String filePath,int n,boolean ascending){

        Map<String,Long> wordOccurrences = retrieveWords(filePath);
        //Sort the words by the number of occurrences in descending order
        List<Map.Entry<String, Long>> sortedWordOccurrences = getSortedWordOccurrences(wordOccurrences,ascending);

        List<Map.Entry<String, Long>> tops = new LinkedList<>();
        int count = 0;

        while (count < n && !sortedWordOccurrences.isEmpty()){
            Map.Entry<String, Long> current = sortedWordOccurrences.get(count);
            tops.add(current);
            count++;
        }

        return tops;
    }

    //Solution: Use HasMap to store words and respective word counts. To get the top 10 most frequent words use a heap
    //realized as a priority queue with a comparator to sort the word counts in descending order. The priority queue
    //only stores the top 10 most frequent words
    //Time Complexity: O(nlogk) where n is the number of words and k is the top k most frequent words. This is for
    //sorting the most frequent words in a descending order in the priority queue. There is O(n) for reading all the
    //words in the file, plus another O(n) for reading the hashmap to populate the priority queue. Add another O(k) for
    //adding the top k into a linked list and O(k) for reversing it.
    //Space Complexity: O(n) for the HashMap + O(k) for the heap. The dominant term is o(n)
    public List<Map.Entry<String, Long>> getNWordOccurrencesUsingMinHeap(String filePath,int n,boolean ascending){

        Map<String,Long> wordOccurrences = retrieveWords(filePath);
        PriorityQueue<Map.Entry<String, Long>> pq = new PriorityQueue<>(getComparatorForPQ(ascending));
        List<Map.Entry<String, Long>> tops = new LinkedList<>();

        for (Map.Entry<String,Long> entry : wordOccurrences.entrySet()){
            if (pq.size() < n) {
                pq.add(entry);
            } else {
                if (ascending) {
                    if (entry.getValue() < pq.peek().getValue()) {
                        pq.remove();
                        pq.add(entry);
                    }
                } else{
                    if (entry.getValue() > pq.peek().getValue()){
                        pq.remove();
                        pq.add(entry);
                    }
                }
            }
        }

        while(!pq.isEmpty()){
            tops.add(pq.remove());
        }

        Collections.reverse(tops);
        return tops;
    }


    public Map retrieveWords(String filePath){

        File file = new File(filePath);
        Map<String,Long> wordOccurrences = new HashMap<>();

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {
                String[] words = line.split("\\s+");

                for(String word : words){
                    if (!word.isEmpty()) {
                        word = word.toLowerCase();
                        Long count = 1l;
                        if (wordOccurrences.containsKey(word)) {
                            count = wordOccurrences.get(word) + 1;
                        }
                        wordOccurrences.put(word, count);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return wordOccurrences;
    }

    public Comparator<Map.Entry<String, Long>> getComparator(boolean ascending){

        Comparator<Map.Entry<String, Long>> valueComparator = new Comparator<Map.Entry<String,Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> e1, Map.Entry<String, Long> e2) {
                if (ascending){
                    return Long.compare(e1.getValue(),e2.getValue());
                } else {
                    return Long.compare(e2.getValue(),e1.getValue());
                }
            }
        };

        return valueComparator;
    }

    public Comparator<Map.Entry<String, Long>> getComparatorForPQ(boolean ascending){

        Comparator<Map.Entry<String, Long>> valueComparator = new Comparator<Map.Entry<String,Long>>() {
            @Override
            public int compare(Map.Entry<String, Long> e1, Map.Entry<String, Long> e2) {

                if (ascending){
                    return Long.compare(e2.getValue(),e1.getValue());
                } else {
                    return Long.compare(e1.getValue(),e2.getValue());
                }
            }
        };
        return valueComparator;
    }

    public List<Map.Entry<String, Long>> getSortedWordOccurrences(Map<String,Long> wordOccurrences,boolean ascending){

        // There is no direct way to sort the HashMap by values. Write your own comparator, which takes
        // Map.Entry object and arrange them in order increasing or decreasing by values.
        Comparator<Map.Entry<String, Long>> valueComparator = getComparator(ascending);

        Set<Map.Entry<String, Long>> entries = wordOccurrences.entrySet();
        List<Map.Entry<String, Long>> listOfEntries = new LinkedList<>(entries);
        // sorting HashMap by values using comparator
        Collections.sort(listOfEntries, valueComparator);

        return listOfEntries;
    }

    public static void main(String[] args) {


        TopTenWordsInFile topTenWords = new TopTenWordsInFile();
        List<Map.Entry<String, Long>> topTenWordOccurrences = topTenWords.getNWordOccurrences(
               "C:\\dev\\files\\sample-2mb-text-file.txt",
                10,
                false);

        //List<Map.Entry<String, Long>> topTenWordOccurrences = topTenWords.getNWordOccurrences(
        //        "C:\\dev\\files\\book.txt",
        //        10,
        //        false);

        System.out.println("Top 10 words with the most occurrences:");
        int count = 1;
        for(Map.Entry<String, Long> current : topTenWordOccurrences){
            System.out.println(count+" "+current.getKey()+" "+ current.getValue());
            count++;
        }

        topTenWordOccurrences = topTenWords.getNWordOccurrencesUsingMinHeap("C:\\dev\\files\\sample-2mb-text-file.txt",
                10,
                false);
        System.out.println("Top 10 words with the most occurrences:");
        count = 1;
        for(Map.Entry<String, Long> current : topTenWordOccurrences){
            System.out.println(count+" "+current.getKey()+" "+ current.getValue());
            count++;
        }
    }
}
