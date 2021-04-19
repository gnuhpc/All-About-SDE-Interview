package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright gnuhpc 2021/4/10
 */
public class FindRestaurant599 {
    public String[] findRestaurant(String[] list1, String[] list2) {
        Map<String, Integer> map = new HashMap<>();
        int ans = Integer.MAX_VALUE;
        List<String> list = new ArrayList<>();
        for (int i = 0; i < list1.length; i++) {
            map.put(list1[i], i); //记录第一个数组的元素和下标
        }
        for (int i = 0; i < list2.length; i++) {
            if (map.containsKey(list2[i])) {
                if (i + map.get(list2[i]) < ans) { //比较索引
                    ans = i + map.get(list2[i]);
                    list.clear();  //注意清空
                    list.add(list2[i]);
                } else if (i + map.get(list2[i]) == ans) {
                    list.add(list2[i]);  //有多个答案时
                }
            }
        }
        return list.toArray(new String[list.size()]);
    }
}
