package com.dsa.foundation.stacks;

import java.util.Stack;

//Amazon Question: Implement change directory Given the current working directory and an argument for cd, implement a
// function that returns the new working directory

public class ChangeDirectory {


    // Solution resolves to SimplifyUnixPathToFilename but before it resolves to it checks need to be conducted against
    // empty change dir (stay with current dir unless we can get to know the home dir). Also if the chDir starts with
    // '/' then the curDir can be ignored.
    public String changeDirectory(String curDir, String chDir){

        //If chDir is empty should we return the home directory of the user account (Unix cd)?
        //Since we don't know the home dir just return the curDir
        if (chDir == null || chDir.length() == 0){
            return curDir;
        }

        chDir = chDir.trim();
        //If the chDir starts with '/' then curDir is ignored
        if (chDir.charAt(0) == '/'){
            curDir = "";
        }

        Stack<String> stack = new Stack<>();

        //Concatenate chDir to curDir
        String concat = curDir + "/" + chDir;

        for (String s : concat.split("/")){

            if (s.equals(".") || s.equals("")){
                continue;
            } else if (s.equals("..")){
                if (!stack.isEmpty()){
                    stack.pop();
                }
            } else{
                stack.push(s);
            }
        }

        StringBuilder sb = new StringBuilder();

        while (!stack.isEmpty()){
            sb.insert(0,"/"+stack.pop());
        }

        if (sb.length() == 0){
            return "/";
        }

        return sb.toString();
    }

    public static void main(String[] args) {
        ChangeDirectory cd = new ChangeDirectory();
        String curDir = "/";
        String chDir = "/facebook";
        System.out.println(cd.changeDirectory(curDir,chDir));
        curDir = "/facebook/anin";
        chDir = "../abc/def";
        System.out.println(cd.changeDirectory(curDir,chDir));
        curDir = "/facebook/instagram";
        chDir = "../../../../.";
        System.out.println(cd.changeDirectory(curDir,chDir));
        curDir = "/p/q";
        chDir = "..";
        System.out.println(cd.changeDirectory(curDir,chDir));
        curDir = "/p";
        chDir = "x";
        System.out.println(cd.changeDirectory(curDir,chDir));
        curDir = "/p/x";
        chDir = "/x/y";
        System.out.println(cd.changeDirectory(curDir,chDir));
        curDir = "/x/y";
        chDir = ".";
        System.out.println(cd.changeDirectory(curDir,chDir));
        curDir = "/p/q";
        chDir = "../x/y";
        System.out.println(cd.changeDirectory(curDir,chDir));
        curDir = "/x/y/z";
        chDir = "../../y/z/..";
        System.out.println(cd.changeDirectory(curDir,chDir));
        curDir = "/x/y";
        chDir = "../.";
        System.out.println(cd.changeDirectory(curDir,chDir));
        curDir = "/xy/xc";
        chDir = "../";
        System.out.println(cd.changeDirectory(curDir,chDir));
        curDir = "/p/q";
        chDir = "/x/y/../z";
        System.out.println(cd.changeDirectory(curDir,chDir));
    }
}
