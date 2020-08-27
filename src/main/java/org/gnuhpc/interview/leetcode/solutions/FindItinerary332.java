package org.gnuhpc.interview.leetcode.solutions;

import org.junit.Test;

import java.util.*;

/**
 * Copyright gnuhpc 2019/10/1
 */
public class FindItinerary332 {
    Map<String, PriorityQueue<String>> flights;
    LinkedList<String> res;

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

    private class Edge implements Comparable<Edge> {
        String src;
        String dst;
        Edge(final String src, final String dst) {
            this.src = src;
            this.dst = dst;
        }
        @Override
        public int compareTo(final Edge e) {
            return this.dst.compareTo(e.dst);
        }
    }

    public List<String> findItinerary2(List<List<String>> tickets) {
        final List<String> res = new ArrayList<>();
        if (tickets == null || tickets.size() == 0) return res;
        final Map<String, List<Edge>> graph = new HashMap<>();
        for (final List<String> t : tickets) {
            String ori = t.get(0);
            String dst = t.get(1);
            graph.putIfAbsent(ori, new ArrayList<>());
            graph.putIfAbsent(dst, new ArrayList<>());
            graph.get(ori).add(new Edge(ori, dst));
        }
        for (final String v : graph.keySet()) {
            Collections.sort(graph.get(v));
        }

        final Set<Edge> visited = new HashSet<>();
        //一个ticket就是一个edge，说有edge走完就完事儿了
        dfs("JFK", tickets.size(), graph, visited, res, new ArrayList<>());
        return res;
    }
    private void dfs(final String v, final int numEdges, final Map<String, List<Edge>> graph,
                     final Set<Edge> visited, final List<String> res, final List<Edge> tmp) {
        if(res.size()>0) return;

        if (tmp.size() == numEdges) {
            res.add("JFK");
            for (final Edge e : tmp) {
                res.add(e.dst);
            }
            return;
        }
        for (final Edge e : graph.get(v)) {
            if (!visited.contains(e)) {
                tmp.add(e);
                visited.add(e);
                dfs(e.dst, numEdges, graph, visited, res, tmp);
                visited.remove(e);
                tmp.remove(tmp.size() - 1);
            }
        }
    }



    @Test
    public void test() {
        List<List<String>> tickets = new ArrayList<>();
        tickets.add(Arrays.asList("MUC", "LHR"));
        tickets.add(Arrays.asList("JFK", "MUC"));
        tickets.add(Arrays.asList("SFO", "SJC"));
        tickets.add(Arrays.asList("LHR", "SFO"));
        System.out.println(findItinerary2(tickets));
    }
}
