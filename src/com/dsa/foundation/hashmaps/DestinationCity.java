package com.dsa.foundation.hashmaps;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class DestinationCity {

    public String destCity(List<List<String>> paths) {

        Set<String> set = new HashSet<>();
        //Add all source cities into HashSet
        for (List<String> path : paths){
            set.add(path.get(0));
        }

        //Search for dest city not in source city set. This city is the end point
        for (List<String> path : paths){
            if (!set.contains(path.get(1))){
                return path.get(1);
            }
        }

        return "Not Found";
    }

    public static void main(String[] args) {

        DestinationCity dc = new DestinationCity();

        List<List<String>> paths = Arrays.asList(Arrays.asList("London","New York"),
                Arrays.asList("New York","Lima"),
                Arrays.asList("Lima","Sao Paulo")
                );

        System.out.println("Destination city: "+dc.destCity(paths));
    }
}
