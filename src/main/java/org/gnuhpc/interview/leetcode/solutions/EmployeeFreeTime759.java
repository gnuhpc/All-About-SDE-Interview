package org.gnuhpc.interview.leetcode.solutions;

import org.gnuhpc.interview.leetcode.utils.Interval;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

//和merge interval思路相同。merge interval求的是时间戳上所有被占据的区间，而759求得是时间戳上没有被占据的间隙。
public class EmployeeFreeTime759 {
    public List<Interval> employeeFreeTime(List<List<Interval>> schedule) {
        List<Interval> res = new ArrayList<>();
        if (schedule == null || schedule.size() == 0) return res;
        TreeMap<Integer, Integer> calendar = new TreeMap<>();
        for (List<Interval> list : schedule) {
            if (list == null || list.size() == 0) continue;
            for (Interval i : list) {
                calendar.put(i.start, calendar.getOrDefault(i.start, 0) + 1);
                calendar.put(i.end, calendar.getOrDefault(i.end, 0) - 1);
            }
        }
        int count = 0;
        int start = -1;
        for (Map.Entry<Integer, Integer> entry : calendar.entrySet()) {
            count += entry.getValue();
            if (count == 0) start = entry.getKey();
            if (count > 0 && start != -1) {
                res.add(new Interval(start, entry.getKey()));
                start = -1;
            }
        }
        return res;
    }
}
