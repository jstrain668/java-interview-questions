package com.dsa.foundation.trees.bst.bft;

import com.dsa.foundation.trees.bst.inorder.NodesWithSingleChild;
import com.dsa.foundation.trees.bst.TreeNode;

import java.util.*;

public class MinDepth {

    class QueueNode {
        TreeNode node;
        int depth;

        public QueueNode(TreeNode node, int depth){
            this.node = node;
            this.depth = depth;
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

    private TreeNode getMaxNode(TreeNode node){
        while (node.right != null){
            node = node.right;
        }
        return node;
    }

    private TreeNode getMinNode(TreeNode node){
        while (node.left != null){
            node = node.left;
        }
        return node;
    }

    public TreeNode deleteNode(TreeNode root,int val){
        if (root == null){
            return null;
        }

        TreeNode curr = root;
        TreeNode parent = null;

        //Find node to delete
        while (curr != null && val != curr.val){

            parent = curr;
            if (val < curr.val){
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        //Node not found with value 'val', no update performed so return root
        if (curr == null){
            return root;
        }

        //Case 1: Deleting leaf node (no children)
        //Parents left or right ptr is set to null
        if (curr.left == null && curr.right == null){

            //Not deleting root node
            if (curr != root){

                if (parent.left == curr){
                    parent.left = null;
                } else {
                    parent.right = null;
                }
            } else{ //deleting root node
                root = null;
            }
        }

        //Case 2: Parent node with 2 children
        //As per BST removal logic when removal node has both left and right node,
        //1. find a minimum value in the right subtree;
        //2. replace value of the node to be removed with found minimum
        //        OR
        //1. find a maximum value in the left subtree;
        //2. replace value of the node to be removed with found maximum.
        else if (curr.left != null && curr.right != null){
            TreeNode successor = getMinNode(curr.right);
            //TreeNode successor = getMaxNode(curr.left);

            //Store successor value
            int value = successor.val;
            //Successor node is a leaf node
            deleteNode(root,successor.val);
            curr.val = value;
        }

        //Case 3: Parent node with 1 child
        //Parent points to child node
        else{
            //Setup child node
            TreeNode child = (curr.left != null) ? curr.left : curr.right;

            if (curr != root){
                if (parent.left == curr){
                    parent.left = child;
                } else{
                    parent.right = child;
                }
            } else{
                root = child;
            }
        }

        return root;
    }

    public TreeNode deleteNodeRecursively(TreeNode root,int val){
        if (root == null){
            return null;
        }

        //Find node in left subtree
        if (val < root.val){
            root.left = deleteNodeRecursively(root.left,val);
        } //Find node in right subtree
        else if (val > root.val){
            root.right = deleteNodeRecursively(root.right,val);
        }
        //Found node - Handle 3 possible types of deletion
        else {
            //Case 1: Node is a leaf node - no children
            if (root.left == null && root.right == null){
                //Update root to be null
                return null;
            }
            //Case 2: Node has two children
            else if (root.left != null && root.right != null){
                TreeNode predecessor = getMaxNode(root.left);
                root.val = predecessor.val;
                root.left = deleteNodeRecursively(root.left, predecessor.val);
            }
            //Case 3: Node has one child
            else{
                TreeNode child = (root.left != null)? root.left : root.right;
                root = child;

            }
        }
        return root;
    }

    public int findMinDepth(TreeNode root){

        if (root == null){
            return 0;
        }

        Queue<QueueNode> queue = new LinkedList<>();
        queue.add(new QueueNode(root,1));

        while (!queue.isEmpty()){
            QueueNode current = queue.poll();
            TreeNode curr = current.node;

            if (curr.left == null && curr.right == null){
                return current.depth;
            }

            if (curr.left != null){
                queue.add(new QueueNode(curr.left, current.depth+1));
            }

            if (curr.right != null){
                queue.add(new QueueNode(curr.right, current.depth+1));
            }
        }

        return 0;
    }

    public static void main(String[] args) {

        MinDepth md = new MinDepth();

        NodesWithSingleChild singleParent = new NodesWithSingleChild();

        int[] values = {22,34,0,36,77,99,33,35};
        Arrays.sort(values);

        TreeNode root = md.sortedArrayToBST(values,0,values.length-1);

        List<Integer> list = new ArrayList<>();
        System.out.println("Inorder:");
        md.traverseInOrder(root,list);
        md.printList(list);

        System.out.println("Min depth of BST: "+md.findMinDepth(root));

        md.deleteNode(root,0);
        md.deleteNode(root,33);
        System.out.println("Inorder:");
        list.clear();
        md.traverseInOrder(root,list);
        md.printList(list);

        System.out.println("Min depth of BST: "+md.findMinDepth(root));

    }
}
