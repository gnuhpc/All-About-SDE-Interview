package org.gnuhpc.interview.leetcode.solutions;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Copyright gnuhpc 2020/10/30
 */
public class SubdomainVisits811 {
    public List<String> subdomainVisits(String[] cpdomains) {
        Map<String, Integer> map = new HashMap<>();
        List<String> res = new ArrayList<>();
        for (String s : cpdomains) {
            String[] data = s.split(" ");
            int times = Integer.parseInt(data[0]);
            String[] domains = data[1].split("\\.");
            // 从后到前 拼成正常的url
            for (int i = domains.length - 2; i >= 0; i--) domains[i] += "." + domains[i + 1];
            for (String d : domains) {
                map.put(d, map.getOrDefault(d, 0) + times);
            }
        }

        for (Map.Entry<String, Integer> entry : map.entrySet()) {

            res.add(entry.getValue() + " " + entry.getKey());

        }
        return res;
    }
}
