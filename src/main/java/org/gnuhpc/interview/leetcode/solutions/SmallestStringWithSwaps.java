package org.gnuhpc.interview.leetcode.solutions;

import com.google.inject.internal.cglib.core.$ProcessArrayCallback;
import org.gnuhpc.interview.datastructure.unionfind.UnionFind;

import java.util.*;

public class SmallestStringWithSwaps {
    public String smallestStringWithSwaps(String s, List<List<Integer>> pairs) {
        int length = s.length();
        UnionFind uf = new UnionFind(length);

        //pair中指向的两个值是可以自由交换的，所以他们是一阵营的，也就是祖宗是同一个。
        for (List<Integer> pair : pairs) {
            uf.union(pair.get(0),pair.get(1));
        }

        Map<Integer, Queue<Character>> map = new HashMap<>();
        for (int i = 0; i < length; i++) {
            //具有同一祖宗的，说明他们是一阵营的，把他们放到同一队列中
            int ancestry = uf.find(i);
            map.computeIfAbsent(ancestry, k -> new PriorityQueue<>()).offer(s.charAt(i));
        }

        //最后在进行合并
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < length; i++) {
            //找到i所在的队列，然后元素出队（这里的队列使用的是PriorityQueue，
            //其实就是个最小堆，每次出来的都是队列中最小的值）
            Queue queue = map.get(uf.find(i));
            stringBuilder.append(queue.poll());
        }
        return stringBuilder.toString();
    }
}
