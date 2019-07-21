package org.gnuhpc.bigdata.datastructure.set.unionfindset;

import org.gnuhpc.bigdata.leetcode.utils.Utils;

public class QuickUnionTest {
    public static void main(String[] args) {
        System.out.println("QuickUnion Program");
        QuickUnionAbstract qu = new QuickFind(10);
        qu.union(1, 9);

        Utils.printArray(qu.id);

    }
}
