package com.dsa.medium.trees.bt;

import java.util.*;

//Question: https://leetcode.com/problems/all-nodes-distance-k-in-binary-tree/

//Reference: https://massivealgorithms.blogspot.com/2018/11/leetcode-863-all-nodes-distance-k-in.html


// Tree is a special kind of Undirected Graph. Each Node in tree has at most three edges, one for left child, one for
// right child, and other one for its parent.
//
// After building all the relationships, the rest of work is to find Distance K nodes from the target node. This can
// be traversed level by level. This type of search is typically breath-first search. To prevent traverse back to the
// nodes we have traversed in previous step. We need another additional data structure which is the set to keep track
// of those nodes which have been traversed before.

public class KDistantFromTargetNode {

    Map<TreeNode,List<TreeNode>> map = new HashMap<>();

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

    public void traversePreOrder(TreeNode node, List<Integer> list){

        if (node != null){
            list.add(node.val);
            traversePreOrder(node.left,list);
            traversePreOrder(node.right,list);
        }
    }

    public void traverseInOrder(TreeNode node, List<Integer> list){

        if (node != null){
            traverseInOrder(node.left,list);
            list.add(node.val);
            traverseInOrder(node.right,list);
        }
    }

    public void traversePostOrder(TreeNode node, List<Integer> list){

        if (node != null){
            traversePostOrder(node.left,list);
            traversePostOrder(node.right,list);
            list.add(node.val);
        }
    }

    public TreeNode insertNodeRecursively(TreeNode root, int val){
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

    public TreeNode insertNodeIteratively(TreeNode root, int val){

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

    public TreeNode searchBST(TreeNode root, int val){

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

    public TreeNode searchBSTRecursively(TreeNode root, int val){

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
            return -1;
        }

        return Math.max(getHeight(root.left),getHeight(root.right)) + 1;
    }

    public void printNodeList(List<TreeNode> list){
        for (TreeNode node : list){
            System.out.print(node.val+" ");
        }
        System.out.println();
    }

    private void buildMap(TreeNode node, TreeNode parent){

        if (node == null){
            return;
        }

        if (!map.containsKey(node)){
            map.put(node,new ArrayList<>());

            if (parent != null){
                map.get(node).add(parent);
                map.get(parent).add(node);
            }

            buildMap(node.left,node);
            buildMap(node.right,node);
        }
    }

    /*
     * Traverse the tree in DFS way and record every node's parent.
     */
    public List<Integer> findNodesKDistance(TreeNode root,TreeNode target, int k){
        List<Integer> result = new ArrayList<>();

        if (root == null || k < 0){
            return result;
        }

        //Build an undirected graph from the binary tree
        buildMap(root,null);
        if (!map.containsKey(target)){
            return result;
        }
        //Track the visited set of nodes
        Set<TreeNode> visited = new HashSet<>();
        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(target);
        visited.add(target);

        while (!queue.isEmpty()){
            int size = queue.size();

            if (k == 0){
                for (int i=0; i < size; i++){
                    result.add(queue.poll().val);
                }
                return result;
            }

            for (int i=0; i < size; i++) {
                TreeNode node = queue.poll();

                for (TreeNode next : map.get(node)) {
                    if (!visited.contains(next)) {
                        queue.add(next);
                        visited.add(next);
                    }
                }
            }
            k--;
        }
        return result;
    }

    public static void main(String[] args) {
        KDistantFromTargetNode kDistant = new KDistantFromTargetNode();

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

        int k = 2;
        int targetVal = 36;
        TreeNode target = kDistant.searchBST(root,targetVal);
        List<Integer> kList = kDistant.findNodesKDistance(root,target,k);
        kDistant.printList(kList);
    }
}
