package com.aekc.algorithm.backpack;

/**
 * 分支定界法，节点属性
 */
public class Node extends ItemInfo implements Comparable {

    /**
     * 层级，用来判断是否为叶子节点
     */
    private int layer;

    /**
     * 当前解，算出最大价值后，方便找出拿了哪些物品
     */
    private String currentSolution;

    /**
     * 上限
     */
    private double up;

    /**
     * 取
     */
    private Node left;

    /**
     * 不取
     */
    private Node right;

    public Node() {}

    public int getLayer() {
        return layer;
    }

    public void setLayer(int layer) {
        this.layer = layer;
    }

    public String getCurrentSolution() {
        return currentSolution;
    }

    public void setCurrentSolution(String currentSolution) {
        this.currentSolution = currentSolution;
    }

    public double getUp() {
        return up;
    }

    public void setUp(double up) {
        this.up = up;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    /**
     * 优先队列排列方式，根据up值大小来排序
     * @param o Node
     * @return int
     */
    @Override
    public int compareTo(Object o) {
        Node node = (Node) o;
        if(node.getUp() > this.getUp()) {
            return 1;
        } else if(node.getUp() == this.getUp()) {
            return 0;
        }
        return -1;
    }


}
