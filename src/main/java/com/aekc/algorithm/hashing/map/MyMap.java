package com.aekc.algorithm.hashing.map;

import java.util.Set;

public interface MyMap<K, V> {

    /**
     * 从该映射表中删除所有条目
     */
    void clear();

    /**
     * 如果该映射表包含指定键的条目，则返回true
     * @param key
     * @return
     */
    boolean containsKey(K key);

    /**
     * 如果该映射表将一个或者多个键映射到指定的值，则返回true
     * @param value
     * @return
     */
    boolean containsValue(V value);

    /**
     * 返回一个包含该映射表中所有条目的集合
     * @return
     */
    Set<Entry<K, V>> entrySet();

    /**
     * 返回映射表中指定键的对应值
     * @param key
     * @return
     */
    V get(K key);

    /**
     * 如果该映射表不包含任何映射，则返回true
     * @return
     */
    boolean isEmpty();

    /**
     * 返回该映射表中所有键的集合
     * @return
     */
    Set<K> keySet();

    /**
     * 将一个映射置于该映射表中
     * @param key
     * @param value
     * @return
     */
    V put(K key, V value);

    /**
     * 删除指定键的条目
     * @param key
     */
    void remove(K key);

    /**
     * 返回该映射表中的映射数目
     * @return
     */
    int size();

    /**
     * 返回一个包含该映射表中的集合
     * @return
     */
    Set<V> values();

    /** 该类会自动标识为静态类 */
    class Entry<K, V> {
        K key;
        V value;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public String toString() {
            return "[" + key + ", " + value + "]";
        }
    }
}
