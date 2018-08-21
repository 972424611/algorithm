package com.aekc.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 八皇后问题(N皇后问题)
 */
public class EightQueens {

    private static List<int[][]> list = new ArrayList<int[][]>();

    private static int[][] checkerboard;

    /** N代表皇后个数 */
    private static int N;

    public void judge(int i, int j) {
        if(checkerboard[i][j] != 0) {
            checkerboard[i][j]++;
        } else {
            checkerboard[i][j] = 1;
        }
    }

    /**
     * 把原来该格子所在的皇后标识和攻击范围去除。
     */
    public void toBack(int i, int m) {
        for(int j = 0; j < N; j++) {
            checkerboard[m][j]--;
            checkerboard[j][i]--;
        }
        for(int x = i, y = m; x >=0 && y < N; x--, y++) {
            checkerboard[y][x]--;
        }
        for(int x = i, y = m; x < N && y < N; x++, y++) {
            checkerboard[y][x]--;
        }
        for(int x = i, y = m; x < N && y >= 0; x++, y--) {
            checkerboard[y][x]--;
        }
        for(int x = i, y = m; x >= 0 && y >= 0; x--, y--) {
            checkerboard[y][x]--;
        }
        checkerboard[m][i] = 0;
    }

    public boolean search(int m) {
        for(int i = 0; i < N; i++) {
            if(checkerboard[m][i] == 0) {

                //在二位数组中把攻击范围和皇后位置加上标识
                for(int j = 0; j < N; j++) {
                    judge(m, j);
                    judge(j, i);
                }
                for(int x = i, y = m; x >=0 && y < N; x--, y++) {
                    judge(y, x);
                }
                for(int x = i, y = m; x < N && y < N; x++, y++) {
                    judge(y, x);
                }
                for(int x = i, y = m; x < N && y >= 0; x++, y--) {
                    judge(y, x);
                }
                for(int x = i, y = m; x >= 0 && y >= 0; x--, y--) {
                    judge(y, x);
                }
                checkerboard[m][i] = -1;

                //当m>=N说明一种情况完成，继续判断下一种情况
                if(++m >= N) {
                    //输出该可能的皇后位置排列
                    /*
                    System.out.println("num: " + list.size() + "--------------------------");
                    for(int x = 0; x < N; x++) {
                        for(int y = 0; y < N; y++) {
                            if(checkerboard[x][y] < 0) {
                                System.out.print("1 ");
                            } else {
                                System.out.print("0 ");
                            }
                        }
                        System.out.println();
                    }
                    */
                    list.add(checkerboard);
                    //把当前位置的皇后标识和攻击范围去除
                    toBack(i, --m);
                    //继续下一格
                    continue;
                }

                //递归往下层走
                if(!search(m)) {
                    //回溯
                    toBack(i, --m);
                } else {
                    return true;
                }
            }
        }
        return false;
    }

    public static void main(String[] args) {
        EightQueens eightQueens = new EightQueens();
        for(int i = 1; i <= 14; i++) {
            N = i;
            checkerboard = new int[i][i];
            eightQueens.search(0);
            System.out.println(i + "皇后一共有" + list.size() + "种可能");
            list.clear();
        }

    }
}
