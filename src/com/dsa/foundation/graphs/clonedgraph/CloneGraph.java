package com.dsa.foundation.graphs.clonedgraph;

import java.util.*;

//Reference: https://www.geeksforgeeks.org/clone-an-undirected-graph/

public class CloneGraph {

    public void bfs(Node source){
       Queue<Node> q = new ArrayDeque<>();
       Map<Node,Boolean> nMap = new HashMap<>();

       q.add(source);
       nMap.put(source,true);

       while (!q.isEmpty()){
           Node cNode = q.poll();
           System.out.println("Value of Node "+cNode.val);
           System.out.println("Address of Node "+cNode);

           for (int i=0; i < cNode.neighbours.size(); i++){
               Node neighbour = cNode.neighbours.get(i);

               if (!nMap.containsKey(neighbour)){
                   nMap.put(neighbour,true);
                   q.add(neighbour);
               }
           }
       }
    }

    public Node cloneGraph(Node source){
        if (source == null){
            return null;
        }

        Map<Node,Node> nMap = new HashMap<>();
        Queue<Node> q = new ArrayDeque<>();
        q.add(source);
        nMap.put(source,new Node(source.val,new ArrayList<>()));

        while (!q.isEmpty()){
            Node cNode = q.poll();

            for (int i=0; i < cNode.neighbours.size(); i++){
                Node neighbour = cNode.neighbours.get(i);

                if (!nMap.containsKey(neighbour)){
                    q.add(neighbour);
                    nMap.put(neighbour,new Node(neighbour.val,new ArrayList<>()));
                }
                nMap.get(cNode).neighbours.add(nMap.get(neighbour));
            }
        }
        return nMap.get(source);
    }

    public Node buildGraph(){

        /*
            Note : All the edges are Undirected
            Given Graph:
            1--2
            |  |
            4--3
        */
        Node node1 = new Node(1);
        Node node2 = new Node(2);
        Node node3 = new Node(3);
        Node node4 = new Node(4);
        List<Node> v = new ArrayList<>();
        v.add(node2);
        v.add(node4);
        node1.neighbours = v;
        v = new ArrayList<>();
        v.add(node1);
        v.add(node3);
        node2.neighbours = v;
        v = new ArrayList<>();
        v.add(node2);
        v.add(node4);
        node3.neighbours = v;
        v = new ArrayList<>();
        v.add(node3);
        v.add(node1);
        node4.neighbours = v;

        return node1;
    }

    public static void main(String[] args) {
        CloneGraph cg = new CloneGraph();

        Node source = cg.buildGraph();
        System.out.println("BFS traversal of a graph before cloning");
        cg.bfs(source);
        Node newSource = cg.cloneGraph(source);
        System.out.println("BFS traversal of a graph after cloning");
        cg.bfs(newSource);
    }
}
