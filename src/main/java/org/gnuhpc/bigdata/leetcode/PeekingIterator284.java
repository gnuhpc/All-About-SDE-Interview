package org.gnuhpc.bigdata.leetcode;

import java.util.Iterator;
import java.util.List;

public class PeekingIterator284 implements Iterator<Integer> {
    private Iterator<Integer> iterator;
    private Integer next;

    public PeekingIterator284(Iterator<Integer> iterator) {
        // initialize any member here.
        this.iterator = iterator;
        if (iterator.hasNext()) {
            this.next = iterator.next();
        }
    }

    // Returns the next element in the iteration without advancing the iterator.
    public Integer peek() {
        return next;
    }

    // hasNext() and next() should behave the same as in the Iterator interface.
    // Override them if needed.
    @Override
    public Integer next() {
        int res = next;
        this.next = this.iterator.hasNext() ? this.iterator.next() : null;
        return res;
    }

    @Override
    public boolean hasNext() {
        return next != null;
    }

}
