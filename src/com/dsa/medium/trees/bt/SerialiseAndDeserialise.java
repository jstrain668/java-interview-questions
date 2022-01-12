package com.dsa.medium.trees.bt;

//Question: https://leetcode.com/problems/serialize-and-deserialize-binary-tree/

//Reference: https://evelynn.gitbooks.io/facebook-interview/content/serialize_and_deserialize_binary_tree.html

import java.util.LinkedList;
import java.util.Queue;

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
      if (root == null){
          return "null";
      }
      StringBuilder result = new StringBuilder();
      Queue<TreeNode> q = new LinkedList<>();
      q.add(root);

      while (!q.isEmpty()){
          TreeNode curr = q.poll();

          if (curr == null){
              result.append("null,");
              continue;
          }
          result.append(curr.val).append(",");

          q.add(curr.left);
          q.add(curr.right);
      }
      return result.toString();
    }

    // Decodes your encoded data to tree.
    public TreeNode deserialise(String data) {
        String[] nodes = data.split(",");
        if (nodes[0].equals("null")){
            return null;
        }
        int index = 0;
        TreeNode root = new TreeNode(Integer.parseInt(nodes[index++]));
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        while (!q.isEmpty()){
            int size = q.size();

            for (int i=0; i < size; i++){
                TreeNode curr = q.poll();
                if (!nodes[index].equals("null")){
                    curr.left = new TreeNode(Integer.parseInt(nodes[index]));
                    q.add(curr.left);
                }
                index++;
                if (!nodes[index].equals("null")){
                    curr.right = new TreeNode(Integer.parseInt(nodes[index]));
                    q.add(curr.right);
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
