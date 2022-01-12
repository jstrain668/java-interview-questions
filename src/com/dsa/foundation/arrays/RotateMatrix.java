package com.dsa.foundation.arrays;

import java.util.Arrays;

//Question: Rotate Matrix: Given an image represented by an NxN matrix, where each pixel in the image is 4
//bytes, write a method to rotate the image by 90 degrees. Can you do this in place?

//Reference: https://www.techiedelight.com/place-rotate-matrix-90-degrees-clock-wise-direction/

public class RotateMatrix {

    //Solution: The idea is to in-place convert the matrix into its transpose first. If we swap the first column with
    //the last column, the second column with the second last column, and so on… we will get our desired matrix
    //Time Complexity: O(n^2) where is row and col length of matrix
    //Space Complexity: O(1)
    public void rotate90Clockwise(int[][] matrix){

        if (matrix == null || matrix.length == 0 || matrix.length != matrix[0].length)
            return;

        /* First Transpose the matrix
         */
        int n = matrix.length;

        // Transpose the matrix - swap over the diagonal of the matrix
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < i; j++)
            {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }

        // swap columns
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < n / 2; j++)
            {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - j - 1];
                matrix[i][n - j - 1] = temp;
            }
        }

    }

    public void rotate90AntiClockwise(int[][] matrix){

        if (matrix == null || matrix.length == 0 || matrix.length != matrix[0].length)
            return;

        /* First Transpose the matrix
         */
        int n = matrix.length;

        // Transpose the matrix - swap over the diagonal of the matrix
        for (int i = 0; i < n; i++)
        {
            for (int j = 0; j < i; j++)
            {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        for (int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }

        // swap rows
        for (int i = 0; i < n / 2; i++)
        {
            for (int j = 0; j < n; j++)
            {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n -i - 1][j];
                matrix[n -i - 1][j] = temp;
            }
        }

    }

    public void reverseRow(int[] row){

        for (int i=0; i < row.length/2; i++){
            int other = row.length - i - 1;
            int temp = row[i];
            row[i] = row[other];
            row[other] = temp;
        }
    }

    public void rotate180(int[][] matrix){
        if (matrix == null || matrix.length == 0 || matrix.length != matrix[0].length)
            return;

        for (int[] row : matrix){
            reverseRow(row);
        }

        for (int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }

        int n = matrix.length;
        //Swap rows
        for (int i=0; i < n/2; i++){
            for (int j=0; j < n; j++){
                int temp = matrix[i][j];
                matrix[i][j] = matrix[n -i -1][j];
                matrix[n -i -1][j] = temp;
            }
        }
    }


    public static void main(String[] args) {
        RotateMatrix rm = new RotateMatrix();

        int[][] mat =
                {
                        { 1, 2, 3, 4 },
                        { 5, 6, 7, 8 },
                        { 9, 10, 11, 12 },
                        { 13, 14, 15, 16 }
                };

        for (int[] row: mat){
            System.out.println(Arrays.toString(row));
        }

        rm.rotate90Clockwise(mat);

        for (var r: mat) {
            System.out.println(Arrays.toString(r));
        }

        System.out.println();
        int[][] matrix =
                {
                        { 1, 2, 3, 4 },
                        { 5, 6, 7, 8 },
                        { 9, 10, 11, 12 },
                        { 13, 14, 15, 16 }
                };

        for (int[] row: matrix){
            System.out.println(Arrays.toString(row));
        }

        rm.rotate90AntiClockwise(matrix);

        for (var r: matrix) {
            System.out.println(Arrays.toString(r));
        }

        System.out.println();
        int[][] matrix2 =
                {
                        { 1, 2, 3, 4 },
                        { 5, 6, 7, 8 },
                        { 9, 10, 11, 12 },
                        { 13, 14, 15, 16 }
                };

        for (int[] row: matrix2){
            System.out.println(Arrays.toString(row));
        }

        rm.rotate180(matrix2);

        for (var r: matrix2) {
            System.out.println(Arrays.toString(r));
        }
    }
}
