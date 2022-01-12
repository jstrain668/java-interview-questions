package com.dsa.foundation.trees.bt.bft;

import com.dsa.foundation.trees.bt.TreeNode;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

//Given a binary tree, print its nodes level by level in spiral order, i.e., all nodes present at level 1 should be
//printed first from right to left, followed by nodes of level 2 left to right, followed by nodes of level 3 from right
//to left and so on…

//Reference: https://java2blog.com/spiral-zigzag-level-order-traversal-binary-tree-java/
//Reference: https://www.geeksforgeeks.org/level-order-traversal-in-spiral-form/



public class SpiralTraversal {

    public TreeNode createBTree(){

        TreeNode root = new TreeNode(15);
        root.left = new TreeNode(10);
        root.right = new TreeNode(20);
        root.left.left = new TreeNode(8);
        root.left.right = new TreeNode(12);
        root.right.left = new TreeNode(16);
        root.right.right = new TreeNode(25);

        return root;
    }

    public TreeNode createBTree2(){
        TreeNode rootNode =new TreeNode(40);
        TreeNode node20=new TreeNode(20);
        TreeNode node10=new TreeNode(10);
        TreeNode node30=new TreeNode(30);
        TreeNode node60=new TreeNode(60);
        TreeNode node50=new TreeNode(50);
        TreeNode node70=new TreeNode(70);
        TreeNode node5=new TreeNode(5);
        TreeNode node55=new TreeNode(55);

        rootNode.left=node20;
        rootNode.right=node60;

        node20.left=node10;
        node20.right=node30;

        node60.left=node50;
        node60.right=node70;
        node10.left=node5;
        node50.right=node55;

        return rootNode;
    }

    //Create an empty stack s and push root to it.
    //while stack is not NULL Do following
    //  Create a empty stack called tempStack.
    //  Pop a node from stack and push it to tempStack depending on directionFlag
    //  Change directionFlag to change the direction of traversal
    //  set stack=tempStack

    public void printSpiral(TreeNode root) {
        if (root == null)
            return; // NULL check

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        boolean directionflag = false;
        while (!stack.isEmpty()) {
            Stack<TreeNode> tempStack = new Stack<>();

            while (!stack.isEmpty()) {
                TreeNode tempNode = stack.pop();

                //Visit the node
                System.out.print(tempNode.val+" ");
                if (!directionflag) {
                    if (tempNode.left != null)
                        tempStack.push(tempNode.left);
                    if (tempNode.right != null)
                        tempStack.push(tempNode.right);
                } else {
                    if (tempNode.right != null)
                        tempStack.push(tempNode.right);
                    if (tempNode.left != null)
                        tempStack.push(tempNode.left);
                }
            }
            // for changing direction
            directionflag = !directionflag;

            stack = tempStack;
        }
    }

    //Use 2 stacks, flag for zig-zag direction and start left to right
    public void printSpiralAlt(TreeNode node)
    {
        if (node == null)
            return; // NULL check

        // Create two stacks to store alternate levels
        // For levels to be printed from right to left
        Stack<TreeNode> s1 = new Stack<>();
        // For levels to be printed from left to right
        Stack<TreeNode> s2 = new Stack<>();

        // Push first level to first stack 's1'
        s1.push(node);

        // Keep printing while any of the stacks has some nodes
        while (!s1.empty() || !s2.empty()) {
            // Print nodes of current level from s1 and push nodes of
            // next level to s2
            while (!s1.empty()) {
                TreeNode temp = s1.pop();
                System.out.print(temp.val + " ");

                // Note that is right is pushed before left
                if (temp.right != null)
                    s2.push(temp.right);

                if (temp.left != null)
                    s2.push(temp.left);
            }

            // Print nodes of current level from s2 and push nodes of
            // next level to s1
            while (!s2.empty()) {
                TreeNode temp = s2.pop();
                System.out.print(temp.val + " ");

                // Note that is left is pushed before right
                if (temp.left != null)
                    s1.push(temp.left);
                if (temp.right != null)
                    s1.push(temp.right);
            }
        }
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

    public static void main(String[] args) {
        SpiralTraversal tree = new SpiralTraversal();
        TreeNode root = tree.createBTree();
        tree.printLevelOrder(root);
        tree.printSpiral(root);
        System.out.println();
        tree.printSpiralAlt(root);
        System.out.println();

        TreeNode root2 = tree.createBTree2();
        tree.printLevelOrder(root2);
        tree.printSpiral(root2);
        System.out.println();
        tree.printSpiralAlt(root2);


    }
}
