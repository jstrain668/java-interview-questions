package com.dsa.foundation.linkedlist;

import org.w3c.dom.Node;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class FrequencyNodeValue {

    ListNode head;
    ListNode tail;

    public ListNode append(int val){

        ListNode newNode = new ListNode(val);

        if (head == null){
            head = newNode;
            tail = head;
        } else {
            tail.next = newNode;
            tail = newNode;
        }

        return newNode;
    }

    public void printList(ListNode node){

        ListNode current = node;

        while (current != null){
            System.out.print(current.val+",");
            current = current.next;
        }
        System.out.println();
    }

    public Map<Integer,Integer> nodeValueFreq(ListNode head){
        Map<Integer,Integer> map = new HashMap<>();

        if (head == null){
            return map;
        }

        ListNode current = head;
        while (current != null){
            if(map.containsKey(current.val)){
                map.put(current.val, map.get(current.val)+1);
            } else{
                map.put(current.val,1);
            }
            current = current.next;
        }
        return map;
    }

    public static void main(String[] args) {
        FrequencyNodeValue fnv = new FrequencyNodeValue();
        int[] nums1 = {3,7,9,3,56,87,99,3,56,7};
        Arrays.sort(nums1);
        for (int num : nums1){
            fnv.append(num);
        }
        fnv.printList(fnv.head);

        Map<Integer,Integer> fmap = fnv.nodeValueFreq(fnv.head);

        for(Map.Entry entry : fmap.entrySet()){
            System.out.println("Number "+entry.getKey()+ " frequency "+entry.getValue());
        }
    }
}
