package com.aekc.algorithm.backpack;

import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 * 处理节点树
 * @author Twilight
 * @date 18-12-5 下午2:58
 */
public class BinaryTree {

    /**
     * 保存所有物品的基本信息，根据每单位质量价值从大到小来排序
     */
    private List<ItemInfo> itemInfoList;

    /**
     * 最优解(假如为：1010，代表第一个拿，第二个不拿，第三个拿，第四个不拿)
     */
    private String bestSolution;

    /**
     * 最优值
     */
    private int max;

    private Backpack backpack;

    /**
     * 判断节点是否符合要求
     * @param node 树的当前节点
     * @param upPriorityQueue 优先队列
     */
    private void judgementNode(Node node, Queue<Node> upPriorityQueue) {
        if(node == null) {
            return;
        }
        // 当前节点对应的总重量超过背包最大容量 || 当前节点最可能的最大价值up小于已知的价值
        // node.getWeight() > backpack.getCapacity()这个判断其实是多余的。。
        if(node.getWeight() > backpack.getCapacity() || node.getUp() < max) {
            return;
        }
        // 当前的节点对应的层级是否小于总数量
        if(node.getLayer() < backpack.getNumber()) {
            upPriorityQueue.add(node);
        } else {
            // 更新最优价值
            max = node.getValue();
            // 更新最优解
            bestSolution = node.getCurrentSolution();
        }
    }

    /**
     * 分支界定
     * @param node 根节点
     */
    public void branchBound(Node node) {
        // 使用优先队列，根据节点的up值，优先权由大到小
        Queue<Node> upPriorityQueue = new PriorityQueue<>();
        upPriorityQueue.add(node);
        while(!upPriorityQueue.isEmpty()) {
            node = upPriorityQueue.poll();
            // 如果队列中第一个节点的up值小于最优价值，则就没必要继续遍历队列。
            if(node.getUp() <= max) {
                break;
            }
            judgementNode(node.getLeft(), upPriorityQueue);
            judgementNode(node.getRight(), upPriorityQueue);
        }
    }

    /**
     * 创建二叉树
     * @param node 当前节点
     * @param index 物品List对应下标
     */
    public void createdBinaryTree(Node node, int index) {
        if(index >= backpack.getNumber()) {
            return;
        }
        // 获取当前物品信息
        ItemInfo currentItem = itemInfoList.get(index);
        if(node.getLeft() == null) {
            Node leftNode = new Node();
            // 因为是左节点，代表拿。被拿当前物品价值+已有的物品总价值
            leftNode.setValue(currentItem.getValue() + node.getValue());
            // 被拿当前物品重量+已有的物品总重量
            leftNode.setWeight(currentItem.getWeight() + node.getWeight());
            // 设置层级index从0开始，0代表根节点的层级
            leftNode.setLayer(index + 1);
            // 算出剩余容量
            int surplus = backpack.getCapacity() - leftNode.getWeight();
            // 这里进行减去枝，如果剩余容量小于0说明当前物品无法放入
            if(surplus >= 0) {
                // 如果不是最后一层，index在这里，可以理解为层级
                if(index + 1 < itemInfoList.size()) {
                    // 当前物品的下一个物品
                    ItemInfo nextNode = itemInfoList.get(index + 1);
                    // 求出剩余物品的最大up值，下一个物品每单位容量最大价值*剩余容量
                    int up = surplus * (nextNode.getValue() / nextNode.getWeight());
                    // 是否为根节点
                    if(index == 0) {
                        // 根节点只需要获取当前物品的价值+up
                        leftNode.setUp(up + currentItem.getValue());
                    } else {
                        // 非根节点需要获取当前总价值+up
                        leftNode.setUp(up + leftNode.getValue());
                    }
                } else {
                    // 最后一层，其实就是最后一个物品，对应最后一层的节点的up就是当前节点的value
                    leftNode.setUp(node.getValue());
                }
                // 更新解过程，1代表left，即拿取
                leftNode.setCurrentSolution(node.getCurrentSolution() + 1);
                node.setLeft(leftNode);
                createdBinaryTree(node.getLeft(), index + 1);
            }
        }
        if(node.getRight() == null) {
            Node rightNode = new Node();
            // 因为是右节点，代表不拿。所以直接设置为已有的物品总价值
            rightNode.setValue(node.getValue());
            rightNode.setWeight(node.getWeight());
            rightNode.setLayer(index + 1);
            int surplus = backpack.getCapacity() - rightNode.getWeight();
            if(surplus >= 0) {
                if(index + 1 < itemInfoList.size()) {
                    ItemInfo nextNode = itemInfoList.get(index + 1);
                    int up = surplus * (nextNode.getValue() / nextNode.getWeight());
                    if(index == 0) {
                        rightNode.setUp(up);
                    } else {
                        rightNode.setUp(up + rightNode.getValue());
                    }
                } else {
                    rightNode.setUp(node.getValue());
                }
                rightNode.setCurrentSolution(node.getCurrentSolution() + 0);
                node.setRight(rightNode);
                createdBinaryTree(node.getRight(), index + 1);
            }
        }
    }

    /**
     * 遍历树
     * @param node 节点
     */
    public void preOrderBinaryTree(Node node) {
        if(node != null) {
            System.out.println(node.toString());
            preOrderBinaryTree(node.getLeft());
            preOrderBinaryTree(node.getRight());
        }
    }

    public List<ItemInfo> getItemInfoList() {
        return itemInfoList;
    }

    public void setItemInfoList(List<ItemInfo> itemInfoList) {
        this.itemInfoList = itemInfoList;
    }

    public String getBestSolution() {
        return bestSolution;
    }

    public void setBestSolution(String bestSolution) {
        this.bestSolution = bestSolution;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public Backpack getBackpack() {
        return backpack;
    }

    public void setBackpack(Backpack backpack) {
        this.backpack = backpack;
    }
}
