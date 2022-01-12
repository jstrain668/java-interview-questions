package com.dsa.foundation.trees.bt.bft;


import com.dsa.foundation.trees.bt.TreeNode;

import java.util.*;

//Question: Print bottom view of a binary tree

//Reference: https://www.geeksforgeeks.org/bottom-view-binary-tree/

public class BottomView {

    // this class' represents the items stored in queue (used for level order
    // traversal). Objects will store the nodes and its level
    class QueueNode {
        int level;
        TreeNode node;

        public QueueNode(int level, TreeNode node) {
            this.level = level;
            this.node = node;

        }
    }

    class Pair
    {
        int nodeData;
        int height;

        public Pair(int key,int num)
        {
            nodeData = key;
            height = num;
        }
    }

    public TreeNode createBTree(){

         /* Construct the following tree
                1
              /   \
             /     \
            2       3
                  /   \
                 /     \
                5       6
              /   \
             /     \
            7       8
                  /   \
                 /     \
                9      10
        */

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.right.left.left = new TreeNode(7);
        root.right.left.right = new TreeNode(8);
        root.right.left.right.left = new TreeNode(9);
        root.right.left.right.right = new TreeNode(10);

        return root;
    }

    public void printLevelOrder(TreeNode root)
    {
        // Base Case
        if(root == null)
            return;

        // Create an empty queue for level order traversal
        Queue<TreeNode> q =new LinkedList<>();

        // Enqueue Root and initialize height
        q.add(root);

        while(!q.isEmpty())
        {
            int size = q.size();
            for (int i =0; i < size; i++)
            {
                TreeNode node = q.poll();
                System.out.print(node.val + " ");

                if(node.left != null)
                    q.add(node.left);
                if(node.right != null)
                    q.add(node.right);
            }
            System.out.println();
        }
    }

    //The following are steps to print Bottom View of Binary Tree.
    //1. We put tree nodes in a queue for the level order traversal.
    //2. Start with the horizontal distance(hd) 0 of the root node, keep on adding left child to queue along with the
    //  horizontal distance as hd-1 and right child as hd+1.
    //3. Also, use a TreeMap which stores key value pair sorted on key.
    //4. Every time, we encounter a new horizontal distance or an existing horizontal distance put the node data for the
    // horizontal distance as key. For the first time it will add to the map, next time it will replace the value.
    // This will make sure that the bottom most element for that horizontal distance is present in the map and if you
    // see the tree from beneath that you will see that element.

    public void printBottomView(TreeNode root,int hDist){

        if (root == null){
            return;
        }

        Map<Integer,Integer> map = new TreeMap<>();
        Queue<QueueNode> q = new LinkedList<>();
        q.add(new QueueNode(hDist,root));

        while (!q.isEmpty()){
            QueueNode qNode = q.poll();
            TreeNode cNode = qNode.node;

            // Put the dequeued tree node to TreeMap having key
            // as horizontal distance. Every time we find a node
            // having same horizontal distance we need to replace
            // the data in the map.
            map.put(qNode.level, cNode.val);

            if (cNode.left != null){
                q.add(new QueueNode(qNode.level-1, cNode.left));
            }

            if (cNode.right != null){
                q.add(new QueueNode(qNode.level+1, cNode.right));
            }
        }

        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            System.out.print(entry.getValue()+" ");
        }
    }

    private void postorder(TreeNode root,int height,int hDist,Map<Integer,Pair> map){

        if (root == null){
            return;
        }

        // If node for a particular
        // horizontal distance is not
        // present, add to the map.
        if (!map.containsKey(hDist)){
            map.put(hDist,new Pair(root.val,height));
        }
        // Compare height for already
        // present node at similar horizontal
        // distance
        else {
            Pair p = map.get(hDist);
            if (p.height <= height){
                p.height = height;
                p.nodeData = root.val;
            }
            map.put(hDist,p);
        }

        postorder(root.left,height+1,hDist-1,map);
        postorder(root.right,height+1,hDist+1,map);

    }

    public void printBottomViewRecursive(TreeNode root) {

        if (root == null) {
            return;
        }

        /* map to store node at each vertical(horizontal) distance(Level)
        i.e. stores bottom view */
        Map<Integer, Pair> map = new TreeMap<>();

        // obtain bottom view of binary tree into the Map
        postorder(root,0,0,map);

        /* traverse the map and print top view */
        for (Map.Entry<Integer, Pair> i : map.entrySet()) {
            System.out.print(i.getValue().nodeData + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BottomView bv = new BottomView();
        TreeNode root = bv.createBTree();

        bv.printLevelOrder(root);
        System.out.println();
        bv.printBottomView(root,0);
        System.out.println();
        bv.printBottomViewRecursive(root);
    }
}
