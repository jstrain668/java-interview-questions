package com.dsa.medium.trees.heap;

import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

//Question and reference: https://www.techiedelight.com/huffman-coding/

//Note that the input string’s storage is 47×8 = 376 bits, but our encoded string only takes 194 bits, i.e., about 48%
// of data compression. To make the program readable, we have used string class to store the above program’s encoded
// string.
//
//Since efficient priority queue data structures require O(log(n)) time per insertion, and a complete binary tree with
// n leaves has 2n-1 nodes, and Huffman coding tree is a complete binary tree, this algorithm operates in O(n.log(n))
// time, where n is the total number of characters.

public class HuffmanCoding {

    // A Tree node
    class Node
    {
        Character ch;
        Integer freq;
        Node left = null, right = null;

        Node(Character ch, Integer freq)
        {
            this.ch = ch;
            this.freq = freq;
        }

        public Node(Character ch, Integer freq, Node left, Node right)
        {
            this.ch = ch;
            this.freq = freq;
            this.left = left;
            this.right = right;
        }
    }

    // Traverse the Huffman Tree and store Huffman Codes in a map.
    public void encode(Node root, String str,
                              Map<Character, String> huffmanCode)
    {
        if (root == null) {
            return;
        }

        // Found a leaf node
        if (isLeaf(root)) {
            huffmanCode.put(root.ch, str.length() > 0 ? str : "1");
        }

        encode(root.left, str + '0', huffmanCode);
        encode(root.right, str + '1', huffmanCode);
    }

    // Traverse the Huffman Tree and decode the encoded string
    public int decode(Node root, int index, StringBuilder sb)
    {
        if (root == null) {
            return index;
        }

        // Found a leaf node
        if (isLeaf(root))
        {
            System.out.print(root.ch);
            return index;
        }

        index++;

        root = (sb.charAt(index) == '0') ? root.left : root.right;
        index = decode(root, index, sb);
        return index;
    }

    // Utility function to check if Huffman Tree contains only a single node
    public boolean isLeaf(Node root) {
        return root.left == null && root.right == null;
    }

    // Builds Huffman Tree and decodes the given input text
    public void buildHuffmanTree(String text)
    {
        // Base case: empty string
        if (text == null || text.length() == 0) {
            return;
        }

        // Count the frequency of appearance of each character
        // and store it in a map

        Map<Character, Integer> freq = new HashMap<>();
        for (char c: text.toCharArray()) {
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        // create a priority queue to store live nodes of the Huffman tree.
        // Notice that the highest priority item has the lowest frequency

        PriorityQueue<Node> pq;
        pq = new PriorityQueue<>(Comparator.comparingInt(l -> l.freq));

        // create a leaf node for each character and add it
        // to the priority queue.

        for (var entry: freq.entrySet()) {
            pq.add(new Node(entry.getKey(), entry.getValue()));
        }

        // do till there is more than one node in the queue
        while (pq.size() != 1)
        {
            // Remove the two nodes of the highest priority
            // (the lowest frequency) from the queue

            Node left = pq.poll();
            Node right = pq.poll();

            // create a new internal node with these two nodes as children
            // and with a frequency equal to the sum of both nodes'
            // frequencies. Add the new node to the priority queue.

            int sum = left.freq + right.freq;
            pq.add(new Node(null, sum, left, right));
        }

        // `root` stores pointer to the root of Huffman Tree
        Node root = pq.peek();

        // Traverse the Huffman tree and store the Huffman codes in a map
        Map<Character, String> huffmanCode = new HashMap<>();
        encode(root, "", huffmanCode);

        // Print the Huffman codes
        System.out.println("Huffman Codes are: " + huffmanCode);
        System.out.println("The original string is: " + text);

        // Print encoded string
        StringBuilder sb = new StringBuilder();
        for (char c: text.toCharArray()) {
            sb.append(huffmanCode.get(c));
        }

        System.out.println("The encoded string is: " + sb);
        System.out.print("The decoded string is: ");

        if (isLeaf(root))
        {
            // Special case: For input like a, aa, aaa, etc.
            while (root.freq-- > 0) {
                System.out.print(root.ch);
            }
        }
        else {
            // Traverse the Huffman Tree again and this time,
            // decode the encoded string
            int index = -1;
            while (index < sb.length() - 1) {
                index = decode(root, index, sb);
            }
        }
    }

    // Huffman coding algorithm implementation in Java
    public static void main(String[] args)
    {
        HuffmanCoding hc = new HuffmanCoding();

        String text = "Huffman coding is a data compression algorithm.";
        hc.buildHuffmanTree(text);
    }
}
