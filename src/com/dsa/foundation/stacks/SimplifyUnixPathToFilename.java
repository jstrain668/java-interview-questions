package com.dsa.foundation.stacks;

//Reference: https://www.geeksforgeeks.org/simplify-directory-path-unix-like/
// https://leetcode.com/problems/simplify-path/
// https://leetcode.com/discuss/interview-question/553454/facebook-phone-change-working-directory

//Question: Collapse path to filename. Write a function that will take string as input. That string will be a a file
// name (e.g. "/usr/bin”). That path might contain special directories “.” (reference to self) and “..” (reference
// to parent directory). Your function will return the name for the file without using special directories
// (e.g. “/usr/var/../../tmp” should become “/tmp”)
//
// don’t expect candidates to have a solution right off the bat. If the candidate needs to understand the question,
// we will talk about it. Will the path always start with “/“? What should happen for input like “/..”
// For SDE I, the bar is describing a working approach for the common cases. O(n^2) is acceptable, but borders on below the bar.
// SDE II, the bar is implementing a working solution, handling “low-hanging fruit" edge cases correctly
// (null/empty/non-absolute paths, the parent of root, paths that collapse to “/").
// I prefer candidates to use a stack or doubly-linked list, but will accept O(n^2) regex based solutions if the
// candidate can understand/discuss the performance issues. This level raises the bar for SDE I.
// For SDE II, raising the bar is discussing or handling some less common edge cases: What should be the output for
// input like “/usr///bin”? What about escaped ‘/‘ characters? What about configuring behavior?

//Examples:
// "/a/./"   --> means stay at the current directory 'a'
//"/a/b/.." --> means jump to the parent directory
//              from 'b' to 'a'
//"////"    --> consecutive multiple '/' are a  valid
//              path, they are equivalent to single "/".
//
//Input : /home/
//Output : /home
//
//Input : /a/./b/../../c/
//Output : /c
//
//Input : /a/..
//Output:/
//
//Input : /a/../
//Output : /
//
//Input : /../../../../../a
//Output : /a
//
//Input : /a/./b/./c/./d/
//Output : /a/b/c/d
//
//Input : /a/../.././../../.
//Output:/
//
//Input : /a//b//c//////d
//Output : /a/b/c/d


import java.util.Deque;
import java.util.LinkedList;
import java.util.Stack;

public class SimplifyUnixPathToFilename {

    //Process the string by ignoring the "/" and multiples of "//". Store the non "/" chars in a temporary string 'dir'
    //Store current 'dir' name in a stack, continue processing (ignore) if "." is encountered in 'dir' and pop the stack
    // if ".." is encountered as long as the stack is not empty.
    // Create another stack (reverseStack) to store all the entries in the current stack to print the path in the correct
    //direction.
    //Time Complexity: O(n) - length of the string
    //Space Complexity: O(n) for the stack its O(2n) for the two stacks but remove constant

    // function to simplify a Unix - styled absolute path
    public String naiveSimplify(String A)
    {
        // Stack to store the file's names.
        Stack<String> st = new Stack<>();

        // Result string starts from root directory.
        String res = "/";

        // stores length of input String.
        int len_A = A.length();

        for (int i = 0; i < len_A; i++)
        {

            // we will clear the temporary String
            // every time to accommodate new directory
            // name or command.
            // dir will contain "a", "b", "..", ".";
            String dir = "";

            // skip all the multiple '/' Eg. "/////""
            while (i < len_A && A.charAt(i) == '/')
                i++;

            // stores directory's name("a", "b" etc.)
            // or commands("."/"..") into dir
            while (i < len_A && A.charAt(i) != '/')
            {
                dir += A.charAt(i);
                i++;
            }

            // if dir has ".." just pop the topmost
            // element if the Stack is not empty
            // otherwise ignore.
            if (dir.equals(".."))
            {
                if (!st.empty())
                    st.pop();
            }

            // if dir has "." then simply continue
            // with the process.
            else if (dir.equals("."))
                continue;

                // pushes if it encounters directory's
                // name("a", "b").
            else if (dir.length() != 0)
                st.push(dir);
        }

        // a temporary Stack (st1) which will contain
        // the reverse of original Stack(st).
        Stack<String> st1 = new Stack<String>();
        while (!st.empty())
        {

            st1.push(st.pop());
            // st.pop();
        }

        // the st1 will contain the actual res.
        while (!st1.empty())
        {

            // if it's the last element no need
            // to append "/"
            if (st1.size() != 1)
                res += (st1.pop() + "/");
            else
                res += st1.pop();

            // st1.pop();
        }
        return res;
    }

