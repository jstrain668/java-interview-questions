package com.dsa.foundation.trees.bst.preorder;

import com.dsa.foundation.trees.bst.TreeNode;

import java.util.*;
//Question: https://www.educative.io/courses/data-structures-coding-interviews-java/gkxWD7P7z8l

//Reference: https://medium.com/@ajinkyajawale/nodes-at-k-distance-from-root-of-binary-tree-664f907502b0

public class NodesKDistantFromRoot {

    public TreeNode sortedArrayToBST(int[] values, int start, int end){
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

    public TreeNode insertNodeRecursively(TreeNode root,int val){
        if (root == null){
            return new TreeNode(val);
        }

        if (val < root.val){
            root.left = insertNodeRecursively(root.left,val);
        } else {
            root.right = insertNodeRecursively(root.right,val);
        }
        return root;
    }

    public TreeNode insertNodeIteratively(TreeNode root,int val){

        if (root == null){
            return new TreeNode(val);
        }

        TreeNode curr = root;
        TreeNode parent = null;

        while (curr != null){
            parent = curr;
            if (val < curr.val){
                curr = curr.left;
            } else {
                curr = curr.right;
            }
        }

        if (val < parent.val){
            parent.left = new TreeNode(val);
        } else {
            parent.right = new TreeNode(val);
        }
        return root;
    }

    public TreeNode searchBST(TreeNode root,int val){

        if (root == null){
            return null;
        }

        TreeNode curr = root;
        while (curr != null){
            if (val < curr.val){
                curr = curr.left;
            } else if (val > curr.val){
                curr = curr.right;
            } else {
                return curr;
            }
        }
        return null;
    }

    public TreeNode searchBSTRecursively(TreeNode root,int val){

        if (root == null){
            return null;
        }

        if (val < root.val){
            root = searchBSTRecursively(root.left,val);
        } else if (val > root.val){
            root = searchBSTRecursively(root.right,val);
        }

        return root;
    }

    public int getHeight(TreeNode root){
        if (root == null){
            return 0;
        }

        return Math.max(getHeight(root.left),getHeight(root.right)) + 1;
    }

    public void printNodeList(List<TreeNode> list){
        for (TreeNode node : list){
            System.out.print(node.val+" ");
        }
        System.out.println();
    }

    private void kDistant(TreeNode root,int k, List<TreeNode> kList){

        if (root == null){
            return;
        }

        if (k == 0){
            kList.add(root);
            //return;
        }

        kDistant(root.left,k-1,kList);
        kDistant(root.right, k-1,kList);
    }

    public List<TreeNode> findNodesKDistanceFromRoot(TreeNode root,int k){

        List<TreeNode> kList = new ArrayList<>();
        if (root == null || k < 0){
            return kList;
        }

        //int height = getHeight(root);
        // if (k < 0 || k > height){
        //    return kList;
        //}

        kDistant(root,k,kList);
        return kList;
    }

    public static void main(String[] args) {
        NodesKDistantFromRoot kDistant = new NodesKDistantFromRoot();

        int[] values = {22,34,0,36,77,99,33,35};
        Arrays.sort(values);

        TreeNode root = kDistant.sortedArrayToBST(values,0,values.length-1);
        List<Integer> list = new ArrayList<>();

        kDistant.traversePreOrder(root,list);
        System.out.println("Preorder:");
        kDistant.printList(list);
        System.out.println("Inorder:");
        list.clear();
        kDistant.traverseInOrder(root,list);
        kDistant.printList(list);
        System.out.println("Postorder:");
        list.clear();
        kDistant.traversePostOrder(root,list);
        kDistant.printList(list);

        int k = 0;
        List<TreeNode> kList = kDistant.findNodesKDistanceFromRoot(root,k);
        System.out.println("List of nodes "+k+" distant from root:");
        kDistant.printNodeList(kList);
    }
}
