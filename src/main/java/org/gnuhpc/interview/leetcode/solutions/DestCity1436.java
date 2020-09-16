package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/9/14
 */
public class DestCity1436 {
    public String destCity(List<List<String>> paths) {
        Map<String, String> map = new HashMap<>();
        // 将所有路径的起点与终点存入map
        for (List<String> list : paths) {
            map.put(list.get(0), list.get(1));
        }
        for (String s : map.keySet()) {
            // 如果某个路径的终点不是另一个路径的起点，则该点就是旅行终点
            if (map.get(map.get(s)) == null)
                return map.get(s);
        }
        // 返回默认null，由题意是不会走到这里返回的
        return null;
    }

}
