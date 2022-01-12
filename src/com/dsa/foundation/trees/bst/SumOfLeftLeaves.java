package com.dsa.foundation.trees.bst;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

//Question: Given, a tree. We need to find the sum of all leaf nodes which are on left side.
//https://leetcode.com/problems/sum-of-left-leaves/
//Solution: https://leetcode.com/problems/sum-of-left-leaves/discuss/1559089/Java-Simple-and-Readable-Solution-or-Short-and-concise-code

//Reference: https://cheonhyangzhang.gitbooks.io/leetcode-solutions/content/404-sum-of-left-leaves.html

public class SumOfLeftLeaves {

    public TreeNode sortedArrayToBST(int[] values,int start,int end){
        //Base exit condition
        if (start > end){
            return null;
        }

        //Find and create the mid node
        int mid = start + (end - start)/2;
        TreeNode root = new TreeNode(values[mid]);

        //Left subtree is less in value than its parent
        root.left = sortedArrayToBST(values,start,mid-1);
        root.right = sortedArrayToBST(values,mid+1,end);

        return root;
    }

    public void printList(List<Integer> list){

        for (int val : list){
            System.out.print(val+" ");
        }
        System.out.println();
    }

    public void traversePreOrder(TreeNode node,List<Integer> list){

        if (node != null){
            list.add(node.val);
            traversePreOrder(node.left,list);
            traversePreOrder(node.right,list);
        }
    }

    public void traverseInOrder(TreeNode node,List<Integer> list){

        if (node != null){
            traverseInOrder(node.left,list);
            list.add(node.val);
            traverseInOrder(node.right,list);
        }
    }

    public void traversePostOrder(TreeNode node,List<Integer> list){

        if (node != null){
            traversePostOrder(node.left,list);
            traversePostOrder(node.right,list);
            list.add(node.val);
        }
    }

    public TreeNode insertRecursively(TreeNode root, int val){
        if (root == null){
            return new TreeNode(val);
        }

        if (val < root.val){
            root.left = insertRecursively(root.left,val);
        } else {
            root.right = insertRecursively(root.right,val);
        }

        return root;
    }

    public TreeNode insertIteratively(TreeNode root,int val){
        if (root == null){
            return new TreeNode(val);
        }

        TreeNode curr = root;
        TreeNode parent = null;

        while (curr != null){
            parent = curr;
            if (val < curr.val){
                curr = curr.left;
            } else{
                curr = curr.right;
            }
        }

        if (val < parent.val){
            parent.left = new TreeNode(val);
        } else{
            parent.right = new TreeNode(val);
        }
        return root;
    }


    private int sumOfLeftLeavesHelper(TreeNode root,boolean isLeft){

        // If the tree is empty, sum is 0.
        if(root == null) {
            return 0;
        }
        // If current node is leaf node and if it is left node, then return its value
        if(root.left==null && root.right==null && isLeft==true)
            return root.val;
        // Perform the same on left and right subtrees, and return their sum. Whenever calling left subtree, pass true as isLeft, because its on left.
        return sumOfLeftLeavesHelper(root.left,true)+sumOfLeftLeavesHelper(root.right,false);
    }

    //Time Complexity: As we are traversing each node once, the time complexity will be O(N), where N is number of nodes.
    //Space Complexity: It takes stack space, as we are doing recursion. So space complexity is O(H), where H is the
    //height of tree.
    //Approach:
    //1.Check if current node is a leaf node or not. If it is a leaf node then check if it is on left or not.
    //2. If it is left leaf node, add it to result.
    //3.To check whether it is on left or not, while passing left subtree, also send a true saying that it is left subtree.
    // And pass right subtree by passing false as other parameter.

    public int sumOfLeftLeaves(TreeNode root){

        // If the tree is empty, sum is 0.
        if(root==null) {
            return 0;
        }

        // Perform the same on left and right subtrees, and return their sum. Whenever calling left subtree, pass true
        // because its on left.
        return sumOfLeftLeavesHelper(root.left,true)+sumOfLeftLeavesHelper(root.right,false);

    }

    public int sumOfRightLeavesHelper(TreeNode root,boolean isRight){
        if (root == null){
            return  0;
        }

        if (root.left == null && root.right == null && isRight){
            return root.val;
        }

        return sumOfRightLeavesHelper(root.left,false)+sumOfRightLeavesHelper(root.right,true);
    }

    public int sumOfRightLeaves(TreeNode root){

        if (root == null){
            return 0;
        }

        return sumOfRightLeavesHelper(root.left,false)+sumOfRightLeavesHelper(root.right,true);
    }

    public static void main(String[] args) {
        SumOfLeftLeaves sum = new SumOfLeftLeaves();

        int[] values = {22,34,0,36,77,99,33,35};
        Arrays.sort(values);

        TreeNode root = sum.sortedArrayToBST(values,0,values.length-1);

        List<Integer> list = new ArrayList<>();
        System.out.println("Inorder:");
        sum.traverseInOrder(root,list);
        sum.printList(list);

        System.out.println("Sum of left leaves: "+sum.sumOfLeftLeaves(root));
        System.out.println("Sum of right leaves: "+sum.sumOfRightLeaves(root));
    }
}
