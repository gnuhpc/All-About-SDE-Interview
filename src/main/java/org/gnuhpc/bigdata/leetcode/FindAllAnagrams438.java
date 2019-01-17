package org.gnuhpc.bigdata.leetcode;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

public class FindAllAnagrams438 {
    public static void main(String[] args) {
        String str1 = "cbaebabacd";
        String p1 = "abc";

        String str2 = "abab";
        String p2 = "ba";

        System.out.println(findAllAnagrams(str1,p1));
        System.out.println();
        System.out.println(findAllAnagrams(str2,p2));
    }

    private static List<Integer> findAllAnagrams(String str, String p) {
        int l=0, r=-1;
        char[] strArray = str.toCharArray();
        List<Integer> result = new ArrayList<>();
        Map<Character, Long> pMap = buildPMap(p);

        while(r + 1 < str.length()){
            //尚未匹配完成
            if(!isAllZero(pMap.values())){
                r++;
                Long mapValue = pMap.get(strArray[r]);
                if (mapValue !=null) {
                    if(mapValue>0){
                        pMap.put(strArray[r], mapValue - 1);
                    } else { //Extra valid char appears
                        pMap.put(strArray[l],pMap.get(strArray[l])+1);
                        l++;
                        r--;
                    }
                } else { // 匹配异常
                    pMap = buildPMap(p);
                    l = r + 1;
                }
            } else { //匹配完成
                result.add(l);
                pMap.put(strArray[l],pMap.get(strArray[l])+1);
                l++;
            }

        }

        if (isAllZero(pMap.values())){
            result.add(l);
        }
        return result;
    }

    private static Map<Character, Long> buildPMap(String p){
        return p.chars().mapToObj(c -> (char) c).collect(Collectors.groupingBy(c -> c, Collectors.counting()));
    }
    private static boolean isAllZero(Collection<Long> values){
        return values.stream().filter(l->l!=0).count()==0;
    }
}

