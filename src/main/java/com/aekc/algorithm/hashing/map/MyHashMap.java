package com.aekc.algorithm.hashing.map;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Set;

public class MyHashMap<K, V> implements MyMap<K, V> {

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
    private LinkedList<MyMap.Entry<K, V>>[] table;

    public MyHashMap() {
        this(DEFAULT_INITIAL_CAPACITY, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity) {
        this(initialCapacity, DEFAULT_MAX_LOAD_FACTOR);
    }

    public MyHashMap(int initialCapacity, float loadFactorThreshold) {
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
        removeEntries();
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        for(int i = 0; i < capacity; i++) {
            if(table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for(Entry<K, V> entry : bucket) {
                    if(entry.getValue().equals(value)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        Set<MyMap.Entry<K, V>> set = new HashSet<>();
        for(int i = 0; i < capacity; i++) {
            if(table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                set.addAll(bucket);
            }
        }
        return set;
    }

    @Override
    public V get(K key) {
        int bucketIndex = hash(key.hashCode());
        if(table[bucketIndex] != null) {
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            for(Entry<K, V> entry : bucket) {
                if(entry.getKey().equals(key)) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public Set<K> keySet() {
        Set<K> set = new HashSet<>();
        for(int i = 0; i < capacity; i++) {
            if(table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for(Entry<K, V> entry : bucket) {
                    set.add(entry.getKey());
                }
            }
        }
        return set;
    }

    @Override
    public V put(K key, V value) {
        if(get(key) != null) {
            int bucketIndex = hash(key.hashCode());
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            for(Entry<K, V> entry : bucket) {
                if (entry.getKey().equals(key)) {
                    V oldValue = entry.getValue();
                    entry.value = value;
                    return oldValue;
                }
            }
        }
        if(size >= capacity * loadFactorThreshold) {
            if(capacity == MAXIMUM_CAPACITY) {
                throw new RuntimeException("Exceeding maximum capacity");
            }
            rehash();
        }
        int bucketIndex = hash(key.hashCode());
        if(table[bucketIndex] == null) {
            table[bucketIndex] = new LinkedList<>();
        }
        table[bucketIndex].add(new MyMap.Entry<>(key, value));
        size++;
        return value;
    }

    @Override
    public void remove(K key) {
        int bucketIndex = hash(key.hashCode());
        if(table[bucketIndex] != null) {
            LinkedList<Entry<K, V>> bucket = table[bucketIndex];
            for(Entry<K, V> entry : bucket) {
                if(entry.getKey().equals(key)) {
                    bucket.remove(entry);
                    size--;
                    break;
                }
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Set<V> values() {
        Set<V> set = new HashSet<>();
        for(int i = 0; i < capacity; i++) {
            if(table[i] != null) {
                LinkedList<Entry<K, V>> bucket = table[i];
                for(Entry<K, V> entry : bucket) {
                    set.add(entry.getValue());
                }
            }
        }
        return set;
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

    /** 删除桶中所有条目 */
    private void removeEntries() {
        for(int i = 0; i < capacity; i++) {
            if(table[i] != null) {
                table[i].clear();
            }
        }
    }

    /** 对map进行在哈希 */
    private void rehash() {
        Set<Entry<K, V>> set = entrySet();
        capacity <<= 1;
        table = new LinkedList[capacity];
        size = 0;
        for(Entry<K, V> entry : set) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("[");
        for(int i = 0; i < capacity; i++) {
            if(table[i] != null && table[i].size() > 0) {
                for(Entry<K, V> entry : table[i]) {
                    builder.append(entry);
                }
            }
        }
        builder.append("]");
        return builder.toString();
    }
}
