package com.aekc.algorithm.graph.dijkstra;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class TestDijkstra<V> {

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

    /** 点到点的路径 */
    private int[] pathMatrix;

    public double[] dijkstra(int sourceVertex) {
        double[] cost = new double[vertices.size()];
        for(int i = 0; i < cost.length; i++) {
            cost[i] = Double.POSITIVE_INFINITY;
        }
        cost[sourceVertex] = 0;
        pathMatrix[sourceVertex] = -1;
        //保存发现的顶点
        List<Integer> foundVertexList = new ArrayList<>();
        while(foundVertexList.size() < vertices.size()) {
            int u = -1;
            double currentMinCost = Double.POSITIVE_INFINITY;
            for(int i = 0; i < vertices.size(); i++) {
                //找到距离更近的点
                if(!foundVertexList.contains(i) && cost[i] < currentMinCost) {
                    currentMinCost = cost[i];
                    u = i;
                }
            }

            foundVertexList.add(u);
            //修正当前最短路径和距离
            for(Edge e : neighbors.get(u)) {
                double weight = cost[u] + e.weight;
                if(!foundVertexList.contains(e.end) && cost[e.end] > weight) {
                    //修改路径长度
                    cost[e.end] = weight;
                    //添加到路径
                    pathMatrix[e.end] = u;
                }
            }
        }
        return cost;
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

    public TestDijkstra(V[] vertices, int[][] edges) {
        createWeightedGraph(Arrays.asList(vertices), edges);
        pathMatrix = new int[vertices.length];
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

        TestDijkstra<String> dijkstra = new TestDijkstra<>(vertices, edges);
        double[] cost = dijkstra.dijkstra(0);
        for (int i = 1; i < cost.length; i++) {
            System.out.print(vertices[0] + "到" + vertices[i] + "的距离为：" + cost[i] + ", ");
            System.out.print("路径为: " + vertices[i]);
            int k = dijkstra.pathMatrix[i];
            while(k != -1) {
                System.out.print("<-" + vertices[k]);
                k = dijkstra.pathMatrix[k];
            }
            System.out.println();
        }
    }
}
