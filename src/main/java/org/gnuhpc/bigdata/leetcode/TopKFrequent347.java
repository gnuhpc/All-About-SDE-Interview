package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Pair;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Copyright gnuhpc 2019-07
 */
public class TopKFrequent347 {
    class PairComparator implements Comparator<Pair<Integer>>{

        @Override
        public int compare(Pair<Integer> o1, Pair<Integer> o2) {
            return o1.y-o2.y;
        }
    }

    public List<Integer> topKFrequent(int[] nums, int k) {
        if (nums == null){
            return null;
        }

        Map<Integer,Integer> countMap = new HashMap<>();

        for (int i :nums){
            if (countMap.containsKey(i)){
                countMap.put(i,countMap.get(i)+1);
            } else {
                countMap.put(i,1);
            }
        }

        Queue<Pair<Integer>> pq = new PriorityQueue<>(new PairComparator());

        //最初的做法用最大堆（comparator的方向和目前答案相反），使用了下边这一步无脑放最大堆，这一步可以优化一下目前答案的做法
        //countMap.keySet().forEach(i-> pq.add(new Pair<>(i, countMap.get(i))));

        for (Map.Entry<Integer, Integer> entry : countMap.entrySet()) {
            if (pq.size() < k) {
                pq.offer(new Pair<>(entry.getKey(),entry.getValue()));
            } else {
                if (pq.peek().y <= entry.getValue()) {
                    pq.poll();
                    pq.offer(new Pair<>(entry.getKey(),entry.getValue()));
                }
            }
        }

        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < k; i++) {
            result.add(pq.poll().x);
        }

        return result;
    }

    @Test
    public void test(){
        int[] array = new int[]{4,1,-1,2,-1,2,3};

        System.out.println(topKFrequent(array,2));

    }
}
