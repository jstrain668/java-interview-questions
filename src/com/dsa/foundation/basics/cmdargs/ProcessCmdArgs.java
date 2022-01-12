package com.dsa.foundation.basics.cmdargs;

//Use a command line utility like https://github.com/kohsuke/args4j or

public class ProcessCmdArgs {

    public static void main(String[] args) {

        int argCount = 1;
        for (String arg : args){
            System.out.println("Arg "+argCount+" = "+arg);
            argCount++;
        }
    }
}
