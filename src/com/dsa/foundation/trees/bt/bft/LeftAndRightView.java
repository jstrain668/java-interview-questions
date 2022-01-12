package com.dsa.foundation.trees.bt.bft;

import com.dsa.foundation.trees.bt.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class LeftAndRightView {

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

    public void printLeftView(TreeNode root){
        if (root == null){
            return;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()){

            int size = q.size();

            for (int i=0; i < size; i++){
                TreeNode curr = q.poll();

                //Print left most node of each level
                if (i == 0){
                    System.out.print(curr.val+" ");
                }

                if (curr.left != null){
                    q.add(curr.left);
                }

                if (curr.right != null){
                    q.add(curr.right);
                }
            }
        }
    }

    public void printRightView(TreeNode root){
        if (root == null){
            return;
        }

        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()){

            int size = q.size();

            for (int i=0; i < size; i++){
                TreeNode curr = q.poll();

                //Print left most node of each level
                if (i == size-1){
                    System.out.print(curr.val+" ");
                }

                if (curr.left != null){
                    q.add(curr.left);
                }

                if (curr.right != null){
                    q.add(curr.right);
                }
            }
        }
    }

    public static void main(String[] args) {
        LeftAndRightView tree = new LeftAndRightView();
        TreeNode root = tree.createBTree();
        tree.printLevelOrder(root);

        System.out.println("Left view of tree: ");
        tree.printLeftView(root);
        System.out.println();
        System.out.println("Right view of tree: ");
        tree.printRightView(root);


    }
}
