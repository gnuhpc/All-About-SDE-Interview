package org.gnuhpc.bigdata.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2019/11/5
 */
public class Excel631 {
    private Map<String, String[]> sumMap = new HashMap<>(); // 用来记录存了sum函数的格子,记录了计算sum的规则
    int[][] m; // 记录了格子表格的数据

    public Excel631(int H, char W) {
        m = new int[H][W - 'A' + 1];
    }

    public void set(int r, char c, int v) {
        sumMap.remove(r + "#" + c); // 如果某个位置被set了，那么之前的sum函数的效果被取消
        m[r - 1][c - 'A'] = v;
    }

    public int get(int r, char c) {
        String key = r + "#" + c;
        if (sumMap.containsKey(key)) {  // 如果之前某个位置被设置sum了，那优先查sum的值
            return sum(r, c, sumMap.get(key));
        }
        return m[r - 1][c - 'A'];
    }

    public int sum(int r, char c, String[] strs) {
        int sum = 0;
        for (String str : strs) {
            int index = str.indexOf(":");
            if (index == -1) { // 如果只是个普通的数字,直接参与求和
                sum += get(Integer.parseInt(str.substring(1)), str.charAt(0));
            }
            else { // 如果是个矩形坐标，对矩形里的所有元素进行求和
                String a = str.substring(0, index);
                String b = str.substring(index + 1);
                int x1 = Integer.parseInt(a.substring(1)), x2 = Integer.parseInt(b.substring(1));
                char y1 = a.charAt(0), y2 = b.charAt(0);
                for (int i = x1; i <= x2; i++) {
                    for (char j = y1; j <= y2; j++) {
                        sum += get(i, j);
                    }
                }
            }
        }
        sumMap.put(r + "#" + c, strs);
        return sum;
    }
}
