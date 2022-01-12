package com.dsa.foundation.trees.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

public class FirstKNonRepeatingChars {




    //https://www.techiedelight.com/first-k-non-repeating-characters-string/
    //Single traversal solution
    class HeapNode{
        //character count
        int count;
        //index of last occurrence
        int index;

        public HeapNode(int count,int index){
            this.count = count;
            this.index = index;
        }

        public HeapNode(){

        }
    }

    //We can solve this problem in a single traversal of the string. The idea is to use a map to store each distinct
    // character count and the index of its first or last occurrence in the string. Then traverse the map and push the
    // index of all characters having count 1 into the min-heap. Finally, pop the top k keys from the min-heap, and
    // that will be our first k non-repeating characters in the string.

    //The solution inserts all the map characters (all having a count of 1) into the min-heap. So, the heap size becomes
    // O(n) in the worst case. So, the time complexity of the following solution is O(n + k.log(n)) and requires O(n)
    // auxiliary space.
    public String findFirstKNonRepeat(String str,int k){
        if (str == null || str.isEmpty()){
            return "";
        }

        Map<Character,HeapNode> map = new HashMap<>();
        for (int i = 0; i < str.length(); i++){
            char ch = str.charAt(i);
            if (map.containsKey(ch)){
                HeapNode node = map.get(ch);
                node.count++;
                node.index = i;
                map.put(ch,node);

            } else {
                map.put(ch,new HeapNode(1,i));
            }
        }

        //Min Heap - stores index of char with count of 1
        Queue<Integer> pq = new PriorityQueue<>();

        for (HeapNode value : map.values()){
            if (value.count == 1){
                pq.add(value.index);
            }
        }
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty() && k-- > 0){
            sb.append(str.charAt(pq.poll())+" ");
        }

        return sb.toString();
    }


    public static void main (String[] args) {
        FirstKNonRepeatingChars nonRepeatingChars = new FirstKNonRepeatingChars();
        String str = "ABCDBAGHCHFAC";
        int k = 3;

       // String res = nonRepeatingChars.findFirstKNonRepeating(str, k);
       // System.out.println(res);

        String unique = nonRepeatingChars.findFirstKNonRepeat(str, k);
        System.out.println(unique);
    }
}
