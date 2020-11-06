package org.gnuhpc.interview.leetcode.solutions;

public class UniqueOccurrences1207 {
    public boolean uniqueOccurrences(int[] arr) {
        int[] map = new int[2001];
        int[] counts = new int[1001];
        for (int i=0; i<arr.length; i++){
            map[arr[i]+1000]++;
        }
        for (int j=0; j<2001; j++){
            if (map[j] != 0){
                if (counts[map[j]] != 0){
                    return false;
                }
                counts[map[j]]++;
            }
        }
        return true;
    }
}
