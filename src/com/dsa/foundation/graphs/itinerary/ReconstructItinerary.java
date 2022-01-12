package com.dsa.foundation.graphs.itinerary;

import java.util.*;

//Reference: http://buttercola.blogspot.com/2016/06/leetcode-332-reconstruct-itinerary.html#comment-form
//Reference: https://aaronice.gitbook.io/lintcode/graph_search/reconstruct-itinerary

public class ReconstructItinerary {

    public List<String> findItinerary(List<List<String>> tickets) {
        // Write your code here
        if (tickets == null || tickets.size() == 0){
            return new ArrayList<String>();
        }

        //Build HashMap with MinHeap
        Map<String, PriorityQueue<String >> map = new HashMap<>();
        for (List<String> ticket : tickets){
            if (!map.containsKey(ticket.get(0))){
                map.put(ticket.get(0),new PriorityQueue<>());
            }
            map.get(ticket.get(0)).add(ticket.get(1));
        }

        LinkedList<String> result = new LinkedList<>();
        findItineraryHelper(map,"JFK",result);

        return new ArrayList<>(result);
    }

    public void findItineraryHelper(Map<String,PriorityQueue<String>> map,String airport,LinkedList<String> result){

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
        List<List<String>> tickets = new ArrayList<>();
        for (int i=0; i < input.length; i++){
            tickets.add(new ArrayList<String>());

            tickets.get(i).add(input[i][0]);
            tickets.get(i).add(input[i][1]);
        }
        List<String> result = ri.findItinerary(tickets);

        System.out.println(result.toString());
    }
}
