package com.dsa.medium.logicpuzzle;

//Question: https://leetcode.com/problems/trapping-rain-water/

//Reference: https://medium.com/@harycane/trapping-rain-water-8a1817b82d98
//Reference; https://github.com/awangdev/LintCode/blob/master/Java/42.%20Trapping%20Rain%20Water.java
//Reference: https://github.com/wzhishen/leetcode/blob/master/src/solutions/TrappingRainWater_042.java

//Solution:The basic algorithm behind trapping rain water is that for each tower, if there exists a tower that is taller
//than itself on its left as well as on its right, then rain water can be trapped above the tower in contention.
//In order to calculate the units of rain water trapped, we subtract the current tower’s height from the result of
//minimum of the maximum height on the tower’s left and right, and finally find the maximum of the resultant difference
//with 0 (to take into account for tower in question to be the tallest tower).
//In order to keep track of the maximum height on the right of each tower, we populate a maxSeenRight array with the
//maximum value witnessed, as we traverse the elevation map array from the right to the left. We keep track of the
//maximum height on the left of each tower, by updating maxSeenLeft variable with the current height, when the current
//height exceeds the maxSeenLeft, as we iterate the elevation map from left to right.
//Finally the rain water variable contains the cumulative units of rain water than can be trapped by the given elevation
//map.
//Test:
//1 Test with null or empty elevation map array.
//2 Test with ascending and descending values in the elevation map.
//3 Test with constant values in the elevation map, and values with higher towers heights on either side.

public class TrappedRainWater {

    //Time Complexity: O(n) although its O(2) for traversing the array twice but drop the constant term
    //Space Complexity: O(n) for the maxSeenRight array
    public int trap(int[] height) {


        if (height == null){
            return -1;
        }

        if (height.length < 3){
            return 0;
        }

        int maxSeenSoFar = 0;//helper variable to formulate max height to the right array
        int[] maxSeenRight = new int[height.length];//array that contains max height seen to the right                                         of tower i
        int maxSeenLeft = 0;//optimized to have a variable that contains max height of a tower traversing from the left.
        int rainWater = 0;//variable to store total units of rain water.

        for (int i = height.length - 1; i >= 0; i--) {//**traversing from Right to Left
            if (height[i] > maxSeenSoFar) {//if curr height is greater than maxSeenSoFar
                maxSeenSoFar = height[i];//then update maxSeenSoFar with curr height
                maxSeenRight[i] = maxSeenSoFar;//and enter that maxSeenSoFar in maxSeenRight array
            } else {//otherwise update maxSeenSoFar into maxSeenRight                             array
                maxSeenRight[i] = maxSeenSoFar;
            }
        }

        for (int i = 0; i < height.length; i++) { //**traversing from Left to Right
            rainWater += Integer.max((Integer.min(maxSeenLeft, maxSeenRight[i]) - height[i]), 0);//0 to take care of case where there is no taller tower to its left & right

            if (height[i] > maxSeenLeft) {//if curr height > maxSeenLeft
                maxSeenLeft = height[i];//update maxSeenLeft with curr height.
            }

        }

        return rainWater;
    }

    /* Two pointers: O(n) time, O(1) space
     * The two pointer approach respects the central highest point:
     * The entire structure is divided by the central highest point, where maxLeft == maxRight.
     * Before left and right meet on this point, keep left++, right--.
     */
    //Time Complexity: O(n) - only one traversal
    //Space Complexity: O(1)
    public int trap2(int[] height) {

        if (height == null){
            return -1;
        }

        if (height.length < 3){
            return 0;
        }

        int left = 0, right = height.length - 1;
        int leftMax = 0, rightMax = 0, rain = 0;

        while (left <= right) {
            if (height[left] <= height[right]) {
                if (height[left] > leftMax) {
                    leftMax = height[left];
                }
                else {
                    rain += leftMax - height[left];
                }
                left++;
            } else {
                if (height[right] > rightMax) {
                    rightMax = height[right];
                }
                else {
                    rain += rightMax - height[right];
                }
                right--;
            }
        }
        return rain;
    }


    public static void main(String[] args) {
        TrappedRainWater trw = new TrappedRainWater();
        int[] nums = {0,1,0,2,1,0,1,3,2,1,2,1};

        System.out.println("Trapped water: "+trw.trap2(nums));

    }
}
