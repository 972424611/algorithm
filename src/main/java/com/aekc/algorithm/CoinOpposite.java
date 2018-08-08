package com.aekc.algorithm;

import com.aekc.algorithm.graph.UnweightedGraph;

import java.util.*;

public class CoinOpposite {

    /** 一维数组保存硬币 */
    private char[] coinChar = new char[9];

    /** 二维数组保存硬币 */
    private List<char[][]> verticesCoinChar = new ArrayList<>();

    /** 存储顶点 */
    private List<String> vertices = new ArrayList<>();

    /** 邻接表 */
    private List<List<Edge>> neighbors = new ArrayList<>();

    /** 全都是反面 */
    private static final String OPPOSITE = "TTTTTTTTT";

    public class Edge {

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

    public CoinOpposite() {
        positiveAndNegative(0);
        structureNeighbors();
    }

    private String convertString(char[][] array) {
        StringBuilder stringBuilder = new StringBuilder();
        for(int i = 1; i <= 3; i++) {
            for(int j = 1; j <= 3; j++) {
                stringBuilder.append(array[i][j]);
            }
        }
        return stringBuilder.toString();
    }

    /**
     * 构建邻接表
     */
    private void structureNeighbors() {
        for(int k = 0; k < verticesCoinChar.size(); k++) {
            for(int i = 1; i <= 3; i++) {
                for(int j = 1; j <= 3; j++) {
                    char[][] temp = arrayEnlarge(verticesCoinChar.get(k));
                    temp[i][j] = temp[i][j] == 'H' ? 'T' : 'H';
                    if(temp[i-1][j] != ' ') {
                        temp[i-1][j] = temp[i-1][j] == 'H' ? 'T' : 'H';
                    }
                    if(temp[i+1][j] != ' ') {
                        temp[i+1][j] = temp[i+1][j] == 'H' ? 'T' : 'H';
                    }
                    if(temp[i][j-1] != ' ') {
                        temp[i][j-1] = temp[i][j-1] == 'H' ? 'T' : 'H';
                    }
                    if(temp[i][j+1] != ' ') {
                        temp[i][j+1] = temp[i][j+1] == 'H' ? 'T' : 'H';
                    }
                    String str = convertString(temp);
                    Edge edge = new Edge(k, vertices.indexOf(str));
                    if(!neighbors.get(edge.u).contains(edge)) {
                        neighbors.get(edge.u).add(edge);
                    }
                }
            }
        }
    }

