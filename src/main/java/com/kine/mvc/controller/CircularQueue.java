package com.kine.mvc.controller;

import java.util.*;

public class CircularQueue<T> implements Queue<T> {

    private Collection<T> inner;
    private int startingIndex;
    private int size;
    private Iterator<T> localIterator;

    public CircularQueue(Collection<T> col, int first) {
        this.inner = col;
        this.startingIndex = first;
        this.size = col.size();
    }

    public CircularQueue(Collection<T> col) {
        this.inner = col;
        this.startingIndex = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return inner.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return inner.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        var list = new ArrayList<>(inner);
        return new Iterator<T>() {

            int current = startingIndex;
            int count = 0;

            @Override
            public boolean hasNext() {
                return count < size();
            }

            @Override
            public T next() {
                var ret = list.get(current);
                count++;
                current = current + 1 % size();
                return ret;
            }

        };
    }

    @Override
    public Object[] toArray() {
        return inner.toArray();
    }

    @Override
    public <R> R[] toArray(R[] a) {
        return inner.toArray(a);
    }

    @Override
    public boolean remove(Object o) {
        return inner.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return inner.containsAll(c);
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("Unimplemented method 'addAll'");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return inner.removeAll(c);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return inner.retainAll(c);
    }

    @Override
    public void clear() {
        inner.clear();
    }

    @Override
    public boolean add(T e) {
        throw new UnsupportedOperationException("Unimplemented method 'add'");
    }

    @Override
    public boolean offer(T e) {
        throw new UnsupportedOperationException("Unimplemented method 'offer'");
    }

    @Override
    public T remove() {
        if (this.localIterator == null)
            this.localIterator = iterator();
        if (localIterator.hasNext()) {
            return localIterator.next();
        }
        throw new NoSuchElementException("Circular queue is empty");
    }

    @Override
    public T poll() {
        if (this.localIterator == null)
            this.localIterator = iterator();
        if (localIterator.hasNext()) {
            return localIterator.next();
        }
        return null;

    }

    @Override
    public T element() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'element'");
    }

    @Override
    public T peek() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'peek'");
    }

}
