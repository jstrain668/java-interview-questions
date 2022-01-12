package com.dsa.medium.strings;

import java.io.*;
import java.util.*;

//Amazon Question: Shortest distance between words Given a text file with words, find the shortest distance between two
// given words.

//Solution: Read the file line by line using a buffered reader. Each line is captured in a String which is further de-
//composed into an array of words using the Strings split function using whitespace as a separator to denote the start
//and end of a word. All words are from each line are store in an ArrayList of strings
// To find the distance between any 2 words we store each word and respective index position for each occurrence in a
// HashMap. Next to find the distance we do sanity checks on the inputs, two strings (not null, empty), hashmap (not
// empty, empty file) and the two strings can be found in the hashmap.
// Retrieve each index position for each word to be found. Calculate shortest distance


public class ShortestDistance {

    public List getWordsFromFile(String filePath){

        File file = new File(filePath);
        List<String> text = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] words = line.split("\\s+");
                text.addAll(Arrays.asList(words));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    public List getWords(String filePath){

        File file = new File(filePath);
        List<String> text = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(file))) {
            for(String line; (line = br.readLine()) != null; ) {
                String[] words = line.split("\\s+");
                text.addAll(Arrays.asList(words));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return text;
    }

    public Map<String,ArrayList<Integer>> processWords(List<String> words){

        Map<String,ArrayList<Integer>> map = new HashMap<>();

        for (int i=0; i < words.size(); i++){

            String word = words.get(i);

            if (map.containsKey(word)){
                ArrayList<Integer> indexes = map.get(word);
                indexes.add(i);
                map.replace(word,indexes);
            } else {
                ArrayList<Integer> indexes = new ArrayList<>();
                indexes.add(i);
                map.put(word,indexes);
            }

        }

        return map;
    }

    public int findShortestDistance(Map<String,ArrayList<Integer>> map,String s1,String s2,int numberOfWords){

        if (map.size() == 0 || s1 == null || s2 == null || s1.isEmpty() || s2.isEmpty()) {
            return -1;
        }

        ArrayList<Integer> s1List = map.get(s1);
        ArrayList<Integer> s2List = map.get(s2);

        if (s1List == null || s2List == null){
            return -1;
        }

        int i = 0;
        int j = 0;
        int minDistance = numberOfWords;

        while (i < s1List.size() && j < s2List.size()){
            int index1 = s1List.get(i);
            int index2 = s2List.get(j);
            minDistance = Math.min(minDistance,Math.abs(index1-index2));

            if (index1 < index2){
                i++;
            }else{
                j++;
            }

        }
        return minDistance;
    }

    public static void main(String[] args) {
        ShortestDistance sd = new ShortestDistance();
        List<String> words = sd.getWords("C:\\dev\\files\\book.txt");
        Map<String,ArrayList<Integer>> map = sd.processWords(words);
        String s1 = "Java";
        String s2 = "as";
        System.out.println("Shortest distance between "+s1+" and "+s2+ ": "+sd.findShortestDistance(map,s1,s2,words.size()));
        s1 = "FAQ:";
        s2 = "examples,";
        System.out.println("Shortest distance between "+s1+" and "+s2+ ": "+sd.findShortestDistance(map,s1,s2,words.size()));
        s1 = "FAQ:";
        s2 = "examples,";
        System.out.println("Shortest distance between "+s1+" and "+s2+ ": "+sd.findShortestDistance(map,s1,s2,words.size()));

    }
}
