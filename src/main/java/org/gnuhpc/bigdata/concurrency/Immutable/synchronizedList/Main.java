package org.gnuhpc.bigdata.concurrency.immutable.synchronizedList;

import java.util.List;

import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static void main(String[] args) {
        final List<Integer> list = Collections.synchronizedList(new ArrayList<Integer>());
        new WriterThread(list).start();
        new ReaderThread(list).start();
    }
}
