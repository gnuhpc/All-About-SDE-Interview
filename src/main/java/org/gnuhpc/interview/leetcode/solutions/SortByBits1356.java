package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;

public class SortByBits1356 {
    public int[] sortByBits(int[] arr) {

        if(arr.length == 1) return arr;
        int[] res = new int[arr.length];

        TreeMap<Integer, List<Integer>> map = new TreeMap<>();

        for(int n: arr){
            int ones = getOnes(n);
            if(!map.containsKey(ones)) map.put(ones, new ArrayList<>());
            map.get(ones).add(n);
        }
        int i = 0;
        for(List<Integer> l: map.values()){
            Collections.sort(l);
            for(int n: l) res[i++] = n;
        }
        return res;
    }

    private int getOnes(int n){
        int res = 0;
        while (n!=0) {
            res += (n % 2);
            n /= 2;
        }
        return res;
    }
}
