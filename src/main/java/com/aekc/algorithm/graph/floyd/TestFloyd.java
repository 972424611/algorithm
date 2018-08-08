package com.aekc.algorithm.graph.floyd;

public class TestFloyd<V> {

    /** 点到点的路径 */
    private int[][] pathMatrix;

    /** 点与点之间的权值 */
    private int[][] shortPathTable;

    private int numVertexes;

    private static final int MAX_VALUE = Integer.MAX_VALUE;

    public void floyd(int[][] matrix) {
        for(int i = 0; i < numVertexes; i++) {
            for(int j = 0; j < numVertexes; j++) {
                shortPathTable[i][j] = matrix[i][j];
                pathMatrix[i][j] = j;
            }
        }

        //i代表中转顶点，j代表起始顶点，k代表结束顶点
        for(int i = 0; i < numVertexes; i++) {
            for(int j = 0; j < numVertexes; j++) {
                for(int k = 0; k < numVertexes; k++) {
                    if(shortPathTable[j][i] != MAX_VALUE && shortPathTable[i][k] != MAX_VALUE) {
                        int weight = shortPathTable[j][i] + shortPathTable[i][k];
                        if(shortPathTable[j][k] > weight) {
                            //如果经过下标为i顶点路径比原两点路径更短，将当前权值设为更小的一个
                            shortPathTable[j][k] = weight;
                            //路径设置经过下标为i的顶点
                            pathMatrix[j][k] = pathMatrix[j][i];
                        }
                    }
                }
            }
        }
    }

    public TestFloyd(int length) {
        pathMatrix = new int[length][length];
        shortPathTable = new int[length][length];
        numVertexes = length;
    }

    public static void main(String[] args) {
        String[] vertices = {"北京", "上海", "广州", "杭州", "长沙", "武汉", "南京"};

        int[][] matrix = {
                {0, 7807, MAX_VALUE, 1331, MAX_VALUE, 2097, MAX_VALUE},             //北京
                {7870, 0, 381, 1267, MAX_VALUE, MAX_VALUE, MAX_VALUE},              //上海
                {MAX_VALUE, 381, 0, 1015, 1663, MAX_VALUE, MAX_VALUE},              //广州
                {1331, 1267, 1015, 0, 599, 1003, MAX_VALUE},                        //杭州
                {MAX_VALUE, MAX_VALUE, 1663, 599, 0, 533, MAX_VALUE},               //长沙
                {2097, MAX_VALUE, MAX_VALUE, 1003, 533, 0, 983},                    //武汉
                {MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, MAX_VALUE, 983, 0}     //南京
        };

        TestFloyd<String> floyd = new TestFloyd<>(vertices.length);
        floyd.floyd(matrix);
        for(int i = 0; i < floyd.numVertexes; i++) {
            for(int j = i + 1; j < floyd.numVertexes; j++) {
                if ("北京".equals(vertices[i])) {
                    System.out.print(vertices[i] + "到" + vertices[j] + "的距离为：" + floyd.shortPathTable[i][j] + ", ");
                    int k = floyd.pathMatrix[i][j];
                    System.out.print("路径为：" + vertices[i]);
                    while (k != j) {
                        System.out.print("->" + vertices[k]);
                        k = floyd.pathMatrix[k][j];
                    }
                    System.out.println("->" + vertices[j]);
                }
            }
        }
    }
}