package org.gnuhpc.interview.leetcode.solutions;

import java.util.PriorityQueue;
import java.util.Queue;

/**
 * Copyright gnuhpc 2021/12/24
 */
public class EatenApples1705 {
    //直觉上，我们会觉得「优先吃掉最快过期的苹果」会是最优，而这个维护苹果过期的过程，可以使用「小根堆」来实现。
    public int eatenApples(int[] apples, int[] days) {
        Queue<int[]> q = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        //令 n 为苹果数组长度，time 为当前时间，ans 为吃到的苹果数量
        int n = apples.length, time = 0, ans = 0;
        //如果「time < n」或者「堆不为空」，说明「还有苹果未被生成」或者「未必吃掉」
        while (time < n || !q.isEmpty()) {
            //若当天有苹果生成，先将苹果以二元组(最后食用日期, 当日产生苹果数量)形式加入小根堆中
            if (time < n && apples[time] > 0) q.add(new int[]{time + days[time] - 1, apples[time]});
            //若最后食用日期小于当前时间，则丢弃
            while (!q.isEmpty() && q.peek()[0] < time) q.poll();
            //如果吃掉 cur 一个苹果后
            if (!q.isEmpty()) {
                int[] cur = q.poll();
                ans++;
                cur[1]--;
                //仍有剩余，并且最后时间大于当前时间（尚未过期），将 cur 重新入堆
                if (cur[1] > 0 && cur[0] > time) q.add(cur);

            }
            time++;
        }
        return ans;
    }
}
