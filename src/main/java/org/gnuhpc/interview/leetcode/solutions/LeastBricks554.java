package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class LeastBricks554 {
    // 将每列砖墙的宽度累加，联合该宽度和出现次数一起存入map中
    // 遍历map，找到具有最大出现次数的宽度和，用墙高减去该值即可
    public int leastBricks(List<List<Integer>> wall) {
        Map<Integer, Integer> map = new HashMap<>();
        for (List<Integer> list : wall){
            int sum = 0;
            int n = list.size();
            // 遍历是不到最后一个元素的
            for (int i = 0; i < n - 1; i++) {
                sum += list.get(i);
                map.put(sum, map.getOrDefault(sum, 0) + 1);
            }
        }
        int max = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()){
            max = Math.max(max, entry.getValue());
        }
        return wall.size() - max;
    }
}
