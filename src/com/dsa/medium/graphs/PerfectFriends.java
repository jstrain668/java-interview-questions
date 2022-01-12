package com.dsa.medium.graphs;

//Question:
//1. You are given a number n (representing the number of students). Each student will have an id
//     from 0 to n - 1.
//2. You are given a number k (representing the number of clubs)
//3. In the next k lines, two numbers are given separated by a space. The numbers are ids of
//     students belonging to same club.
//4. You have to find in how many ways can we select a pair of students such that both students are
//     from different clubs.


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PerfectFriends {

    private void dfs(int cVertex,LinkedList<Integer>[] adjList,boolean[] visited,List<Integer> conList){
        visited[cVertex] = true;
        conList.add(cVertex);

        for (int i=0; i< adjList[cVertex].size(); i++){
            int adjVertex = adjList[cVertex].get(i);

            if (!visited[adjVertex]){
                dfs(adjVertex,adjList,visited,conList);
            }
        }
    }

    public int countGroups(int n,int[][] edges){
        //Create Adjacency list from edges
        LinkedList<Integer>[] adjList = new LinkedList[n];
        for (int i=0; i < n; i++){
            adjList[i] = new LinkedList<>();
        }

        //UnDirected graph
        for (int[] edge : edges){
            adjList[edge[0]].addFirst(edge[1]);
            adjList[edge[1]].addFirst(edge[0]);
        }

        List<List<Integer>> conLists = new ArrayList<>();
        boolean[] visited = new boolean[n];
        for (int i=0; i < n; i++){
            if (!visited[i]){
                List<Integer> conList = new ArrayList<>();
                dfs(i,adjList,visited,conList);
                conLists.add(conList);
            }
        }

        System.out.println("Lists of connected components");
        for (List<Integer> list : conLists){
            System.out.println(list);
        }

        int count = 0;
        for(int i = 0; i < conLists.size(); i++){
            for(int j = i + 1; j < conLists.size(); j++){
                count += conLists.get(i).size() * conLists.get(j).size();
            }
        }
        return count;
    }

    public static void main(String[] args) {
        PerfectFriends pf = new PerfectFriends();
        int n = 7;
        int[][] edges = {{0,1},{2,3},{4,5},{5,6},{4,6}};

        System.out.println("Number of groups " +pf.countGroups(n,edges));
    }
}
