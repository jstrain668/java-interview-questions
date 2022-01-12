package com.dsa.foundation.trees.bst;


//Question: Given a BST, find the floor and ceil of a given key in it. If the given key lies in the BST, then both floor
// and ceil are equal to that key; otherwise, the ceil is equal to the next greater key (if any) in the BST, and the
// floor is equal to the previous greater key (if any) in the BST

//Reference: https://www.techiedelight.com/floor-ceil-bst-iterative-recursive/

public class FloorAndCeil {

    class FloorCeil
    {
        private TreeNode floor, ceil;

        public FloorCeil()
        {
            floor = new TreeNode(-1);
            ceil = new TreeNode(-1);
        }

        public void setCeil(TreeNode ceil) {
            this.ceil = ceil;
        }

        public void setFloor(TreeNode floor) {
            this.floor = floor;
        }

        public int getCeilData() {
            return ceil.val;
        }

        public int getFloorData() {
            return floor.val;
        }
    }

    // Recursive function to insert a key into a BST
    public TreeNode insert(TreeNode root, int val)
    {
        // if the root is null, create a new node and return it
        if (root == null) {
            return new TreeNode(val);
        }

        // if the given key is less than the root node, recur for the left subtree
        if (val < root.val) {
            root.left = insert(root.left, val);
        }

        // if the given key is more than the root node, recur for the right subtree
        else {
            root.right = insert(root.right, val);
        }

        return root;
    }

    public void traverseInOrder(TreeNode node){

        if (node != null){
            traverseInOrder(node.left);
            System.out.print(node.val+" ");
            traverseInOrder(node.right);
        }
    }

    public TreeNode createBST(){
        /* Construct the following tree
                   8
                 /   \
                /     \
               4       10
              / \     /  \
             /   \   /    \
            2     6 9     12
        */

        int[] keys = { 2, 4, 6, 8, 9, 10, 12 };

        TreeNode root = null;
        for (int key: keys) {
            root = insert(root, key);
        }

        return root;
    }

    // Recursive function to find the floor and ceil of a given key in the BST
    public void findFloorCeil(TreeNode root,FloorCeil obj, int key)
    {
        // base case
        if (root == null) {
            return;
        }

        // if a node with the desired value is found, both floor and ceil is equal
        // to the current node
        if (root.val == key)
        {
            obj.setFloor(root);
            obj.setCeil(root);
        }

        // if the given key is less than the root node, recur for the left subtree
        else if (key < root.val)
        {
            // update ceil to the current node before visiting the left subtree
            obj.setCeil(root);
            findFloorCeil(root.left, obj, key);
        }

        // if the given key is more than the root node, recur for the right subtree
        else {
            // update floor to the current node before visiting the right subtree
            obj.setFloor(root);
            findFloorCeil(root.right, obj, key);
        }
    }

    public FloorCeil createFloorCeil(){
        return new FloorCeil();
    }

    public static void main(String[] args) {
        FloorAndCeil fac = new FloorAndCeil();
        TreeNode root = fac.createBST();
        fac.traverseInOrder(root);
        System.out.println();

        // calculate the ceil and floor for each key
        for (int i = 0; i < 15; i++)
        {
            FloorCeil ob = fac.createFloorCeil();

            fac.findFloorCeil(root, ob, i);
            System.out.println(i + " —> Floor is " + ob.getFloorData() +
                    ", Ceil is " + ob.getCeilData());
        }
    }
}
