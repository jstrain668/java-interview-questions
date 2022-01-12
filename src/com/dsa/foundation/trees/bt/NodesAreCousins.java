package com.dsa.foundation.trees.bt;

//Question: Check if two nodes are cousins in a Binary tree


import java.util.LinkedList;
import java.util.Queue;

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

    public TreeNode findNode(TreeNode root,int val){

        if (root == null){
            return null;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()){
            TreeNode curr = queue.poll();

            if (curr.val == val){
                return curr;
            }

            if (curr.left != null){
                queue.add(curr.left);
            }

            if (curr.right != null){
                queue.add(curr.right);
            }
        }

        return null;
    }

    //Search for that given node in the tree using recursion.
    //Each time you left or right , increase the height by 1.
    //Once you found the given node, return the height.
    //If till the end you wont find the node, return 0

    private int getHeight(TreeNode root,int level,TreeNode node){

        if (root == null){
            return 0;
        }

        if (root == node){
            return level;
        }

        //check if the node is present in the left sub tree
        int height = getHeight(root.left,level+1,node);

        if (height != 0){
            return height;
        }

        //check if the node is present in the right sub tree
        return getHeight(root.right,level+1,node);
    }

    private TreeNode findParent(TreeNode root,TreeNode node){

        if (root == null){
            return null;
        }

        if (root.left == node || root.right == node){
            return root;
        }

        TreeNode left = findParent(root.left,node);
        if (left != null){
            return left;
        }
        return findParent(root.right,node);
    }

    //Two nodes are cousins if they exist on the same level and do not share the same parent
    public boolean areCousins(TreeNode root,TreeNode node1,TreeNode node2){

        if (root == null){
            return false;
        }

        int node1level = getHeight(root,1,node1);
        System.out.println("Node "+node1.val+ " level "+node1level);
        int node2level = getHeight(root,1,node2);
        System.out.println("node "+node2.val+ " level "+node2level);

        if (node1level != node2level){
            return false;
        }

        TreeNode node1Parent = findParent(root,node1);
        System.out.println(node1.val+ " parent is "+node1Parent.val);
        TreeNode node2Parent = findParent(root,node2);
        System.out.println(node2.val+ " parent is "+node2Parent.val);

        if (node1Parent == node2Parent){
            return false;
        }

        return true;
    }

    public static void main(String[] args) {
        NodesAreCousins nac = new NodesAreCousins();

        TreeNode root = nac.createBTree();
        nac.printLevelOrder(root);

        TreeNode node1 = nac.findNode(root,4);
        TreeNode node2 = nac.findNode(root,6);

        System.out.println("Are nodes "+node1.val+" and "+node2.val+" cousins: "+nac.areCousins(root,node1,node2));
    }
}
