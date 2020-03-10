package org.gnuhpc.interview.leetcode.solutions;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Comparator.*;

public class FrequencySort451 {
    public static void main(String[] args) {
        String str1 = "tree";
        String str2 = "cccaaa";
        String str3 = "Aabb";

        System.out.println(frequencySort(str1));
        System.out.println(frequencySort(str2));
        System.out.println(frequencySort(str3));


    }

    public static String frequencySort(String s) {

        int[] records = new int[256];

        for (int i : s.chars().toArray()) {
            records[i]++;
        }

        int[] countings = Arrays.stream(records).mapToObj(i -> (i)).sorted(Collections.reverseOrder())
                .filter(i -> i != 0).mapToInt(i -> i).toArray();

        StringBuilder sb = new StringBuilder();

        for (int count : countings) {
            for (int i = 0; i < records.length; i++) {
                if (count == records[i]) {
                    sb.append(new String(new char[count]).replace("\0", String.valueOf((char) i)));
                    records[i] = 0;
                }
            }
        }

        return sb.toString();

//        Map<Character, Long> charCountMap = Arrays.stream(s.split("")).collect(Collectors.groupingBy(c -> c.charAt(0), Collectors.counting()));
//
//        Map<Long, List<Character>> countCharMap = new HashMap<>();
//
//        charCountMap.forEach((k,v)-> {
//            if (!countCharMap.containsKey(v)){
//                List<Character> cList = new ArrayList<>();
//                cList.add(k);
//                countCharMap.put(v,cList);
//            } else {
//                List<Character> cList = countCharMap.get(v);
//                cList.add(k);
//                countCharMap.put(v,cList);
//            }
//        });
    }

    public String frequencySort3(String s) {
        if (s == null || s.length() <= 2) return s;
        int length = s.length();
        //统计各个字符出现的次数
        HashMap<Character, Integer> map = new HashMap<Character, Integer>();
        for (int i = 0; i < length; i++) {
            char c = s.charAt(i);
            if (map.containsKey(c)) {
                map.put(c, map.get(c) + 1);
            } else {
                map.put(c, 1);
            }
        }
        //sb1求出各种频率的字符
        //"eeeee"的时候，e出现了length次，所以申请的时候length+1
        StringBuilder[] sb1 = new StringBuilder[length + 1];
        int max = 0;
        for (char c : map.keySet()) {
            int fre = map.get(c);
            if (sb1[fre] == null) {
                sb1[fre] = new StringBuilder();
            }
            if (fre > max) max = fre;
            for (int i = 0; i < fre; i++) {
                sb1[fre].append(c);
            }
        }
        //最后ret把各种频率的字符由高到低连接起来
        StringBuilder ret = new StringBuilder();
        for (int i = max; i > 0; i--) {
            if (sb1[i] != null)
                ret.append(sb1[i]);
        }
        return ret.toString();
    }


    public String frequencySort2(String s) {
        //方法2；二维数组
        int[][] count = new int[128][2];
        char[] ch = s.toCharArray();
        for (char c : ch) {
            count[c][0] = c;
            count[c][1]++;
        }
        Arrays.sort(count, (a, b) -> b[1] - a[1]);
        StringBuilder ret = new StringBuilder();
        for (int i = 0; i < 128; i++) {
            for (int j = 0; j < count[i][1]; j++) {
                ret.append((char) count[i][0]);
            }
        }
        return ret.toString();
    }

}
