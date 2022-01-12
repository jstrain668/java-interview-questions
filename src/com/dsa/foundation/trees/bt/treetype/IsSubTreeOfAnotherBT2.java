package com.dsa.foundation.trees.bt.treetype;

//Given two binary trees, check if the first tree is subtree of the second one. A subtree of a tree T is a tree S
// consisting of a node in T and all of its descendants in T. The subtree corresponding to the root node is the entire
// tree; the subtree corresponding to any other node is called a proper subtree.

//Reference: https://www.geeksforgeeks.org/check-binary-tree-subtree-another-binary-tree-set-2/

//Solution: https://algorithms.tutorialhorizon.com/given-two-binary-trees-check-if-one-binary-tree-is-a-subtree-of-another/

public class IsSubTreeOfAnotherBT2 {

    class Node{
        char data;
        Node left, right;

        public Node(char item)
        {
            data = item;
            left = right = null;
        }
    }

    public Node createBTree1(){
        Node T = new Node('a');
        T.left = new Node('b');
        T.right = new Node('d');
        T.left.left = new Node('c');
        T.right.right = new Node('e');

        return T;
    }

    public Node createBTree2(){

        Node S = new Node('a');
        S.left = new Node('b');
        S.right = new Node('d');
        S.left.left = new Node('c');

        return S;
    }


    //store the inOrder and postorder traversal for both the array,
    //if one array is the sub array of another array, that means one tree is the subtree of another one
    public String inOrder(Node root, String i){
        if(root!=null){
            return inOrder(root.left,i) + " " + root.data + " " +inOrder(root.right,i);
        }
        return "";
    }

    public String postOrder(Node root, String i){
        if(root!=null){
            return postOrder(root.left,i) + " " + postOrder(root.right,i) + " " + root.data;
        }
        return "";
    }

    //Time Complexity: Inorder and Postorder traversals of Binary Tree take O(n) time.
    //Auxiliary Space: O(n)

    //Approach:
    //
    //Any binary tree can be represented as the combination of either inorder and preorder traversal OR
    // inorder and postorder traversal OR inorder and Level order traversal.
    //
    //Say our trees are A and B.
    //Do the inorder traveral of treeA and store it in a String say inorderA.
    //Do the inorder traveral of treeB and store it in a String say inorderB.
    //Do the postorder traveral of treeA and store it in a String say postorderA.
    //Do the postorder traveral of treeB and store it in a String say postorderB.
    //Check if inorderA contains inorderB AND postorderA contains postorderB then it means treeB is a subtree of treeA.
    public boolean checkSubtree(Node rootA, Node rootB){
        String inOrderA = inOrder(rootA,"");
        String inOrderB = inOrder(rootB,"");
        String postOrderA = postOrder(rootA,"");
        String postOrderB = postOrder(rootB,"");

        return (inOrderA.toLowerCase().contains(inOrderB.toLowerCase()) &&
                postOrderA.toLowerCase().contains(postOrderB.toLowerCase()));
    }

    public void display(Node root){
        if(root!=null){
            display(root.left);
            System.out.print(" " + root.data);
            display(root.right);
        }
    }


    // Driver program to test above functions
    public static void main(String[] args)
    {
        IsSubTreeOfAnotherBT2 tree = new IsSubTreeOfAnotherBT2();
        Node T = tree.createBTree1();
        Node S = tree.createBTree2();

        System.out.print(" Tree T : ");
        tree.display(T);
        System.out.println();
        System.out.print(" Tree S : ");
        tree.display(S);
        System.out.println();

        if (tree.checkSubtree(T, S)) {
            System.out.println("Yes, S is a subtree of T");
        }
        else {
            System.out.println("No, S is not a subtree of T");
        }
    }
}
