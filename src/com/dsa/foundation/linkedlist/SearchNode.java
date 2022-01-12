package com.dsa.foundation.linkedlist;


//Question: Search for a node in an iterative and recursive manner. Also execute a
//binary search after sorting the linked list.

//Reference: https://afteracademy.com/blog/middle-of-the-linked-list
//Reference: https://iq.opengenus.org/binary-search-in-linked-list/
//Reference: https://www.geeksforgeeks.org/search-an-element-in-a-linked-list-iterative-and-recursive/


import java.util.Arrays;

public class SearchNode {

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

    public ListNode prepend(int val){
        ListNode newNode = new ListNode(val);

        if (head == null){
            head = newNode;
            tail = head;
        } else {
            newNode.next = head;
            head = newNode;
        }

        return newNode;
    }

    public ListNode insertAfter(ListNode prev,int val){

        if (prev == null){
            System.out.println("Cannot insert after null node");
            return null;
        }

        ListNode newNode = new ListNode(val);
        newNode.next = prev.next;
        prev.next = newNode;

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


    //Time Complexity: O(n)
    public ListNode findNodeIterative(ListNode root,int val){

        if (root == null){
            return root;
        }

        ListNode current = root;

        while (current != null){
            if (val == current.val){
                return current;
            }
            current = current.next;
        }

        return null;
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

    //Time Complexity: O(n+n)
    public ListNode findMidNodeSlow(ListNode root){

        if (root == null){
            return null;
        }

        int len = 0;
        ListNode current = root;
        while (current != null){
            current = current.next;
            len++;
        }

        int count = 0;
        current = root;
        while (count < len/2){
            count++;
            current = current.next;
        }

        return current;

    }

    public ListNode findMidNodeFast(ListNode start,ListNode end){

        if (start == null){
            return null;
        }

        ListNode slow = start;
        ListNode fast = start.next;

        while (fast != end){
            fast = fast.next;
            if (fast != end){
                slow = slow.next;
                fast = fast.next;
            }
        }
        return slow;
    }

    //Start node is set to head of the list and the last node is set to NULL.
    //Middle element is calculated using the two pointers approach.
    //If the middle element is same as the key to be searched, we return it.
    //Else if middle element is less than the key to be searched, we have to search is the right side of the singly linked list. So, we set start pointer to the next of middle element.
    //Else if middle element is greater than the key to be searched, we have to search is the left side of the singly linked list. So, we set last pointer to the middle element.
    //If the key is found or the entire linked list gets traversed once, we break the
    //loop

    //Time Complexity: O(N) as we are traversing the singly linked list at least once
    // either to find the middle element or to move the pointers for binary search.
    public ListNode binSearch(ListNode root,int val){
        if (root == null){
            return null;
        }

        ListNode start = root;
        ListNode end = null;

        do{
            ListNode mid = findMidNodeFast(start,end);

            if (mid == null){
                return null;
            }

            if (mid.val == val){
                return mid;
            }
            else  if (mid.val < val){
                start = mid.next;
            } else {
                end = mid;
            }
        } while (end == null || end != start);

        return null;
    }

    public ListNode findMidNodeFast(ListNode root){
        if (root == null){
            return null;
        }

        ListNode slow = head;
        ListNode fast = head;

        while(fast != null){
            fast = fast.next;
            if (fast!=null && fast.next != null){
                slow = slow.next;
                fast = fast.next;
            }
        }

        return slow;
    }

    public int findLength(ListNode head){

        if (head == null){
            return 0;
        }

        ListNode current = head;
        int count =0;

        while (current != null){
            count++;
            current = current.next;
        }

        return count;
    }

    public int findLengthRecursive(ListNode head){

        if (head == null){
            return 0;
        }

        return 1 + findLengthRecursive(head.next);
    }

    public static void main(String[] args) {
        SearchNode sn = new SearchNode();
        int[] nums = {3,7,9,45,56,87,99};
        Arrays.sort(nums);
        for (int num : nums){
            sn.append(num);
        }

        int val = 87;
        ListNode findNode = sn.findNodeIterative(sn.head,val);
        if (findNode != null){
            System.out.println("Found node: "+findNode.val);
        } else {
            System.out.println("Could not find value "+val);
        }

        val = 9;
        findNode = sn.findNodeRecursive(sn.head,val);
        if (findNode != null){
            System.out.println("Found node: "+findNode.val);
        } else {
            System.out.println("Could not find value "+val);
        }

        System.out.println("Middle node of linked list: "+sn.findMidNodeSlow(sn.head).val);
        System.out.println("Middle node of linked list: "+sn.findMidNodeFast(sn.head).val);

        val = 56;
        ListNode binSearch = sn.binSearch(sn.head,56);

        if (binSearch != null){
            System.out.println(binSearch.val+" found in linked list using bin search");
        } else{
            System.out.println(val+" not found in linked list using bin search");
        }

        sn.printList(sn.head);
        System.out.println("Number of nodes in linked list "+sn.findLength(sn.head));
        System.out.println("Number of nodes in linked list "+sn.findLengthRecursive(sn.head));
    }
}
