package com.aekc.algorithm.hashing.set;

public class TestMyHashSet {

    public static void main(String[] args) {
        MySet<String> set = new MyHashSet<>();
        set.add("张三");
        set.add("李四");
        set.add("王五");
        set.add("赵六");
        System.out.println(set);
        set.remove("李四");
        System.out.println(set);
        set.clear();
        System.out.println(set);
    }
}
