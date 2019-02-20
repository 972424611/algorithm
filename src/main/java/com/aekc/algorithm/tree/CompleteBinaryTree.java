package com.aekc.algorithm.tree;

import java.util.LinkedList;

/**
 * @author Twilight
 * @date 19-1-28 下午9:34
 */
public class CompleteBinaryTree {

    class Node {
        public int value;
        public Node leftNode;
        public Node rightNode;

        public Node(int value) {
            this.value = value;
        }
    }

    /**
     *       1
     *   2      3
     * 4   5  #   #
     */
    private char[] treeValue = {'1', '2', '3', '4', '5', '#', '#'};

    public Node buildTree() {
        LinkedList<Node> queue = new LinkedList<>();
        Node head = new Node(treeValue[0] - '0');
        queue.offer(head);
        for(int i = 1; i < treeValue.length; i = i + 2) {
            Node node = queue.poll();
            if(node == null) {
                break;
            }
            if(treeValue[i] != '#') {
                node.leftNode = new Node(treeValue[i] - '0');
                queue.offer(node.leftNode);
            } else {
                node.leftNode = null;
            }
            if(i + 1 < treeValue.length && treeValue[i + 1] != '#') {
                node.rightNode = new Node(treeValue[i + 1] - '0');
                queue.offer(node.rightNode);
            } else {
                node.rightNode = null;
            }
        }
        return head;
    }

    public boolean isCompleteBinaryTree(Node root) {
        if(root == null) {
            return false;
        }
        LinkedList<Node> queue = new LinkedList<>();
        queue.offer(root);
        boolean flag = true;
        while(!queue.isEmpty()) {
            Node node = queue.poll();
            // 如果flag为false，说明当前节点的兄弟节点（当前节点的父亲节点的左孩子）没有左孩子或者右孩子，或者没有子节点。
            if(!flag) {
                // 如果有孩子
                if(node.leftNode != null || node.rightNode != null) {
                    return false;
                }
            }
            if(node.leftNode != null) {
                queue.offer(node.leftNode);
            } else {
                // 如果当前节点没有左孩子，则该节点不应该有右孩子，这里设置标识
                flag = false;
            }
            if(node.rightNode != null) {
                queue.offer(node.rightNode);
                // 如果flag为false，则代表该节点没有左孩子
                if(!flag) {
                   return false;
                }
            } else {
                // 如果该节点没有右孩子，则他的兄弟节点不能有子节点，这里设置标识
                flag = false;
            }
        }
        return true;
    }



    public static void main(String[] args) {
        CompleteBinaryTree tree = new CompleteBinaryTree();
        Node node = tree.buildTree();
        System.out.println(tree.isCompleteBinaryTree(node));
    }
}
