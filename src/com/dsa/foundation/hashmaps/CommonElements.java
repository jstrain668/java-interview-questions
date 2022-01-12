package com.dsa.foundation.hashmaps;

import java.util.*;

public class CommonElements {


    public int[] commonElements(int[] nums1,int[] nums2){
        Map<Integer,Integer> map = new HashMap<>();
        for (int num : nums1){
            map.put(num,map.getOrDefault(num,0)+1);
        }

        Queue<Integer> common = new PriorityQueue<>();

        for (int num : nums2){
            if (map.containsKey(num)){
                int count = map.get(num);
                if (count > 1 ){
                    count--;
                    map.put(num,count);
                } else{
                    map.remove(num);
                }
                common.add(num);
            }
        }

        int[] result = new int[common.size()];
        int index = 0;
        while (!common.isEmpty()){
            result[index++] = common.poll();
        }

        return result;
    }

    public static void main(String[] args) {
        CommonElements ce = new CommonElements();

        int[] nums1 = {3, 4, 2, 2, 4};
        int[] nums2 = {3, 2, 2, 7};

        int[] common = ce.commonElements(nums1,nums2);
        System.out.println("Nums 1 array: "+ Arrays.toString(nums1));
        System.out.println("Nums 2 array: "+ Arrays.toString(nums2));
        System.out.println("Common elements: "+ Arrays.toString(common));
    }
}
