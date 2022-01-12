package com.dsa.foundation.strings;

public class VowelsAndConsonants {

    public boolean isAlphabetic(char ch){
        return (ch >= 'a' && ch <= 'z');
    }

    public void printVowelAndConsonantCounts(String s){

        if (s== null || s.isEmpty()){
            System.out.println();
        }

        s = s.toLowerCase();
        char[] chars = s.toCharArray();

        int vowelCnt = 0;
        int consonantCnt = 0;
        for (char ch: chars){

            if (isAlphabetic(ch)){
                switch (ch){
                    case 'a' :
                    case 'e' :
                    case 'i' :
                    case 'o' :
                    case 'u' :
                        vowelCnt++;
                        break;
                    default :
                        consonantCnt++;
                }
            }
        }

        System.out.println("Number of vowels "+vowelCnt);
        System.out.println("Number of consonants "+consonantCnt);
    }


    public static void main(String[] args) {
        VowelsAndConsonants vac = new VowelsAndConsonants();
        String s1 = "about a boy";
        vac.printVowelAndConsonantCounts(s1);
        String s2 = "Nothingness";
        vac.printVowelAndConsonantCounts(s2);
        String s3 = "99899.-;";
        vac.printVowelAndConsonantCounts(s3);
    }
}
