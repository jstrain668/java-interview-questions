package com.dsa.medium.strings;

import java.util.ArrayList;
import java.util.List;

public class StringPermutation {

    private void permutationList(String str,String prefix,List<String> list){

        if (str.isEmpty()){
            list.add(prefix);
        } else {
            for (int i=0; i < str.length(); i++){
                String rem = str.substring(0,i) + str.substring(i+1);
                permutationList(rem,prefix +str.charAt(i),list);
            }
        }
    }

    public List<String> permutationList(String str) {
        List<String> arrayList = new ArrayList<>();
        permutationList(str,"",arrayList);
        return arrayList;
    }

    public void permutation(String str) {
        permutation(str, "");
    }

    //Since we are calling permutation 0( n * n ! ) times (as an upper bound), and each one takes 0( n) time,
    //the total runtime will not exceed O ( n^2 * n ! ) .
    //Note: Empty string "" is returned for substring(0,0) or for any start and end index which are the same, Also
    //empty string returned for substring(index) when index is equal to the length of the string.
    public void permutation(String str, String prefix) {
         if (str.length() == 0) {
             System.out.println(prefix);
         } else {
             for (int i= 0; i < str.length(); i++) {
                 String rem = str.substring(0, i) + str.substring(i + 1);
                 permutation(rem, prefix + str.charAt(i));
             }
         }
    }

    public static void main(String[] args) {
        StringPermutation sp = new StringPermutation();
        String s = "Mad";
        sp.permutation(s);

        System.out.println();
        String ss = "Mad";
        List<String> list = sp.permutationList(ss);
        for (String str: list){
            System.out.println(str);
        }
    }
}
