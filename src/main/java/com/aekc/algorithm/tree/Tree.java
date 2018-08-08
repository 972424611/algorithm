package com.aekc.algorithm.tree;

public interface Tree<E> {

    boolean search(E e);

    boolean insert(E e);

    boolean delete(E e);

    /**
     * 中序遍历
     */
    void inorder();

    /**
     * 后序遍历
     */
    void postorder();

    /**
     * 前序遍历
     */
    void preorder();

    int getSize();

    boolean isEmpty();
}
