package com.dsa.medium.graphs;

import java.util.*;
//Reference: http://buttercola.blogspot.com/2016/06/leetcode-332-reconstruct-itinerary.html#comment-form
//Reference: https://aaronice.gitbook.io/lintcode/graph_search/reconstruct-itinerary

public class ReconstructItinerary {

    public List<String> findItinerary(String[][] tickets) {
        // Write your code here
        if (tickets == null || tickets.length == 0){
            return new ArrayList<String>();
        }

        //Build HashMap with MinHeap
        Map<String, PriorityQueue<String >> map = new HashMap<>();
        for (String[] ticket : tickets){
            if (!map.containsKey(ticket[0])){
                map.put(ticket[0],new PriorityQueue<>());
            }
            map.get(ticket[0]).add(ticket[1]);
        }

        LinkedList<String> result = new LinkedList<>();
        findItineraryHelper(map,"JFK",result);

        return new ArrayList<>(result);
    }

    private void findItineraryHelper(Map<String,PriorityQueue<String>> map,String airport,LinkedList<String> result){

        PriorityQueue<String> neighbours = map.get(airport);

        if (neighbours != null) {
            while (!neighbours.isEmpty()) {
                String neighbour = neighbours.poll();
                findItineraryHelper(map,neighbour, result);
            }
        }

        result.addFirst(airport);
    }

    public static void main(String[] args) {
        ReconstructItinerary ri = new ReconstructItinerary();
        String[][] input = {{"MUC","LHR"},{"JFK","MUC"},{"SFO","SJC"},{"LHR","SFO"}};
        List<String> result = ri.findItinerary(input);

        System.out.println(result.toString());
    }
}
