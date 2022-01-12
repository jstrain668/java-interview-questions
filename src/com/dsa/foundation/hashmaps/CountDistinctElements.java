package com.dsa.foundation.hashmaps;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Count distinct elements in every window: https://practice.geeksforgeeks.org/problems/count-distinct-elements-in-every-window/1



public class CountDistinctElements {

    public List<Integer> countDistinct(int[] A,int n,int k){

        Map<Integer,Integer> m = new HashMap<>();
        List<Integer> ans = new ArrayList<>();

        for(int i=0;i<k;i++){
            m.put(A[i],m.getOrDefault(A[i],0)+1);
        }

        ans.add(m.size());
        int last=0;

        for(int i=k;i<n;i++){
            m.put(A[i],m.getOrDefault(A[i],0)+1);

            int temp = A[last++];
            if(m.get(temp)>1){
                m.put(temp,m.get(temp)-1);
            } else {
                m.remove(temp);
            }
            ans.add(m.size());
        }
        return ans;

    }

    public static void main(String[] args) {
        CountDistinctElements cde = new CountDistinctElements();

        int[] A = {1,2,1,3,4,2,3};
        int k = 4;

        System.out.println("Number of distinct elements for window slide "+k+" :"+cde.countDistinct(A,A.length,k).toString());

    }
}
