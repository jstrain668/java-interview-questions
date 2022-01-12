package com.dsa.foundation.sort;

//Question: Given an array of meeting time intervals consisting of start and end times [[s1,e1],[s2,e2],...] find the
// minimum number of conference rooms required. Given a list of intervals representing the start and end time of ‘N’
// meetings, find the minimum number of rooms required to hold all the meetings.".
// Solved by finding the max number of intersecting intervals.

//Reference: https://guides.codepath.com/compsci/Scheduling-Meeting-Rooms
//Reference: http://leetcode.libaoj.in/meeting-rooms-ii.html

import java.util.*;

public class MeetingsRoomII {

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

    // The most basic way of processing the meetings is in increasing order of their start times. Sorting part is easy,
    // but for every meeting how do we find out efficiently if a room is available or not? Instead of manually iterating
    // on every room that's been allocated and checking if the room is available or not, we can keep all the rooms in a
    // min heap where the key for the min heap would be the ending time of meeting.
    // So, every time we want to check if any room is free or not, simply check the topmost element of the min heap as
    // that would be the room that would get free the earliest out of all the other rooms currently occupied
    // If the room we extracted from the top of the min heap isn't free, then no other room is . So, we can save time
    // here and simply allocate a new room.
    //
    // 1. Sort the given meetings by their start time
    // 2. Initialize a new min-heap and add the first meeting's ending time to the heap. We simply need to keep track of
    // the ending times as that tells us when a meeting room will get free.
    // 3. For every meeting room check if the minimum element of the heap i.e. the room at the top of the heap is free
    // or not.
    // 4. If the room is free, then we extract the topmost element and add it back with the ending time of the current
    // meeting we are processing.
    // 5. If not, then we allocate a new room and add it to the heap.
    // 6. After processing all the meetings, the size of the heap will tell us the number of rooms allocated.
    // This will be the minimum number of rooms needed to accommodate all the meetings.

    //Time Complexity: O(NlogN).
    //
    //There are two major portions that take up time here. One is sorting of the array that takes O(NlogN) considering
    //that the array consists of N elements. Then we have the min-heap . In the worst case, all N meetings will collide
    // with each other. In any case we have N add operations on the heap. In the worst case we will have N extract-min
    // operations as well. Overall complexity being (NlogN) since extract-min operation on a heap takes O(logN).
    //Space Complexity: O(N) because we construct the min-heap and that can contain N elements in the worst case as
    // described above in the time complexity section. Hence, the space complexity is O(N).
    public int findMinNoOfMeetingRooms(List<Interval> intervals) {

        if (intervals == null) {
            return 0;
        }

        if (intervals.size() < 2){
            return intervals.size();
        }

        // Sort the intervals by start time
        intervals.sort(new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                return i1.start - i2.start;
            }
        });

        // Use a min heap to track the minimum end time of merged intervals
        PriorityQueue<Interval> heap = new PriorityQueue<>(intervals.size(), new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end - o2.end;
            }
        });

        // start with the first meeting, put it to a meeting room
        heap.offer(intervals.get(0));

        for (int i = 1; i < intervals.size(); i++) {
            // get the meeting room that finishes earliest
            Interval interval = heap.poll();

            if (intervals.get(i).start >= interval.end) {
                // if the current meeting starts right after
                // there's no need for a new room, merge the interval
                interval.end = intervals.get(i).end;
            } else {
                // otherwise, this meeting needs a new room
                heap.offer(intervals.get(i));
            }

            // don't forget to put the meeting room back
            heap.offer(interval);
        }

        return heap.size();
    }

    //Sort both start and end times
    //Sort start times, and end times in 2 different arrays
    //Loop over start time
    //- when start[i] < end[endIndex], Count++, need more room
    //- start[i] >= end[endIndex], done using some room, move to next end time, endIndex++ (like vacating a room)
    //        - Note: we never decrese count because:
    //        - what ever count reaches, it is the max
    //- since we keep moving endIndex, when start[i] >= end[endIndex], we will just reuse meeting room w/o count++
    //        - time: O(nlogn)
    // - space: O(n)
    // - inspired by: https://leetcode.com/problems/meeting-rooms-ii/discuss/67855/Explanation-of-%22Super-Easy-Java-Solution-Beats-98.8%22-from-%40pinkfloyda

    public int minMeetingRooms(List<Interval> intervals) {
        if (intervals == null)
            return 0;

        int[] starts = extract(intervals, 0);
        int[] ends = extract(intervals, 1);

        int count = 0, endIndex = 0;
        for (int i = 0; i < starts.length; i++) {
            if (starts[i] < ends[endIndex]) {
                count++;
            }
            else {
                endIndex++;
            }
        }

        return count;
    }

    private int[] extract(List<Interval> intervals, int index) {
        int[] array = new int[intervals.size()];

        for (int i = 0; i < intervals.size(); i++) {
            if (index == 0){
                array[i] = intervals.get(i).start;
            } else if (index == 1) {
                array[i] = intervals.get(i).end;
            }
        }

        Arrays.sort(array);
        return array;
    }

    public Interval createInterval(int s, int e){
        return new Interval(s,e);
    }

    // Driver Code
    public static void main (String[] args)
    {

        MeetingsRoomII mm = new MeetingsRoomII();

        // Start times
        int[] s = { 9, 7, 8, 10};

        // Finish times
        int[] f = { 10, 9, 10, 11};

        // Defining an arraylist of meet type
        ArrayList<Interval> pair1 = new ArrayList<>();

        for(int i = 0; i < s.length; i++)
        {
            //Creating pairs of Interval(start,end) and appending to a arraylist
            pair1.add(mm.createInterval(s[i], f[i]));
        }
        System.out.println(mm.minMeetingRooms(pair1));

        // Defining an arraylist of meet type
        ArrayList<Interval> pair2 = new ArrayList<>();

        for(int i = 0; i < s.length; i++)
        {
            //Creating pairs of Interval(start,end) and appending to a arraylist
            pair2.add(mm.createInterval(s[i], f[i]));
        }

        System.out.println(mm.findMinNoOfMeetingRooms(pair2));

        //Given [[0, 30],[5, 10],[15, 20]],
        int[] x = {0,5,15};
        int[] y = {30,10,20};

        pair1.clear();
        for(int i = 0; i < x.length; i++)
        {
            //Creating pairs of Interval(start,end) and appending to a arraylist
            pair1.add(mm.createInterval(x[i], y[i]));
        }

        System.out.println(mm.minMeetingRooms(pair1));

        pair2.clear();
        for(int i = 0; i < x.length; i++)
        {
            //Creating pairs of Interval(start,end) and appending to a arraylist
            pair2.add(mm.createInterval(x[i], y[i]));
        }
        System.out.println(mm.findMinNoOfMeetingRooms(pair2));

        int[] a = {1,2,3,8,10,11};
        int[] b = {10,7,19,12,20,30};
        pair1.clear();

        for(int i = 0; i < a.length; i++)
        {
            //Creating pairs of Interval(start,end) and appending to a arraylist
            pair1.add(mm.createInterval(a[i], b[i]));
        }

        System.out.println(mm.minMeetingRooms(pair1));

        pair2.clear();

        for(int i = 0; i < a.length; i++)
        {
            //Creating pairs of Interval(start,end) and appending to a arraylist
            pair2.add(mm.createInterval(a[i], b[i]));
        }
        System.out.println(mm.findMinNoOfMeetingRooms(pair2));
    }
}
