package org.gnuhpc.interview.misc;

import org.junit.Test;

import java.util.*;

import static org.junit.Assert.assertEquals;

/**
 * Copyright gnuhpc 2021/2/26
 * https://1o24bbs.com/t/topic/5821
 */
public class AnalyticalDB {
    @Test
    public void test() {
        Map<String, Integer> t1_map1 = new HashMap<>();
        t1_map1.put("a", 1);
        t1_map1.put("b", 2);
        Map<String, Integer> t1_map2 = new HashMap<>();
        t1_map2.put("a", 2);

        List<Map<String, Integer>> example1 = Arrays.asList(t1_map1, t1_map2);
        assertEquals(example1.get(0), minByKey("a", example1));
        assertEquals(example1.get(1), minByKey("b", example1));

        Map<String, Integer> t2_map1 = new HashMap<>();
        t2_map1.put("a", 2);
        Map<String, Integer> t2_map2 = new HashMap<>();
        t2_map2.put("a", 1);
        t2_map2.put("b", 2);

        List<Map<String, Integer>> example2 = Arrays.asList(t2_map1, t2_map2);
        assertEquals(example2.get(1), minByKey("a", example2));

        List<Map<String, Integer>> example3 = Arrays.asList(new HashMap<>());
        assertEquals(example3.get(0), minByKey("a", example3));

        Map<String, Integer> t4_map1 = new HashMap<>();
        t4_map1.put("a", -1);
        Map<String, Integer> t4_map2 = new HashMap<>();
        t4_map2.put("b", -1);

        List<Map<String, Integer>> example4 = Arrays.asList(t4_map1, t4_map2);
        assertEquals(example4.get(1), minByKey("b", example4));
    }

    public static Map<String, Integer> minByKey(String key, List<Map<String, Integer>> records) {
        /*
        if(records.size()==1) return records.get(0);

        int minIdx = 0;
        int minValue = records.get(0).containsKey(key)?records.get(0).get(key):0;

        for (int i = 0; i < records.size(); i++) {
            Map<String, Integer> record = records.get(i);
            if(record.containsKey(key)){
                if(record.get(key)<minValue) {
                    minIdx = i;
                    minValue = record.get(key);
                }
            } else {
                if(minValue>0) {
                    minIdx = i;
                    minValue = 0;
                }
            }
        }

        return records.get(minIdx);
         */
        return firstByKey(key, "asc", records);
    }

    public static Map<String, Integer> firstByKey(String key, String direction, List<Map<String, Integer>> records) {
        Comparator<Map<String, Integer>> ascending = (o1, o2) -> (
                o1.getOrDefault(key, 0)) - (o2.getOrDefault(key, 0)
        );

        Comparator<Map<String, Integer>> descending = (o1, o2) -> (
                o2.getOrDefault(key, 0)) - (o1.getOrDefault(key, 0)
        );

        if (direction.equals("asc")) Collections.sort(records, ascending);
        if (direction.equals("desc")) Collections.sort(records, descending);

        return records.get(0);
    }

}
