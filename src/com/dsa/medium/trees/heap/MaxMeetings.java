package com.dsa.medium.trees.heap;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

//Question: 1353. Maximum Number of Events That Can Be Attended
// https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/

//Reference: https://costia.medium.com/leetcode-1353-maximum-number-of-events-that-can-be-attended-medium-7afca19b1292

public class MaxMeetings {

    /**
     * Credit: https://leetcode.com/problems/maximum-number-of-events-that-can-be-attended/discuss/510263/JavaC%2B%2BPython-Priority-Queue
     *
     * 1. Sort events by start time, if ties, by end time;
     * 2. From day 1 to day 100,000, we add all events that start on this day into a priority queue,
     * also, we remove the events that closed on this day from the priority queue;
     * 3. attend the event that ends on this day (earliest, i.e. greedy) and pop it out of the priority queue
     *
     * */

    // Solution: Intuitive idea to sort events such as events that has sooner end day are located in front, and when
    // they have the same end date, sort by start date in ascending order. Then iterate through days 1…100_001 and for
    // each day find still not used day that fells into event’s interval.
    //TC: O(max(d, nlogn)) SC: O(n) - for PriorityQueue
    public int maxEvents(int[][] events) {
        //Arrays.sort(events, (a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

        Arrays.sort(events, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return (o1[0] != o2[0]) ? o1[0] - o2[0] : o1[1] - o2[1];
            }
        });

        PriorityQueue<Integer> heap = new PriorityQueue<>();
        int maxEvents = 0;
        int i = 0;
        for (int day = 1; day <= 100000; day++) {
            while (i < events.length && events[i][0] == day) {
                heap.offer(events[i++][1]); //add all events that CAN be attended on day d
            }
            while (heap.size() > 0 && heap.peek() < day) {
                heap.poll();  //remove all events which are already closed
            }
            if (heap.size() > 0) {
                heap.poll();
                maxEvents++;  //Use Day d to attend event that closes first     TC: O(logn)
            }
        }
        return maxEvents;
    }

    public static void main(String[] args) {

        MaxMeetings mm = new MaxMeetings();

        int[][] events = new int[3][2];
        events[0][0] = 1;
        events[0][1] = 2;
        events[1][0] = 2;
        events[1][1] = 3;
        events[2][0] = 3;
        events[2][1] = 4;

        System.out.println("Number of rows "+events.length);
        System.out.println("Number of columns "+events[0].length);

        System.out.println("Max number of events that you can attend "+mm.maxEvents(events));
    }
}
