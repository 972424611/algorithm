package com.aekc.algorithm;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

/**
 * 排序算法
 */
public class Sort {

    /**
     * 冒泡排序
     * 时间复杂度：O(n^2)
     */
    public int[] bubbleSort(int[] array) {
        //如果某一次遍历没有交换，那就不必进行下一次遍历，因为所有元素都排序好了。
        boolean flag = true;
        for(int i = 1; i < array.length && flag; i++) {
            System.out.println(i);
            flag = false;
            for(int j = 0; j < array.length - i; j++) {
                if(array[j] > array[j + 1]) {
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    flag = true;
                }
            }
        }
        return array;
    }

    /**
     * 插入排序
     * 时间复杂度：O(n^2)
     */
    public int[] insertionSort(int[] array) {
        for(int i = 1; i < array.length; i++) {
            int currentElement = array[i];
            int j;
            for(j = i - 1; j >=0 && array[j] > currentElement; j--) {
                array[j + 1] = array[j];
            }
            array[j + 1] = currentElement;
        }
        return array;
    }

    /**
     * 选择排序
     * 时间复杂度：O(n^2)
     */
    public int[] selectionSort(int[] array) {
        for(int i = array.length; i > 1; i--) {
            int max = 0;
            for(int j = 1; j < i; j++) {
                if(array[j] > array[max]) {
                    max = j;
                }
                if(max != i - 1) {
                    int temp = array[max];
                    array[max] = array[i - 1];
                    array[i -1] = temp;
                }
            }
        }
        return array;
    }

    /**
     * 归并排序
     * 时间复杂度：O(nlogn)
     * java.util.Arrays类中sort方法是使用归并排序算法的变体来实现的。
     */
    public int[] mergeSort(int[] array) {
        if(array.length > 1) {
            int[] firstHalf = new int[array.length / 2];
            System.arraycopy(array, 0, firstHalf, 0, array.length / 2);
            mergeSort(firstHalf);

            int secondHalfLength = array.length - array.length / 2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(array, array.length / 2, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);

            merge(firstHalf, secondHalf, array);
        }
        return array;
    }

    private void merge(int[] array1, int[] array2, int[] temp) {
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;
        while(current1 < array1.length && current2 < array2.length) {
            if(array1[current1] < array2[current2]) {
                temp[current3++] = array1[current1++];
            } else {
                temp[current3++] = array2[current2++];
            }

            while(current1 < array1.length) {
                temp[current3++] = array1[current1++];
            }
            while(current2 < array2.length) {
                temp[current3++] = array2[current2++];
            }
        }
    }

    /**
     * 快速排序
     * 时间复杂度：平均为O(nlogn)
     * 在最差的情况下，归并排序的效率高于快速排序。
     * 但是快速排序的空间效率高于归并排序。
     */
    public int[] quickSort(int[] array) {
        quickSort(array, 0, array.length - 1);
        return array;
    }

    private void quickSort(int[] array, int first, int last) {
        if(last > first) {
            int pivotIndex = partition(array, first, last);
            quickSort(array, first, pivotIndex - 1);
            quickSort(array, pivotIndex + 1, last);
        }
    }

    private int partition(int[] array, int first, int last) {
        int pivot = array[first];
        int low = first + 1;
        int high = last;
        while(high > low) {
            while(low <= high && array[low] <= pivot) {
                low++;
            }
            while(low <= high && array[high] > pivot) {
                high--;
            }
            if(high > low) {
                int temp = array[high];
                array[high] = array[low];
                array[low] = temp;
            }
        }
        while(high > first && array[high] >= pivot) {
            high--;
        }
        if(pivot > array[high]) {
            array[first] = array[high];
            array[high] = pivot;
            return high;
        } else {
            return first;
        }
    }

    /**
     * 桶排序。
     * 局限于整数，因为它是把待排序的数字作为数组下表
     */
    public int[] bucketSort(int[] array) {
        int[] bucket = new int[array.length];
        for(int anArray : array) {
            bucket[anArray]++;
        }
        array = new int[array.length];
        int k = 0;
        for(int i = 0; i < bucket.length; i++) {
            if(bucket[i] != 0) {
                for(int j = 0; j < bucket[i]; j++) {
                    array[k++] = i;
                }
            }
        }
        return array;
    }

    /**
     * 堆排序
     * 时间复杂度为：O(nlogn)
     */
    public int[] heapSort(int[] array) {
        for(int i = 0; i < array.length; i++) {
            add(array[i]);
        }
        for(int i = array.length - 1; i >= 0; i--) {
            array[i] = remove();
        }
        return array;
    }

    private List<Integer> list = new ArrayList<>();

    private void add(Integer number) {
        list.add(number);
        int currentIndex = list.size() - 1;
        while(currentIndex > 0) {
            int parentIndex = (currentIndex - 1) / 2;
            if(list.get(currentIndex) > list.get(parentIndex)) {
                Integer temp = list.get(currentIndex);
                list.set(currentIndex, list.get(parentIndex));
                list.set(parentIndex, temp);
            } else {
                break;
            }
        }
    }

    private Integer remove() {
        if(list.size() == 0) {
            return null;
        }

        Integer removedObject = list.get(0);
        list.set(0, list.get(list.size() - 1));
        list.remove(list.size() - 1);

        int currentIndex = 0;
        while(currentIndex < list.size()) {
            int leftChildIndex = 2 * currentIndex + 1;
            int rightChildIndex = 2 * currentIndex + 2;
            if(leftChildIndex >= list.size()) {
                break;
            }
            int maxIndex = leftChildIndex;
            if(rightChildIndex < list.size()) {
                if(list.get(maxIndex) < list.get(rightChildIndex)) {
                    maxIndex = rightChildIndex;
                }
            }

            if(list.get(currentIndex) < list.get(maxIndex)) {
                Integer temp = list.get(maxIndex);
                list.set(maxIndex, list.get(currentIndex));
                list.set(currentIndex, temp);
                currentIndex = maxIndex;
            } else {
                break;
            }
        }
        return removedObject;
    }

    private void print(String methodName, int[] array) {
        long start;
        long end;
        int[] temp = new int[array.length];
        System.arraycopy(array, 0, temp, 0, array.length);
        Method method;
        try {
            method = this.getClass().getDeclaredMethod(methodName, int[].class);
            start = System.currentTimeMillis();
            method.invoke(this, (Object) temp);
            end = System.currentTimeMillis();
            System.out.printf(methodName + "(时间毫秒)：%d\n", end - start);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int max = 100000000;
        Sort sort = new Sort();
        Random random = new Random();
        int[] temp = new int[max];
        for(int i = 0; i < temp.length; i++) {
            temp[i] = random.nextInt(max);
        }
        //System.out.println(Arrays.toString(temp));

        //sort.print("bubbleSort", temp);

        //sort.print("insertionSort", temp);

        //sort.print("selectionSort", temp);

        sort.print("mergeSort", temp);

        sort.print("quickSort", temp);

        sort.print("bucketSort", temp);

        //sort.print("heapSort", temp);
    }
}
