package com.aekc.algorithm.backpack;

/**
 * @author Twilight
 * @date 18-12-5 下午3:29
 */
public class Main {

    public static void main(String[] args) {
        Backpack backpack = new Backpack();
        backpack.binaryTree = new BinaryTree();
        backpack.input();
        backpack.initialization();
        System.out.println(backpack.binaryTree.getMax());
        System.out.println(backpack.binaryTree.getBestSolution());
    }
}
