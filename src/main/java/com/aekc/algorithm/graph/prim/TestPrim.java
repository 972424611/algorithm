package com.aekc.algorithm.graph.prim;

public class TestPrim {

    public static void main(String[] args) {
        String[] vertices = {"北京", "上海", "广州", "杭州", "长沙", "武汉", "南京"};

        System.out.println("----------邻接表----------");
        int[][] edges = {
            {0, 1, 7807}, {0, 3, 1331}, {0, 5, 2097},
            {1, 0, 7807}, {1, 2, 381}, {1, 3, 1267},
            {2, 1, 381}, {2, 3, 1015}, {2, 4, 1663},
            {3, 0, 1331}, {3, 1, 1267}, {3, 2, 1015}, {3, 4, 599}, {3, 5, 1003},
            {4, 2, 1663}, {4, 3, 599}, {4, 5, 533},
            {5, 0, 2097}, {5, 3, 1003}, {5, 4, 533}, {5, 6, 983},
            {6, 5, 983}
        };

        Adjacency<String> adjacencyList = new AdjacencyList<>(vertices, edges);
        adjacencyList.prim(1);
        adjacencyList.print();

        System.out.println("----------邻接矩阵----------");
        int[][] matrix = {
                {0, 7807, 0, 1331, 0, 2097, 0},      //北京
                {7870, 0, 381, 1267, 0, 0, 0},       //上海
                {0, 381, 0, 1015, 1663, 0, 0},       //广州
                {1331, 1267, 1015, 0, 599, 1003, 0}, //杭州
                {0, 0, 1663, 599, 0, 533, 0},        //长沙
                {2097, 0, 0, 1003, 533, 0, 983},     //武汉
                {0, 0, 0, 0, 0, 983, 0}              //南京
        };
        Adjacency<String> adjacencyMatrix = new AdjacencyMatrix<>(vertices, matrix);
        adjacencyMatrix.prim(1);
        adjacencyList.print();
    }
}
