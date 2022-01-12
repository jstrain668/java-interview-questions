package com.dsa.medium.linkedlist;


import java.util.HashMap;
import java.util.Map;

//Reference: https://www.interviewcake.com/concept/java/lru-cache
//Reference: https://java2blog.com/lru-cache-implementation-java/

public class LRUCache {

    int capacity;
    Node head;
    Node tail;
    Map<Integer,Node> map = new HashMap<>();

    public LRUCache(int capacity){
        this.capacity = capacity;
    }

    public int get(int key){

        if (map.containsKey(key)){
            Node node = map.get(key);
            delete(node);
            setHead(node);
            return node.value;
        }

        return -1;
    }

    public void delete(Node node){

        //Check prev node exists and update accordingly
        if (node.prev != null){
            node.prev.next = node.next;
        } else { // deleting head so need new head
            head = node.next;
        }

        //Check next node exists and update accordingly
        if (node.next != null){
            node.next.prev = node.prev;
        } else{ //deleting tail so need new tail
            tail = node.prev;
        }
    }

    public void setHead(Node node){

       //New head next points to old head
        node.next = head;
        node.prev = null;

        //Old head prev points to new head
        if (head != null){
            head.prev = node;
        }

        //New head replace old head
        head = node;

        if (tail == null){
            tail = head;
        }
    }

    public void set(int key, int value){

        if (map.containsKey(key)){
            Node node = map.get(key);
            node.value = value;
            delete(node);
            setHead(node);
        } else { //Create a new node and entry in the cache
            Node newNode = new Node(key,value);
            if (map.size()>=capacity){ //Ran out of room so evict the tail entry in cache as its least recently used
                map.remove(tail.key);
                delete(tail);
            }

            setHead(newNode);
            map.put(key,newNode);
        }
    }

    public static void main(String[] args) {
        LRUCache lru = new LRUCache(4);
        lru.set(1, 100);
        lru.set(10, 99);
        lru.set(15, 98);
        lru.set(10, 97);
        lru.set(12, 96);
        lru.set(18, 95);
        lru.set(1, 94);

        System.out.println(lru.get(1));
        System.out.println(lru.get(10));
        System.out.println(lru.get(15));
    }
}
