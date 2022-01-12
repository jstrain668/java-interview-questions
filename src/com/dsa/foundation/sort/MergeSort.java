package com.dsa.foundation.sort;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MergeSort {

    // Merges two subList arrays of left and right

    public List<Integer> merge(List<Integer> left, List<Integer> right)
    {
        ArrayList<Integer> result = new ArrayList<>();
        int leftIndex = 0;
        int rightIndex = 0;

        while (leftIndex < left.size() && rightIndex < right.size()) {
            if (left.get(leftIndex) < right.get(rightIndex)) {
                result.add(left.get(leftIndex));
                leftIndex++;
            } else {
                result.add(right.get(rightIndex));
                rightIndex++;
            }
        }
        // Copy the rest
        List<Integer> leftRemaining = left.subList(leftIndex, left.size());
        List<Integer> rightRemaining = right.subList(rightIndex, right.size());

        result.addAll(leftRemaining);
        result.addAll(rightRemaining);

        return result;
    }

    // Main function that sorts numbers using merge()
    public List<Integer> sort(List<Integer> elements)
    {
        // Base condition reached when size of ArrayList is 1
        if (elements.size() == 1)
            return elements;

        // Divide and conquer by halving elements into left and right ArrayLists until their size is 1
        List<Integer> left = elements.subList(0, elements.size() / 2);
        List<Integer> right = elements.subList(elements.size() / 2, elements.size());
        left = sort(left);
        right = sort(right);

        // Merge and sort the halves
        return merge(left,right);

    }

    // Driver code
    public static void main(String[] args)
    {
        //int[] arr = { 12, 11, 13, 5, 6, 7 };
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(99, 44, 6, 2, 1, 5, 63, 87, 283, 4, 0));

        System.out.print("Given Array: ");
        System.out.println(numbers);

        MergeSort mergeSort = new MergeSort();
        List<Integer> sorted = mergeSort.sort(numbers);

        System.out.print("\nSorted array: ");
        System.out.println(sorted);
    }
}
