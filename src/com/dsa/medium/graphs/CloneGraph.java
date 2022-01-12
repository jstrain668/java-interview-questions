package com.dsa.medium.graphs;

import java.util.*;

//Question: https://leetcode.com/problems/clone-graph/

//Reference: https://www.geeksforgeeks.org/clone-an-undirected-graph/

public class CloneGraph {

    class Node {
        public int val;
        public List<Node> neighbours;
        public Node() {
            val = 0;
            neighbours = new ArrayList<Node>();
        }
        public Node(int val) {
            this.val = val;
            this.neighbours = new ArrayList<Node>();
        }
        public Node(int val, ArrayList<Node> neighbours) {
            this.val = val;
            this.neighbours = neighbours;
        }
    }

    public Node cloneGraph(Node node) {
      if (node == null){
          return null;
      }

      Map<Node,Node> map = new HashMap<>();
      map.put(node,new Node(node.val,new ArrayList<>()));
      Queue<Node> queue = new ArrayDeque<>();
      queue.add(node);

      while (!queue.isEmpty()){
          Node cNode = queue.poll();

          for (int i=0; i < cNode.neighbours.size(); i++){
              Node neighbor = cNode.neighbours.get(i);

              if (!map.containsKey(neighbor)){
                  map.put(neighbor,new Node(neighbor.val,new ArrayList<>()));
                  queue.add(neighbor);
              }
              map.get(cNode).neighbours.add(map.get(neighbor));
          }
      }
      return map.get(node);
    }

    // BFS traversal of a graph to
    // check if the cloned graph is correct
    public void bfs(Node source)
    {
        Queue<Node> q = new LinkedList<>();
        q.add(source);
        HashMap<Node,Boolean> visit = new HashMap<Node,Boolean>();
        visit.put(source,true);

        while (!q.isEmpty())
        {
            Node u = q.poll();
            System.out.println("Value of Node " + u.val);
            System.out.println("Address of Node " + u);
            if (u.neighbours != null)
            {
                List<Node> v = u.neighbours;
                for (Node g : v)
                {
                    if (visit.get(g) == null)
                    {
                        q.add(g);
                        visit.put(g,true);
                    }
                }
            }
        }
        System.out.println();
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
