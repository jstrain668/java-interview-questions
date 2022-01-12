package com.dsa.medium.graphs;

import java.util.*;

//Reference: https://aaronice.gitbook.io/lintcode/graph_search/course-schedule-ii

public class CourseScheduleII {

    //This problem is equivalent to finding if a cycle exists in a directed graph. If a cycle exists, no topological
    //ordering exists and therefore it will be impossible to take all courses.
    //Note:
    //The input is represented by a list of edges, not adjacency matrices or list.
    //You may assume that there are no duplicate edges in the input prerequisites.
    //Note: First, we convert the input to a graph represented by an adjacency list (gain efficiency).

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        if (numCourses <= 0) {
            return new int[0];
        }

        int[] result = new int[numCourses];

        // No prerequisites
        if (prerequisites == null || prerequisites.length == 0 || prerequisites[0].length == 0) {
            for (int i = 0; i < numCourses; i++) {
                result[i] = i;
            }
            return result;
        }

        LinkedList<Integer>[] graph = new LinkedList[numCourses];

        for (int i = 0; i < numCourses; i++) {
            graph[i] = new LinkedList<>();
        }

        for (int[] p: prerequisites) {
            graph[p[0]].add(p[1]);
        }

        // states: 0 = unknown, 1 = visiting, 2 = visited
        int[] visit = new int[numCourses];
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (dfsCyclic(i, visit, graph,queue)) {
                return new int[0];
            }
        }

        int i = 0;
        while (!queue.isEmpty()) {
            result[i++] = queue.poll();
        }
        return result;
    }

    private boolean dfsCyclic(int cur, int[] v, LinkedList<Integer>[] graph,Queue<Integer> queue) {
        if (v[cur] == 1)
            return true;
        if (v[cur] == 2)
            return false;
        v[cur] = 1;
        for (int i = 0; i < graph[cur].size(); i++) {
            int adjVertex = graph[cur].get(i);
            if (dfsCyclic(adjVertex, v, graph,queue))
                return true;
        }
        v[cur] = 2;
        queue.offer(cur);
        return false;
    }

    public static void main(String[] args) {
        CourseScheduleII cs = new CourseScheduleII();
        int numCourses = 2;
        int[][] prerequisites = {{1,0}};
        System.out.println("Can finish all courses: "+Arrays.toString(cs.findOrder(2,prerequisites)));

        int[][] prerequisites2 = {{1,0},{0,1}};
        System.out.println("Can finish all courses: "+ Arrays.toString(cs.findOrder(2,prerequisites2)));

         int[][] prerequisites3 = {{1,0},{2,0},{3,1},{3,2}};
        System.out.println("Can finish all courses: "+ Arrays.toString(cs.findOrder(4,prerequisites3)));

        int[][] prerequisites4 = {{}};
        System.out.println("Can finish all courses: "+ Arrays.toString(cs.findOrder(2,prerequisites4)));

        int[][] prerequisites5 = {{0,1}};
        System.out.println("Can finish all courses: "+ Arrays.toString(cs.findOrder(2,prerequisites5)));


    }
}
