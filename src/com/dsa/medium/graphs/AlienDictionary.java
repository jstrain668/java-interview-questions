package com.dsa.medium.graphs;

//Question: https://www.lintcode.com/problem/892/

import java.util.*;

//Background: https://stackoverflow.com/questions/45950646/what-is-lexicographical-order/45950665

//Reference: http://buttercola.blogspot.com/2015/09/leetcode-alien-dictionary.html

//Solution Approach: The problem can be solved by a topological sorting. First we construct the graph based on the
// ordering relationship. Then do a topological sorting, which return the correct order.

public class AlienDictionary {

    Map<Character, Set<Character>> map = new HashMap<>();
    Map<Character, Integer> indegree = new HashMap<>();
    StringBuilder sb = new StringBuilder();
    boolean invalidDictionary = false;

    public String alienOrder(String[] words) {
        initIndegree(words);
        constructGraph(words);
        if (invalidDictionary) {
            return "";
        }
        bfs();
        return indegree.size() == 0 ? sb.toString() : "";
    }

    private void bfs() {
        // Use PriorityQueue because
        // 1) parent -> children. All children need to be in alphabetical order.
        //    Instead of enforcing in graph, we enforce it in queue.
        // 2) "yz" "xz" -> "yxz". Though z has 0 indegree, but all 0 indegree
        //    has to be polled in alphabetical order.
        Queue<Character> queue = new PriorityQueue<>();
        List<Character> list = getZeroIndegreeList();
        for (Character key : list) {
            if (indegree.get(key) == 0) {
                queue.offer(key);
            }
        }

        while (!queue.isEmpty()) {
            Character parent = queue.poll();
            sb.append(parent);
            // Remove 0 indegree cases here because
            // 1) All valid char will be appended.
            // 2) For the future indegree.size() check to know if cycle exists.
            indegree.remove(parent);

            // The last char will no children.
            if (!map.containsKey(parent)) {
                continue;
            }
            for (Character child : map.get(parent)) {
                int count = indegree.get(child) - 1;
                if (count == 0) {
                    queue.offer(child);
                } else {
                    indegree.put(child, count);
                }
            }
        }
    }

    private void constructGraph(String[] words) {
        for (int i = 1; i < words.length; i++) {
            char[] pair = parse(words[i - 1], words[i]);
            if (pair.length == 0) {
                continue;
            }
            // Populate indegree.
            indegree.put(pair[1], indegree.get(pair[1]) + 1);
            // Populate graph.
            Set<Character> children
                    = map.computeIfAbsent(pair[0], key -> new HashSet<>());
            children.add(pair[1]);
        }
    }

    // Return empty array if no result "aa" "aa" or "ab" "abc".
    private char[] parse(String a, String b) {
        int length = Math.min(a.length(), b.length());
        boolean hasPotentialPrefix = true;
        for (int i = 0; i < length; i++) {
            if (a.charAt(i) != b.charAt(i)) {
                hasPotentialPrefix = false;
                return new char[] {a.charAt(i), b.charAt(i)};
            }
        }
        // ["ab", "ab"] is valid but ["abc", "ab"] is not.
        if (hasPotentialPrefix && a.length() > b.length()) {
            invalidDictionary = true;
        }
        // ["ab", "ab"] where valid but nothing can be inferred.
        return new char[0];
    }

    // Better to walks every char explicitly. Because when building the graph
    // For case ["abc", "abd"] or ["x", "xab"] where 'a' and 'b' need extra
    // care to set to indegree. So by constructing the indegree here is a single
    // place, we avoid all those edge cases.
    private void initIndegree(String[] words) {
        for (int i = 0; i < words.length; i++) {
            for (int j = 0; j < words[i].length(); j++) {
                indegree.putIfAbsent(words[i].charAt(j), 0);
            }
        }
    }

    // Graph map will not have all 0 indegree nodes. Reference the examples at
    // `initIndegree()` comment.
    private List<Character> getZeroIndegreeList() {
        List<Character> list = new ArrayList<>();
        for (Character key : indegree.keySet()) {
            if (indegree.get(key) == 0) {
                list.add(key);
            }
        }
        return list;
    }

    public static void main(String[] args) {
        AlienDictionary al = new AlienDictionary();
        String[] words1 = {"wrt","wrf","er","ett","rftt"};
        System.out.println("Correct order of alien dictionary:"+al.alienOrder(words1));

        AlienDictionary al1 = new AlienDictionary();
        String[] words2 = {"z","x"};
        System.out.println("Correct order of alien dictionary:"+al1.alienOrder(words2));

        AlienDictionary al2 = new AlienDictionary();
        String[] words3 = {"z","x","z"};
        System.out.println("Correct order of alien dictionary:"+al2.alienOrder(words3));
    }
}
