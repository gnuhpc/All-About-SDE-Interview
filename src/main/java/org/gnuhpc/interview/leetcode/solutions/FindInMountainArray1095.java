package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/4/29
 */
public class FindInMountainArray1095 {
    class MyCache {
        private final MountainArray mountainArray;
        Map<Integer, Integer> cache = new HashMap<>();

        public MyCache(MountainArray mountainArray) {
            this.mountainArray = mountainArray;
        }

        public int get(int idx) {
            int res = 0;
            if (cache.containsKey(idx)) res = cache.get(idx);
            else {
                res = mountainArray.get(idx);
                cache.put(idx, res);
            }

            return res;
        }
    }

    private MyCache cache;

    public int findInMountainArray(int target, MountainArray mountainArr) {
        cache = new MyCache(mountainArr);
        int p_idx = peakIndex(mountainArr);
        int res = binarySearch(target, 0, p_idx, true);
        return res >= 0 ? res : binarySearch(target, p_idx, mountainArr.length() - 1, false);
    }

    private int peakIndex(MountainArray mountainArr) throws IllegalArgumentException {
        if (mountainArr.length() < 3) throw new IllegalArgumentException(); //因为题目说了整个moutain长度大于等于3
        int low = 0, high = mountainArr.length() - 1;
        //由于长度限制，因此一定进入
        while (low + 1 < high) {
            int mid = low + (high - low) / 2;
            int mid_val = cache.get(mid),
                    left_val = cache.get(mid - 1),
                    right_val = cache.get(mid + 1);

            if (mid_val > left_val && mid_val > right_val) return mid;
            else if (mid_val > left_val && mid_val < right_val) low = mid;
            else high = mid;
        }

        int mid_val = cache.get(low),
                left_val = cache.get(low - 1),
                right_val = cache.get(low + 1);
        if (mid_val > left_val && mid_val > right_val) return low;
        else return high;
    }

    private int binarySearch(int target, int begin, int end, boolean mode) {
        while (begin + 1 < end) {
            int mid = begin + (end - begin) / 2;
            int mid_val = cache.get(mid);
            if (mid_val == target) end = mid;
            if (mid_val > target == mode) end = mid;//注意这个写法，
            else begin = mid;
        }

        if (cache.get(begin) == target) return begin;
        else if (cache.get(end) == target) return end;
        else return -1;
    }

    @Test
    public void test() {
        System.out.println(findInMountainArray(5, new MountainArrayImpl(new int[]{3, 5, 3, 2, 0})));
    }

}

interface MountainArray {
    public int get(int index);

    public int length();
}

class MountainArrayImpl implements MountainArray {
    private int[] arr;
    private int size;

    public MountainArrayImpl(int[] arr) {
        this.arr = arr;
        this.size = this.arr.length;
    }

    @Override
    public int get(int index) {
        return this.arr[index];
    }

    @Override
    public int length() {
        return this.size;
    }

}

