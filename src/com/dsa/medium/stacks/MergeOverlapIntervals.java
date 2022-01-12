package com.dsa.medium.stacks;

import java.util.*;

public class MergeOverlapIntervals {

    public class Interval{
        int start;
        int end;
        public Interval(int start, int end){
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "{" + start + ", " + end + "}";
        }
    }

    public Interval createInterval(int start,int end){
        return new Interval(start,end);
    }

    //1) Sort all intervals in increasing order of start time.
    //2) Traverse sorted intervals starting from first interval,
    //   do following for every interval.
    //      a) If current interval is not first interval and it
    //         overlaps with previous interval, then merge it with
    //         previous interval. Keep doing it while the interval
    //         overlaps with the previous one.
    //      b) Else add current interval to output list of intervals.

    public List<Interval> mergeIntervals(List<Interval> intervals){

        if (intervals == null || intervals.size() == 0 || intervals.size() == 1){
            return intervals;
        }

        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.start, o2.start);
            }
        });

        for (Interval interval: intervals){
            System.out.println(interval.toString());
        }

        List<Interval> mergedIntervals = new ArrayList<>();
        Interval prev = intervals.get(0);
        int index = 0;
        mergedIntervals.add(0,prev);
        for (int i=1; i < intervals.size(); i++){
            Interval curr = intervals.get(i);

            //Merge and add or augment existing interval in 'mergedIntervals'
            if (prev.end > curr.start){
                mergedIntervals.set(index,new Interval(Math.min(prev.start,curr.start),Math.max(prev.end, curr.end)));
            } else{ //No merge just add to 'mergedIntervals'
                index++;
                mergedIntervals.add(index,new Interval(curr.start, curr.end));
            }
        }

        return mergedIntervals;
    }

    //1. Sort the intervals based on increasing order of starting time.
    //2. Push the first interval on to a stack.
    //3. For each interval do the following
    //   a. If the current interval does not overlap with the stack
    //       top, push it.
    //   b. If the current interval overlaps with stack top and ending
    //       time of current interval is more than that of stack top,
    //       update stack top with the ending  time of current interval.
    //4. At the end stack contains the merged intervals.
    public Stack<Interval> mergeIntervalsStack(List<Interval> intervals) {

        Stack<Interval> stack = new Stack<>();
        if (intervals == null || intervals.size() == 0 || intervals.size() == 1) {
            return stack;
        }

        intervals.sort(new Comparator<Interval>() {
            @Override
            public int compare(Interval o1, Interval o2) {
                return Integer.compare(o1.start, o2.start);
            }
        });

        for (Interval interval: intervals){

            // if the stack is empty or the top interval in the stack does not overlap
            // with the current interval, push it into the stack
            if (stack.isEmpty() || interval.start >= stack.peek().end){
                stack.push(interval);
            }

            // if the top interval of the stack overlaps with the current interval,
            // merge two intervals by updating the end of the top interval
            // to the current interval
            if (stack.peek().end < interval.end) {
                stack.peek().end = interval.end;
            }
        }

        return stack;

    }
    public static void main(String[] args) {
        MergeOverlapIntervals moi = new MergeOverlapIntervals();

        List<Interval> intervals = Arrays.asList(moi.createInterval(1, 5), moi.createInterval(2, 3),
                moi.createInterval(4,6), moi.createInterval(7,8), moi.createInterval(8,10),
                moi.createInterval(12,15));

        List<Interval> mergedIntervals = moi.mergeIntervals(intervals);

        System.out.println();
        for (Interval interval: mergedIntervals){
            System.out.println(interval.toString());
        }

        System.out.println();

        Stack<Interval> stackIntervals = moi.mergeIntervalsStack(intervals);
        // print all non-overlapping intervals
        while (!stackIntervals.empty()) {
            System.out.println(stackIntervals.pop());
        }

    }
}
