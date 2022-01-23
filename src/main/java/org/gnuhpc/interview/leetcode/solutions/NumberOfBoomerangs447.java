package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

public class NumberOfBoomerangs447 {
    private int getDistance(int[] a, int[] b) {
        int dx = a[0] - b[0];
        int dy = a[1] - b[1];
        return dx * dx + dy * dy;
    }

    public int numberOfBoomerangs(int[][] points) {
        if (points == null) {
            return 0;
        }
        int ans = 0;
        for (int i = 0; i < points.length; i++) {
            Map<Integer, Integer> disCount = new HashMap<>();
            for (int j = 0; j < points.length; j++) {
                if (i == j) {
                    continue;
                }
                int distance = getDistance(points[i], points[j]);
                int count = disCount.getOrDefault(distance, 0);
                disCount.put(distance, count + 1);
            }

            //选择计算
            for (int count : disCount.values()) {
                ans += count * (count - 1);
            }
        }
        return ans;
    }
    /*
    func numberOfBoomerangs(points [][]int) int {
    res := 0 //最终返回结果
    for i:=0;i<len(points);i++{ //i当做枢纽，寻找到i距离想等的点
        hash := make(map[int]int) //本轮的哈希表，不同的i点有不同的哈希表
        for j := 0;j<len(points);j++{ //j为另外一个点
            if i != j{ //两点不能相同
                hash[distance(points[i],points[j])]++ //频率加一
            }
        } //二层循环for
        for _,v := range hash{
            res += v * (v-1) //第一次有v种选择，第二次有v-1种选择
        }
    }
        return res
}

func distance(a,b []int)int{ //如果距离太大需要改用int64
    return (a[0]-b[0]) * (a[0]-b[0]) + (a[1]-b[1]) * (a[1]-b[1]) //计算的是a,b两点的平方
}
     */
}
