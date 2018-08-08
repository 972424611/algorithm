package com.aekc.algorithm.graph.prim;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdjacencyList<V> extends Adjacency<V> {

    public class Edge {

        /** 起始顶点 */
        public int start;

        /** 结束顶点 */
        public int end;

        /** 权重 */
        public int weight;

        public Edge(int start, int end, int weight) {
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }

    /** 存储顶点 */
    public List<V> vertices = new ArrayList<>();

    /** 邻接表 */
    public List<List<Edge>> neighbors = new ArrayList<>();

    public AdjacencyList(V[] vertices, int[][] edges) {
        createWeightedGraph(Arrays.asList(vertices), edges);
    }

    /**
     * 构建邻接线性表
     */
    private void createWeightedGraph(List<V> vertices, int[][] edges) {
        this.vertices = vertices;
        for(int i = 0; i < vertices.size(); i++) {
            neighbors.add(new ArrayList<>());
        }
        for(int i = 0; i < edges.length; i++) {
            neighbors.get(edges[i][0])
                    .add(new Edge(edges[i][0], edges[i][1], edges[i][2]));
        }
    }

    @Override
    public void prim(int startingVertex) {
        //权重
        double[] cost = new double[vertices.size()];
        for(int i = 0; i < cost.length; i++) {
            //初始化
            cost[i] = Double.POSITIVE_INFINITY;
        }
        //初始化是0
        cost[startingVertex] = 0;
        //父节点
        this.parent = new int[vertices.size()];
        parent[startingVertex] = -1;
        //树的总权值
        this.totalWeight = 0;
        //保存符合条件的顶点。
        this.list = new ArrayList<>();

        //找出最小权重
        while(list.size() < vertices.size()) {
            //当前顶点
            int u = -1;
            double currentMinCost = Double.POSITIVE_INFINITY;
            for(int i = 0; i < vertices.size(); i++) {
                if(!list.contains(i) && cost[i] < currentMinCost) {
                    currentMinCost = cost[i];
                    u = i;
                }
            }
            //增添新的顶点
            list.add(u);
            //把当前权值增加给数的总权值
            totalWeight += cost[u];

            //在待查找的顶点调整顶点相邻边的cost权重
            for(Edge e : neighbors.get(u)) {
                if(!list.contains(e.end) && cost[e.end] > e.weight) {
                    cost[e.end] = e.weight;
                    parent[e.end] = u;
                }
            }
        }
    }

    @Override
    public void print() {
        System.out.println("total weight is: " + super.totalWeight);
        System.out.print("查找顺序为:");
        for(int i : super.list) {
            System.out.print(" " + vertices.get(i));
        }
        System.out.println("\n节点之间的关系为: ");
        int[] parent = super.parent;
        for(int i = 0; i < parent.length; i++) {
            if(parent[i] != -1) {
                System.out.println(vertices.get(i) + "---" + vertices.get(parent[i]));
            }
        }
    }

}
