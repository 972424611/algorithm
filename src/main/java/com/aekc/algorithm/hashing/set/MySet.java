package com.aekc.algorithm.hashing.set;

import java.util.Iterator;

public interface MySet<E> extends Iterator<E> {

    void clear();

    boolean contains(E e);

    boolean add(E e);

    boolean remove(E e);

    boolean isEmpty();

    int size();
}
