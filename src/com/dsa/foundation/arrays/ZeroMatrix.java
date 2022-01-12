package com.dsa.foundation.arrays;

import java.util.Arrays;


//Question: Zero Matrix: Write an algorithm such that if an element in an MxN matrix is 0, its entire row and
//column are set to 0.
//https://leetcode.com/problems/set-matrix-zeroes/

public class ZeroMatrix {


    //Time Complexity: O(m*n) where m is the number of rows in the matrix and n the number of columns in the matrix
    //Space Complexity: O(m+n)
    public void zeroTheMatrix(int[][] matrix){

        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return;

        int[] rows = new int[matrix.length];
        int[] cols = new int[matrix[0].length];


        for (int i=0; i < matrix.length; i++){
            for ( int j=0; j < matrix[0].length; j++){
                if (matrix[i][j] == 0){
                    rows[i] = 1;
                    cols[j] = 1;
                }
            }
        }

        for (int i=0; i < rows.length; i++){
            if (rows[i] == 1){
                for (int j=0; j < matrix[0].length; j++){
                    matrix[i][j] = 0;
                }
            }
        }

        for (int i=0; i < cols.length; i++){
            if (cols[i] == 1){
                for (int j=0; j < matrix.length; j++){
                    matrix[j][i] = 0;
                }
            }
        }

    }

    public static void main(String[] args) {
        ZeroMatrix zm = new ZeroMatrix();

        int[][] mat =
                {
                        { 1, 2, 3, 4 },
                        { 5, 6, 0, 8 },
                        { 9, 0, 11, 12 },
                        { 13, 14, 15, 16 },
                        { 17, 18, 19, 20}
                };

        for (int[] row: mat){
            System.out.println(Arrays.toString(row));
        }

        zm.zeroTheMatrix(mat);

        for (int[] row: mat){
            System.out.println(Arrays.toString(row));
        }
    }
}
