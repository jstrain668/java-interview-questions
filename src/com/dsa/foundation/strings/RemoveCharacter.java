package com.dsa.foundation.strings;

//Reference: https://javarevisited.blogspot.com/2015/04/how-to-remove-given-character-from.html#axzz777a8QTgg

public class RemoveCharacter {

    public String removeChar(String word,char ch){
        if (word == null || word.isEmpty()){
            return word;
        }

        StringBuilder sb = new StringBuilder();

        for (char c: word.toCharArray()){
            if (ch != c){
                sb.append(c);
            }
        }
        return sb.toString();
    }

    public String removeCharRecursive(String word,char ch){

        if (word == null || word.isEmpty()){
            return word;
        }

        int index = word.indexOf(ch);
        if (index == -1){
            return word;
        }

        return removeCharRecursive(word.substring(0,index) + word.substring(index+1,word.length()),ch);

    }

    public static void main(String[] args) {
        RemoveCharacter rc = new RemoveCharacter();
        String word = "Madness";
        char ch = 'M';
        System.out.println("String "+word+" with "+ch+" removed = "+rc.removeChar(word,ch));
        System.out.println("String "+word+" with "+ch+" removed = "+rc.removeCharRecursive(word,ch));


    }
}
