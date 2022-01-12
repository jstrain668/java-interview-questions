package com.dsa.foundation.graphs.adjlist;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

//Reference: https://progressivecoder.com/graph-implementation-in-java-using-hashmap/

class GraphHashMap {
    private Map<Integer, List<Integer>> graph = new HashMap<>();

    public void addEdge(int source, int destination, boolean biDirectional) {
        if (!graph.containsKey(source)) {
            addVertex(source);
        }

        if (!graph.containsKey(destination)) {
            addVertex(destination);
        }

        graph.get(source).add(destination);
        if(biDirectional) {
            graph.get(destination).add(source);
        }
    }

    private void addVertex(int vertex) {
        graph.put(vertex, new LinkedList<Integer>());
    }

    public void hasVertex(int vertex) {
        if(graph.containsKey(vertex)) {
            System.out.println("The Graph contains " + vertex + " as a vertex");
        }else {
            System.out.println("The Graph does not contain " + vertex + " as a vertex");
        }
    }

    public void hasEdge(int source, int destination) {
        if(graph.get(source).contains(destination)) {
            System.out.println("The Graph has an edge between " + source + " and " + destination);
        }else {
            System.out.println("The Graph has no edge between " + source + " and " + destination);
        }
    }

    public String printGraph() {
        StringBuilder builder = new StringBuilder();

        for(int vertex : graph.keySet()) {
            builder.append(vertex + ": ");
            for(int node: graph.get(vertex)) {
                builder.append(node + " ");
            }
            builder.append("\n");
        }
        return builder.toString();
    }

    public static void main(String[] args) {
        GraphHashMap ghm = new GraphHashMap();

        ghm.addEdge(0, 1, true);
        ghm.addEdge(1, 3, true);
        ghm.addEdge(2, 1, true);
        ghm.addEdge(3, 2, true);
        ghm.addEdge(3, 4, true);
        ghm.addEdge(4, 0, true);
        ghm.addEdge(4, 1, true);
        ghm.addEdge(4, 3, true);


        System.out.println("Graph:\n"
                + ghm.printGraph());

        ghm.hasVertex(5);
        ghm.hasEdge(0,1);
    }
}