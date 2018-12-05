package com.aekc.algorithm.backpack;

/**
 * 运用分支定界法解决背包问题
 * @author Twilight
 * @date 18-12-5 下午3:29
 */
public class Main {

    public static void main(String[] args) {
        Backpack backpack = new Backpack();
        backpack.binaryTree = new BinaryTree();
        backpack.input();
        backpack.initialization();
        System.out.println("最大价值为：" + backpack.binaryTree.getMax());
        String bestSolution = backpack.binaryTree.getBestSolution();
        System.out.println("拿取的物品为：");
        for(int i = 0; i < bestSolution.length(); i++) {
            if(bestSolution.charAt(i) != '0') {
                ItemInfo itemInfo = backpack.binaryTree.getItemInfoList().get(i);
                System.out.println("[" + itemInfo.getWeight() + " " + itemInfo.getValue() + "]");
            }
        }
    }
}
