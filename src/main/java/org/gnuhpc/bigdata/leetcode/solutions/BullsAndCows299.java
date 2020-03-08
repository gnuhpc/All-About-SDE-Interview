package org.gnuhpc.bigdata.leetcode.solutions;

import java.util.HashMap;

public class BullsAndCows299 {

    // add by Tina, 纯暴力解法，hashmap记次数。
    public String getHint(String secret, String guess) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int bulls = 0;
        int cows = 0;
        int n = secret.length();
        for (int i = 0; i < n; i++) {
            int nums = secret.charAt(i);
            int numg = guess.charAt(i);
            if (nums == numg) bulls += 1;
            else map.put(nums, map.getOrDefault(nums, 0) + 1);
        }

        for (int i = 0; i < n; i++) {
            int numg = guess.charAt(i);
            if (map.getOrDefault(numg, 0) >= 1 && numg != secret.charAt(i)) {
                cows += 1;
                map.put(numg, map.getOrDefault(numg, 0) - 1);
            }
        }

        StringBuilder sb = new StringBuilder();
        return sb.append(bulls).append("A").append(cows).append("B").toString();
    }

    // 优化一：基于数组实现的hash表的思路，对上面需要取map值进行加减，然后再赋回去是一个简化
    public String getHint2(String secret, String guess) {
        int[] m = new int[10];
        int bulls = 0;
        int cows = 0;
        for (int i = 0; i < secret.length(); ++i) {
            if (secret.charAt(i) == guess.charAt(i)) ++bulls;
            else ++m[secret.charAt(i)];
        }
        for (int i = 0; i < secret.length(); ++i) {
            if (secret.charAt(i) != guess.charAt(i) && m[guess.charAt(i)] > 0) {
                ++cows;
                --m[guess.charAt(i)];
            }
        }
        return bulls + "A" + cows + "B";
    }

    // 优化二，仍然同上基于hash表来实现，但是只用遍历一遍数组
    //尤其体现在处理cows的地方。
    // 创建了一个长度为10的int数组，因为每个位置的数字范围就是0——9，
    // 数组的index就对应0——9，而index对应的元素值表示secret中该index出现的次数。如果secret出现一次某个数字，
    // 该位置就自增1，如果此时该位置小于0，就说明这个数字在guess出现过，
    // 因此cows就自增1；guess的处理和secret类似，
    // 区别就是index对应元素需要自减。

    public String getHint3(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] numbers = new int[10];
        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) bulls++;
            else {
                if (numbers[secret.charAt(i) - '0']++ < 0) cows++;
                if (numbers[guess.charAt(i) - '0']-- > 0) cows++;
            }
        }
        return bulls + "A" + cows + "B";
    }


}
