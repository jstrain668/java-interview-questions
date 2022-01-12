package com.dsa.foundation.strings;

//Reference: https://leetcode.com/problems/count-and-say/

//Difficult question to follow. Not my solution that follows

public class CountAndSay {

    public String countAndSay(int n) {
        if (n <= 0)
            return null;

        String result = "1";
        int i = 1;

        while (i < n) {
            StringBuilder sb = new StringBuilder();
            int count = 1;
            for (int j = 1; j < result.length(); j++) {
                if (result.charAt(j) == result.charAt(j - 1)) {
                    count++;
                } else {
                    sb.append(count);
                    sb.append(result.charAt(j - 1));
                    count = 1;
                }
            }

            sb.append(count);
            sb.append(result.charAt(result.length() - 1));
            result = sb.toString();
            i++;
        }

        return result;
    }

    public static void main(String[] args) {
        CountAndSay cas = new CountAndSay();
        int n = 4;
        System.out.println("N is:" +n +" and count and say: "+cas.countAndSay(n));
    }

}
