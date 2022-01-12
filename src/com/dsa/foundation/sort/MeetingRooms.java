package com.dsa.foundation.sort;

//Question: Given an array of intervals representing N meetings, find out if a person can attend all the meetings


//A person can not attend two or more meetings at one time. It means if the timings of two meetings are overlapping,
// then she/he will not able to attend it. How to recognize/check that the two meetings are overlapping or not.
// We will use the time interval to check that the meetings are overlapping or not.

//If the intervals overlap, you cannot participate in the meeting; Core: Sort the time periods according to the
//start time

//Reference: https://aaronice.gitbook.io/lintcode/sweep-line/meeting-rooms
//Reference: https://massivealgorithms.blogspot.com/2015/08/like-coding-leetcode-252-meeting-rooms.html
//Reference: https://github.com/wzhishen/leetcode/blob/master/src/solutions/MeetingRooms_252.java
//Reference (need a subscription): https://leetcode.com/problems/meeting-rooms/


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MeetingRooms {

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


    // Sort all the interval times by start time and if start times are equal then sort by end time.After sorting you
    // go through each object comparing it with the previous object to find if there is an overlap - O(n) time
    // complexity. To determine if there are overlapping intervals compare last.end vs. current.start and if overlap
    // return false otherwise true.
    // This solution takes an array of type Interval objects
    // To sort create anonymous class to execute compare of the start times of each interval to sort them
    // Time complexity: O(nlogn)
    // Space complexity : O(1). Since no additional space is allocated.
    public boolean canAttendMeetings(Interval[] intervals) {

        if (intervals == null || intervals.length < 2){
            return true;
        }

        Arrays.sort(intervals, new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                int result = i1.start - i2.start;
                return (result == 0) ? i1.end - i2.end : result;
            }
        });

       /* Interval last = null;
        for (Interval i: intervals) {
            if (last != null && i.start < last.end) {
                return false;
            }
            last = i;
        } */

        for (int i=1; i < intervals.length; i++){
            if (intervals[i].start < intervals[i-1].end){
                return false;
            }
        }

        return true;
    }


    // Sort all the interval times by start time and if start times are equal then sort by end time.After sorting you
    // go through each object comparing it with the previous object to find if there is an overlap - O(n) time
    // complexity. To determine if there are overlapping intervals compare last.end vs. current.start and if overlap
    // return false otherwise true.
    // This solution takes an array list of type Interval objects
    // To sort create anonymous class to execute compare of the start times of each interval to sort them
    // Time complexity: O(nlogn)
    // Space complexity : O(1). Since no additional space is allocated.
    public boolean canAttendMeetings(List<Interval> intervals) {

        if (intervals == null || intervals.size() < 2){
            return true;
        }

        intervals.sort(new Comparator<Interval>() {
            public int compare(Interval i1, Interval i2) {
                int result = i1.start - i2.start;
                return (result == 0) ? i1.end - i2.end : result;
            }
        });

        for (int i=1; i < intervals.size(); i++){
            if (intervals.get(i).start < intervals.get(i-1).end){
                return false;
            }
        }

        return true;
    }

    // Sort all the interval times by start time and if start times are equal then sort by end time.After sorting you
    // go through each object comparing it with the previous object to find if there is an overlap - O(n) time
    // complexity. To determine if there are overlapping intervals compare last.end vs. current.start and if overlap
    // return false otherwise true.
    // This solution takes a 2D array of ints where a row index 0 = start and row index = end
    // To sort create anonymous class to execute compare of the start times of each interval to sort them
    // Time complexity: O(nlogn)
    // Space complexity : O(1). Since no additional space is allocated.
    public boolean canAttendMeetings(int[][] intervals) {
        if (intervals == null || intervals.length < 2){
            return true;
        }

        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                int result = o1[0] - o2[0];
                return (result == 0) ? o1[1] - o2[1] : result;
            }
        });

        for (int i=1; i < intervals.length; i++){
            if (intervals[i][0] < intervals[i-1][1]){
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        MeetingRooms mr = new MeetingRooms();
        Interval[] list11 = new Interval[4];

        list11[0] = mr.add(0,30);
        list11[1] = mr.add(5,10);
        list11[2] = mr.add(15,20);
        list11[3] = mr.add(20,22);

        System.out.println("Can I attend all meetings: "+mr.canAttendMeetings(list11));

        List<Interval> list1 = new ArrayList<>();
        list1.add(mr.add(0,30));
        list1.add(mr.add(5,10));
        list1.add(mr.add(15,20));
        list1.add(mr.add(20,22));

        System.out.println("Can I attend all meetings: "+mr.canAttendMeetings(list1));

        int[][] events1 = new int[4][2];
        events1[0][0] = 0;
        events1[0][1] = 30;
        events1[1][0] = 5;
        events1[1][1] = 10;
        events1[2][0] = 15;
        events1[2][1] = 20;
        events1[3][0] = 20;
        events1[3][1] = 22;

        System.out.println("Can I attend all meetings: "+mr.canAttendMeetings(events1));

        list11[0] = mr.add(5,10);
        list11[1] = mr.add(15,20);
        list11[2] = mr.add(20,22);
        list11[3] = mr.add(22,23);

        System.out.println("Can I attend all meetings: "+mr.canAttendMeetings(list11));

        List<Interval> list2 = new ArrayList<>();
        list2.add(mr.add(5,10));
        list2.add(mr.add(15,20));
        list2.add(mr.add(20,22));
        list2.add(mr.add(22,23));

        System.out.println("Can I attend all meetings: "+mr.canAttendMeetings(list2));

        events1[0][0] = 5;
        events1[0][1] = 10;
        events1[1][0] = 15;
        events1[1][1] = 20;
        events1[2][0] = 20;
        events1[2][1] = 22;
        events1[3][0] = 22;
        events1[3][1] = 23;

        System.out.println("Can I attend all meetings: "+mr.canAttendMeetings(events1));

        Interval[] list22 = new Interval[3];
        list22[0] = mr.add(7,10);
        list22[1] = mr.add(2,4);
        list22[2] = mr.add(15,20);

        System.out.println("Can I attend all meetings: "+mr.canAttendMeetings(list22));

        List<Interval> list3 = new ArrayList<>();
        list3.add(mr.add(7,10));
        list3.add(mr.add(2,4));
        list3.add(mr.add(15,20));

        System.out.println("Can I attend all meetings: "+mr.canAttendMeetings(list3));

        int[][] events2 = new int[3][2];
        events1[0][0] = 7;
        events1[0][1] = 10;
        events1[1][0] = 2;
        events1[1][1] = 4;
        events1[2][0] = 15;
        events1[2][1] = 20;

        System.out.println("Can I attend all meetings: "+mr.canAttendMeetings(events2));

    }
}
