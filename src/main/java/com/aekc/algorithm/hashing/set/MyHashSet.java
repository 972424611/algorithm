package com.aekc.algorithm.hashing.set;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

public class MyHashSet<E> implements MySet<E> {

    /** 定义默认哈希表大小，必须是2的幂 */
    private static int DEFAULT_INITIAL_CAPACITY = 4;

    /** 定义最大哈希表大小 1<<30 ~ 2^30 */
    private static int MAXIMUM_CAPACITY = 1 << 30;

    /** 当前哈希表的容量，大小为2的倍数 */
    private int capacity;

    /** 定义默认负载因子 */
    private static float DEFAULT_MAX_LOAD_FACTOR = 0.75f;

    /** 指定哈希表中使用的负载因子 */
    private float loadFactorThreshold;

    /** 表中的条目数 */
    private int size = 0;

    /** 具有默认容量和负载因子的链表 */
    private LinkedList<E>[] table;

    public MyHashSet() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashSet(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashSet(int initialCapacity, float loadFactorThreshold) {
        if(initialCapacity > MAXIMUM_CAPACITY) {
            this.capacity = MAXIMUM_CAPACITY;
        } else {
            this.capacity = trimToPowerOf2(initialCapacity);
        }
        this.loadFactorThreshold = loadFactorThreshold;
        table = new LinkedList[capacity];
    }

    @Override
    public void clear() {
        size = 0;
        removeElements();
    }

    @Override
    public boolean contains(E e) {
        int bucketIndex = hash(e.hashCode());
        if(table[bucketIndex] != null) {
            LinkedList<E> bucket = table[bucketIndex];
            for(E element : bucket) {
                if(element.equals(e)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public boolean add(E e) {
        if(contains(e)) {
            return false;
        }
        if(size + 1 > capacity * loadFactorThreshold) {
            if(capacity == MAXIMUM_CAPACITY) {
                throw new RuntimeException("Exceeding maximum capacity");
            }
            rehash();
        }
        int bucketIndex = hash(e.hashCode());
        if(table[bucketIndex] == null) {
            table[bucketIndex] = new LinkedList<E>();
        }
        table[bucketIndex].add(e);
        size++;
        return true;
    }

    @Override
    public boolean remove(E e) {
        if(!contains(e)) {
            return false;
        }
        int bucketIndex = hash(e.hashCode());
        if(table[bucketIndex] != null) {
            LinkedList<E> bucket = table[bucketIndex];
            for(E element : bucket) {
                if(e.equals(element)) {
                    bucket.remove(element);
                    break;
                }
            }
        }
        size--;
        return true;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    public Iterator<E> iterator() {
        return new MyHashSetIterator(this);
    }

    @Override
    public boolean hasNext() {
        return false;
    }

    @Override
    public E next() {
        return null;
    }

    private class MyHashSetIterator implements Iterator<E> {

        private ArrayList<E> list;

        private int current = 0;

        private MyHashSet<E> set;

        public MyHashSetIterator(MyHashSet<E> set) {
            this.set = set;
            this.list = setToList();
        }

        @Override
        public boolean hasNext() {
            if(current < list.size()) {
                return true;
            }
            return false;
        }

        @Override
        public E next() {
            return list.get(current++);
        }

        @Override
        public void remove() {
            set.remove(list.get(current));
            list.remove(current);
        }
    }

    //------------Hash function------------

    private int hash(int hashCode) {
        //supplementalHash(hashCode) % capacity
        return supplementalHash(hashCode) & (capacity - 1);
    }

    /** 确保散列分布均匀 */
    private static int supplementalHash(int h) {
        h ^= (h >>> 20) ^ (h >>> 12);
        return h ^ (h >>> 7) ^ (h >>> 4);
    }

    /** 为初始容量返回2的倍数 */
    private int trimToPowerOf2(int initialCapacity) {
        int capacity = 1;
        while(capacity < initialCapacity) {
            capacity <<= 1;
        }
        return capacity;
    }

    private void removeElements() {
        for(int i = 0; i < capacity; i++) {
            if(table[i] != null) {
                table[i].clear();
            }
        }
    }

    private void rehash() {
        ArrayList<E> list = setToList();
        capacity <<= 1;
        table = new LinkedList[capacity];
        size = 0;
        for(E element : list) {
            add(element);
        }
    }

    private ArrayList<E> setToList() {
        ArrayList<E> list = new ArrayList<>();
        for(int i = 0; i < capacity; i++) {
            if(table[i] != null) {
                for(E e : table[i]) {
                    list.add(e);
                }
            }
        }
        return list;
    }

    @Override
    public String toString() {
        ArrayList<E> list = setToList();
        StringBuilder builder = new StringBuilder("[");
        for(int i = 0; i < list.size() - 1; i++) {
            builder.append(list.get(i) + ", ");
        }

        if(list.size() == 0) {
            builder.append("]");
        } else {
            builder.append(list.get(list.size() - 1) + "]");
        }
        return builder.toString();
    }
}
