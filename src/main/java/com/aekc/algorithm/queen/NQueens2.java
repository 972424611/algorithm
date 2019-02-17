package com.aekc.algorithm.queen;

/**
 * @author Twilight
 * @date 18-11-27 下午8:46
 * 回溯 & 降维(一维) & 子集树
 */
public class NQueens2 {

    /**
     * 皇后个数
     */
    private int n;

    /**
     * 当前解
     */
    private int[] currentSolution;

    /**
     * 当前已经找到可行的方案个数
     */
    private long sum;

    public NQueens2(int n) {
        this.n = n;
        this.sum = 0;
        this.currentSolution = new int[n];
        backtrack(0);
    }

    public boolean isSuitablePlacement(int t) {
        // 因为是对称的，所以第一行只需要计算一半即可
        if(t == 0) {
            if((n + 1) / 2 <= currentSolution[t]) {
                return false;
            }
        }
        for(int i = 0; i < t; i++) {
            // 判断列
            if(currentSolution[i] == currentSolution[t]) {
                return false;
            }
            // 判断斜向，这里通过斜率判断
            if(Math.abs(currentSolution[i] - currentSolution[t]) == t - i) {
                return false;
            }
        }
        return true;
    }

    public void saveResult() {
        // 这里可以打印放置情况
    }

    public void backtrack(int t) {
        if(t >= n) {
            sum++;
            saveResult();
            return;
        }

        for(int i = 0; i < n; i++) {
            currentSolution[t] = i;
            // 判断摆放是否合适，不合适就回溯
            if(isSuitablePlacement(t)) {
               backtrack(t + 1);
            }
        }
    }

    public static void main(String[] args) {
        long start = System.currentTimeMillis();
        NQueens2 nQueens2 = new NQueens2(12);
        System.out.println(nQueens2.sum * 2);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
