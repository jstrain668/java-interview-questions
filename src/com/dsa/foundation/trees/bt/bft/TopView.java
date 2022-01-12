package com.dsa.foundation.trees.bt.bft;

import com.dsa.foundation.trees.bt.TreeNode;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;


// Reference: https://algorithms.tutorialhorizon.com/print-the-top-view-of-a-binary-tree/
// Reference: https://www.tutorialcup.com/interview/tree/top-view-of-binary-tree.htm

public class TopView {

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

        Pair(int key,int num)
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

    //Take a variable called hDist, when ever you go left, do hDist-- AND when ever you go right do hDist++.
    //With step above we have separated out the levels vertically.
    //Now all we need to do the level order traversal just to ensure that we will visit the top most node at level
    //before we visit any other node at that level.
    //We will use simple queue technique for level order traversal or BFS.
    //we will create a class QueueNode this will store the objects containing TreeNode and its level (hDist)

    public void printTopView(TreeNode root,int hDist){
        if (root == null){
            return;
        }

        Map<Integer,Integer> map = new TreeMap<>();
        Queue<QueueNode> queue = new LinkedList<>();
        queue.add(new QueueNode(hDist,root));

        while (!queue.isEmpty()){
            QueueNode curr = queue.poll();
            TreeNode node = curr.node;

            if (!map.containsKey(curr.level)){
              map.put(curr.level,node.val);
            }

            if (node.left != null){
                queue.add(new QueueNode(curr.level-1, node.left));
            }

            if (node.right != null){
                queue.add(new QueueNode(curr.level+1,node.right));
            }
        }

        //Print the top view
        for (Map.Entry<Integer,Integer> entry: map.entrySet()){
            System.out.print(entry.getValue()+" ");
        }
        System.out.println();
    }

    /* the function performs inorder traversal and stores top view
    of the binary tree.
    for every node encountered during the traversal we have it's :
    w -> horizontal depth(width)
    h -> vertical depth(height)
    */

    public void inorder(TreeNode root,int w,int h, TreeMap <Integer,Pair> map)
    {
        if(root == null)
            return;

        inorder(root.left,w-1,h+1,map);

        /* check if a particular horizontal level has been visited or not */
        if(!map.containsKey(w))
            map.put(w,new Pair(root.val,h));

        /* if particular horizontal level has been visited
        then check if height of current node is less than
        the previous node met at same horizontal level, if this
        is true then the current node should replace previous node
        in top view of the binary tree */
        else if(map.get(w).height > h)
            map.put(w,new Pair(root.val,h));

        inorder(root.right,w+1,h+1,map);
    }

    public void topViewRecursive(TreeNode root)
    {
        if(root == null)
            return;

        /* map to store node at each vertical(horizontal) distance(Level)
        i.e. stores top view */
        TreeMap<Integer,Pair> map = new TreeMap<>();

        // obtain top view of binary tree into the Map
        inorder(root,0,0,map);

        /* traverse the map and print top view */
        for (Map.Entry<Integer, Pair> i : map.entrySet()) {
            System.out.print(i.getValue().nodeData + " ");
        }
        System.out.println();
    }

    public static void main(String[] args) {
        TopView tv = new TopView();
        TreeNode root = tv.createBTree();

        tv.printLevelOrder(root);
        System.out.println();
        tv.printTopView(root,0);
        tv.topViewRecursive(root);
    }

}