    // Split/Tokenize the path string using '/' as the separator. This can result in ".", "..", "", "<folder|filename"
    // "." and "" are ignored. '..' results in popping the last folder pushed onto the stack. Otherwise push contents
    //onto the stack. Since Stack is LIFO, the Stringbuilder prepends the pop string value (inserts at start of the
    // string). If the Stringbuilder is zero length return "/" (stack was empty)
    // Time Complexity: O(n) for the split function where n is the number of characters in string. Plus O(m) for the
    // number of substrings in the path string governed by the split pattern. Plus O(i) for the number of items processed
    // on the stack. O(n+m+i)
    // Space Complexity: O(m) + O(i)

    public String simplifyPathStack(String path) {

        if (path == null || path.length() == 0){
            return path;
        }

        String pathComponents[] = path.split("/");
        Stack<String> stack = new Stack<>();

        for(int i=0;i<pathComponents.length;i++) {

            switch(pathComponents[i]) {
                case "." :
                case ""  :
                    break;
                case "..":
                    if(stack.size()!=0) {
                        stack.pop();
                    }
                    break;
                default:
                    stack.push(pathComponents[i]);
            }
        }

        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            sb.insert(0, "/"+stack.pop());

        }

        if(sb.length()==0) {
            return "/";
        }

        return sb.toString();
    }

    // Split/Tokenize the path string using '/' as the separator. This can result in ".", "..", "", "<folder|filename"
    // "." and "" are ignored. '..' results in removing the last folder added to the dequeue. Otherwise add contents to
    // the end of the deque. Since most recent items are added to the end of the deque, the order from start to end is
    // in the correct sequence for building the string of the simplified path.
    // Use the string join function using "/" as the separator between each item to formulate the string to be returned
    // Time Complexity: O(n) for the split function where n is the number of characters in string. Plus O(m) for the
    // number of substrings in the path string governed by the split pattern. Plus O(i) for the number of items processed
    // on the Deque. O(n+m+i)
    // Space Complexity: O(m) + O(i)

    Deque<String> getpath(String path){

        Deque<String> m = new LinkedList<>();

        for(String s:path.split("/")){

            if(s.equals("") || s.equals(".")) {
                continue;
            }
            else if(s.equals("..")){
                if(!m.isEmpty()) {
                    m.removeLast();
                }
            }
            else {
                m.addLast(s);
            }
        }
        return m;
    }

    public String simplifyPathDeque(String path) {

        if (path == null || path.length() == 0){
            return path;
        }

        String res="";
        return "/"+res.join("/",getpath(path));
    }


    public static void main(String []args)
    {
        SimplifyUnixPathToFilename sup = new SimplifyUnixPathToFilename();
        // Filename with unix path reduce to be reduced to filename.
        String str = new String("/a/./b/../../c");
        String res = sup.naiveSimplify(str);
        System.out.println(res);
        str = "/home/";
        res = sup.naiveSimplify(str);
        System.out.println(res);
        str = "/usr/var/../../tmp";
        res = sup.naiveSimplify(str);
        System.out.println(res);
        str = "/a/./b/./c/./d/";
        res = sup.naiveSimplify(str);
        System.out.println(res);
        str = "/..";
        System.out.println( sup.naiveSimplify(str));
        str = "////usr//bin";
        System.out.println( sup.naiveSimplify(str));
        System.out.println();

        str = "/a/./b/../../c";
        System.out.println(sup.simplifyPathStack(str));
        str = "/home/";
        System.out.println(sup.simplifyPathStack(str));
        str = "/usr/var/../../tmp";
        System.out.println(sup.simplifyPathStack(str));
        str = "/a/./b/./c/./d/";
        System.out.println(sup.simplifyPathStack(str));
        str = "/..";
        System.out.println( sup.simplifyPathStack(str));
        str = "////usr//bin";
        System.out.println( sup.simplifyPathStack(str));
        System.out.println();

        str = "/a/./b/../../c";
        System.out.println(sup.simplifyPathDeque(str));
        str = "/home/";
        System.out.println(sup.simplifyPathDeque(str));
        str = "/usr/var/../../tmp";
        System.out.println(sup.simplifyPathDeque(str));
        str = "/a/./b/./c/./d/";
        System.out.println(sup.simplifyPathDeque(str));
        str = "/..";
        System.out.println( sup.simplifyPathDeque(str));
        str = "////usr//bin";
        System.out.println( sup.simplifyPathDeque(str));
        System.out.println();
    }
}
