package com.aekc.algorithm;

/**
 * @author Twilight
 * @date 2019-02-20 11:34
 */
public class OrderedSearch {

    private int[] array = {0, 1, 16, 24, 35, 47, 59, 62, 73, 88, 99};

    /**
     * 折半查找（二分查找）
     * 时间效率O(logn)
     */
    public int binarySearch(int key) {
        int high = array.length - 1;
        int low = 0;
        int mid;
        while(low <= high) {
            mid = (high + low) / 2;
            if(array[mid] > key) {
                high = mid - 1;
            } else if(array[mid] < key) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }

    /**
     * 插值查找
     * 时间效率O(logn)
     */
    public int interpolationSearch(int key) {
        int high = array.length - 1;
        int low = 0;
        int mid;
        while(low <= high) {
            mid = low + (high - low) * (key - array[low]) / (array[high] - array[low]);
            if(array[mid] > key) {
                high = mid - 1;
            } else if(array[mid] < key) {
                low = mid + 1;
            } else {
                return mid;
            }
        }
        return -1;
    }



    private int[] fibonacciArray = {0, 1, 1, 2, 3, 5, 8, 13, 21, 34};

    /**
     * 斐波那契查找
     */
    public int fibonacciSearch(int key) {
        int high = array.length - 1;
        int low = 0;
        int k = 0;
        while(high > fibonacciArray[k] - 1) {
            k++;
        }
        int[] newArray = new int[fibonacciArray[k] - 1];
        for(int i = 0; i < fibonacciArray[k] - 1; i++) {
            if(i >= high) {
                newArray[i] = array[high];
            } else {
                newArray[i] = array[i];
            }
        }

        while(low <= high) {
            int mid = low + fibonacciArray[k - 1] - 1;
            if(key < newArray[mid]) {
                high = mid - 1;
                k = k - 1;
            } else if(key > newArray[mid]) {
                low = mid + 1;
                k = k - 2;
            } else {
                if(mid <= array.length - 1) {
                    return mid;
                } else {
                    return array.length - 1;
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        OrderedSearch orderedSearch = new OrderedSearch();
        System.out.println(orderedSearch.binarySearch(35));
        System.out.println(orderedSearch.interpolationSearch(35));
        System.out.println(orderedSearch.fibonacciSearch(0));
    }
}
