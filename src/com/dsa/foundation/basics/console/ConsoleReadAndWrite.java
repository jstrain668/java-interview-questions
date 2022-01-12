package com.dsa.foundation.basics.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ConsoleReadAndWrite {

    public List<String> readFromConsole(){
        String input = "";
        List<String> lines = new ArrayList<>();
        try (Scanner sc = new Scanner(System.in)) {
            while (!input.equalsIgnoreCase("q")){
                System.out.print("Input: ");
                input = sc.nextLine();
                lines.add(input);
            }
        }
        //Remove "q" from the list
        lines.remove(lines.size()-1);

        return lines;
    }

    public static void main(String[] args) {
        ConsoleReadAndWrite craw = new ConsoleReadAndWrite();
        List<String> lines = craw.readFromConsole();

        System.out.println("Echoing lines back to console");
        for (String line: lines){
            System.out.println(line);
        }
    }
}
