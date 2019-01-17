package org.gnuhpc.bigdata.datastructure.string;

import java.util.Map;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

public class NoneRepeatedChar {
    public static void main(String[] args) {
        String str = "aaccbb";

        System.out.println(nonRepeatedChar(str));
    }

    private static Character nonRepeatedChar(String str) {
        Map<Character, Long> countMap = str.chars().mapToObj(c -> (char) c)
                .collect(Collectors.groupingBy(c -> c, counting()));
        for (Map.Entry<Character,Long> entry: countMap.entrySet()){
            if (entry.getValue()==1){
                return entry.getKey();
            }
        }

        return null;
    }
}
