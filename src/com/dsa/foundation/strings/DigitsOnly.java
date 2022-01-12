package com.dsa.foundation.strings;

import java.util.regex.Pattern;

public class DigitsOnly {

    public static void main(String[] args) {
        Pattern pattern = Pattern.compile(".*[^0-9].*");
        //Pattern pattern = Pattern.compile(".*\\D.*");
        String [] inputs = {"123", "-123" , "123.12", "abcd123"};

        for(String input: inputs){
            System.out.println( "does " + input + " is number : "
                    + !pattern.matcher(input).matches());
        }

    }
}
