package com.dsa.foundation.trees.bt;

import java.util.LinkedList;
import java.util.Queue;

//Question: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

//Reference: https://evelynn.gitbooks.io/facebook-interview/content/serialize_and_deserialize_binary_tree.html

public class SerialiseAndDeserialise {

    public TreeNode createBT()
    {

        //                     20
        //                  /      \
        //                8         22
        //               /  \
        //              4    12
        //                  /   \
        //                 10    14

        TreeNode root = new TreeNode(20);
        root.left = new TreeNode(8);
        root.right = new TreeNode(22);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(12);
        root.left.right.left = new TreeNode(10);
        root.left.right.right = new TreeNode(14);

        return root;
    }

    // A simple inorder traversal used for testing the
    // constructed tree
    public void inorder(TreeNode root)
    {
        if (root != null) {
            inorder(root.left);
            System.out.print(root.val + " ");
            inorder(root.right);
        }
    }

    public String serialise(TreeNode root) {
        if (root == null) {
            return "null";
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);
        StringBuilder result = new StringBuilder();
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (current == null) {
                    result.append("null,");
                    continue;
                }
                result.append(current.val).append(",");
                queue.offer(current.left);
                queue.offer(current.right);
            }
        }
        System.out.println(result);
        return result.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialise(String data) {
        String[] nodes = data.split(",");
        int index = 0;
        if (nodes[index].equals("null")) {
            return null;
        }
        TreeNode root = new TreeNode(Integer.parseInt(nodes[index++]));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode current = queue.poll();
                if (!nodes[index].equals("null")) {
                    current.left = new TreeNode(Integer.parseInt(nodes[index]));
                    queue.offer(current.left);
                }
                index++;
                if (!nodes[index].equals("null")) {
                    current.right = new TreeNode(Integer.parseInt(nodes[index]));
                    queue.offer(current.right);
                }
                index++;
            }
        }
        return root;
    }

    public static void main(String[] args) {
        SerialiseAndDeserialise tree = new SerialiseAndDeserialise();
        TreeNode root = tree.createBT();

        String serialised = tree.serialise(root);
        System.out.println("Serialised view of the tree:");
        System.out.println(serialised);
        System.out.println();

        // Let us deserialize the stored tree into root1
        TreeNode t = tree.deserialise(serialised);

        System.out.println(
                "Inorder Traversal of the tree constructed from serialized String:");
        tree.inorder(t);


    }
}
