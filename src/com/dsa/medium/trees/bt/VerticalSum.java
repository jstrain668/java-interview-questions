package com.dsa.medium.trees.bt;

import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.TreeMap;

//Reference: https://algorithms.tutorialhorizon.com/print-the-vertical-sum-in-binary-tree/

public class VerticalSum {

    class QueueNode {
        TreeNode tNode;
        int hDist;
        public QueueNode(TreeNode tNode,int hDist){
            this.tNode = tNode;
            this.hDist = hDist;
        }
    }

    public TreeNode createBTree(){

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);

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

    public void printVerticalSumPath(TreeNode root,int hDist){
        if (root == null){
            return;
        }
        Queue<QueueNode> q = new LinkedList<>();
        q.add(new QueueNode(root,hDist));
        Map<Integer,Integer> map = new TreeMap<>();

        while (!q.isEmpty()){
            QueueNode qNode = q.poll();
            TreeNode tNode = qNode.tNode;

            if (!map.containsKey(qNode.hDist)){
                map.put(qNode.hDist, tNode.val);
            } else{
                map.put(qNode.hDist, map.get(qNode.hDist)+tNode.val);
            }

            if (tNode.left != null){
                q.add(new QueueNode(tNode.left, qNode.hDist-1));
            }

            if (tNode.right != null){
                q.add(new QueueNode(tNode.right, qNode.hDist+1));
            }
        }

        for (Map.Entry<Integer,Integer> entry : map.entrySet()){
            System.out.println("Level : "+entry.getKey()+ " vertical sum : "+entry.getValue());
        }
    }

    public static void main(String[] args) {
        VerticalSum tree = new VerticalSum();
        TreeNode root = tree.createBTree();
        tree.printLevelOrder(root);

        tree.printVerticalSumPath(root,0);
    }
}
