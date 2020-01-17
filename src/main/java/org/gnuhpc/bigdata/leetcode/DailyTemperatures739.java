package org.gnuhpc.bigdata.leetcode;

import org.gnuhpc.bigdata.leetcode.utils.Utils;
import org.junit.Test;

import java.util.Deque;
import java.util.LinkedList;

public class DailyTemperatures739 {
    public int[] dailyTemperatures(int[] T) {
        int[] ans = new int[T.length];
        Deque<Integer> s = new LinkedList<>(); // 这里放元素索引，而不是元素
        for (int i = T.length - 1; i >= 0; i--) {
            //无人可比
            if(s.isEmpty()) {
                ans[i] = 0;
            }
            else{//对栈里的的元素比较身高，小个子统统丢掉
                while (!s.isEmpty() && T[s.peek()] <= T[i]) {
                    s.pop();
                }

                //留下大个子的（如果有的话）
                ans[i] = s.isEmpty() ? 0 : (s.peek() - i); // 得到索引间距
            }
            s.push(i); // 加入索引，而不是元素
        }
        return ans;

    }

    @Test
    public void test(){
        Utils.printArray(dailyTemperatures(new int[]{73,74,75,71,69,72,76,73}));
    }
}
