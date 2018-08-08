package com.aekc.algorithm.graph.prim;

import java.util.ArrayList;

public class AdjacencyMatrix<V> extends Adjacency<V> {

    private String[] vertices;

    private int[][] matrix;

    public AdjacencyMatrix(String[] vertices, int[][] matrix) {
        this.vertices = vertices;
        this.matrix = matrix;
    }

    @Override
    public void prim(int startingVertex) {
        //权重
        double[] cost = new double[vertices.length];
        for(int i = 0; i < cost.length; i++) {
            //初始化
            cost[i] = Double.POSITIVE_INFINITY;
        }
        //初始化是0
        cost[startingVertex] = 0;
        //父节点
        this.parent = new int[vertices.length];
        parent[startingVertex] = -1;
        //树的总权值
        this.totalWeight = 0;
        //保存符合条件的顶点。
        this.list = new ArrayList<>();

        //找出最小权重
        while(list.size() < vertices.length) {
            //当前顶点
            int u = -1;
            double currentMinCost = Double.POSITIVE_INFINITY;
            for(int i = 0; i < vertices.length; i++) {
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
            for(int e = 0; e < matrix[u].length; e++) {
                if(!list.contains(e) && cost[e] > matrix[u][e]) {
                    cost[e] = matrix[u][e];
                    parent[e] = u;
                }
            }
        }
    }

    @Override
    public void print() {
        System.out.println("total weight is: " + super.totalWeight);
        System.out.print("查找顺序为:");
        for(int i : super.list) {
            System.out.print(" " + vertices[i]);
        }
        System.out.println("\n节点之间的关系为: ");
        int[] parent = super.parent;
        for(int i = 0; i < parent.length; i++) {
            if(parent[i] != -1) {
                System.out.println(vertices[i] + "---" + vertices[parent[i]]);
            }
        }
    }
}
