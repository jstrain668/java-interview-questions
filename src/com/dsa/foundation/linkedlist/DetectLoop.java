package com.dsa.foundation.linkedlist;

import java.util.Arrays;

public class DetectLoop {
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

    public boolean hasLoop(ListNode head){

        if (head == null){
            return false;
        }

        ListNode fast = head;
        ListNode slow = head;

        while (fast != null && fast.next != null){

            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast){
                return true;
            }
        }

        return false;
    }

    //Time Complexity: O(n)
    public ListNode findNodeRecursive(ListNode root, int val){

        if (root == null){
            return null;
        }

        if (root.val == val){
            return root;
        }

        return findNodeRecursive(root.next,val);
    }

    public ListNode createCircular(int val1, int val2){

        ListNode circular = findNodeRecursive(this.head,val1);
        ListNode second = findNodeRecursive(this.head,val2);

        circular.next = second;

        return circular;

    }

    public ListNode findMiddleNode(ListNode head,ListNode end){

        if (head == null){
            return null;
        }

        ListNode slow = head;
        ListNode fast = head.next;

        while (fast != end){
            fast = fast.next;
            if (fast != end){
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }

    public static void main(String[] args) {
        DetectLoop dl = new DetectLoop();
        int[] nums = {3, 7, 9, 45, 56, 87, 99};
        Arrays.sort(nums);
        for (int num : nums) {
            dl.append(num);
        }
        dl.printList(dl.head);

        //ListNode circular = dl.createCircular(87,7);
        //System.out.println("Circular node: "+circular.val);
        //System.out.println("Circular node next value: "+circular.next.val);
        if (dl.hasLoop(dl.head)){
            System.out.println("Circular loop detected");
        } else {
            System.out.println("Circular loop not detected");
        }

        ListNode midNode = dl.findMiddleNode(dl.head,null);
        System.out.println("Middle node is "+midNode.val);
        //dl.printList(dl.head);
    }
}
