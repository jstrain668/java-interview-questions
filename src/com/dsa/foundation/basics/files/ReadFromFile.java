package com.dsa.foundation.basics.files;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

//Reference: https://www.javatpoint.com/how-to-read-csv-file-in-java

public class ReadFromFile {

    public List<String> readFileLineByLine(String filePath){

        File file = new File(filePath);
        List<String> lines = new ArrayList<>();

        try(BufferedReader br = new BufferedReader(new FileReader(file))){
          for(String line;(line = br.readLine()) != null;){
              lines.add(line);
          }
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }

        return lines;
    }

    public List<String> getWordListFromFile(String filePath){
        File file = new File(filePath);
        List<String> wordList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(file))){
            for(String line;(line = br.readLine()) != null;){
                String[] words = line.split("\\s+");

                for (String word : words){
                    if (!word.isEmpty()){
                        wordList.add(word);
                    }
                }
            }
        }catch (FileNotFoundException fnfe){
            fnfe.printStackTrace();
        }catch (IOException ioe){
            ioe.printStackTrace();
        }

        return wordList;
    }

    public static void main(String[] args) {
        ReadFromFile rff = new ReadFromFile();
        List<String> lines = rff.readFileLineByLine("C:\\dev\\files\\book.txt");
        System.out.println("Lines read from file book.txt");
        for(String line : lines){
            System.out.println(line);
        }
        System.out.println();

        List<String> wordList = rff.getWordListFromFile("C:\\dev\\files\\book.txt");
        System.out.println("Words read from file book.txt");
        for(String word : wordList){
            System.out.println(word);
        }
    }
}
