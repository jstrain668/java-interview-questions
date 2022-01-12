package com.dsa.foundation.graphs.toplogicalsort;

import java.util.LinkedList;
import java.util.Stack;

//Topological Sort: A topological sort or topological ordering of a directed graph is a linear ordering of its vertices
// such that for every directed edge uv from vertex u to vertex v, u comes before v in the ordering. A topological
// ordering is possible if and only if the graph has no directed cycles, that is, if it is a directed acyclic graph (DAG).

//Reference: https://algorithms.tutorialhorizon.com/topological-sort/

public class DAGraph {

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

            if (source < 0 || source >= this.vertices || destination < 0 || destination >= this.vertices) {
                return;
            }
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

        public void topologicalSorting() {
            boolean[] visited = new boolean[vertices];
            Stack<Integer> stack = new Stack<>();
            //visit from each node if not already visited
            for (int i = 0; i < vertices; i++) {
                if (!visited[i]) {
                    topologicalSortUtil(i, visited, stack);
                }
            }
            System.out.println("Topological Sort: ");
            int size = stack.size();
            for (int i = 0; i <size ; i++) {
                System.out.print(stack.pop() + " ");
            }
        }

        public void topologicalSortUtil(int start, boolean[] visited, Stack<Integer> stack) {
            visited[start] = true;
            for (int i = 0; i < adjList[start].size(); i++) {
                int vertex = adjList[start].get(i);
                if (!visited[vertex])
                    topologicalSortUtil(vertex, visited, stack);
            }
            stack.push(start);
        }
    }

    public Graph createGraph(int vertices){
        return new Graph(vertices);
    }


    public static void main(String[] args) {
        DAGraph dag = new DAGraph();
        int vertices = 8;
        Graph graph = dag.createGraph(vertices);
        graph.addEdge(7, 6);
        graph.addEdge(7, 5);
        graph.addEdge(6, 4);
        graph.addEdge(6, 3);
        graph.addEdge(5, 4);
        graph.addEdge(5, 2);
        graph.addEdge(3, 1);
        graph.addEdge(2, 1);
        graph.addEdge(1, 0);
        graph.topologicalSorting();

        DAGraph dag1 = new DAGraph();
        Graph g1 = dag1.createGraph(vertices);
        g1.addEdge(0,6);
        g1.addEdge(1,2);
        g1.addEdge(1,4);
        g1.addEdge(1,6);
        g1.addEdge(3,0);
        g1.addEdge(3,4);
        g1.addEdge(5,1);
        g1.addEdge(7,0);
        g1.addEdge(7,1);
        System.out.println();
        g1.topologicalSorting();

    }
}
