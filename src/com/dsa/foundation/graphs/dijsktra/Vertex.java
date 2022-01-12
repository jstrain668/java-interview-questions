package com.dsa.foundation.graphs.dijsktra;

public class Vertex implements Comparable{
    int id;
    float distance;
    Vertex parent;

    public Vertex(){
        distance = Float.MAX_VALUE; // "infinity"
        parent = null;
    }

    public Vertex(int id){
        this.id = id;
        distance = Float.MAX_VALUE; // "infinity"
        parent = null;
    }

    public int compareTo(Object o) {
        Vertex other = (Vertex) o;
        return Double.compare(this.distance, other.distance);
    }
}
