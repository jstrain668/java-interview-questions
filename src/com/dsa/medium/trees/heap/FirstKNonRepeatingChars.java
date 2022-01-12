package com.dsa.medium.trees.heap;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class FirstKNonRepeatingChars {


    //Two traversal solution
    //Time Complexity : O(n + n) = O(n)
    //Space Complexity : O(n)
    public String findFirstKNonRepeating(String str,int k){

        if (str == null || str.isEmpty()){
            return new String();
        }

        Map<Character,Integer> map = new HashMap<>();

        for (char ch: str.toCharArray()){
            map.put(ch, map.getOrDefault(ch,0)+1);
        }

        StringBuilder sb = new StringBuilder();

        for (char ch : str.toCharArray()){
            if (map.get(ch) == 1 && k-- >0){
                sb.append(ch);
            }

            if (k == 0){
                break;
            }
        }

        return sb.toString();
    }

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
    public String findFirstKNonRepeat(String str,int k){

        if (str == null || str.isEmpty()){
            return new String();
        }

        Map<Character,HeapNode> map = new HashMap<>();

        int index = 0;
        for (char ch: str.toCharArray()){
            if (map.containsKey(ch)){
                HeapNode node = map.get(ch);
                node.index = index++;
                node.count++;
            } else {
                HeapNode node = new HeapNode(1,index++);
                map.put(ch,node);
            }

        }

        StringBuilder sb = new StringBuilder();

        // create an empty min-heap
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        // traverse the map and push the index of all characters
        // having the count of 1 into the min-heap
        for (HeapNode node : map.values()){
            int count = node.count;
            int ind = node.index;

            if (count == 1){
                pq.add(ind);
            }
        }

        // pop the top `k` keys from the min-heap
        while (!pq.isEmpty() && k-- > 0)
        {
            // extract the minimum node from the min-heap
            sb.append(str.charAt(pq.poll()) + " ");
        }

        return sb.toString();
    }


    public static void main (String[] args) {
        FirstKNonRepeatingChars nonRepeatingChars = new FirstKNonRepeatingChars();
        String str = "ABCDBAGHCHFAC";
        int k = 3;

        String res = nonRepeatingChars.findFirstKNonRepeating(str, k);
        System.out.println(res);

        String unique = nonRepeatingChars.findFirstKNonRepeat(str, k);
        System.out.println(unique);
    }
}
