package com.dsa.foundation.basics.files;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WriteToFile {

    public void writeLinesToFile(String filePath,List<String> lines){
        File file = new File(filePath);

        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filePath))){
            for(String line : lines){
                bw.write(line);
                bw.newLine();
            }
        }catch (IOException ioe){
            ioe.printStackTrace();
        }
    }

    public static void main(String[] args) {

        ReadFromFile rff = new ReadFromFile();
        List<String> lines = rff.readFileLineByLine("C:\\dev\\files\\book.txt");
        lines.add("Addendum: Array lists are dynamic where as arrays are static");

        WriteToFile wtf = new WriteToFile();
        wtf.writeLinesToFile("C:\\dev\\files\\book2.txt",lines);

    }
}
