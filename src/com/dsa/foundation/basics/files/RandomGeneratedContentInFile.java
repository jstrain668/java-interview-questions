package com.dsa.foundation.basics.files;

import java.io.*;
import java.util.Random;

public class RandomGeneratedContentInFile {

    public static final String FILE_PATH = "C:\\dev\\files\\";
    public static final int STR_LEN = 40;

    //https://www.geeksforgeeks.org/generate-random-string-of-given-size-in-java/
    public String getAlphaNumericString(int n)
    {

        // lower limit for LowerCase Letters
        int lowerLimit = 97;

        // lower limit for LowerCase Letters
        int upperLimit = 122;

        Random random = new Random();

        // Create a StringBuffer to store the result
        StringBuffer r = new StringBuffer(n);

        for (int i = 0; i < n; i++) {

            // take a random value between 97 and 122
            int nextRandomChar = lowerLimit
                    + (int)(random.nextFloat()
                    * (upperLimit - lowerLimit + 1));

            // append a character at the end of bs
            r.append((char)nextRandomChar);
        }

        // return the resultant string
        return r.toString();
    }

    public void createFile(String filePath,int numLines){

        File file = new File(filePath);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))){

            // generate input
            for (int i = 0; i < numLines; i++) {

                bw.write(getAlphaNumericString(STR_LEN));
                bw.newLine();
            }

        } catch (FileNotFoundException fne){
            System.out.println(fne.toString());
        }
        catch (IOException ioe){
            System.out.println("Error in writing to file: "+filePath);
        }
    }


    public File openFile(String filePath){
        return new File(filePath);
    }

    public static void main(String[] args) {
        RandomGeneratedContentInFile ms = new RandomGeneratedContentInFile();

        String filename = "input.txt";

        // number of lines in the file
        int numLines = 10000;

        ms.createFile(FILE_PATH+filename,numLines);
    }
}
