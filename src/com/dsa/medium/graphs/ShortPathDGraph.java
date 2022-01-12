package com.dsa.medium.graphs;



import java.util.LinkedList;
import java.util.Queue;

public class ShortPathDGraph {

    public class Graph {
        int vertices;
        LinkedList<Integer>[] adjList;

        Graph(int vertices) {
            this.vertices = vertices;
            adjList = new LinkedList[vertices];
            for (int i = 0; i < vertices; i++) {
                adjList[i] = new LinkedList<>();
            }
        }

        //Directed graph
        public void addEdge(int source, int destination) {
            adjList[source].addFirst(destination);
        }

        public void printGraph() {
            for (int i = 0; i < vertices; i++) {
                LinkedList<Integer> nodeList = adjList[i];
                System.out.println("Vertex connected from vertex: " + i);

                for (int j = 0; j < nodeList.size(); j++) {
                    System.out.print("->" + nodeList.get(j));
                }
                System.out.println();
            }
        }

        public LinkedList<Integer> shortest(int from, int to) {

            Queue<Integer> q = new LinkedList<>();
            LinkedList<Integer> result = new LinkedList<>();
            int[] prev = new int[vertices];

            for (int i=0; i < vertices; i++){
                prev[i] = -1;
            }

            if(from == to){
                return result;
            }

            q.add(from);

            while (!q.isEmpty()){
                int cVertex = q.poll();

                for (int i=0; i < adjList[cVertex].size(); i++){
                    int adjVertex = adjList[cVertex].get(i);

                    if (prev[adjVertex] == -1){
                        prev[adjVertex] = cVertex;

                        if (adjVertex == to){
                            while (adjVertex != from){
                                result.addFirst(adjVertex);
                                adjVertex = prev[adjVertex];
                            }
                            return result;
                        }
                        q.add(adjVertex);
                    }
                }
            }
            return result;
        }
    }

    public Graph createGraph(int vertices){
        return new Graph(vertices);
    }

    public static void main(String[] args) {

        ShortPathDGraph dg = new ShortPathDGraph();
        Graph g = dg.createGraph(8);

        g.addEdge(0, 1);
        g.addEdge(0, 3);
        g.addEdge(1, 2);
        g.addEdge(3, 4);
        g.addEdge(3, 7);
        g.addEdge(4, 5);
        g.addEdge(4, 6);
        g.addEdge(4, 7);
        g.addEdge(5, 6);
        g.addEdge(6, 7);


        // Given source and destination
        int src = 0;
        int dest = 6;


        LinkedList<Integer> result = g.shortest(src,dest);
        System.out.println("Shortest path between "+src+" and "+dest+" "+result);
        System.out.println("Length of shortest path: "+result.size());

    }
}
