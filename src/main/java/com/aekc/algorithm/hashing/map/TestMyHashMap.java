package com.aekc.algorithm.hashing.map;

public class TestMyHashMap {

    public static void main(String[] args) {
        MyMap<String, Integer> map = new MyHashMap<>();
        map.put("张山", 33);
        map.put("李四", 22);
        map.put("王五", 23);
        map.put("赵六", 45);

        System.out.println(map);
        System.out.println(map.get("李四"));
        map.remove("王五");
        System.out.println(map);
        map.clear();
        System.out.println(map);
    }
}
