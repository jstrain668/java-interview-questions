package com.dsa.medium.trees.heap;

import java.util.*;

//Question: Assuming you have a list of n data sets (example: Products on the Amazon website, with. name:string +
// price:float). How can you most efficiently find the top x elements (where x << n). You may safely assume that all
// prices and names are distinct, the data is already in memory.
// Normal Solution: Go over the data, keep the top x elements in a temp data structure. Use a binary tree or heap for
// that data structure. O(n*log x)

//Reference: https://www.geeksforgeeks.org/time-complexity-of-building-a-heap/

public class TopXProducts {


    //Solution: To find the top X products create a Max heap tree structure as a temporary store for the top 10 products
    //that are sourced from a HashMap for all products and respective prices. The max heap is a realised as a priority
    //queue where a comparator is created to sort the products in descending order from max to min price. An ArrayList
    //is returned with the top 10 products by removing the top 10 from priority queue and adding to the List which is
    //reversed to present top x in the correct order
    //Time Complexity: O(n) to create the max heap as you need to traverse all data in the HasHMap to arrive ar the top
    //X in the max heap. Since there are only X entries in the Max heap the runtime complexity for heap processing is
    // O(nlogX). Populating the arraylist is O(10) and and reversing the list is O(10). The overall time complexity is
    // O(nlogX)
    //Space Complexity: O(X) for the heap and the 'tops' array list
    public  List<Map.Entry<String, Float>> findTopXProducts(Map<String,Float> products,int topX){
        List<Map.Entry<String, Float>> tops = new ArrayList<>();
        //Specify comparator to sort priority queue as max heap (top of the tree is the max float value)
        Queue<Map.Entry<String, Float>> pq = new PriorityQueue<>(new Comparator<Map.Entry<String, Float>>() {
            @Override
            public int compare(Map.Entry<String, Float> o1, Map.Entry<String, Float> o2) {
                return Float.compare(o1.getValue(), o2.getValue());
            }
        });

        //Create the heap with the top X products
        for (Map.Entry<String,Float> entry : products.entrySet()){
            if (pq.size() < topX){
                pq.add(entry);
            } else { //Remove and add to ensure only top 10 is recorded in heap
                if (entry.getValue() > pq.peek().getValue()){
                    pq.remove();
                    pq.add(entry);
                }
            }
        }

        while (!pq.isEmpty()){
            tops.add(pq.remove());
        }

        Collections.reverse(tops);
        return tops;
    }

    public Map<String,Float> setUpProducts(Object[][] objects) {

        Map<String,Float> map = new HashMap<>();

        for (Object[] object : objects){

            map.put((String) object[0],(Float)object[1]);
        }
        return map;
    }

    public static void main(String[] args) {
        TopXProducts tp = new TopXProducts();
        int X = 10;
        Object[][] objects = {{"Diamond", 159000.23f}, {"Platinum",45432.22f}, {"Gold", 5678.99f}, {"Silver", 2345.23f}, {"Bronze", 1234.45f}, {"Tin", 789.44f}, {"Iron", 999.1f}, {"Paper", 10f}, {"Plastic", 7f},
                {"Coal", 345f}, {"Water", 2f},{"Coke",3f},{"Fanta",2.5f}};
        Map<String,Float> products = tp.setUpProducts(objects);
        List<Map.Entry<String, Float>> topXProducts = tp.findTopXProducts(products,X);

        System.out.println("Top 10 words with the most occurrences:");
        int count = 1;
        for(Map.Entry<String, Float> current : topXProducts){
            System.out.println(count+" "+current.getKey()+" "+ current.getValue());
            count++;
        }

    }
}
