package org.gnuhpc.bigdata.leetcode.utils;

import java.util.ArrayList;
import java.util.List;

/*
  Copyright gnuhpc 2019/9/28
 */
public class NestedInteger {
    private List<NestedInteger> list;
    private Integer             integer;

    public NestedInteger(List list) {
        this.list = list;
    }

    public void add(NestedInteger nestedInteger) {
        if (this.list != null) {
            this.list.add(nestedInteger);
        }
        else {
            this.list = new ArrayList<>();
            this.list.add(nestedInteger);
        }
    }

    public void setInteger(int num) {
        this.integer = num;
    }

    public NestedInteger(Integer integer) {
        this.integer = integer;
    }

    public NestedInteger() {
        this.list = new ArrayList<>();
    }

    public boolean isInteger() {
        return integer != null;
    }

    public Integer getInteger() {
        return integer;
    }

    public List<NestedInteger> getList() {
        return list;
    }

    @Override
    public String toString() {

        StringBuilder string = new StringBuilder();

        if (null != integer) {
            string.append(integer);
        }
        if (null != list) {
            string.append("[");
            for (NestedInteger current : list) {
                string.append(current.toString() + ",");
            }
            if (string.length() != 1) {
                string.delete(string.length() - 1, string.length());
            }
            string.append("]");
        }
        return string.toString();
    }
}
