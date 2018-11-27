package com.aekc.algorithm;

/**
 * @author Twilight
 * @date 18-11-27 下午8:46
 */
public class NQueens {

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

    public NQueens(int n) {
        this.n = n;
        this.sum = 0;
        this.currentSolution = new int[n];
        backtrack(0);
    }

    public boolean isSuitablePlacement(int t) {
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
        NQueens nQueens = new NQueens(14);
        System.out.println(nQueens.sum);
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
