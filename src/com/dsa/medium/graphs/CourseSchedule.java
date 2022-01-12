package com.dsa.medium.graphs;

import java.util.LinkedList;

//Reference: https://aaronice.gitbook.io/lintcode/graph_search/course-schedule

public class CourseSchedule {

    //This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological
    //ordering exists and therefore it will be impossible to take all courses.
    //Note:
    //The input is represented by a list of edges, not adjacency matrices or list.
    //You may assume that there are no duplicate edges in the input prerequisites.
    //Note: First, we convert the input to a graph represented by an adjacency list (gain efficiency).

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0 || prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {
            return true;
        }

        LinkedList<Integer>[] graph = new LinkedList[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] p: prerequisites) {
            graph[p[0]].addFirst(p[1]);
        }

        // states: 0 = not visited, 1 = visiting, 2 = visited
        int[] visit = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            if (dfsCyclic(i, visit, graph)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfsCyclic(int cur, int[] v, LinkedList<Integer>[] graph) {
        if (v[cur] == 1)
            return true;
        if (v[cur] == 2)
            return false;
        v[cur] = 1;
        for (int i = 0; i < graph[cur].size(); i++) {
            int adjVertex = graph[cur].get(i);
            if (dfsCyclic(adjVertex, v, graph))
                return true;
        }
        v[cur] = 2;
        return false;
    }

    public static void main(String[] args) {
        CourseSchedule cs = new CourseSchedule();
        int numCourses = 2;
        int[][] prerequisites = {{1,0}};
        System.out.println("Can finish all courses: "+cs.canFinish(2,prerequisites));

        int[][] prerequisites2 = {{1,0},{0,1}};
        System.out.println("Can finish all courses: "+cs.canFinish(2,prerequisites2));


    }
}
