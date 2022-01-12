package com.dsa.foundation.sort;

// Question: Find the maximum number of meetings you can attend without overlapping with other meetings
// In this problem, you are provided with one room in your company. In this meeting room, there are "N" meetings that
// need to take place such that only one meeting can take place at a single time. Each meeting has a starting time and
// an ending time, both of which are provided to you in the form of two arrays(s[] and f[]). Our task here is to find
// the maximum number of meetings that can take place in this single room without any conflicts based on their starting
// and end timings.

// Reference: Background on the sorting choice for finding max number of meetings that can be attended which is to
// schedule the meeting with the earliest ending time
// https://courses.cs.duke.edu//spring19/compsci330/lecture7scribe.pdf
// https://www.codesdope.com/blog/article/find-maximum-meetings-in-one-room/
// https://www.geeksforgeeks.org/find-maximum-meetings-in-one-room/

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MaxMeetingsInOneRoom {

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

    // We sort the start and end time pairs for each meeting on the basis of the end time. Then we compare if the next
    // meeting's start time is greater than the previous meeting's end time. If the start time is greater, we can
    // schedule this meeting and we increase our meeting counter otherwise this meeting can't take place in the room.
    // In this way we traverse through all the meeting pairs, comparing in a greedy fashion and checking if the meeting
    // can take place or not.
    // Main motive behind using the greedy approach to solve this problem is that we want to adjust all the meetings in
    // such a way so that maximum meetings can take place in a single room. So, using the greedy approach, if we select
    // the meeting with the least time first, we then have more time remaining to allocate the other meetings in the
    // same room and this way we can get the maximum meetings that can take place
    //1. Sort all meetings according to their end time
    //2. Select first meeting and its end time in variable := prev_end
    //3. Now iterate through all other meetings and for each current_meet:
    //                1. if start time of current_meet > prev_end, then
    //                            a) increment counter by 1
    //                            b) prev_end = end time of current_meet
    //                2. else ignore the current_meet
    // Time complexity: traverse through all the elements only once while comparing. Thus, if the timings arrays are
    // already provided in a sorted form, then we have a time complexity of O(N). But if the arrays are unsorted,
    // then there is an overhead cost of sorting them which results in O(NlogN) time complexity.
    // Space Complexity: Used an extra array to store all the meeting pairs, the space complexity is O(N).
    public int getMaximumMeetings(List<Interval> list) {

        //Sorting meetings based on finish timings
        list.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return o1.end - o2.end;
            }
        });

        //list.sort((i1, i2) -> {
        //    return i1.end - i2.end;
        //});

        //list.sort(Comparator.comparingInt(i -> i.end)); // sort by finish times ascending

        ArrayList<Interval> meetings = new ArrayList<>();
        int count = 1;

        // First meeting is always selected
        meetings.add(list.get(0));

        int prev_end = list.get(0).end;

        // Checking if meetings can take place or not.
        for(int i = 1; i < list.size(); i++)
        {
            if (list.get(i).start > prev_end)
            {
                meetings.add(list.get(i));
                prev_end = list.get(i).end;
                count++;
            }
        }

        System.out.println("Maximum meetings that can take place are: "+count);
        System.out.println("Selected meetings: ");
        for(int i = 0; i < meetings.size(); i++) {
            System.out.println("Meeting " + i + " begins at " + meetings.get(i).start + " ends at " + meetings.get(i).end);
        }

        return count;
    }

    public Interval createInterval(int s,int e){
        return new Interval(s,e);
    }

    // Driver Code
    public static void main (String[] args)
    {

        MaxMeetingsInOneRoom mmr = new MaxMeetingsInOneRoom();

        // Start times
        int[] s = { 1, 2, 10, 6, 9, 0 };

        // Finish times
        int[] f = { 3, 5, 12, 8, 11, 7 };

        // Defining an arraylist of meet type
        ArrayList<Interval> pair = new ArrayList<>();

        for(int i = 0; i < s.length; i++)
        {
            //Creating pairs of Interval(start,end) and appending to a arraylist
            pair.add(mmr.createInterval(s[i], f[i]));
        }

        mmr.getMaximumMeetings(pair);

        pair.clear();

        // Starting time
        int s1[] = { 1, 3, 0, 5, 8, 5 };

        // Finish time
        int f1[] = { 2, 4, 6, 7, 9, 9 };

        for(int i = 0; i < s.length; i++)
        {
            //Creating pairs of Interval(start,end) and appending to a arraylist
            pair.add(mmr.createInterval(s1[i], f1[i]));
        }

        mmr.getMaximumMeetings(pair);
    }
}
