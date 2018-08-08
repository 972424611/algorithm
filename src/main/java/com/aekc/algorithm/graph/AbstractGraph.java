package com.aekc.algorithm.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public abstract class AbstractGraph<V> implements Graph<V> {

    /** 存储顶点 */
    protected List<V> vertices = new ArrayList<>();

    /** 邻接表 */
    protected List<List<Edge>> neighbors = new ArrayList<>();

    protected AbstractGraph() {}

    protected AbstractGraph(V[] vertices, int[][] edges) {
        for(int i = 0; i < vertices.length; i++) {
            addVertex(vertices[i]);
        }
        createAdjacencyLists(edges, vertices.length);
    }

    protected AbstractGraph(List<V> vertices, List<Edge> edges) {
        for(int i = 0; i < vertices.size(); i++) {
            addVertex(vertices.get(i));
        }
        createAdjacencyLists(edges, vertices.size());
    }

    protected AbstractGraph(List<Edge> edges, int numberOfVertices) {
        for(int i = 0; i < numberOfVertices; i++) {
            addVertex((V) new Integer(i));
        }
        createAdjacencyLists(edges, numberOfVertices);
    }

    protected AbstractGraph(int[][] edges, int numberOfVertices) {
        for(int i = 0; i < numberOfVertices; i++) {
            addVertex((V) new Integer(i));
        }
        createAdjacencyLists(edges, numberOfVertices);
    }

    public static class Edge {

        /** 起始顶点 */
        public int u;

        /** 结束顶点 */
        public int v;

        public Edge(int u, int v) {
            this.u = u;
            this.v = v;
        }

        @Override
        public boolean equals(Object o) {
            return u == ((Edge) o).u && v == ((Edge) o).v;
        }
    }

    /**
     * 为每个顶点创建邻接表
     * @param edges
     * @param numberOfVertices
     */
    private void createAdjacencyLists(int[][] edges, int numberOfVertices) {
        for (int[] edge : edges) {
            addEdge(edge[0], edge[1]);
        }
    }

    /**
     * 为每个顶点创建邻接表
     * @param edges
     * @param numberOfVertices
     */
    private void createAdjacencyLists(List<Edge> edges, int numberOfVertices) {
        for(Edge edge : edges) {
            addEdge(edge.u, edge.v);
        }
    }

    @Override
    public int getSize() {
        return vertices.size();
    }

    @Override
    public List<V> getVertices() {
        return vertices;
    }

    @Override
    public V getVertex(int index) {
        return vertices.get(index);
    }

    @Override
    public int getIndex(V v) {
        return vertices.indexOf(v);
    }

    @Override
    public List<Integer> getNeighbors(int index) {
        List<Integer> result = new ArrayList<>();
        for(Edge e : neighbors.get(index)) {
            result.add(e.v);
        }
        return result;
    }

    @Override
    public int getDegree(int v) {
        return neighbors.get(v).size();
    }

    @Override
    public void printEdges() {
        for(int u = 0; u < neighbors.size(); u++) {
            System.out.print(getVertex(u) + " (" + u + "): ");
            for(Edge e : neighbors.get(u)) {
                System.out.print("(" + e.u + ", " + e.v + ") ");
            }
            System.out.println();
        }
    }

    @Override
    public void clear() {
        vertices.clear();
        neighbors.clear();
    }

    @Override
    public void addVertex(V vertex) {
        if(!vertices.contains(vertex)) {
            vertices.add(vertex);
            neighbors.add(new ArrayList<>());
        }
    }

    protected boolean addEdge(Edge e) {
        if(e.u < 0 || e.u > getSize() - 1) {
            throw new IllegalArgumentException("No such index: " + e.u);
        }
        if(e.v < 0 || e.v > getSize() - 1) {
            throw new IllegalArgumentException("No such index: " + e.v);
        }
        if(!neighbors.get(e.u).contains(e)) {
            neighbors.get(e.u).add(e);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void addEdge(int u, int v) {
        addEdge(new Edge(u, v));
    }

    @Override
    public AbstractGraph<V>.Tree dfs(int v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for(int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        boolean[] isVisited = new boolean[vertices.size()];
        dfs(v, parent, searchOrder, isVisited);
        return new Tree(v, parent, searchOrder);
    }

    private void dfs(int u, int[] parent, List<Integer> searchOrder, boolean[] isVisited) {
        searchOrder.add(u);
        isVisited[u] = true;
        for(Edge e : neighbors.get(u)) {
            if(!isVisited[e.v]) {
                parent[e.v] = u;
                dfs(e.v, parent, searchOrder, isVisited);
            }
        }
    }

    @Override
    public AbstractGraph<V>.Tree bfs(int v) {
        List<Integer> searchOrder = new ArrayList<>();
        int[] parent = new int[vertices.size()];
        for(int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        boolean[] isVisited = new boolean[vertices.size()];
        queue.offer(v);
        isVisited[v] = true;
        while(!queue.isEmpty()) {
            int u = queue.poll();
            searchOrder.add(u);
            for(Edge e : neighbors.get(u)) {
                if(!isVisited[e.v]) {
                    queue.offer(e.v);
                    parent[e.v] = u;
                    isVisited[e.v] = true;
                }
            }
        }
        return new Tree(v, parent, searchOrder);
    }

    public class Tree {

        private int root;

        /** 存储每个顶点的父节点 */
        private int[] parent;

        private List<Integer> searchOrder;

        public Tree(int root, int[] parent, List<Integer> searchOrder) {
            this.root = root;
            this.parent = parent;
            this.searchOrder = searchOrder;
        }

        public int getRoot() {
            return root;
        }

        public int getParent(int v) {
            return parent[v];
        }

        /** 返回表示搜索顺序的数组 */
        public List<Integer> getSearchOrder() {
            return searchOrder;
        }

        /** 返回查找到的顶点的数目 */
        public int getNumberOfVerticesFound() {
            return searchOrder.size();
        }

        /** 返回顶点从顶点到根的路径 */
        public List<V> getPath(int index) {
            ArrayList<V> path = new ArrayList<>();
            do {
                path.add(vertices.get(index));
                index = parent[index];
            } while(index != -1);
            return path;
        }

        public void printPath(int index) {
            List<V> path = getPath(index);
            System.out.print("A path form " + vertices.get(root) + " to " + vertices.get(index) + ": ");
            for(int i = path.size() - 1; i >= 0; i--) {
                System.out.print(path.get(i) + ", ");
            }
        }

        public void printTree() {
            System.out.println("Root is: " + vertices.get(root));
            System.out.print("Edges: ");
            for(int i = 0; i < parent.length; i++) {
                if(parent[i] != -1) {
                    System.out.print("(" + vertices.get(parent[i]) + ", " + vertices.get(i) + ") ");
                }
            }
            System.out.println();
        }
    }
}
