package com.dsa.medium.strings;

//Reference: https://javarevisited.blogspot.com/2016/10/how-to-check-if-string-contains-another-substring-in-java-indexof-example.html#axzz777a8QTgg

public class SubstringIndex {

    //Time Complexity: O(m*n) where n is the length of 'source' string (search string) and m is the length of the
    //pattern you are searching in the search string.
    public int subStringIndex(String target,String source){

        if (target == null || target.isEmpty() || source == null || source.isEmpty()){
            return -1;
        }

        if (target.length() > source.length()){
            return -1;
        }

        return source.indexOf(target);
    }

    ////Time Complexity: O(n)
    //Ref: https://stackoverflow.com/questions/12752274/java-indexofstring-str-method-complexity
    public int subStringIndex2(String target,String source){

        if (target == null || target.isEmpty() || source == null || source.isEmpty()){
            return -1;
        }

        if (target.length() > source.length()){
            return -1;
        }

        int count =0;
        int index =-1;
        for (int i=0; i < source.length(); i++){

            if (count == target.length()){
                return index;
            }

            if (source.charAt(i) == target.charAt(count)){
                if (count == 0){
                    index = i;
                }
                count++;
            } else{
                if (count > 0){
                    i -= count;
                }
                count = 0;
                index = -1;
            }
        }
        return index;
    }


    public static void main(String[] args) {
        SubstringIndex si = new SubstringIndex();
        String target = "geak";
        String source = "allgeekstogether";
        System.out.println(target+" is a sub string of "+source+ " at index "+si.subStringIndex2(target,source));
    }
}
