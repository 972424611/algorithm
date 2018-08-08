package com.aekc.algorithm;

import java.util.ArrayList;
import java.util.List;

/**
 * 八皇后问题
 */
public class EightQueens {

    private static List<int[][]> list = new ArrayList<int[][]>();

    private static int[][] checkerboard = new int[8][8];

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
        for(int j = 0; j < 8; j++) {
            checkerboard[m][j]--;
            checkerboard[j][i]--;
        }
        for(int x = i, y = m; x >=0 && y < 8; x--, y++) {
            checkerboard[y][x]--;
        }
        for(int x = i, y = m; x < 8 && y < 8; x++, y++) {
            checkerboard[y][x]--;
        }
        for(int x = i, y = m; x < 8 && y >= 0; x++, y--) {
            checkerboard[y][x]--;
        }
        for(int x = i, y = m; x >= 0 && y >= 0; x--, y--) {
            checkerboard[y][x]--;
        }
        checkerboard[m][i] = 0;
    }

    public boolean search(int m) {
        for(int i = 0; i < 8; i++) {
            if(checkerboard[m][i] == 0) {

                //在二位数组中把攻击范围和皇后位置加上标识
                for(int j = 0; j < 8; j++) {
                    judge(m, j);
                    judge(j, i);
                }
                for(int x = i, y = m; x >=0 && y < 8; x--, y++) {
                    judge(y, x);
                }
                for(int x = i, y = m; x < 8 && y < 8; x++, y++) {
                    judge(y, x);
                }
                for(int x = i, y = m; x < 8 && y >= 0; x++, y--) {
                    judge(y, x);
                }
                for(int x = i, y = m; x >= 0 && y >= 0; x--, y--) {
                    judge(y, x);
                }
                checkerboard[m][i] = -1;

                //当m>=8说明一种情况完成，继续判断下一种情况
                if(++m >= 8) {
                    System.out.println("num: " + list.size() + "--------------------------");
                    for(int x = 0; x < 8; x++) {
                        for(int y = 0; y < 8; y++) {
                            if(checkerboard[x][y] < 0) {
                                System.out.print("1 ");
                            } else {
                                System.out.print("0 ");
                            }
                        }
                        System.out.println();
                    }
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
        eightQueens.search(0);
        System.out.println(list.size());

    }
}
