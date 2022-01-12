package com.dsa.medium.graphs;

import java.util.LinkedList;

//Reference: https://www.junhaow.com/lc/problems/graph/207_course-schedule

public class CourseScheduleDFS {


    //This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological
    //ordering exists and therefore it will be impossible to take all courses.
    //Note:
    //The input is represented by a list of edges, not adjacency matrices or list.
    //You may assume that there are no duplicate edges in the input prerequisites.
    //Note: First, we convert the input to a graph represented by an adjacency list (gain efficiency).

    public boolean canFinish(int numCourses, int[][] preReq) {
        if (numCourses == 0 || preReq == null || preReq.length == 0) {
            return true; // or false
        }

        // convert to adjacency list O(E)
        LinkedList<Integer>[] graph = new LinkedList[numCourses];

        for (int i=0; i < numCourses; i++){
            graph[i] = new LinkedList<>();
        }

        for (int[] edge : preReq){
            graph[edge[1]].addFirst(edge[0]);
        }

        //marked array has three states:
        //If node v has not been visited, then mark it as 0.
        //If node v is being visited, then mark it as -1. If we find a vertex marked as -1 in DFS, then there is a ring.
        //If node v has been visited, then mark it as +1. If a vertex was marked as -1, then no ring contains v or its
        // successors.
        int[] marked = new int[numCourses];
        for (int v=0; v < numCourses; v++){
            if (dfs(v,graph,marked)){
                return false;
            }
        }
        return true;
    }

    public boolean dfs(int v,LinkedList<Integer>[] graph,int[] marked) {
        marked[v] = -1;  //visiting
        for (int i = 0; i < graph[v].size(); i++) {
            int adjVertex = graph[v].get(i);

            if (marked[adjVertex] == -1) {
                return true; // cycle exists
            }
            if (marked[adjVertex] == 0) { // not visited
                if (dfs(adjVertex, graph, marked)) {
                    return true;
                }
            }
        }
        marked[v] = 1; // visited
        return false; // no cycle is detected
    }

    public static void main(String[] args) {
        CourseScheduleDFS cs = new CourseScheduleDFS();
        int numCourses = 2;
        int[][] prerequisites = {{1,0}};
        System.out.println("Can finish all courses: "+cs.canFinish(2,prerequisites));

        int[][] prerequisites2 = {{1,0},{0,1}};
        System.out.println("Can finish all courses: "+cs.canFinish(2,prerequisites2));


    }
}
