package com.dsa.foundation.trees.bt.treetype;

import com.dsa.foundation.trees.bt.TreeNode;

import java.util.LinkedList;
import java.util.Queue;

public class IdenticalTrees {

    public TreeNode createBTree(){

        TreeNode root = new TreeNode(26);
        root.left = new TreeNode(10);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(6);
        root.right.right = new TreeNode(7);

        return root;
    }

    public TreeNode createBTree2(){

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

    //If both trees are empty then return true.
    //2. Else If both trees are non -empty
    //     (a) Check data of the root nodes (tree1->data ==  tree2->data)
    //     (b) Check left subtrees recursively  i.e., call sameTree(
    //          tree1->left_subtree, tree2->left_subtree)
    //     (c) Check right subtrees recursively  i.e., call sameTree(
    //          tree1->right_subtree, tree2->right_subtree)
    //     (d) If a,b and c are true then return 1.
    //3  Else return 0 (one is empty and other is not)
    public boolean identicalTrees(TreeNode root1,TreeNode root2){

        if (root1 == null && root2 == null){
            return true;
        }

        if (root1 != null && root2 != null){
            return (root1.val == root2.val && identicalTrees(root1.left,root2.left)
                    && identicalTrees(root1.right,root2.right));
        }

        return false;
    }

    public static void main(String[] args) {
        IdenticalTrees it = new IdenticalTrees();

        TreeNode root1 = it.createBTree();
        TreeNode root2 = it.createBTree2();

        System.out.println("Both tree are identical: "+it.identicalTrees(root1,root2));

    }
}
