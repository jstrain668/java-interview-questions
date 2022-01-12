package com.dsa.foundation.strings;

import java.util.Arrays;

public class Urlify {

    // Assume string has sufficient free space at the end
    public void replaceSpaces(char[] str, int trueLength) {
        int spaceCount = 0, index, i = 0;
        for (i = 0; i < trueLength; i++) {
            if (str[i] == ' ') {
                spaceCount++;
            }
        }
        index = trueLength + spaceCount * 2;
        if (trueLength < str.length) str[trueLength] = '\0';
        for (i = trueLength - 1; i >= 0; i--) {
            if (str[i] == ' ') {
                str[index - 1] = '0';
                str[index - 2] = '2';
                str[index - 3] = '%';
                index = index - 3;
            } else {
                str[index - 1] = str[i];
                index--;
            }
        }
    }

    public int findLastCharacter(char[] str) {
        for (int i = str.length - 1; i >= 0; i--) {
            if (str[i] != ' ') {
                return i;
            }
        }
        return -1;
    }

    //Reference: https://stackoverflow.com/questions/10007631/write-a-method-to-replace-all-spaces-in-a-string-with-20

    //Time Complexity: O(mn) for split method where n is length of string and m for length of pattern which is 1. So O(n)
    //O(w) for the number of words in the string delivered by split method. So O(n) + o(w)
    //Space Complexity: O(1)
    public String replace(String str) {
        String[] words = str.split(" ");
        StringBuilder sentence = new StringBuilder(words[0]);

        for (int i = 1; i < words.length; i++) {
            sentence.append("%20");
            sentence.append(words[i]);
        }

        return sentence.toString();
    }


    //Time Complexity: O(n) for stripTrailing and O(n) for replaceall or is it more?
    //Space Complexity: O(n)
    //Reference: https://codereview.stackexchange.com/questions/259667/urlify-a-given-string-replace-spaces-with-20
    public String URLify(String input){
        return input.stripTrailing().replaceAll(" ","%20");
    }


    public static void main(String[] args) {
        Urlify url = new Urlify();
        String str = "Mr John Smith    ";
        char[] arr = str.toCharArray();
        int trueLength = url.findLastCharacter(arr) + 1;
        url.replaceSpaces(arr, trueLength);
        System.out.println("\"" + Arrays.toString(arr) + "\"");
        System.out.println("\"" + new String(arr) + "\"");
        System.out.println("\"" + str + "\"");
        System.out.println("\"" + url.replace(str) + "\"");
        System.out.println("\"" + url.URLify(str) + "\"");
    }
}
