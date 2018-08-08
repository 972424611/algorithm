package com.aekc.algorithm;

import java.util.Scanner;

/**
 * 求n以内的所有素数
 */
public class PrimeNumber {

    /**
     * 穷举法，检测所有可能的因子。
     * 时间复杂度为：O(n^2)
     */
    public void primeNumber1(int n) {
        int number = 2;
        int count = 0;
        while(number <= n) {
            boolean isPrime = true;
            for(int divisor = 2; divisor <= number / 2; divisor++) {
                if(number % divisor == 0) {
                    isPrime = false;
                    break;
                }
            }
            if(isPrime) {
                count++;
            }
            number++;
        }
        System.out.println("\ncount: " + count);
    }

    /**
     * 检测直到√n的因子
     * 时间复杂度为：O(n√n)
     */
    public void primeNumber2(int n) {
        int number = 2;
        int count = 0;
        while(number <= n) {
            boolean isPrime = true;
            int squareRoot = (int) Math.sqrt(number);
            for(int divisor = 2; divisor <= squareRoot; divisor++) {
                if(number % divisor == 0) {
                    isPrime = false;
                    break;
                }
            }
            if(isPrime) {
                count++;
            }
            number++;
        }
        System.out.println("\ncount: " + count);
    }

    /**
     * 检测直到√n的素数因子
     * 时间复杂度为：O(n√n / logn)
     */
    public void primeNumber3(int n) {
        int number = 2;
        int count = 0;
        int squareRoot = 1;
        int[] primes = new int[n];
        while(number <= n) {
            boolean isPrime = true;
            if(squareRoot * squareRoot < number) {
                squareRoot++;
            }
            for(int k = 0; k < count && primes[k] <= squareRoot; k++) {
                if(number % primes[k] == 0) {
                    isPrime = false;
                    break;
                }
            }
            if(isPrime) {
                primes[count] = number;
                count++;
            }
            number++;
        }
        System.out.println("\ncount: " + count);
    }

    /**
     * 埃拉托色尼筛选算法
     * 时间复杂度为：O(n√n / logn)
     */
    public void primeNumber4(int n) {
        boolean[] primes = new boolean[n + 1];
        for(int i = 0; i < primes.length; i++) {
            primes[i] = true;
        }
        for(int k = 2; k <= n / k; k++) {
            if(primes[k]) {
                for(int i = k; i <= n / k; i++) {
                    primes[k * i] = false;
                }
            }
        }
        int count = 0;
        for(int i = 2; i < primes.length; i++) {
            if(primes[i]) {
                count++;
            }
        }
        System.out.println("\ncount :" + count);
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Find all prime numbers <= n，enter n：");
        int n = input.nextInt();

        long start = 0;
        long end = 0;

        start = System.currentTimeMillis();
        new PrimeNumber().primeNumber1(n);
        end = System.currentTimeMillis();

        System.out.printf("primeNumber1 所用时间为(单位毫秒)：%d\n", end - start);

        start = System.currentTimeMillis();
        new PrimeNumber().primeNumber2(n);
        end = System.currentTimeMillis();

        System.out.printf("primeNumber2 所用时间为(单位毫秒)：%d\n", end - start);

        start = System.currentTimeMillis();
        new PrimeNumber().primeNumber3(n);
        end = System.currentTimeMillis();

        System.out.printf("primeNumber3 所用时间为(单位毫秒)：%d\n", end - start);

        start = System.currentTimeMillis();
        new PrimeNumber().primeNumber4(n);
        end = System.currentTimeMillis();

        System.out.printf("primeNumber4 所用时间为(单位毫秒)：%d\n", end - start);
    }
}
