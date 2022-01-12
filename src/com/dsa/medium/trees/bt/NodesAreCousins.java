package com.dsa.medium.trees.bt;


import java.util.LinkedList;
import java.util.Queue;

//Question: https://leetcode.com/problems/cousins-in-binary-tree/

public class NodesAreCousins {

    public TreeNode createBTree(){

        TreeNode root = new TreeNode(26);
        root.left = new TreeNode(10);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(7);

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

    private int getHeight(TreeNode root,int val,int level){
        if (root == null){
            return -1;
        }

        if (root.val == val){
            return level;
        }

        int height = getHeight(root.left,val,level+1);
        if (height != -1){
            return height;
        }

        return getHeight(root.right,val,level+1);
    }

    private TreeNode getParent(TreeNode root,int val){
        if (root == null){
            return null;
        }

        if (root.left != null && root.left.val == val){
            return root;
        }

        if (root.right != null && root.right.val == val){
            return root;
        }

        TreeNode left = getParent(root.left,val);
        if (left != null){
            return left;
        }

        return getParent(root.right,val);
    }

    public boolean isCousins(TreeNode root, int x, int y) {

        if (root == null){
            return false;
        }

        int xLevel = getHeight(root,x,0);
        int yLevel = getHeight(root,y,0);

        if (xLevel != yLevel){
            return false;
        }

        TreeNode xParent = getParent(root,x);
        TreeNode yParent = getParent(root,y);

        if (xParent == yParent){
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        NodesAreCousins nac = new NodesAreCousins();

        TreeNode root = nac.createBTree();
        nac.printLevelOrder(root);

        int node1Val = 4;
        int node2Val = 6;

        System.out.println("Are nodes "+node1Val+" and "+node2Val+" cousins: "+nac.isCousins(root,node1Val,node2Val));
    }
}
