package com.dsa.foundation.trees.bt;

//Reference: https://www.techiedelight.com/boundary-traversal-binary-tree/
//Reference: https://titanwolf.org/Network/Articles/Article?AID=04b0f568-850c-4549-b64a-cbc4b9de7e8e#gsc.tab=0
//Reference: https://www.thealgorists.com/Algo/Tree/ProblemSolving

//Solution is also applicable to https://www.geeksforgeeks.org/iterative-boundary-traversal-of-complete-binary-tree/

public class PrintTreeBoundary {

    public TreeNode createBT(){

        // Root node of the tree
        TreeNode root = new TreeNode(20);

        root.left = new TreeNode(8);
        root.right = new TreeNode(22);

        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(12);

        root.right.left = new TreeNode(10);
        root.right.right = new TreeNode(25);

        return root;
    }

    public TreeNode createBinaryTree()
    {

        TreeNode rootNode =new TreeNode(40);
        TreeNode node20=new TreeNode(20);
        TreeNode node10=new TreeNode(10);
        TreeNode node30=new TreeNode(30);
        TreeNode node60=new TreeNode(60);
        TreeNode node50=new TreeNode(50);
        TreeNode node70=new TreeNode(70);
        TreeNode node5=new TreeNode(5);
        TreeNode node45=new TreeNode(45);
        TreeNode node55=new TreeNode(55);

        rootNode.left=node20;
        rootNode.right=node60;

        node20.left=node10;
        node20.right=node30;

        node60.left=node50;
        node60.right=node70;

        node10.right=node5;
        node5.right=node45;

        node50.right=node55;
        return rootNode;
    }

    public TreeNode createBinaryTree2(){
        TreeNode root = new TreeNode(20);
        TreeNode node8 = new TreeNode(8);
        TreeNode node4 = new TreeNode(4);
        TreeNode node12 = new TreeNode(12);
        TreeNode node10 = new TreeNode(10);
        TreeNode node14 = new TreeNode(14);
        TreeNode node22  = new TreeNode(22);
        TreeNode node25  = new TreeNode(25);

        root.left = node8;
        root.right = node22;

        node8.left = node4;
        node8.right = node12;

        node12.left = node10;
        node12.right = node14;

        node22.right = node25;
        return root;
    }

    public TreeNode createBinaryTree3() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);

        root.right = node2;
        node2.left = node3;
        node2.right = node4;

        return root;
    }

    public TreeNode createBinaryTree4() {
        TreeNode root = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node7 = new TreeNode(7);
        TreeNode node8 = new TreeNode(8);

        TreeNode node3 = new TreeNode(3);
        TreeNode node6 = new TreeNode(6);
        TreeNode node9 = new TreeNode(9);
        TreeNode node10 = new TreeNode(10);

        root.left = node2;
        root.right = node3;

        node2.left = node4;
        node2.right = node5;

        node5.left = node7;
        node5.right = node8;

        node3.left = node6;

        node6.left = node9;
        node6.right = node10;

        return root;
    }

    public TreeNode createBinaryTree5(){

        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.left.left = new TreeNode(8);
        root.left.left.right = new TreeNode(9);
        root.left.right.right = new TreeNode(10);
        root.right.right.left = new TreeNode(11);
        root.left.left.right.left = new TreeNode(12);
        root.left.left.right.right = new TreeNode(13);
        root.right.right.left.left = new TreeNode(14);

        return root;
    }

    private void traverseAndPrint(TreeNode node, boolean leftEdge, boolean rightEdge){

        if (node == null){
            return;
        }

        if (leftEdge) {
            System.out.print(node.val+" ");
        }

        //add bottom
        if(!leftEdge && !rightEdge && node.left == null && node.right == null) {
            System.out.print(node.val+" ");
        }

        traverseAndPrint(node.left, leftEdge, rightEdge && node.right == null);
        traverseAndPrint(node.right, leftEdge && node.left == null, rightEdge);

        if (rightEdge) {
            System.out.print(node.val+" ");
        }
    }

    //Description: The boundary of a binary tree consists of:
    //1. Left most boundary in top-down direction,
    //2. All the leaf nodes from left to right,
    //3. Right most boundary in bottom-up direction.
    //The algorithm splits the traversal into two main traversals left and right of root node. Left edge traversing is
    //done in a pre-order fashion, visit a node first then its children and the right edge traversing is done in a post
    //order fashion, visit the children first before visiting the node.Printing the leaf nodes which are not left edge
    //leaf node and right edge leaf node is accomplished by checking the node's left and right pointers are null and the
    // left edge check and right edge check are false.
    //1. If on the left boundary then if the node has a non-null left child node then the left node becomes part of the
    //left boundary, but if a node on left boundary does not have a non-null left node but has a non-null right node
    //then the right node becomes part of the left boundary. Leverage left edge flag to reflect left boundary processing
    //2. While processing on the left hand-side of the tree, print the leaf nodes by turning of the left edge flag and
    //checking left and right pointers are null.
    //3. After processing left hand of tree, turn on right edge flag and switch of left edge flag. Process from bottom
    // up by processing leaf nodes and then right hand boundary nodes. When processing leaf nodes apart from leaf node
    // which is the right boundary the left and right edge flags are null.
    //4. Recursive calls on the right boundary prints the right boundary nodes from bottom to top.
    //Time Complexity: O(n)
    //Space Complexity: O(1)

    public void printTreeBoundary(TreeNode node){

        if (node != null){

            System.out.print(node.val+" ");
            traverseAndPrint(node.left, true, false);
            traverseAndPrint(node.right, false, true);
        }
    }

    public static void main(String[] args) {
        PrintTreeBoundary tree = new PrintTreeBoundary();

        // Creating a binary tree
        TreeNode rootNode = tree.createBinaryTree5();
        System.out.println("Boundary traversal of binary tree:");
        tree.printTreeBoundary(rootNode); // 1 2 4 8 12 13 10 6 14 11 7 3
        System.out.println();

        rootNode = tree.createBT();
        System.out.println("Boundary traversal of binary tree:");
        tree.printTreeBoundary(rootNode); // 20 8 4 12 10 25 22
        System.out.println();

    }
}
