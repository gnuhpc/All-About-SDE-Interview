package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2019/10/6
 */

/*
应对读多写少的场景
 */
public class TwoSum1701 {
    Map<Integer, Integer> map;

    public TwoSum1701() {
        map = new HashMap<>();
    }

    // Add the number to an internal data structure.
    public void add(int number) {
        if (map.containsKey(number)) {
            map.put(number, 2);
        }
        else {
            map.put(number, 1);
        }
    }

    // Find if there exists any pair of numbers which sum is equal to the value.
    public boolean find(int value) {
        for (int num1 : map.keySet()) {
            int num2 = value - num1;
            if (map.containsKey(num2)) {
                //map中包含num2 不一定就说明有两个数字加起来等于value
                if (num1 == num2) {
                    if (map.get(num1) == 2) { //这个数字有重复
                        return true;
                    }
                    else { //没有重复说明是自己+自己, 其实只有一个数字,因此不算数,往下找
                        continue;
                    }
                }
                return true;
            }
        }
        return false;
    }

}
