package com.dsa.foundation.graphs.adjlist;

import java.util.*;

//Reference: https://www.geeksforgeeks.org/implementing-generic-graph-in-java/

public class GenericGraphUsingHashMap<T> {

    private Map<T, LinkedList<T>> gMap = new HashMap<>();

    public void addEdge(T src,T dest,boolean biDir){

        if (!gMap.containsKey(src)){
            addVertex(src);
        }

        if (!gMap.containsKey(dest)){
            addVertex(dest);
        }

        gMap.get(src).addFirst(dest);
        if(biDir){
            gMap.get(dest).addFirst(src);
        }
    }

    private void addVertex(T vertex){
        gMap.put(vertex,new LinkedList<>());
    }

    public int getVertexCount(){
        return gMap.keySet().size();
    }

    public int getEdgeCount(boolean biDir){

        int count = 0;
        for (T vertex : gMap.keySet()){
            count += gMap.get(vertex).size();
        }

        if (biDir){
            return count/2;
        }

        return count;
    }

    public boolean hasVertex(T s){
        return (gMap.containsKey(s));
    }

    public boolean hasEdge(T s,T d){
        return gMap.get(s).contains(d);
    }

    @Override
    public String toString(){

        StringBuilder sb = new StringBuilder();

        for(T vertex : gMap.keySet()){
            sb.append(vertex + ": ");
            for (T neighbour : gMap.get(vertex)){
                sb.append(neighbour.toString()+" ");
            }
            sb.append("\n");
        }
        return sb.toString();
    }

    public void DFSTraversal(){

        boolean[] visited = new boolean[gMap.keySet().size()];
        Stack<Integer> stack = new Stack<>();

        for(T vertex : gMap.keySet()){
            int cVertex = (int) vertex;

            if (!visited[cVertex]){

                stack.push(cVertex);
                visited[cVertex] = true;

                while (!stack.isEmpty()){
                    int vIndex = stack.pop();
                    LinkedList<Integer> vList = (LinkedList<Integer>) gMap.get(vIndex);

                    System.out.print(vIndex+" ");

                    for (int i=0; i < vList.size(); i++){
                        int neighIndex = vList.get(i);
                        if (!visited[neighIndex]) {
                            visited[neighIndex] = true;
                            stack.push(neighIndex);
                        }
                    }
                }
            }
        }
    }

    public void dfsUtil(int sVertex,boolean[] visited){

        visited[sVertex] = true;
        System.out.print(sVertex+" ");
        for (int i=0; i < gMap.get(sVertex).size(); i++){
            int adjVertex = (int) gMap.get(sVertex).get(i);
            if (!visited[adjVertex]){
                dfsUtil(adjVertex,visited);
            }
        }
    }

    public void DFSRecursion(int sVertex){
        boolean[] visited = new boolean[gMap.keySet().size()];
        dfsUtil(sVertex,visited);
    }

    public void BFSTraversal(int sVertex){
        boolean[] visited = new boolean[gMap.keySet().size()];
        Queue<Integer> queue = new LinkedList<>();
        queue.add(sVertex);

        while (!queue.isEmpty()){
            int cVertex = queue.poll();
            visited[cVertex] = true;
            System.out.print(cVertex+" ");
            LinkedList<Integer> neighList = (LinkedList<Integer>) gMap.get(cVertex);

            for (int i=0; i < neighList.size(); i++){
                int adjVertex = neighList.get(i);
                if (!visited[adjVertex]){
                    visited[adjVertex] = true;
                    queue.add(adjVertex);
                }
            }
        }
    }

    public static void main(String[] args) {


        // Object of graph is created.
        GenericGraphUsingHashMap<Integer> g = new GenericGraphUsingHashMap<Integer>();

        // edges are added.
        // Since the graph is bidirectional,
        // so boolean bidirectional is passed as true.
        g.addEdge(0, 1, true);
        g.addEdge(0, 4, true);
        g.addEdge(1, 2, true);
        g.addEdge(1, 3, true);
        g.addEdge(1, 4, true);
        g.addEdge(2, 3, true);
        g.addEdge(3, 4, true);

        // print the graph.
        System.out.println("Graph:\n"
                    + g.toString());

        // gives the no of vertices in the graph.
        System.out.println("Number of vertices in graph: " +g.getVertexCount());

        // gives the no of edges in the graph.
        System.out.println("Number of edges in graph: " +g.getEdgeCount(true));

        // tells whether the edge is present or not.
        System.out.println("Edge: 3,4 is present in graph: "+g.hasEdge(3, 4));

        // tells whether vertex is present or not
        System.out.println("Vertex: 5 exists: "+g.hasVertex(5));

        System.out.println("DFS traversal using stack: ");
        g.DFSTraversal();
        System.out.println();
        System.out.println("DFS traversal using recursion: ");
        g.DFSRecursion(0);
        System.out.println();
        System.out.println("BFS traversal using queue: ");
        g.BFSTraversal(0);
    }
}
