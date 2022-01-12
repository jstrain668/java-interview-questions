package com.dsa.foundation.graphs.dijsktra.cheapflight;


//Question: https://leetcode.com/problems/cheapest-flights-within-k-stops/
// There are n cities connected by some number of flights. You are given an array flights where flights[i] =
// [fromi, toi, pricei] indicates that there is a flight from city fromi to city toi with cost pricei.
//
//You are also given three integers src, dst, and k, return the cheapest price from src to dst with at most k stops.
// If there is no such route, return -1.

//Reference: https://leetcode.com/problems/cheapest-flights-within-k-stops/discuss/1506659/JAVA-BFS-Priority-Queue

import java.util.*;

public class CheapFlightWithinKStops {

    public int findCheapestPrice(int n, int[][] flights, int src, int dst, int k) {
        // arr {dest, cost}
        Map<Integer, List<int[]>> graph = populateGraph(flights);

        return search(graph, src, dst, k, n);
    }

    private int search(Map<Integer, List<int[]>> graph, int src, int dst, int stops, int n) {

        // arr = {src, cost, stops}
        // min heap based on cost, greedy approach
        Queue<int[]> q = new PriorityQueue<>(new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return Integer.compare(o1[1],o2[1]);
            }
        });

        q.add(new int[]{src, 0, 0});
        int[] visited = new int[n];
        Arrays.fill(visited, -1);

        while(!q.isEmpty()) {
            int[] arr = q.remove();
            int curSrc = arr[0];
            int curCost = arr[1];
            int curStops = arr[2];


            if(curSrc == dst) {
                return curCost;
            }

            // if number of stops exhausted
            // already visited this node with fewer stops
            if(curStops > stops || (visited[curSrc] != -1 && visited[curSrc] < curStops)) {
                continue;
            }

            // System.out.println("curSrc = "+curSrc+" curCost = "+curCost+" curStops = "+curStops);

            // current path reaches this source with this many stops
            visited[curSrc] = curStops;



            List<int[]> dests = graph.get(curSrc);

            if(dests != null) {
                for(int[] d : dests) {
                    q.add(new int[] {d[0], d[1] + curCost, curStops + 1});
                }
            }
        }
        return -1;
    }


    private Map<Integer, List<int[]>> populateGraph(int[][] flights) {
        // arr {dest, cost}
        Map<Integer, List<int[]>> grph = new HashMap();

        for(int[] p : flights) {

            int src = p[0];
            int dest = p[1];
            int cost = p[2];

            if(grph.containsKey(src)) {
                grph.get(src).add(new int[] {dest, cost});
            } else {
                List<int[]> d = new ArrayList();
                d.add(new int[] {dest, cost});
                grph.put(src, d);
            }
        }

        return grph;
    }

    public static void main(String[] args) {
        CheapFlightWithinKStops cfwks = new CheapFlightWithinKStops();
        int[][] flights = { {0,1,100},
                            {1,2,100},
                            {0,2,500}};
        int n = 3;
        int src = 0;
        int dst = 2;
        int k = 1;
        System.out.println("Cheapest price is "+cfwks.findCheapestPrice(n,flights,src,dst,k));
        System.out.println("Cheapest price is "+cfwks.findCheapestPrice(n,flights,src,dst,0));


    }
}
