package com.dsa.foundation.trees.bst.bft;

import com.dsa.foundation.trees.bst.TreeNode;

import java.util.*;

public class DeepestOddLevel {

    class QueueNode{
        int level;
        TreeNode node;

        public QueueNode(int level,TreeNode node){
            this.level = level;
            this.node = node;
        }
    }

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

    public int getDeepestOddLevel(TreeNode root){

        if (root == null){
            return 0;
        }

        int oddLevel = 0;
        Queue<QueueNode> queue = new LinkedList<>();
        queue.add(new QueueNode(1,root));

        while (!queue.isEmpty()){
            QueueNode curr = queue.poll();

            if (curr.node.left == null && curr.node.right == null){
                if (curr.level % 2 != 0){
                    oddLevel = curr.level;
                }
            }

            if (curr.node.left != null){
                queue.add(new QueueNode(curr.level+1,curr.node.left ));
            }

            if (curr.node.right != null){
                queue.add(new QueueNode(curr.level+1,curr.node.right ));
            }

        }
        return oddLevel;
    }

    // return max odd number depth of leaf node
    public int maxOddLevelDepth(TreeNode root)
    {
        if (root == null)
            return 0;

        // create a queue for level order
        // traversal
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);

        int result = Integer.MAX_VALUE;
        int level = 0;

        // traverse until the queue is empty
        while (!q.isEmpty())
        {
            int size = q.size();
            level++;

            // traverse for complete level
            for (int i=0; i < size; i++) {
                TreeNode curr = q.poll();

                // check if the node is leaf node and
                // level is odd if level is odd,
                // then update result
                if(curr.left == null && curr.right == null && (level % 2 != 0))
                {
                    result = level;
                }

                // check for left child
                if (curr.left != null)
                {
                    q.add(curr.left);
                }

                // check for right child
                if (curr.right != null)
                {
                    q.add(curr.right);
                }
            }
        }
        return result;
    }

    public static void main(String[] args) {
        DeepestOddLevel dol = new DeepestOddLevel();

        int[] values = {22,34,0,36,77,99,33,35};
        Arrays.sort(values);

        TreeNode root = dol.sortedArrayToBST(values,0,values.length-1);

        List<Integer> list = new ArrayList<>();
        System.out.println("Inorder:");
        dol.traverseInOrder(root,list);
        dol.printList(list);

        System.out.println("Deepest odd level in BT: "+dol.getDeepestOddLevel(root)); //3
        System.out.println("Deepest odd level in BT using alt method: "+dol.maxOddLevelDepth(root)); //3

        dol.insertIteratively(root,100);

        System.out.println("Deepest odd level in BT: "+dol.getDeepestOddLevel(root)); //5
        System.out.println("Deepest odd level in BT using alt method: "+dol.maxOddLevelDepth(root)); //5
    }
}
