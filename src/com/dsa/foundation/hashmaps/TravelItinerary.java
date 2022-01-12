package com.dsa.foundation.hashmaps;

import java.util.*;

public class TravelItinerary {

    //Time Complexity: O(n) for creating 'reverseDataSet', O(n) for traversing 'dataSet' to find key not in 'reverse
    //DataSet'. Loop through the 'dataSet' map retrieving the toLocation (value in Hashmap) until null (no more
    // entries). All O(n) so overall time complexity O(n)
    // Space Complexity: O(n) for the additional HashMap

    public List<String> findTravelItinerary(Map<String,String> map){
        List<String> route = new ArrayList<>();

        // To store reverse of given map
        Map<String,String> reverseMap = new HashMap<>();

        for (Map.Entry<String,String> entry : map.entrySet()){
            reverseMap.put(entry.getValue(),entry.getKey());
        }

        //Find the start city location
        String fromCity = null;
        for (String fromLocation : map.keySet()){
            if (!reverseMap.containsKey(fromLocation)){
                fromCity = fromLocation;
                break;
            }
        }

        route.add(fromCity);
        String toCity = map.get(fromCity);
        while (toCity != null){
            route.add(toCity);
            fromCity = toCity;
            toCity = map.get(fromCity);
        }

        return route;
    }

    public List<String> findItinerary(List<List<String>> tickets){

        Map<String,String> map = new HashMap<>();

        for (List<String> ticket : tickets){
            //Add From and To location
            map.put(ticket.get(0),ticket.get(1));
        }

        Map<String,String> reverseMap = new HashMap<>();
        for (var entry : map.entrySet()){
            //Add To and From location
            reverseMap.put(entry.getValue(), entry.getKey());
        }


        // A To location will not exist in the From To map. This is the end location
        String toLocation = null;
        for (var entry : reverseMap.keySet()){
            if (!map.containsKey(entry)){
                toLocation = entry;
                break;
            }
        }

        List<String> result = new LinkedList<>();

        while (toLocation != null)
        {
            result.add(0,toLocation);
            String fromLocation = toLocation;
            toLocation = reverseMap.get(fromLocation);
        }
        return result;
    }

    public static void main(String[] args) {
        TravelItinerary ti = new TravelItinerary();

        List<List<String>> paths = Arrays.asList(Arrays.asList("London","New York"),
                Arrays.asList("New York","Lima"),
                Arrays.asList("Lima","Sao Paulo")
        );

        Map<String,String> itinerary = new HashMap<>();

        for (List<String> path : paths){
            itinerary.put(path.get(0),path.get(1));
        }

        System.out.println("Route: "+ti.findTravelItinerary(itinerary));

        System.out.println("Route: "+ti.findItinerary(paths));

    }
}
