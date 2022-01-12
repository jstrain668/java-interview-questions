package com.dsa.foundation.linkedlist;


import java.util.LinkedHashMap;
import java.util.Map;

//Reference: https://java2blog.com/lru-cache-implementation-java/
//Reference: https://www.interviewcake.com/concept/java/lru-cache

//Description:  All you have to do is extend java.util.LinkedHashMap and override its protected removeEldestEntry()
// method so that it checks if the size of map is greater than a size you specified while creating the Map. If yes
// remove the eldest entry. Now the question is when Map is full which entry will it remove, you have 2 options
//
//Eldest: If you just want to remove the first entry that you inserted in the Map when adding a new entry then in your
// constructor you could use super(cacheSize, 0.75f);, so LinkedHashMap wont keep track of when a particular entry were
// accessed.
//Least recently used (LRU): But if you want to make sure that the entry that was least recently used should be removed
// then call super(cacheSize, 0.75f, true); from constructor of your LRUCache so that LinkedHashMap keeps track of when
// entry was accessed and removes the Least recently used entry


public class LRUCacheUsingLinkedHashMap<Key,Value> extends LinkedHashMap<Key,Value> {
    private int cacheSize;

    public LRUCacheUsingLinkedHashMap(int cacheSize) {
        super(cacheSize, 0.75f,true);
        this.cacheSize = cacheSize;
    }

    @Override
    protected boolean removeEldestEntry(Map.Entry<Key, Value> eldest) {
        return size() > cacheSize;
    }

    public static void main(String[] argv){
        LRUCacheUsingLinkedHashMap<Integer, Integer> lrucache = new LRUCacheUsingLinkedHashMap<>(4);

        lrucache.put(1, 100);
        lrucache.put(10, 99);
        lrucache.put(15, 98);
        lrucache.put(10, 97);
        lrucache.put(12, 96);
        lrucache.put(18, 95);
        lrucache.put(1, 94);

        System.out.println(lrucache.getOrDefault(1,-1));
        System.out.println(lrucache.getOrDefault(10,-1));
        System.out.println(lrucache.getOrDefault(15,-1));
    }
}