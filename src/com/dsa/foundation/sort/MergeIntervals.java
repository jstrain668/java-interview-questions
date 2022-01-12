package com.dsa.foundation.sort;

//Question: https://leetcode.com/problems/merge-intervals/

//Reference: https://github.com/awangdev/LintCode/blob/master/Java/56.%20Merge%20Intervals.java

import java.util.*;

public class MergeIntervals {

    public class Interval {
        int start;
        int end;

        Interval() {
            start = 0;
            end = 0;
        }

        Interval(int s, int e) {
            start = s;
            end = e;
        }
    }

    Interval add(int s, int e){
        return new Interval(s,e);
    }

    //Solution: Sort the arraylist by start time, and if start times same, then sort sort by end time. After sorting
    //check current start time is greater than end time of last interval. If yes add to merged list otherwise merge
    //last and current intervals by setting the end time of last interval to max last time of current or last.
    //Time Complexity: O(nlog n) fort sorting the 2D array + O(n) for traversing the arraylist to create merged list.
    //Since O(nlogn) is the dominant term this is the time complexity
    //Space Complexity: O(n) for ArrayList
    public List<Interval> merge(List<Interval> intervals) {

        if (intervals == null || intervals.size() == 0){
            return intervals;
        }

        List<Interval> result = new ArrayList<>();

        Collections.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                int result = i1.start - i2.start;
                return (result == 0) ? i1.end - i2.end : result;
            }
        });

        Interval last = null;
        for (Interval current : intervals) {
            if (result.isEmpty() || current.start > last.end) {
                result.add(current);
                last = current;
            } else {
                last.end = Math.max(current.end, last.end);
            }
        }
        return result;
    }


    //Solution: Sort the 2D array by start time, and if start times same, then sort sort by end time. After sorting
    //check current start time is greater than end time of last interval. If yes add to merged list otherwise merge
    //last and current intervals by setting the end time of last interval to max last time of current or last.
    //Time Complexity: O(nlog n) fort sorting the 2D array + O(n) for traversing the 2D array to create merged list +
    //O(n) for converting merged array list into 2d array. Since O(nlogn) is the dominant term this is the time complexity
    //Space Complexity: O(n) for ArrayList and O(n) for result 2D array. Overall is O(n)
    public int[][] merge(int[][] intervals) {

        if (intervals == null || intervals.length == 0 || intervals[0].length == 0){
            return intervals;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int result = o1[0] - o2[0];
                return (result == 0) ? o1[1] - o2[1] : result;
            }
        });

        List<int[]> resultList = new ArrayList<>();

        int[] last = null;

        for (int[] current : intervals){
            if (last == null || current[0] > last[1]){ // last == null then add first entry otherwise if current start
                                                       // > than last end time then add
                resultList.add(current);
                last = current;
            } else {
                // last != null && last[1] >= curr[0]
                // Keep `last` to have the longest END index possible,
                // so to merge all visited interval in-between
                last[1] = Math.max(current[1],last[1]);
            }
        }

        int[][] result = new int[resultList.size()][2];

        for (int i=0; i < resultList.size(); i++){
            result[i] = resultList.get(i);
        }

        return result;
    }

    public static void main(String[] args) {
        MergeIntervals mi = new MergeIntervals();
        int[][] intervals = {{1,3},
                            {2,6},
                            {8,10},
                            {15,18}};

        int[][] intervals2 = {{1,4},
                              {4,6}};

        System.out.print("Source 2D array: ");
        for (int[] current : intervals){
            System.out.print(Arrays.toString(current)+", ");
        }
        System.out.println();
        int[][] result = mi.merge(intervals);
        System.out.print("Merged 2D array: ");
        for (int[] current : result){
            System.out.print(Arrays.toString(current)+", ");
        }
        System.out.println();

        List<Interval> inputs = new ArrayList<>();

        for (int[] current : intervals){
            inputs.add(mi.add(current[0],current[1]));
        }

        System.out.print("Merged 2D array: ");
        List<Interval> results = mi.merge(inputs);
        for (Interval current : results){
            System.out.print("["+current.start+","+ current.end+"], ");
        }
        System.out.println();
    }
}
