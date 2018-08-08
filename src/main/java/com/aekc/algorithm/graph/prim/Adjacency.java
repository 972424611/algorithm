package com.aekc.algorithm.graph.prim;

import java.util.List;

public abstract class Adjacency<V> {

    /** 总权重 */
    protected int totalWeight;

    /** 顶点的父亲 */
    protected int[] parent;

    /** 保存顶点的添加顺序 */
    protected List<Integer> list;

    /**
     * prim
     * @param startingVertex 开始的顶点
     */
    public abstract void prim(int startingVertex);

    /**
     * 打印结果
     */
    public abstract void print();
}