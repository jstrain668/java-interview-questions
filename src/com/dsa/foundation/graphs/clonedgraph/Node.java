package com.dsa.foundation.graphs.clonedgraph;

import java.util.ArrayList;
import java.util.List;

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