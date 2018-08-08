package com.aekc.algorithm.graph.kruskal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TestKruskal {

    public class Edge implements Comparable<Edge> {

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

        @Override
        public int compareTo(Edge o) {
            return this.weight - o.weight;
        }
    }

    /** 总权重 */
    public int totalWeight = 0;

    /** 保存顶点之间的关系 */
    public int[] parent;

    /** 存储边的信息 */
    public List<Edge> edgeList = new ArrayList<>();

    public List<Integer> weightList = new ArrayList<>();

    /**
     * 查找连线顶点的尾部下标
     */
    private int find(int f) {
        while(parent[f] > 0) {
            f = parent[f];
        }
        return f;
    }

    public void kruskal() {
        int size = edgeList.size();
        parent = new int[size];
        //根据权由小到大排序
        Collections.sort(edgeList);
        for(int i = 0; i < size; i++) {
            //初始化
            parent[i] = 0;
        }
        //循环所有边
        for (Edge edge : edgeList) {
            int n = find(edge.start);
            int m = find(edge.end);
            //如果n与m不等，说明此边没有与现有生成树形成环路
            if (n != m) {
                //将此边的结尾顶点放入下标为起点的parent中
                //表示此顶点已经在生成树集合中
                parent[n] = m;
                totalWeight += edge.weight;
            }
        }
    }

    public TestKruskal(int[][] edges) {
        for(int[] edge : edges) {
            if(!weightList.contains(edge[2])) {
                edgeList.add(new Edge(edge[0], edge[1], edge[2]));
                weightList.add(edge[2]);
            }
        }
    }

    public static void main(String[] args) {
        String[] vertices = {"北京", "上海", "广州", "杭州", "长沙", "武汉", "南京"};

        int[][] edges = {
                {0, 1, 7807}, {0, 3, 1331}, {0, 5, 2097},
                {1, 0, 7807}, {1, 2, 381}, {1, 3, 1267},
                {2, 1, 381}, {2, 3, 1015}, {2, 4, 1663},
                {3, 0, 1331}, {3, 1, 1267}, {3, 2, 1015}, {3, 4, 599}, {3, 5, 1003},
                {4, 2, 1663}, {4, 3, 599}, {4, 5, 533},
                {5, 0, 2097}, {5, 3, 1003}, {5, 4, 533}, {5, 6, 983},
                {6, 5, 983}
        };

        TestKruskal kruskal = new TestKruskal(edges);
        kruskal.kruskal();
        System.out.println("total weight is: " + kruskal.totalWeight);
        System.out.println("节点之间的关系为: ");
        for(int i = 0; i < kruskal.parent.length; i++) {
            if(kruskal.parent[i] != 0) {
                System.out.println(vertices[i] + "---" + vertices[kruskal.parent[i]]);
            }
        }

    }
}
