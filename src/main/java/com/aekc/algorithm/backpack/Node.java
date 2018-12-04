package com.aekc.algorithm.backpack;

/**
 * 分支定界法，节点属性
 */
public class Node implements Comparable {

    /**
     * 重量
     */
    private int weight;

    /**
     * 价值
     */
    private int value;

    /**
     * 层级
     */
    private int layer;

    /**
     * 当前解
     */
    private String currentSolution;

    /**
     * 上限
     */
    private int up;

    /**
     * 取
     */
    private Node left;

    /**
     * 不取
     */
    private Node right;

    public Node() {}


    public Node(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

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

    public int getUp() {
        return up;
    }

    public void setUp(int up) {
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

    @Override
    public String toString() {
        return "Node{" +
                "weight=" + weight +
                ", value=" + value +
                ", layer=" + layer +
                ", currentSolution=" + currentSolution +
                ", up=" + up +
                ", left=" + left +
                ", right=" + right +
                '}';
    }
}
