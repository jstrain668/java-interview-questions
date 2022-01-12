package com.dsa.medium.linkedlist;

//Reference: https://java2blog.com/add-two-numbers-represented-by-linked-list-in-java/



public class SumTwoLists {

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

    public ListNode reverse(ListNode head){

        if (head.next == null){
            return head;
        }

        ListNode newHead = reverse(head.next);
        head.next.next = head;
        head.next = null;

        return newHead;
    }

    public ListNode sum(ListNode r1, ListNode r2){
       ListNode newHead = null;
       ListNode tempIterNode = null;

       int firstIter = 0;
       int sum = 0;
       int carry = 0;

       while (r1 != null || r2 != null){

           firstIter++;
           sum = carry;
           if (r1 != null){
               sum += r1.val;
               r1 = r1.next;
           }

           if (r2 != null){
               sum += r2.val;
               r2 = r2.next;
           }

           carry = sum/10;
           sum = sum%10;

           if (firstIter == 1){
               tempIterNode = new ListNode(sum);
               newHead = tempIterNode;
           } else{
               ListNode tempNode = new ListNode(sum);
               tempIterNode.next = tempNode;
               tempIterNode = tempIterNode.next;
           }
       }

       if (carry != 0){
           ListNode tempNode = new ListNode(carry);
           tempIterNode.next = tempNode;
       }

       return newHead;
    }

    public ListNode addLists(ListNode head1, ListNode head2){

        if (head1 == null && head2 == null){
            return null;
        }

        if (head1 == null){
            return head2;
        }

        if (head2 == null){
            return head1;
        }

        ListNode rhead1 = reverse(head1);
        ListNode rhead2 = reverse(head2);

        return reverse(sum(rhead1,rhead2));
    }

    public static void main(String[] args) {
        SumTwoLists stl1 = new SumTwoLists();
        int[] nums1 = {5,7,3,4};
        for (int num : nums1){
            stl1.append(num);
        }
        stl1.printList(stl1.head);

        SumTwoLists stl2 = new SumTwoLists();
        int[] nums2 = {9,4,6};
        for (int num : nums2){
            stl2.append(num);
        }
        stl1.printList(stl2.head);

        ListNode sumList = stl1.addLists(stl1.head,stl2.head);
        stl1.printList(sumList);
    }
}
