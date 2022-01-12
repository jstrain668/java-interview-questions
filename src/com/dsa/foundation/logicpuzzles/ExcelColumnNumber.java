package com.dsa.foundation.logicpuzzles;

//Description: https://leetcode.com/problems/excel-sheet-column-number/

public class ExcelColumnNumber {

    public int convertCharToNumber(char character){

        int index = character - 'A' + 1;

        return index;
    }

    // Description: Cycle through each character in the string converting it to a number. Base number for
    // alphabet is 26. The exponent for each number is the string length -1 and decrement for each cycle.
    // Use the Math power of function to calculate the base amount multiplied by number. Amount is accumulated
    // over each iteration
    // Time Complexity: O(n)
    // Space Complexity: O(1)

    public long convertExcelColumnToNumber(String str){

        int base = 26;
        int exponent = str.length()-1;
        int amount = 0;

        for (int i=0; i < str.length(); i++){
            int number = convertCharToNumber(str.charAt(i));

            if (exponent >= 0)
                amount += (Math.pow(base,exponent) * number);

            exponent--;
        }

        return amount;
    }


    public static void main(String[] args) {
        ExcelColumnNumber ecn = new ExcelColumnNumber();
        String excelColumn = "FXSHRXW";
        System.out.println("Convert excel sheet column: "+excelColumn+ " to number: "+ecn.convertExcelColumnToNumber(excelColumn));
    }
}
