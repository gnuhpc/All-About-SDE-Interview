package org.gnuhpc.bigdata.datastructure.set.unionfindset;

public class QuickUnionTest {
    public static void main(String[] args) {
        System.out.println("QuickUnion Program");
        IQuickUnion qu = new QuickFind(10);
        qu.union(1, 9);
    }
}
