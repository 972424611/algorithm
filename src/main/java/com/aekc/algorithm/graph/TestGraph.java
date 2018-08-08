package com.aekc.algorithm.graph;

import java.util.ArrayList;
import java.util.Arrays;

public class TestGraph {

    public static void main(String[] args) {
        String[] vertices = {"123", "asdf", "asd", "fff", "re", "yopp", "dgf"};
        int[][] edges = {
                {0, 1}, {0, 3}, {0, 5},
                {1, 0}, {1, 2}, {1, 3},
                {2, 1}, {2, 3}, {2, 4},
                {3, 0}, {3, 1}, {3, 2}, {3, 4}, {3, 5},
                {4, 2}, {4, 3}, {4, 5},
                {5, 0}, {5, 3}, {5, 4}, {5, 6},
                {6, 5}
        };
        Graph<String> graph = new UnweightedGraph<>(vertices, edges);
        System.out.println(graph.getSize());
        System.out.println(graph.getVertex(1));
        System.out.println(graph.getIndex("asdf"));
        graph.printEdges();

        /*String[] names = {"ggg", "kkkk", "ppppp", "99999", "0000"};
        ArrayList<AbstractGraph.Edge> edgeList = new ArrayList<>();
        edgeList.add(new AbstractGraph.Edge(0, 2));
        edgeList.add(new AbstractGraph.Edge(1, 2));
        edgeList.add(new AbstractGraph.Edge(2, 4));
        edgeList.add(new AbstractGraph.Edge(3, 4));
        graph = new UnweightedGraph<>(Arrays.asList(names), edgeList);
        System.out.println(graph.getSize());
        graph.printEdges();*/

        System.out.println();
        AbstractGraph.Tree tree = graph.dfs(0);
        tree.printPath(4);
        tree.printTree();

        tree = graph.bfs(0);
        tree.printPath(3);
        tree.printTree();

    }
}
