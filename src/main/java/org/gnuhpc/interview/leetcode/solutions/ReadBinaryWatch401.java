package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 组合＋表达的问题
 * 先是一个C10k的问题，
 * 然后将这个list组合成可以表示时间的字符串，不符合要求的pass
 */
public class ReadBinaryWatch401 {
    private final static Map<Integer, Integer> map = initialMap();

    public List<String> readBinaryWatch(int num) {
        List<List<Integer>> res = new ArrayList<>();
        List<Integer> temp = new ArrayList<>();
        int[] nums = new int[10];
        for (int i = 0; i < 10; i++) {
            nums[i] = i;
        }
        helper(nums, num, 0, temp, res);
        System.out.println(res);
        return represent(res);
    }

    public void helper(int[] nums, int k, int start, List<Integer> temp, List<List<Integer>> res) {
        if (temp.size() == k) {
            res.add(new ArrayList<>(temp));
            return;
        }
        for (int i = start; i < nums.length; i++) {
            temp.add(nums[i]);
            helper(nums, k, i + 1, temp, res);
            temp.remove(temp.size() - 1);
        }
    }

    public List<String> represent(List<List<Integer>> res) {

        List<String> rs = new ArrayList<>();
        for (List<Integer> temp : res) {
            int h = 0;
            int min = 0; //注意h和min的有效性
            for (int x : temp) {
                if (x < 4) {
                    h += map.get(x);
                    if (h > 11) break;
                } else {
                    min += map.get(x);
                    if (min > 59) break;
                }
            }
            if (h > 11 || min > 59) continue;
            String s = String.format("%d:%02d", h, min); //h +":"+ String.format("%02d", min);
            rs.add(s);
        }

        return rs;
    }

    public static Map<Integer, Integer> initialMap() {

        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        map.put(1, 2);
        map.put(2, 4);
        map.put(3, 8);
        map.put(4, 1);
        map.put(5, 2);
        map.put(6, 4);
        map.put(7, 8);
        map.put(8, 16);
        map.put(9, 32);
        return map;
    }

    // from lc disscussion, 这个方法的优越之处在于第一个
    // 1、把问题拆解成时和分2个问题，如此递归时输入的数组比较小，效率高
    // 2、不用map，用数组取代，在递归时，完成加法运算
    public List<String> readBinaryWatch2(int num) {
        List<String> res = new ArrayList<>();
        int[] hourDigits = new int[]{8, 4, 2, 1}, minDigits = new int[]{32, 16, 8, 4, 2, 1};
        for (int i = 0; i <= num; i++) {
            List<Integer> hours = generateDigit(hourDigits, i);
            List<Integer> mins = generateDigit(minDigits, num - i);
            for (int h : hours) {// 2轮循环，完成字符串表达
                if (h >= 12)
                    continue;
                for (int m : mins) {
                    if (m >= 60) continue;
                    res.add(h + ":" + (m < 10 ? "0" + m : m));
                }
            }
        }
        return res;
    }

    private List<Integer> generateDigit(int[] nums, int count) {
        List<Integer> res = new ArrayList<>();
        generateDigitHelper(nums, count, 0, 0, res);
        return res;
    }

    private void generateDigitHelper(int[] nums, int count, int pos, int sum, List<Integer> res) {
        if (count == 0) {//递归结束条件
            res.add(sum);
            return;
        }

        for (int i = pos; i < nums.length; i++) {
            generateDigitHelper(nums, count - 1, i + 1, sum + nums[i], res);
        }
    }

    @Test
    public void test() {
        readBinaryWatch2(2);
    }

}
