package com.dsa.foundation.logicpuzzles;

import java.util.ArrayList;
import java.util.List;

//Reference: https://leetcode.com/problems/pascals-triangle/

public class PascalsTriangle {


    //Description: First, we generate the overall triangle list, which will store each row as a sublist.
    //Then, we check for the special case of 0, as we would otherwise return [1]. Since numRows is always
    // greater than 0, we can initialize triangle with [1] as its first row, and proceed to fill the rows
    // as follows:
    // First and last elements in each row = 1
    // The other elements are equal to the sum of the adjacent elements to the left of the previous row
    // Time Complexity: O(numRows to the power of 2) The outer loop obviously runs numRowsnumRows times,
    // but for each iteration of the outer loop, the inner loop runs rowNum times. Therefore, the overall
    // number of triangle updates that occur is 1 + 2 + 3 + ... + numRows which, according to Gauss'
    // formula numRows(numRows + 1) /2
    // Space Complexity: O(numRows to the power of 2)

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> triangle = new ArrayList<List<Integer>>();

        //First row is always 1
        triangle.add(new ArrayList<>());
        triangle.get(0).add(1);

        for(int rowNum = 1; rowNum < numRows; rowNum++) {
            List<Integer> row = new ArrayList<>();
            List<Integer> prevRow = triangle.get(rowNum - 1);

            //First element in a row is always one
            row.add(1);

            for (int j = 1; j < rowNum; j++) {
                row.add(prevRow.get(j - 1) + prevRow.get(j));
            }

            //Last element in a row is always one
            row.add(1);
            triangle.add(row);
        }
        return triangle;
    }

    public void printPascalsTriangle(List<List<Integer>> output){

        for (int i = 0; i < output.size(); i++) {
            for (int j = 0; j < output.get(i).size(); j++) {
                System.out.print(output.get(i).get(j) + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {

        PascalsTriangle pt = new PascalsTriangle();
        int n = 6;
        System.out.println("Number of rows in Pascals triangle to print: "+n);
        pt.printPascalsTriangle(pt.generate(n));
    }

}
