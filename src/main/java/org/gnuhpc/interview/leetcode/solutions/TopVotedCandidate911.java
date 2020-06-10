package org.gnuhpc.interview.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * Copyright gnuhpc 2020/5/28
 */
public class TopVotedCandidate911 {
    // 记录各时间当选者
    TreeMap<Integer, Integer> tree = new TreeMap<>();

    public TopVotedCandidate911(int[] persons, int[] times) {
        //time, person
        int[][] input = new int[times.length][2];
        for (int i = 0; i < persons.length; i++) {
            input[i][0] = times[i];
            input[i][1] = persons[i];
        }
        // Arrays.sort(input, (a1, a2) -> a1[0] - a2[0]);

        //PersonID - votes，记录当前 全部票数
        Map<Integer, Integer> map = new HashMap<>();
        // 当选者和票数
        int[] res = {0, 0};
        // 遍历投票计算结果
        for (int i = 0; i < input.length; i++) {
            //什么时候投给了谁
            int time = input[i][0];
            int person = input[i][1];

            int k = (map.containsKey(person)) ? (map.get(person) + 1) : 1;
            map.put(person, k);
            if (k >= res[1]) { //这个等于号非常关键，“在平局的情况下，最近获得投票的候选人将会获胜”就是这么实现的
                res[0] = person;
                res[1] = k;
                tree.put(time, res[0]);
            }
        }

    }

    public int q(int t) {
        return tree.floorEntry(t).getValue();
    }
}
