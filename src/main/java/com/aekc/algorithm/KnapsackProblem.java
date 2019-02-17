package com.aekc.algorithm;

/**
 * @author Twilight
 * @date 18-10-22 下午5:51
 */
public class KnapsackProblem {

    private int capacity;

    private int[] value;

    private int[] weight;

    private int[][] eachWeight;

    public void backpack(int q, int p, int currentWeight) {

        if(p <= value[q] && currentWeight - weight[q] >= 0) {
            backpack(q, p + 1, currentWeight - weight[q]);
        }
        if(q < value.length - 1 && currentWeight - weight[q + 1] >= 0) {
            backpack(q + 1, 1, currentWeight - weight[q + 1]);
        }
        if(capacity - currentWeight > eachWeight[q][currentWeight]) {
            eachWeight[q][currentWeight] = capacity - currentWeight;
        }
    }

    public static void main(String[] args) {
        KnapsackProblem knapsackProblem = new KnapsackProblem();
        knapsackProblem.value = new int[] {6, 3, 5, 4, 6};
        knapsackProblem.weight = new int[] {2, 2, 6, 5, 4};
        knapsackProblem.capacity = 10;
        knapsackProblem.eachWeight = new int[knapsackProblem.value.length][knapsackProblem.capacity];
        knapsackProblem.backpack(0, 0, knapsackProblem.capacity);
        System.out.println(knapsackProblem.eachWeight[knapsackProblem.value.length - 1][knapsackProblem.capacity - 1]);
    }
}
