package com.aekc.algorithm.tree;

public class TestBSTDelete {

    public static void main(String[] args) {
        BST<String> tree = new BST<>();
        tree.insert("George");
        tree.insert("Michael");
        tree.insert("Tom");
        tree.insert("Adam");
        tree.insert("Jones");
        tree.insert("Pater");
        tree.insert("Daniel");
        printTree(tree);
        System.out.println("\nAfter delete George");
        tree.delete("George");
        printTree(tree);
    }

    public static void printTree(BST tree) {
        System.out.print("Intorder: ");
        tree.inorder();

        System.out.print("\nPostorder: ");
        tree.postorder();

        System.out.print("\nPreorder: ");
        tree.preorder();

        System.out.print("\nThe number of nodes is " + tree.getSize());
        System.out.println();
    }
}
