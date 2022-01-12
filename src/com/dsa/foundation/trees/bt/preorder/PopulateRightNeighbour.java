package com.dsa.foundation.trees.bt.preorder;

//Question: Populate right neighbors for all nodes in a binary tree
//https://leetcode.com/problems/populating-next-right-pointers-in-each-node/

//Reference: https://leetcode.com/problems/populating-next-right-pointers-in-each-node/discuss/1565814/Simple-java-solution

public class PopulateRightNeighbour {

    class Node {
        public int val;
        public Node left;
        public Node right;
        public Node next;

        public Node() {}

        public Node(int _val) {
            val = _val;
        }

        public Node(int _val, Node _left, Node _right, Node _next) {
            val = _val;
            left = _left;
            right = _right;
            next = _next;
        }
    };


    public Node createBTree(){

        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);

        return root;
    }

    public void inOrder(Node root){

        if (root != null){
            inOrder(root.left);
            System.out.print("["+root.val+","+root.next+"], ");
            inOrder(root.right);
        }
    }

    //Pre-order traversal. left node's next ptr is set to right node and right to the current node's next ptr if not null
    //otherwise null
    public Node connect(Node root) {
        if(root==null) {
            return null;
        }

        if(root.left!=null) {
            root.left.next = root.right;
        }

        if(root.right!=null) {
            root.right.next = (root.next!=null) ? root.next.left: null;
        }

        connect(root.left);
        connect(root.right);

        return root;
    }

    public static void main(String[] args) {
        PopulateRightNeighbour prn = new PopulateRightNeighbour();
        Node root = prn.createBTree();
        prn.inOrder(root);
        root = prn.connect(root);
        System.out.println();
        prn.inOrder(root);

    }
}
