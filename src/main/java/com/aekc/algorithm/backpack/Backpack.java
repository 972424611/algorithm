package com.aekc.algorithm.backpack;

import java.util.*;

public class Backpack {

    private List<Node> nodeList;

    private String bestSolution;

    private int max;

    private int capacity;

    private int number;

    public void judge(Node node, Queue<Node> upPriorityQueue) {
        if(node == null) {
            return;
        }
        if(node.getWeight() > capacity || node.getUp() < max) {
            return;
        }
        if(node.getLayer() < number) {
            upPriorityQueue.add(node);
        } else {
            max = node.getValue();
            bestSolution = node.getCurrentSolution();
        }
    }

    public void branchBound(Node node) {
        Queue<Node> upPriorityQueue = new PriorityQueue<>();
        upPriorityQueue.add(node);
        while(!upPriorityQueue.isEmpty()) {
            node = upPriorityQueue.poll();
            judge(node.getLeft(), upPriorityQueue);
            judge(node.getRight(), upPriorityQueue);
        }
    }

    public void createdBinaryTree(Node node, int index) {
        if(index >= nodeList.size()) {
            return;
        }
        Node currentNode = nodeList.get(index);
        if(node.getLeft() == null) {
            Node leftNode = new Node();
            leftNode.setValue(currentNode.getValue() + node.getValue());
            leftNode.setWeight(currentNode.getWeight() + node.getWeight());
            leftNode.setLayer(index);
            if(index < nodeList.size() - 1) {
                Node nextNode = nodeList.get(index + 1);
                int up = (capacity - node.getWeight()) * (nextNode.getValue() / nextNode.getWeight());
                leftNode.setUp(up + node.getValue());
            } else {
                node.setUp(node.getValue());
            }
            leftNode.setCurrentSolution(node.getCurrentSolution() + 1);
            node.setLeft(leftNode);
            createdBinaryTree(node.getLeft(), index + 1);
        }
        if(node.getRight() == null) {
            Node rightNode = new Node();
            rightNode.setValue(node.getValue());
            rightNode.setWeight(node.getWeight());
            rightNode.setLayer(index);
            if(index < nodeList.size() - 1) {
                Node nextNode = nodeList.get(index + 1);
                int up = (capacity - node.getWeight()) * (nextNode.getValue() / nextNode.getWeight());
                rightNode.setUp(up);
            } else {
                node.setUp(node.getValue());
            }
            rightNode.setCurrentSolution(node.getCurrentSolution() + 0);
            node.setRight(rightNode);
            createdBinaryTree(node.getRight(), index + 1);
        }
    }

    public void preOrderBinaryTree(Node node) {
        if(node != null) {
            System.out.println(node.toString());
            preOrderBinaryTree(node.getLeft());
            preOrderBinaryTree(node.getRight());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Backpack backpack = new Backpack();
        backpack.number = scanner.nextInt();
        backpack.capacity = scanner.nextInt();
        backpack.nodeList = new ArrayList<>(backpack.number);
        for(int i = 0; i < backpack.number; i++) {
            int weight = scanner.nextInt();
            int value = scanner.nextInt();
            Node node = new Node(weight, value);
            backpack.nodeList.add(node);
        }
        backpack.nodeList.sort((o1, o2) -> {
            Double a = (double) (o1.getValue() / o1.getWeight());
            Double b = (double) (o2.getValue() / o2.getWeight());
            return b.compareTo(a);
        });
        Node head = new Node();
        head.setWeight(0);
        head.setValue(0);
        head.setLayer(0);
        head.setCurrentSolution("");
        head.setUp(backpack.capacity * (backpack.nodeList.get(0).getValue() / backpack.nodeList.get(0).getWeight()));
        backpack.createdBinaryTree(head, 1);
        backpack.preOrderBinaryTree(head);
        backpack.branchBound(head);
        System.out.println(backpack.max);
    }
}
