package com.dsa.foundation.basics.files;

import java.io.*;
import java.util.Arrays;
import java.util.Random;

public class ExternalMergeSort {

    private static final int N = 2000000; // size of the file in disk
    private static final int M = 100000; // max items the memory buffer can hold
    private static final String FILE_PATH = "C:\\dev\\files\\";

    public  String generateInput(int n)
    {
        String fileName = FILE_PATH + "external-sort.txt";
        File file = new File(fileName);
        Random rand = new Random();

        try (PrintWriter pw = new PrintWriter(new FileWriter(file))){

            for (int i = 0; i < n; i++)
                pw.println(rand.nextInt(101));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        return fileName;
    }

    public static void externalSort(String fileName)
    {
        String tfile = FILE_PATH + "temp-file-";
        int[] buffer = new int[M < N ? M : N];

        try (BufferedReader br = new BufferedReader(new FileReader(fileName))){
            int slices = (int) Math.ceil((double) N/M);

            int i, j;
            i = j = 0;
            // Iterate through the elements in the file
            for (i = 0; i < slices; i++)
            {
                // Read M-element chunk at a time from the file
                for (j = 0; j < (M < N ? M : N); j++)
                {
                    String t = br.readLine();
                    if (t != null)
                        buffer[j] = Integer.parseInt(t);
                    else
                        break;
                }
                // Sort M elements
                Arrays.sort(buffer);


                // Write the sorted numbers to temp file
                FileWriter fw = new FileWriter(tfile + Integer.toString(i) + ".txt");
                PrintWriter pw = new PrintWriter(fw);
                for (int k = 0; k < j; k++)
                    pw.println(buffer[k]);

                pw.close();
                fw.close();
            }

            br.close();

            // Now open each file and merge them, then write back to disk
            int[] topNums = new int[slices];
            BufferedReader[] brs = new BufferedReader[slices];

            for (i = 0; i < slices; i++)
            {
                brs[i] = new BufferedReader(new FileReader(tfile + Integer.toString(i) + ".txt"));
                String t = brs[i].readLine();
                if (t != null)
                    topNums[i] = Integer.parseInt(t);
                else
                    topNums[i] = Integer.MAX_VALUE;
            }

            FileWriter fw = new FileWriter("C:\\dev\\files\\external-sorted.txt");
            PrintWriter pw = new PrintWriter(fw);

            for (i = 0; i < N; i++)
            {
                int min = topNums[0];
                int minFile = 0;

                for (j = 0; j < slices; j++)
                {
                    if (min > topNums[j])
                    {
                        min = topNums[j];
                        minFile = j;
                    }
                }

                pw.println(min);
                String t = brs[minFile].readLine();
                if (t != null)
                    topNums[minFile] = Integer.parseInt(t);
                else
                    topNums[minFile] = Integer.MAX_VALUE;

            }
            for (i = 0; i < slices; i++)
                brs[i].close();

            pw.close();
            fw.close();
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args)
    {
        ExternalMergeSort ems = new ExternalMergeSort();
        String fileName = ems.generateInput(N);
        externalSort(fileName);
    }
}
