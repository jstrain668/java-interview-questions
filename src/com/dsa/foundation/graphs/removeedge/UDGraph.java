package com.dsa.foundation.graphs.removeedge;

import java.util.LinkedList;

//Reference: https://kalkicode.com/check-removing-given-edge-disconnects-graph

public class UDGraph {

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

            if (source < 0 || source >= this.vertices || destination < 0 || destination >= this.vertices)
            {
                return;
            }
            adjList[source].addFirst(destination);
            adjList[destination].addFirst(source);
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

        public void dfs(int start,boolean[] visited)
        {
            if (start < 0 || start >= this.vertices)
            {
                // In case given invalid node
                return;
            }
            // Mark a current visited node
            visited[start] = true;

            //int i = 0;
            // Execute edges of given start vertices
            for (int i =0; i < adjList[start].size(); i++)
            {
                int adjVertex = adjList[start].get(i);
                if (!visited[adjVertex])
                {
                    // When edge node not visiting, then perform DFS operation
                    dfs(adjVertex,visited);
                }
            }
        }

        public int edgePosition(int u, int v)
        {
            for (int i =0; i < adjList[u].size(); i++)
            {
                if(adjList[u].get(i) == v)
                {
                    return i;
                }
            }
            return -1;
        }

        public boolean removeNodeEdge(int u, int v)
        {
            if(u < 0 || v < 0 || u > this.vertices || v > this.vertices )
            {
                return false;
            }

            int a = edgePosition(u,v);
            int b = edgePosition(v,u);

            if(a==-1 || b==-1)
            {
                // Given edge are not exist between given nodes
                return false;
            }

            // Remove edge
            adjList[u].remove(a);
            adjList[v].remove(b);

            return true;
        }

        public void unsetVisit(boolean[] visited)
        {
            for (int i = 0; i < vertices; i++)
            {
                visited[i] = false;
            }
        }

        public boolean isAllVerticesVisited(boolean[] visited)
        {
            for (int i = 0; i < this.vertices; ++i)
            {
                if(!visited[i])
                {
                    return false;
                }
            }
            return true;
        }

        public void disconnectByEdge(int u, int v)
        {

            boolean[] visited = new boolean[vertices];
            dfs(0,visited);

            if(isAllVerticesVisited(visited))
            {

                if(removeNodeEdge(u,v))
                {
                    unsetVisit(visited);

                    dfs(0,visited);

                    // Add remove edge
                    addEdge(u,v);

                    if(isAllVerticesVisited(visited))
                    {
                        // not a bridge edge
                        // graph are not disconnect

                        System.out.print("\n Remove edge ["+u+"-"+v+"] not disconnecting this graph ");
                    }
                    else
                    {

                        System.out.print("\n Remove edge ["+u+"-"+v+"] disconnecting this graph ");
                    }

                }
                else
                {
                    // When edges are not exist
                    System.out.print("\n No edge between vertices ["+u+"-"+v+"]");
                }
            }
            else
            {
                // When graph is already disconnected?
                System.out.print("\n Graph is already disconnected");
            }

        }

    }

    public Graph createGraph(int vertices){
        return new Graph(vertices);
    }

    public static void main(String[] args)
    {
        UDGraph dg = new UDGraph();
        Graph graph = dg.createGraph(12);

        graph.addEdge(0, 1);
        graph.addEdge(0, 3);
        graph.addEdge(0, 4);
        graph.addEdge(1, 2);
        graph.addEdge(1, 5);
        graph.addEdge(2, 3);
        graph.addEdge(2, 6);
        graph.addEdge(3, 4);
        graph.addEdge(3, 9);
        graph.addEdge(6, 7);
        graph.addEdge(6, 8);
        graph.addEdge(9, 10);
        graph.addEdge(9, 11);
        graph.addEdge(10, 11);
        // Display graph element
        graph.printGraph();

        // Test case
        graph.disconnectByEdge(4,7);
        graph.disconnectByEdge(3,9);
        graph.disconnectByEdge(1,2);
        graph.disconnectByEdge(6,2);
        graph.disconnectByEdge(10,11);

    }


}
