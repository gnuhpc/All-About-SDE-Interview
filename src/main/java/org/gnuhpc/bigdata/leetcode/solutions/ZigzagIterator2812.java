package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.Arrays;
import java.util.List;

/**
 * Copyright gnuhpc 2019/10/6
 */
public class ZigzagIterator2812 {
    List<Integer>[] list;
    int             num = 0;
    int             x   = 0;
    int             y   = 0;

    int count = 0;

    public ZigzagIterator2812(List<Integer> v1, List<Integer> v2) {
        list = new List[2];
        list[0] = v1;
        list[1] = v2;

        num = list.length;

        for (List<Integer> l : list) {
            count += l.size();
        }
    }

    public int next() {
        int res = -1;
        while (res == -1 && hasNext()) {
            if (x >= list[y].size()) {
                if (y + 1 == num) {
                    x++;
                }
                y = (y + 1) % num;
            }
            else {
                res = list[y].get(x);
                if (y + 1 == num) {
                    x++;
                }
                y = (y + 1) % num;
            }
        }

        if (res != -1) count--;
        return res;
    }

    public boolean hasNext() {
        return count > 0;
    }

    public static void main(String[] args) {
        ZigzagIterator2812 iterator = new ZigzagIterator2812(
                Arrays.asList(1, 2),
                Arrays.asList()
        );

        while (iterator.hasNext()) {
            System.out.println(iterator.next());
        }
    }
}