    private char[][] oneToTwoDimensional(char[] coinChar) {
        int k = 0;
        char[][] chars = new char[3][3];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                chars[i][j] = coinChar[k++];
            }
        }
        return chars;
    }

    private char[][] arrayEnlarge(char[][] array) {
        char[][] chars = new char[5][5];
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 3; j++) {
                chars[i+1][j+1] = array[i][j];
            }
        }
        return chars;
    }

    /**
     * 生成9枚硬币正反的所有可能
     * @param n
     */
    public void positiveAndNegative(int n) {
        if(n >= 9) {
            vertices.add(String.valueOf(coinChar));
            verticesCoinChar.add(oneToTwoDimensional(coinChar));
            neighbors.add(new ArrayList<>());
            return;
        }
        for(int i = 0; i < 2; i++) {
            coinChar[n] = coinChar[n] == 'H' ? 'T' : 'H';
            int k = n + 1;
            positiveAndNegative(k);
        }
    }

    public void printEdges() {
        for(int u = 0; u < neighbors.size(); u++) {
            System.out.print(vertices.get(u) + " (" + u + "): ");
            for(Edge e : neighbors.get(u)) {
                System.out.print("(" + e.u + ", " + e.v + ") ");
            }
            System.out.println();
        }
    }

    /** 查找顺序 */
    private List<String> searchOrder = new ArrayList<>();

    /** 保存最少翻转次数的所有可能 */
    private Map<Integer, List<String[]>> map = new HashMap<>();

    /** 是否已经被查找 */
    private boolean[] isVisited;

    private int min = 512;

    public void dfs(int v) {
        isVisited = new boolean[vertices.size()];
        int sum = 0;
        dfs(v, sum);
    }

    private boolean dfs(int u, int sum) {
        //sum表示步骤，如果步骤大于最小步骤，则返回
        if(++sum > min) {
            return true;
        }
        searchOrder.add(vertices.get(u));
        isVisited[u] = true;
        for(Edge e : neighbors.get(u)) {
            //如果已经查找过该顶点
            if(isVisited[e.v]) {
                continue;
            }
            //找到全都是反面的可能，并把保存步骤
            if(OPPOSITE.equals(vertices.get(e.v))) {
                int temp = min;
                //更新最小步骤的值
                min = sum;
                String[] str = searchOrder.toArray(new String[searchOrder.size() + 1]);
                str[str.length - 1] = OPPOSITE;
                if(map.get(min) != null) {
                    map.get(min).add(str);
                } else {
                    map.remove(temp);
                    List<String[]> list = new ArrayList<>();
                    list.add(str);
                    map.put(min, list);
                }
                //该顶点对应的邻接表不需要在继续查询。
                break;
            }
            //如果为true，继续查找，如果为false跳出进行回溯
            if(!dfs(e.v, sum)) {
                break;
            }
        }
        //回溯
        if(searchOrder.size() > 0) {
            //回溯的时候需要把该标记去掉，这样后面的顶点才能访问到它
            isVisited[u] = false;
            //把不合适的顶点去除
            searchOrder.remove(searchOrder.size() - 1);
        }
        return true;
    }

    /** 保存bfs查找到的所有情况 */
    private List<List<String>> list = new ArrayList<>();

    /** 保存节点的所有父节点，不包括祖父节点等辈份更高的节点 */
    private Map<Integer, List<Integer>> parentMap = new HashMap<>();

    /**
     * bfs为什么不像dfs使用isVisited来标记已经访问节点，而是用parent数组来判断。
     * 举个例子：像这个HTTHHTHTT 可以有HHTTTHHHT或者HTHHTHHTH通过一次翻转变成它。
     * 所以用isVisited就会导致结果只有一种。即经HHTTTHHHT访问到HTTHHTHTT后，就不能继续访问了。
     * 所以用parent来替代。但是使用parent有个缺陷就是后面的父节点会把该节点原来的父节点替换。
     * 原来的父节点并还没使用在。所以需要新增加parentMap来给节点动态存储父节点。当一个使用完后
     * 就去除，并更新父节点。
     *
     * @param v
     */
    public void bfs(int v) {
        int[] parent = new int[vertices.size()];
        for(int i = 0; i < parent.length; i++) {
            parent[i] = -1;
        }
        LinkedList<Integer> queue = new LinkedList<>();
        queue.offer(v);
        boolean flag = false;
        while(!queue.isEmpty()) {
            int u = queue.poll();
            for(Edge e : neighbors.get(u)) {
                if(OPPOSITE.equals(vertices.get(e.v))) {
                    parent[e.v] = u;
                    ArrayList<String> path = new ArrayList<>();
                    v = vertices.indexOf(OPPOSITE);
                    do {
                        path.add(vertices.get(v));
                        v = parent[v];
                    } while(v != -1);
                    list.add(path);
                    //如果查找到，就没必要继续深入了，因为这就是最少步骤的方法。
                    flag = true;
                    List<Integer> list = parentMap.get(u);
                    //当前父节点已使用完毕，去除。但如果父节点只有一个就不去除。
                    if(list.size() > 1) {
                        list.remove(0);
                    }
                    //重新设置父节点
                    parent[u] = list.get(0);
                    break;
                }
                if(!flag) {
                    //保存当前顶点的父亲节点的父亲节点....依次类推，这样既可以避免重复查询，又可以保证多次查询。
                    List<Integer> list = new ArrayList<>();
                    v = u;
                    while(v != -1) {
                        list.add(v);
                        v = parent[v];
                    }
                    if(!list.contains(e.v)) {
                        queue.offer(e.v);
                        //这里做个判断，防止后面节点的改变当前节点的父类
                        if(parent[e.v] == -1) {
                            parent[e.v] = u;
                        }
                        parentMap.computeIfAbsent(e.v, k -> new ArrayList<>());
                        //添加当前节点的父节点，可能有多个。到时候需要一个一个来取值。
                        parentMap.get(e.v).add(u);
                    }
                }
            }
        }
    }

    /** 待判断的硬币 */
    private static final String DEFAULT = "HHHTHTHHH";

    public static void main(String[] args) {
        System.out.println("-----------dfs-----------");
        CoinOpposite coinOpposite = new CoinOpposite();
        coinOpposite.dfs(coinOpposite.vertices.indexOf(DEFAULT));
        List<String[]> list = coinOpposite.map.get(coinOpposite.min);
        for(String[] strs : list) {
            for(String s : strs) {
                System.out.println(s);
            }
            System.out.println();
        }

        System.out.println("-----------bfs-----------");

        CoinOpposite coinOpposite2 = new CoinOpposite();
        coinOpposite2.bfs(coinOpposite.vertices.indexOf(DEFAULT));
        for(List<String> stringList : coinOpposite2.list) {
            for(int i = stringList.size() - 1; i >= 0; i--) {
                System.out.println(stringList.get(i));
            }
            System.out.println();
        }
        //coinOpposite.printEdges();
    }
}
