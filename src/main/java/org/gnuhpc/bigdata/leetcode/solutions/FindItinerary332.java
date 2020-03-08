package org.gnuhpc.bigdata.leetcode.solutions;

import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 2019/10/1
 */
public class FindItinerary332 {
    Map<String, PriorityQueue<String>> flights;
    LinkedList<String>                 res;

    public List<String> findItinerary(List<List<String>> tickets) {
        flights = new HashMap<>();
        res = new LinkedList<>();
        for (List<String> ticket : tickets) {
            flights.putIfAbsent(ticket.get(0), new PriorityQueue<>());
            flights.get(ticket.get(0)).add(ticket.get(1));
        }
        dfs("JFK");
        return res;
    }

    public void dfs(String departure) {
        //利用了一定有解,就贪婪的往下找
        PriorityQueue<String> arrivals = flights.get(departure);
        while (arrivals != null && !arrivals.isEmpty())
            dfs(arrivals.poll());
        res.addFirst(departure);
    }

    /*
    Method2 : DFS通用模板
     */
    public List<String> findItinerary2(List<List<String>> tickets) {
        Map<String, List<String>> itineraryMap = new HashMap<>();
        for (List<String> ticket : tickets) {
            List<String> dests = itineraryMap.get(ticket.get(0));
            if (dests == null) {
                dests = new ArrayList<>();
                dests.add(ticket.get(1));
                itineraryMap.put(ticket.get(0), dests);
            }
            else {
                dests.add(ticket.get(1));
            }
        }
        for (List<String> dests : itineraryMap.values()) {
            Collections.sort(dests);
        }
        List<String> res = new ArrayList<>();
        res.add("JFK");
        dfs(res, new ArrayList<String>(), itineraryMap, "JFK", tickets.size());
        return res;
    }

    private void dfs(List<String> res, List<String> cur, Map<String, List<String>> itineraryMap, String src, int len) {
        if (res.size() > 1) { //res 已经更新了
            return;
        }
        if (cur.size() == len) { // 所有的tickets都用光了
            res.addAll(cur);
            return;
        }
        List<String> dests = itineraryMap.get(src);
        if (dests != null && dests.size() > 0) {
            for (int i = 0; i < dests.size(); i++) {
                String des = dests.get(i);
                dests.remove(i);
                cur.add(des);
                dfs(res, cur, itineraryMap, des, len);
                dests.add(i, des);
                cur.remove(cur.size() - 1);
            }
        }
    }

    @Test
    public void test() {
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("JFK", "SFO"));
        tickets.add(Arrays.asList("JFK", "ATL"));
        tickets.add(Arrays.asList("SFO", "ATL"));
        tickets.add(Arrays.asList("ATL", "JFK"));
        tickets.add(Arrays.asList("ATL", "SFO"));
        System.out.println(findItinerary2(tickets));
    }
}
