package com.aekc.algorithm.graph.weight;

import java.util.List;

public class TestWeightedGraph {

    public static void main(String[] args) {
        String[] vertices = {"sdf", "tttt", "ppp", "auuu", "asdf", "tttr", "gggg"};
        int[][] edges = {
                {0, 1, 7807}, {0, 3, 1331}, {0, 5, 2097},
                {1, 0, 7807}, {1, 2, 381}, {1, 3, 1267},
                {2, 1, 381}, {2, 3, 1015}, {2, 4, 1663},
                {3, 0, 1331}, {3, 1, 1267}, {3, 2, 1015}, {3, 4, 599}, {3, 5, 1003},
                {4, 2, 1663}, {4, 3, 599}, {4, 5, 533},
                {5, 0, 2097}, {5, 3, 1003}, {5, 4, 533}, {5, 6, 983},
                {6, 5, 983}
        };
        WeightedGraph<String> graph = new WeightedGraph<>(vertices, edges);
//        List<Integer> list = graph.getMinimumSpanningTree(0);
//        for(Integer i : list) {
//            System.out.println(graph.getVertex(i));
//        }
        System.out.println("The number of vertices in graph: " + graph.getSize());
        System.out.println("The vertex with index 1 is " + graph.getVertex(1));
        System.out.println("The index for tttt is " + graph.getIndex("tttt"));
        System.out.println("The edges for graph:" );
        graph.printWeightedEdges();


    }
}
