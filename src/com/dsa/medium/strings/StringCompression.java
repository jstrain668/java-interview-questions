package com.dsa.medium.strings;

//Question: Implement a method to perform basic string compression using th counts of
//repeated characters.
//For example, the string aabcccccaaa would become a2blc5a3. If the
// "compressed" string would not become smaller than the original string, your
// method should return the original string. You can assume the string has only
// uppercase and lowercase letters (a - z)

public class StringCompression {

    //Solution: Pick the first character from the input string (s).
    //Append it to the compressed string.
    //Count the number of subsequent occurrences of the character (in s) & append the
    //count to the compressed string
    //Pick the next character and repeat the steps above until the end of string is reached.
    //Check if the compressed string length is < original string, if yes return compressed
    //otherwise original
    //Time Complexity: O(n)
    //Space Complexity: O(1)
    public String compressString(String s){

        if (s== null || s.isEmpty() || s.length()==1){
            return s;
        }

        s = s.toLowerCase();
        StringBuilder sb = new StringBuilder();

        int len = s.length();

        for (int i=0; i < len; i++){

            int count = 1;
            while (i < (len -1) && (s.charAt(i) == s.charAt(i+1))){
                count++;
                i++;
            }

            sb.append(s.charAt(i));
            sb.append(count);

        }

        return (sb.length() < s.length()) ? sb.toString() : s;
    }

    public static void main(String[] args) {
        StringCompression sc = new StringCompression();
        String s = "aabcccccaaa";
        System.out.println("Source string: "+s+ " compressed = " +sc.compressString(s));

        String str = "wwwwaaadexxxxxxywww";
        System.out.println("Source string: "+str+ " compressed = " +sc.compressString(str));

    }


}
