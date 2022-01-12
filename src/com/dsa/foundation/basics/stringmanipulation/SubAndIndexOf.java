package com.dsa.foundation.basics.stringmanipulation;

public class SubAndIndexOf {

    public String subStr(String s){

        if (s == null || s.isEmpty()) return null;

        //Substring from char index 8 to end of string
        String sub = s.substring(8);
        System.out.println(sub);
        //In the case of substring the end index is the actual length of string where as in primitive array types its
        //one less than the length of the array
        sub = s.substring(8,s.length());
        System.out.println(sub);

        return sub;
    }

    public void indexRoutines(String s){
        if (s == null || s.isEmpty()) return;

        for (Character ch : s.toCharArray()){

            //IndexOf finds first occurrence and lastIndexOf finds last, if they both return the same index value then
            //you have found unique value in the string
            if (s.indexOf(ch) == s.lastIndexOf(ch)){
                System.out.println("Found non repeating character: "+ch+" at index "+s.indexOf(ch));
            }
        }
    }

    public static void main(String[] args) {
        SubAndIndexOf ss = new SubAndIndexOf();
        String s = "Madness is all in the mind";
        System.out.println("Source string: "+s);
        ss.subStr(s);
        ss.indexRoutines(s);
    }
}
