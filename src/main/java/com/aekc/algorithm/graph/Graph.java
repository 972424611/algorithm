package com.aekc.algorithm.graph;

import java.util.List;

public interface Graph<V> {

    /**
     * 返回图中的顶点数
     * @return
     */
    int getSize();

    /**
     * 返回图的顶点
     * @return
     */
    List<V> getVertices();

    /**
     * 返回指定顶点下标的顶点对象
     * @param index
     * @return
     */
    V getVertex(int index);

    /**
     * 返回指定顶点的下表
     * @param v
     * @return
     */
    int getIndex(V v);

    /**
     * 返回指定下标的顶点的邻居
     * @param index
     * @return
     */
    List<Integer> getNeighbors(int index);

    /**
     * 返回指定顶点下标的度
     * @param v
     * @return
     */
    int getDegree(int v);

    /**
     * 打印边
     */
    void printEdges();

    /**
     * 清楚图
     */
    void clear();

    /**
     * 如果v添加到图中，返回true；如果v已经在图中，返回false
     * @param vertex
     */
    void addVertex(V vertex);

    /**
     * 添加从u到v的边到图中，如果u或者v是无效的，则抛出IllegalArgumentException异常。
     * 如果边添加成功则返回true，如果(u, v)已经在图中则返回false
     * @param u
     * @param v
     */
    void addEdge(int u, int v);

    /**
     * 得到从v开始的一个深度优先搜索树
     * @param v
     * @return
     */
    AbstractGraph<V>.Tree dfs(int v);

    /**
     * 得到从v开始的一个广度优先搜索树
     * @param v
     * @return
     */
    AbstractGraph<V>.Tree bfs(int v);

}
