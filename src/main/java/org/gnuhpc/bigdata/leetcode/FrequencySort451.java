package org.gnuhpc.bigdata.leetcode;

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

        for (int i : s.chars().toArray()){
            records[i]++;
        }

        int[] countings = Arrays.stream(records).mapToObj(i -> (i)).sorted(Collections.reverseOrder())
                .filter(i->i!=0).mapToInt(i -> i).toArray();

        StringBuilder sb = new StringBuilder();

        for (int count:countings){
            for (int i = 0; i < records.length; i++) {
                if (count == records[i]){
                    sb.append(new String(new char[count]).replace("\0", String.valueOf((char)i)));
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
}
