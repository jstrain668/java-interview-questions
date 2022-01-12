package com.dsa.medium.hashmaps;

import java.util.HashMap;
import java.util.Map;

//https://www.geeksforgeeks.org/find-itinerary-from-a-given-list-of-tickets/
//https://www.techiedelight.com/find-itinerary-from-given-list-tickets/

public class TravelItinerary {

    // Driver function
    public static void main(String[] args)
    {
        TravelItinerary ti = new TravelItinerary();
        Map<String, String> dataSet = new HashMap<String, String>();
        dataSet.put("Dublin", "Cork");
        dataSet.put("Cork", "Limerick");
        dataSet.put("Limerick", "Galway");
        dataSet.put("Galway", "Belfast");

        ti.printResult(dataSet);
    }


    //It is assumed that the input list of tickets is not cyclic and there is one ticket from every city except
    //final destination. One Solution is to build a graph and do Topological Sorting of the graph. Time complexity of
    //this solution is O(n). Can also use hashmap to avoid building a graph. The idea is to first find the starting
    // point. A starting point would never be on ‘to’ side of a ticket. Once we find the starting point,traverse the
    // given map to print itinerary in order. Following are steps.
    // 1. Create a HashMap of given pair of tickets.  Let the created HashMap be 'dataset'. Every entry of 'dataset' is of
    // the form "fromLocation ->toLocation" like "Dublin" -> "Cork"
    // 2. Find the starting point of itinerary.
    //    a) Create a reverse HashMap.  Let the reverse be 'reverseDataSet'
    //       Entries of 'reverseMap' are of the form "to->form".
    //       Following is 'reverseMap' for above example.
    //        "Cork" -> "Dublin"
    //        "Limerick" -> "Cork"
    //        "Galway" -> "Limerick"
    //        "Belfast" ->  "Galway"
    //    b) Traverse 'dataset'.  For every key of dataset, check if it
    //       is in 'reverseDataSet'.  If a key is not present, then we found the starting point.
    //       In the above example, "Dublin" is starting point.
    // 3. Start from above found starting point and traverse the 'dataset' to print itinerary.
    // Time Complexity: O(n) for creating 'reverseDataSet', O(n) for traversing 'dataSet' to find key not in 'reverse
    // DataSet'. Loop through the 'dataSet' map retrieving the toLocation (value in Hashmap) until null (no more
    // entries). All O(n) so overall time complexity O(n)
    // Space Complexity: O(n) for the additional HashMap


    // This function populates 'result' for given input 'dataset'
    private void printResult(Map<String, String> dataSet)
    {
        // To store reverse of given map
        Map<String,String> reverseDataSet = new HashMap<>();

        for (Map.Entry<String,String> mapElement : dataSet.entrySet()){
            reverseDataSet.put(mapElement.getValue(),mapElement.getKey());
        }

        //Find the start location
        String fromLocation = null;
        for (Map.Entry<String,String> mapElement : dataSet.entrySet()){
            if (!reverseDataSet.containsKey(mapElement.getKey())){
                fromLocation = mapElement.getKey();
                break;
            }
        }

        // If we could not find a starting point, then something wrong
        // with input
        if (fromLocation == null)
        {
            System.out.println("Invalid Input");
            return;
        }


        String toLocation = dataSet.get(fromLocation);
        while (toLocation != null)
        {
            System.out.print(fromLocation +  "->" + toLocation + ", ");
            fromLocation = toLocation;
            toLocation = dataSet.get(toLocation);
        }
    }



}
